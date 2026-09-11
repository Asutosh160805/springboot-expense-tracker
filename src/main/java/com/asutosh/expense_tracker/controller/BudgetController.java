package com.asutosh.expense_tracker.controller;

import com.asutosh.expense_tracker.dto.*;
import com.asutosh.expense_tracker.service.BudgetService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<BudgetResponseDTO> getCurrentBudget() {

        BudgetResponseDTO budget = budgetService.getCurrentBudget();

        if (budget == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(budget);
    }

    @GetMapping("/status")
    public BudgetStatusDTO getBudgetStatus() {

        return budgetService
                .getBudgetStatus();
    }

    @GetMapping("/reports/monthly")
    public List<CategoryReportDTO> getMonthlyReport() {

        return budgetService
                .getMonthlyReport();
    }
}