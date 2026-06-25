package com.asutosh.expense_tracker.controller;

import com.asutosh.expense_tracker.dto.BudgetRequestDTO;
import com.asutosh.expense_tracker.dto.BudgetResponseDTO;
import com.asutosh.expense_tracker.service.BudgetService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.asutosh.expense_tracker.dto.DashboardResponseDTO;
import com.asutosh.expense_tracker.dto.CategoryReportDTO;

import java.util.List;

@RestController
@RequestMapping("/budgets")
@SecurityRequirement(name = "bearerAuth")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(
            BudgetService budgetService
    ) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public BudgetResponseDTO createBudget(
            @Valid @RequestBody
            BudgetRequestDTO request
    ) {
        System.out.println("BUDGET ENDPOINT HIT");

        return budgetService
                .createBudget(request);
    }
    @GetMapping("/current")
    public BudgetResponseDTO getCurrentBudget() {

        return budgetService
                .getCurrentBudget();
    }

    @GetMapping("/status")
    public DashboardResponseDTO getDashboard() {

        return budgetService
                .getDashboard();
    }

    @GetMapping("/reports/monthly")
    public List<CategoryReportDTO> getMonthlyReport() {

        return budgetService
                .getMonthlyReport();
    }
}