package com.asutosh.expense_tracker.repository;

import com.asutosh.expense_tracker.entity.Category;
import com.asutosh.expense_tracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository
        extends JpaRepository<Expense, Long> {

    // User-specific expenses
    Page<Expense> findByUserEmail(
            String email,
            Pageable pageable
    );

    List<Expense> findByCategory(
            Category category
    );

    List<Expense> findByAmountGreaterThan(
            Double amount
    );
}