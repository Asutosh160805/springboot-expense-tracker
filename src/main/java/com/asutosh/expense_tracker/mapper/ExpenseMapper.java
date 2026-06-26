package com.asutosh.expense_tracker.mapper;

import com.asutosh.expense_tracker.dto.ExpenseResponseDTO;
import com.asutosh.expense_tracker.entity.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {

    public ExpenseResponseDTO toDTO(
            Expense expense
    ) {

        ExpenseResponseDTO dto =
                new ExpenseResponseDTO();

        dto.setId(expense.getId());
        dto.setTitle(expense.getTitle());
        dto.setAmount(expense.getAmount());
        dto.setCategory(expense.getCategory());

        dto.setExpenseDate(
                expense.getExpenseDate()
        );

        dto.setCreatedAt(
                expense.getCreatedAt()
        );

        return dto;
    }
}