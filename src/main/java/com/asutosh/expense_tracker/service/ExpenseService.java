package com.asutosh.expense_tracker.service;

import com.asutosh.expense_tracker.entity.Expense;
import com.asutosh.expense_tracker.exception.ExpenseNotFoundException;
import com.asutosh.expense_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense saveExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ExpenseNotFoundException(
                                "Expense not found with id: " + id
                        ));
    }

    public Expense updateExpense(Long id,
                                 Expense updatedExpense) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ExpenseNotFoundException(
                                "Expense not found with id: " + id
                        ));

        expense.setTitle(updatedExpense.getTitle());
        expense.setAmount(updatedExpense.getAmount());

        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {

        if (!expenseRepository.existsById(id)) {
            throw new ExpenseNotFoundException(
                    "Expense not found with id: " + id
            );
        }

        expenseRepository.deleteById(id);
    }
}