package com.asutosh.expense_tracker.controller;

import com.asutosh.expense_tracker.dto.ExpenseRequestDTO;
import com.asutosh.expense_tracker.dto.ExpenseResponseDTO;
import com.asutosh.expense_tracker.entity.Category;
import com.asutosh.expense_tracker.service.ExpenseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Expense Management",
        description = "APIs for creating, retrieving, updating and deleting user expenses"
)
@RestController
@RequestMapping("/expenses")
@SecurityRequirement(name = "bearerAuth")
public class ExpenseController {

    // Service layer handles all business logic
    private final ExpenseService expenseService;

    public ExpenseController(
            ExpenseService expenseService
    ) {
        this.expenseService = expenseService;
    }

    // ==========================================================
    // CREATE
    // ==========================================================

    @Operation(
            summary = "Create Expense",
            description = "Creates a new expense for the authenticated user"
    )
    @PostMapping
    public ExpenseResponseDTO createExpense(
            @Valid @RequestBody ExpenseRequestDTO request
    ) {

        return expenseService.saveExpense(request);
    }

    // ==========================================================
    // READ
    // ==========================================================

    @Operation(
            summary = "Get All Expenses",
            description = "Returns paginated expenses of the logged-in user"
    )
    @GetMapping
    public Page<ExpenseResponseDTO> getAllExpenses(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "amount")
            String sortBy
    ) {

        return expenseService.getAllExpenses(
                page,
                size,
                sortBy
        );
    }

    @Operation(
            summary = "Get Expense By ID",
            description = "Returns a single expense belonging to the authenticated user"
    )
    @GetMapping("/{id}")
    public ExpenseResponseDTO getExpenseById(
            @PathVariable Long id
    ) {

        return expenseService.getExpenseById(id);
    }

    @Operation(
            summary = "Filter By Category",
            description = "Returns expenses of the logged-in user for a specific category"
    )
    @GetMapping("/category/{category}")
    public List<ExpenseResponseDTO> getExpensesByCategory(

            @PathVariable
            Category category

    ) {

        return expenseService
                .getExpensesByCategory(category);
    }

    @Operation(
            summary = "Filter By Amount",
            description = "Returns expenses greater than the specified amount"
    )
    @GetMapping("/amount/{amount}")
    public List<ExpenseResponseDTO> getExpensesAboveAmount(

            @PathVariable
            Double amount

    ) {

        return expenseService
                .getExpensesAboveAmount(amount);
    }

    // ==========================================================
    // UPDATE
    // ==========================================================

    @Operation(
            summary = "Update Expense",
            description = "Updates an existing expense"
    )
    @PutMapping("/{id}")
    public ExpenseResponseDTO updateExpense(

            @PathVariable
            Long id,

            @Valid
            @RequestBody
            ExpenseRequestDTO request

    ) {

        return expenseService.updateExpense(
                id,
                request
        );
    }

    // ==========================================================
    // DELETE
    // ==========================================================

    @Operation(
            summary = "Delete Expense",
            description = "Deletes an expense belonging to the authenticated user"
    )
    @DeleteMapping("/{id}")
    public void deleteExpense(

            @PathVariable
            Long id

    ) {

        expenseService.deleteExpense(id);
    }
}