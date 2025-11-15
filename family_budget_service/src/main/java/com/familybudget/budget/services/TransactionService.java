package com.familybudget.budget.services;

import com.familybudget.budget.dto.request.TransactionRequest;
import com.familybudget.budget.entity.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction addTransaction(Long budgetId, Long categoryId, TransactionRequest request);

    List<Transaction> getTransactions(Long budgetId, Long categoryId);
}
