package com.asutosh.expense_tracker.repository;

import com.asutosh.expense_tracker.entity.Category;
import com.asutosh.expense_tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository
        extends JpaRepository<Expense, Long> {
    List<Expense> findByCategory(
            Category category
    );
    List<Expense>
    findByAmountGreaterThan(
            Double amount
    );
}