package com.familybudget.budget.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(Long id) {
        super("Transaction not found with id: " + id);
    }
}
