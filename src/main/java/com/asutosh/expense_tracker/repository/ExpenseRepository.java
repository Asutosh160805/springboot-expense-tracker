package com.asutosh.expense_tracker.repository;

import com.asutosh.expense_tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository
        extends JpaRepository<Expense, Long> {
}