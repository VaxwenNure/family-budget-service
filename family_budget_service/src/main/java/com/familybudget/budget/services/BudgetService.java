package com.familybudget.budget.services;

public interface BudgetService {
    Transaction addTransaction(Long budgetId, TransactionRequest request);
}
