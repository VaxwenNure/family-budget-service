package com.familybudget.budget.services.impl;

import com.familybudget.budget.dto.request.TransactionRequest;
import com.familybudget.budget.entity.Budget;
import com.familybudget.budget.entity.Category;
import com.familybudget.budget.entity.Transaction;
import com.familybudget.budget.exception.BudgetNotFoundException;
import com.familybudget.budget.exception.CategoryNotFoundException;
import com.familybudget.budget.repository.BudgetRepository;
import com.familybudget.budget.repository.CategoryRepository;
import com.familybudget.budget.repository.TransactionRepository;
import com.familybudget.budget.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction addTransaction(Long budgetId, Long categoryId, TransactionRequest request) {

        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new BudgetNotFoundException(budgetId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        if (!category.getBudget().getId().equals(budgetId)) {
            throw new RuntimeException("Category does not belong to this budget");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setDate(request.getDate());
        transaction.setCategory(category);

        // Update spent
        category.setSpent(category.getSpent().add(request.getAmount()));
        budget.setSpent(budget.getSpent().add(request.getAmount()));

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactions(Long budgetId, Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        if (!category.getBudget().getId().equals(budgetId)) {
            throw new RuntimeException("Category does not belong to this budget");
        }

        return category.getTransactions();
    }
}
