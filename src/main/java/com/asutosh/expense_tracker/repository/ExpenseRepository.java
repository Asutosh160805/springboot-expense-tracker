package com.asutosh.expense_tracker.repository;

import com.asutosh.expense_tracker.entity.Category;
import com.asutosh.expense_tracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
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
    List<Expense> findByUserEmailAndCategory(
            String email,
            Category category
    );
    List<Expense> findByUserEmailAndAmountGreaterThan(
            String email,
            Double amount
    );

    @Query("""
        SELECT COALESCE(SUM(e.amount), 0)
        FROM Expense e
        WHERE e.user.email = :email
        AND e.expenseDate BETWEEN :startDate AND :endDate
        """)
    Double getTotalSpentForPeriod(
            @Param("email") String email,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}