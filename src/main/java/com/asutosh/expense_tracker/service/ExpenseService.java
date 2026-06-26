package com.asutosh.expense_tracker.service;

import com.asutosh.expense_tracker.dto.ExpenseRequestDTO;
import com.asutosh.expense_tracker.dto.ExpenseResponseDTO;
import com.asutosh.expense_tracker.entity.Category;
import com.asutosh.expense_tracker.entity.Expense;
import com.asutosh.expense_tracker.entity.User;
import com.asutosh.expense_tracker.exception.ExpenseNotFoundException;
import com.asutosh.expense_tracker.exception.UnauthorizedExpenseAccessException;
import com.asutosh.expense_tracker.mapper.ExpenseMapper;
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

    private final ExpenseMapper expenseMapper;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            UserRepository userRepository,
            ExpenseMapper expenseMapper
    ) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.expenseMapper = expenseMapper;
    }

    /**
     * Create a new expense for the currently logged-in user
     */
    public ExpenseResponseDTO saveExpense(
            ExpenseRequestDTO request
    ) {

        User user =
                getCurrentUser();

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

        return expenseMapper.toDTO(
                savedExpense
        );
    }

    /**
     * Return only expenses belonging to logged-in user
     */
    /**
     * Return paginated expenses belonging to the logged-in user
     */
    public Page<ExpenseResponseDTO> getAllExpenses(
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

        User currentUser =
                getCurrentUser();

        return expenseRepository
                .findByUserEmail(
                        currentUser.getEmail(),
                        pageable
                )
                .map(expenseMapper::toDTO);
    }

    /**
     * Fetch expense by ID
     */
    /**
     * Return a single expense belonging to the logged-in user
     */
    public ExpenseResponseDTO getExpenseById(
            Long id
    ) {

        Expense expense =
                getUserExpense(id);

        return expenseMapper.toDTO(
                expense
        );
    }


    /**
     * Return all expenses of a category
     * belonging to the logged-in user
     */
    public List<ExpenseResponseDTO> getExpensesByCategory(
            Category category
    ) {

        User currentUser =
                getCurrentUser();

        return expenseRepository
                .findByUserEmailAndCategory(
                        currentUser.getEmail(),
                        category
                )
                .stream()
                .map(expenseMapper::toDTO)
                .toList();
    }


    /**
     * Return expenses greater than the given amount
     */
    public List<ExpenseResponseDTO> getExpensesAboveAmount(
            Double amount
    ) {

        User currentUser =
                getCurrentUser();

        return expenseRepository
                .findByUserEmailAndAmountGreaterThan(
                        currentUser.getEmail(),
                        amount
                )
                .stream()
                .map(expenseMapper::toDTO)
                .toList();
    }

    /**
     * Update an existing expense
     */
    public ExpenseResponseDTO updateExpense(

            Long id,

            ExpenseRequestDTO request

    ) {

        Expense expense =
                getUserExpense(id);

        expense.setTitle(
                request.getTitle()
        );

        expense.setAmount(
                request.getAmount()
        );

        expense.setCategory(
                request.getCategory()
        );

        Expense updatedExpense =
                expenseRepository.save(
                        expense
                );

        return expenseMapper.toDTO(
                updatedExpense
        );
    }

    /**
     * Delete an expense owned by the logged-in user
     */
    public void deleteExpense(
            Long id
    ) {

        Expense expense =
                getUserExpense(id);

        expenseRepository.delete(expense);
    }

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

    private Expense getUserExpense(
            Long expenseId
    ) {

        Expense expense =
                expenseRepository
                        .findById(expenseId)
                        .orElseThrow(
                                () ->
                                        new ExpenseNotFoundException(
                                                "Expense not found"
                                        )
                        );

        User currentUser =
                getCurrentUser();

        if (!expense.getUser()
                .getId()
                .equals(currentUser.getId())) {

            throw new UnauthorizedExpenseAccessException(
                    "You do not own this expense"
            );
        }

        return expense;
    }
}