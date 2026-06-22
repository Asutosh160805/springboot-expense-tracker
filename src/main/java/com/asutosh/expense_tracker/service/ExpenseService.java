package com.asutosh.expense_tracker.service;

import com.asutosh.expense_tracker.dto.ExpenseRequestDTO;
import com.asutosh.expense_tracker.dto.ExpenseResponseDTO;
import com.asutosh.expense_tracker.entity.Category;
import com.asutosh.expense_tracker.entity.Expense;
import com.asutosh.expense_tracker.entity.User;
import com.asutosh.expense_tracker.exception.ExpenseNotFoundException;
import com.asutosh.expense_tracker.repository.ExpenseRepository;
import com.asutosh.expense_tracker.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    // Repository used for expense operations
    private final ExpenseRepository expenseRepository;

    // Repository used to fetch logged-in user
    private final UserRepository userRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            UserRepository userRepository
    ) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    /**
     * Create a new expense for the currently logged-in user
     */
    public ExpenseResponseDTO saveExpense(
            ExpenseRequestDTO request
    ) {

        // Get email from Security Context
        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        // Find the logged-in user
        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "User not found"
                                )
                        );

        // Create expense entity
        Expense expense = new Expense();

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());

        // Assign ownership
        expense.setUser(user);

        // Save expense
        Expense savedExpense =
                expenseRepository.save(expense);

        // Convert Entity -> DTO
        ExpenseResponseDTO response =
                new ExpenseResponseDTO();

        response.setId(savedExpense.getId());
        response.setTitle(savedExpense.getTitle());
        response.setAmount(savedExpense.getAmount());
        response.setCategory(savedExpense.getCategory());

        return response;
    }

    /**
     * Return only expenses belonging to logged-in user
     */
    public Page<Expense> getAllExpenses(
            int page,
            int size,
            String sortBy
    ) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(sortBy)
                );

        // Current authenticated user's email
        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return expenseRepository
                .findByUserEmail(
                        email,
                        pageable
                );
    }

    /**
     * Fetch expense by ID
     */
    public Expense getExpenseById(Long id) {

        return expenseRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new ExpenseNotFoundException(
                                        "Expense not found with id: " + id
                                )
                );
    }

    /**
     * Get expenses by category
     */
    public List<Expense> getExpensesByCategory(
            Category category
    ) {

        return expenseRepository
                .findByCategory(category);
    }

    /**
     * Get expenses greater than amount
     */
    public List<Expense> getExpensesAboveAmount(
            Double amount
    ) {

        return expenseRepository
                .findByAmountGreaterThan(amount);
    }

    /**
     * Update expense
     */
    public Expense updateExpense(
            Long id,
            Expense updatedExpense
    ) {

        Expense expense =
                expenseRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ExpenseNotFoundException(
                                                "Expense not found with id: " + id
                                        )
                        );

        expense.setTitle(
                updatedExpense.getTitle()
        );

        expense.setAmount(
                updatedExpense.getAmount()
        );

        expense.setCategory(
                updatedExpense.getCategory()
        );

        return expenseRepository.save(expense);
    }

    /**
     * Delete expense
     */
    public void deleteExpense(
            Long id
    ) {

        if (!expenseRepository.existsById(id)) {

            throw new ExpenseNotFoundException(
                    "Expense not found with id: " + id
            );
        }

        expenseRepository.deleteById(id);
    }
}