package com.familybudget.budget.services;


public interface TransactionService {
    Transaction addTransaction(Long budgetId, TransactionRequest request);
}
