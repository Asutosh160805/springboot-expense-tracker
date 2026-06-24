package com.asutosh.expense_tracker.service;

import com.asutosh.expense_tracker.dto.BudgetRequestDTO;
import com.asutosh.expense_tracker.dto.BudgetResponseDTO;
import com.asutosh.expense_tracker.entity.Budget;
import com.asutosh.expense_tracker.entity.User;
import com.asutosh.expense_tracker.exception.BudgetNotFoundException;
import com.asutosh.expense_tracker.repository.BudgetRepository;
import com.asutosh.expense_tracker.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.asutosh.expense_tracker.repository.ExpenseRepository;
import com.asutosh.expense_tracker.dto.DashboardResponseDTO;

import java.time.LocalDate;
import java.time.Month;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    public BudgetService(
            BudgetRepository budgetRepository,
            UserRepository userRepository,
            ExpenseRepository expenseRepository
    ) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    // Create budget
    public BudgetResponseDTO createBudget(
            BudgetRequestDTO request
    ) {

        User user = getCurrentUser();

        // Check if budget already exists
        budgetRepository
                .findByUserEmailAndMonthAndYear(
                        user.getEmail(),
                        request.getMonth(),
                        request.getYear()
                )
                .ifPresent(
                        budget -> {
                            throw new RuntimeException(
                                    "Budget already exists for this month"
                            );
                        }
                );

        Budget budget = new Budget();

        budget.setAmount(request.getAmount());
        budget.setMonth(request.getMonth());
        budget.setYear(request.getYear());
        budget.setUser(user);

        Budget savedBudget =
                budgetRepository.save(budget);

        BudgetResponseDTO response =
                new BudgetResponseDTO();

        response.setId(savedBudget.getId());
        response.setAmount(savedBudget.getAmount());
        response.setMonth(savedBudget.getMonth());
        response.setYear(savedBudget.getYear());

        return response;
    }

    // Helper method
    private User getCurrentUser() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"
                        )
                );
    }

    public BudgetResponseDTO getCurrentBudget() {

        User user = getCurrentUser();

        Budget budget =
                budgetRepository
                        .findByUserEmailAndMonthAndYear(
                                user.getEmail(),
                                java.time.LocalDate.now().getMonth(),
                                java.time.LocalDate.now().getYear()
                        )
                        .orElseThrow(
                                () -> new BudgetNotFoundException(
                                        "No budget found for current month"
                            )
                        );

        BudgetResponseDTO response =
                new BudgetResponseDTO();

        response.setId(budget.getId());
        response.setAmount(budget.getAmount());
        response.setMonth(budget.getMonth());
        response.setYear(budget.getYear());

        return response;
    }
    public DashboardResponseDTO getDashboard() {

        User user = getCurrentUser();

        Month currentMonth =
                LocalDate.now().getMonth();

        Integer currentYear =
                LocalDate.now().getYear();

        Budget budget =
                budgetRepository
                        .findByUserEmailAndMonthAndYear(
                                user.getEmail(),
                                currentMonth,
                                currentYear
                        )
                        .orElseThrow(
                                () -> new BudgetNotFoundException(
                                        "No budget found for current month"
                                )
                        );

        LocalDate startDate =
                LocalDate.now()
                        .withDayOfMonth(1);

        LocalDate endDate =
                LocalDate.now()
                        .withDayOfMonth(
                                LocalDate.now()
                                        .lengthOfMonth()
                        );

        Double spent =
                expenseRepository
                        .getTotalSpentForPeriod(
                                user.getEmail(),
                                startDate,
                                endDate
                        );

        Double remaining =
                budget.getAmount() - spent;

        DashboardResponseDTO response =
                new DashboardResponseDTO();

        response.setBudget(
                budget.getAmount()
        );

        response.setSpent(
                spent
        );

        response.setRemaining(
                remaining
        );

        return response;
    }
}