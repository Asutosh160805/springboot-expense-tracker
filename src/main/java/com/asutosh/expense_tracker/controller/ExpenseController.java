package com.asutosh.expense_tracker.controller;

import com.asutosh.expense_tracker.dto.ExpenseRequestDTO;
import com.asutosh.expense_tracker.dto.ExpenseResponseDTO;
import com.asutosh.expense_tracker.entity.Expense;
import com.asutosh.expense_tracker.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.asutosh.expense_tracker.entity.Category;

import java.util.List;


@Tag(
        name = "Expense Management",
        description = "APIs for managing expenses"
)
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @Operation(
            summary = "Create a new expense",
            description = "Creates and stores a new expense in the database"
    )
    @PostMapping
    public ExpenseResponseDTO createExpense(
            @Valid @RequestBody ExpenseRequestDTO request
    ){
        return expenseService.saveExpense(request);
    }

    @Operation(
            summary = "Get all expenses",
            description = "Returns paginated and sorted expenses"
    )
    @GetMapping
    public Page<Expense> getAllExpenses(

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
            summary = "Get expense by ID",
            description = "Returns a single expense using its ID"
    )
    @GetMapping("/{id}")
    public Expense getExpenseById(
            @PathVariable Long id
    ) {
        return expenseService.getExpenseById(id);
    }

    @GetMapping("/category/{category}")
    public List<Expense> getExpensesByCategory(

            @PathVariable
            Category category

    ) {

        return expenseService
                .getExpensesByCategory(category);
    }

    @GetMapping("/amount/{amount}")
    public List<Expense>
    getExpensesAboveAmount(

            @PathVariable
            Double amount

    ) {

        return expenseService
                .getExpensesAboveAmount(
                        amount
                );
    }

    @Operation(
            summary = "Update an expense",
            description = "Updates an existing expense using its ID"
    )
    @PutMapping("/{id}")
    public Expense updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody Expense expense
    ){
        return expenseService.updateExpense(id, expense);
    }

    @Operation(
            summary = "Delete an expense",
            description = "Deletes an expense using its ID"
    )
    @DeleteMapping("/{id}")
    public void deleteExpense(
            @PathVariable Long id
    ){
        expenseService.deleteExpense(id);
    }
}