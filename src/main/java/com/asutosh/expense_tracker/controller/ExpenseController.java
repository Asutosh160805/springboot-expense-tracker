package com.asutosh.expense_tracker.controller;

import com.asutosh.expense_tracker.dto.ExpenseRequestDTO;
import com.asutosh.expense_tracker.dto.ExpenseResponseDTO;
import com.asutosh.expense_tracker.entity.Expense;
import com.asutosh.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping
    public ExpenseResponseDTO createExpense(
            @Valid @RequestBody ExpenseRequestDTO request
    ){
        return expenseService.saveExpense(request);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody Expense expense
    ){
        return expenseService.updateExpense(id, expense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(
            @PathVariable Long id
    ){
        expenseService.deleteExpense(id);
    }
}