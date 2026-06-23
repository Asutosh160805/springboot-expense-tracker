package com.asutosh.expense_tracker.dto;

import lombok.Data;
import java.time.Month;

@Data
public class BudgetRequestDTO {

    private Double amount;
    private Month month;
    private Integer year;
}