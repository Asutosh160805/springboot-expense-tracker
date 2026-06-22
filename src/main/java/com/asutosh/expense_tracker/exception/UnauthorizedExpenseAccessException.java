package com.asutosh.expense_tracker.exception;

public class UnauthorizedExpenseAccessException
        extends RuntimeException {

    public UnauthorizedExpenseAccessException(
            String message
    ) {
        super(message);
    }
}