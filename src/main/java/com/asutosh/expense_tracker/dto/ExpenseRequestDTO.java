package com.asutosh.expense_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import lombok.Data;

@Data
public class ExpenseRequestDTO {

    @NotBlank
    private String title;

    @Positive
    private Double amount;
}