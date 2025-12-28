package com.familybudget.budget.services.impl;

import com.familybudget.budget.dto.request.TransactionRequest;
import com.familybudget.budget.entity.Budget;
import com.familybudget.budget.entity.Category;
import com.familybudget.budget.entity.Transaction;
import com.familybudget.budget.exception.BudgetNotFoundException;
import com.familybudget.budget.exception.CategoryNotFoundException;
import com.familybudget.budget.exception.FamilyNotFoundException;
import com.familybudget.budget.exception.TransactionNotFoundException;
import com.familybudget.budget.messaging.dto.TransactionCreatedEvent;
import com.familybudget.budget.messaging.dto.TransactionUpdatedEvent;
import com.familybudget.budget.messaging.publisher.DomainEventPublisher;
import com.familybudget.budget.repository.BudgetRepository;
import com.familybudget.budget.repository.CategoryRepository;
import com.familybudget.budget.repository.FamilyRepository;
import com.familybudget.budget.repository.TransactionRepository;
import com.familybudget.budget.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;
    private final FamilyRepository familyRepository;
    private final DomainEventPublisher publisher;

    @Override
    public Transaction addTransaction(Long familyId, Long budgetId, Long categoryId, TransactionRequest request) {

        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new BudgetNotFoundException(budgetId));

        if (budget.getFamily() == null || !budget.getFamily().getId().equals(familyId)) {
            throw new RuntimeException("Budget does not belong to this family");
        }

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        if (category.getBudget() == null || !category.getBudget().getId().equals(budgetId)) {
            throw new RuntimeException("Category does not belong to this budget");
        }

        BigDecimal amount = request.getAmount() == null ? BigDecimal.ZERO : request.getAmount();

        Transaction transaction = Transaction.builder()
                .amount(amount)
                .description(request.getDescription())
                .date(request.getDate())
                .category(category)
                .build();

        // Update spent
        category.setSpent(category.getSpent().add(amount));
        budget.setSpent(budget.getSpent().add(amount));

        Transaction saved = transactionRepository.save(transaction);

        publisher.publish(
                "transaction.created",
                new TransactionCreatedEvent(
                        familyId,
                        budgetId,
                        categoryId,
                        saved.getId(),
                        saved.getAmount(),
                        saved.getDescription(),
                        saved.getDate(),
                        Instant.now()
                )
        );

        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getTransactions(Long familyId, Long budgetId, Long categoryId) {

        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new BudgetNotFoundException(budgetId));

        if (budget.getFamily() == null || !budget.getFamily().getId().equals(familyId)) {
            throw new RuntimeException("Budget does not belong to this family");
        }

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        if (category.getBudget() == null || !category.getBudget().getId().equals(budgetId)) {
            throw new RuntimeException("Category does not belong to this budget");
        }

        return new ArrayList<>(category.getTransactions());
    }

    @Override
    @Transactional(readOnly = true)
    public Transaction getTransaction(Long familyId, Long budgetId, Long categoryId, Long transactionId) {
        return transactionRepository
                .findByIdAndCategoryIdAndCategoryBudgetIdAndCategoryBudgetFamilyId(transactionId, categoryId, budgetId, familyId)
                .orElseThrow(() -> new TransactionNotFoundException(transactionId));
    }

    @Override
    public Transaction updateTransaction(Long familyId, Long budgetId, Long categoryId, Long transactionId, TransactionRequest request) {

        Transaction transaction = transactionRepository
                .findByIdAndCategoryIdAndCategoryBudgetIdAndCategoryBudgetFamilyId(transactionId, categoryId, budgetId, familyId)
                .orElseThrow(() -> new TransactionNotFoundException(transactionId));

        Category category = transaction.getCategory();
        Budget budget = category.getBudget();

        if (request.getAmount() != null) {
            BigDecimal oldAmount = transaction.getAmount() == null ? BigDecimal.ZERO : transaction.getAmount();
            BigDecimal newAmount = request.getAmount();

            BigDecimal delta = newAmount.subtract(oldAmount);

            category.setSpent(category.getSpent().add(delta));
            budget.setSpent(budget.getSpent().add(delta));

            transaction.setAmount(newAmount);
        }

        if (request.getDescription() != null) {
            transaction.setDescription(request.getDescription());
        }

        if (request.getDate() != null) {
            transaction.setDate(request.getDate());
        }

        Transaction saved = transactionRepository.save(transaction);

        publisher.publish(
                "transaction.updated",
                new TransactionUpdatedEvent(
                        familyId,
                        budgetId,
                        category.getId(),
                        saved.getId(),
                        saved.getAmount(),
                        saved.getDescription(),
                        saved.getDate(),
                        Instant.now()
                )
        );

        return saved;
    }
}
