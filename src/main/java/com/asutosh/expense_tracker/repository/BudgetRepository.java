package com.asutosh.expense_tracker.repository;

import com.asutosh.expense_tracker.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Month;
import java.util.Optional;

public interface BudgetRepository
        extends JpaRepository<Budget, Long> {

    // Find budget for a specific user and month
    Optional<Budget> findByUserEmailAndMonthAndYear(
            String email,
            Month month,
            Integer year
    );
}