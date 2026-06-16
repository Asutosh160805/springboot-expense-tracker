package com.asutosh.expense_tracker.dto;


import com.asutosh.expense_tracker.entity.Category;
import lombok.Data;

@Data
public class ExpenseResponseDTO {

    private Long id;

    private String title;

    private Double amount;

    private Category category;
}