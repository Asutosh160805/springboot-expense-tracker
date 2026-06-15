package com.asutosh.expense_tracker.dto;


import lombok.Data;

@Data
public class ExpenseResponseDTO {

    private Long id;

    private String title;

    private Double amount;
}