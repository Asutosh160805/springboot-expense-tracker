package com.asutosh.expense_tracker.dto;

import com.asutosh.expense_tracker.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryReportDTO {

    private Category category;

    private Double totalAmount;
}