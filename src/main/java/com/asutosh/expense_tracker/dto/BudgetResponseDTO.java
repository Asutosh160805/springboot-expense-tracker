package com.asutosh.expense_tracker.dto;

import lombok.Data;
import java.time.Month;

@Data
public class BudgetResponseDTO {

    private Long id;
    private Double amount;
    private Month month;
    private Integer year;
}