package com.asutosh.expense_tracker.dto;


import com.asutosh.expense_tracker.entity.Category;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ExpenseResponseDTO {

    private Long id;

    private String title;

    private Double amount;

    private Category category;

    private LocalDate expenseDate;

    private LocalDateTime createdAt;
}