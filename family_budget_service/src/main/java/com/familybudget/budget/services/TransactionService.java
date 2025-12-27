package com.familybudget.budget.services;

import com.familybudget.budget.dto.request.TransactionRequest;
import com.familybudget.budget.entity.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction addTransaction(Long familyId, Long budgetId, Long categoryId, TransactionRequest request);

    List<Transaction> getTransactions(Long familyId, Long budgetId, Long categoryId);

    Transaction getTransaction(Long familyId, Long budgetId, Long categoryId, Long transactionId);

    Transaction updateTransaction(Long familyId, Long budgetId, Long categoryId, Long transactionId, TransactionRequest request);
}
