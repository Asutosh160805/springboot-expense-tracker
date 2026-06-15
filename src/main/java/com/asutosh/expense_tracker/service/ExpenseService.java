package com.asutosh.expense_tracker.service;

import com.asutosh.expense_tracker.dto.ExpenseRequestDTO;
import com.asutosh.expense_tracker.dto.ExpenseResponseDTO;
import com.asutosh.expense_tracker.entity.Expense;
import com.asutosh.expense_tracker.exception.ExpenseNotFoundException;
import com.asutosh.expense_tracker.repository.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ExpenseResponseDTO saveExpense(
            ExpenseRequestDTO request
    ) {

        Expense expense = new Expense();

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());

        Expense savedExpense =
                expenseRepository.save(expense);

        ExpenseResponseDTO response =
                new ExpenseResponseDTO();

        response.setId(savedExpense.getId());
        response.setTitle(savedExpense.getTitle());
        response.setAmount(savedExpense.getAmount());

        return response;
    }

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

        return expenseRepository.findAll(pageable);
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