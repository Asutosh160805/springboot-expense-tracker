package com.asutosh.expense_tracker.dto;


import lombok.Data;

@Data
public class DashboardResponseDTO {

    private Double budget;

    private Double spent;

    private Double remaining;
}
