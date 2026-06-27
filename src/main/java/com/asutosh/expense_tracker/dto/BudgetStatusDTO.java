package com.asutosh.expense_tracker.dto;

import com.asutosh.expense_tracker.entity.BudgetStatus;
import lombok.Data;

@Data
public class BudgetStatusDTO {

    // Monthly budget
    private Double budget;

    // Total amount spent this month
    private Double spent;

    // Remaining budget
    private Double remaining;

    // Budget usage percentage
    private Integer percentageUsed;

    // True if budget exceeded
    private Boolean overspent;

    // User-friendly message
    private String message;

    // SAFE | WARNING | EXCEEDED
    private BudgetStatus status;

    // GREEN | YELLOW | RED
    private String color;
}