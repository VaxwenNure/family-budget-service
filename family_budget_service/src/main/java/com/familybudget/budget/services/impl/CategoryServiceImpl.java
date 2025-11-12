package com.familybudget.budget.services.impl;

private final TransactionRepository transactionRepository;
@Override
public Transaction addTransaction(Long categoryId, Transaction transaction) {
    Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found"));

    transaction.setCategory(category);
    return transactionRepository.save(transaction);
}
