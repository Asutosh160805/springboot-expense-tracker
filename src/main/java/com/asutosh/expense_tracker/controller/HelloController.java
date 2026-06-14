package com.asutosh.expense_tracker.controller;

import com.asutosh.expense_tracker.entity.Expense;
import com.asutosh.expense_tracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class HelloController {

    private final ExpenseService expenseService;

    public HelloController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Expense Tracker";
    }
}