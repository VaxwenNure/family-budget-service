package com.familybudget.budget.services.impl;

import com.familybudget.budget.dto.request.CategoryRequest;
import com.familybudget.budget.entity.Budget;
import com.familybudget.budget.entity.Category;
import com.familybudget.budget.exception.BudgetNotFoundException;
import com.familybudget.budget.exception.CategoryNotFoundException;
import com.familybudget.budget.exception.FamilyNotFoundException;
import com.familybudget.budget.messaging.dto.CategoryCreatedEvent;
import com.familybudget.budget.messaging.dto.CategoryUpdatedEvent;
import com.familybudget.budget.messaging.publisher.DomainEventPublisher;
import com.familybudget.budget.repository.BudgetRepository;
import com.familybudget.budget.repository.CategoryRepository;
import com.familybudget.budget.repository.FamilyRepository;
import com.familybudget.budget.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final FamilyRepository familyRepository;
    private final DomainEventPublisher publisher;

    @Override
    public Category addCategory(Long familyId, Long budgetId, CategoryRequest request) {
        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        Budget budget = budgetRepository.findByIdAndFamilyId(budgetId, familyId)
                .orElseThrow(() -> new BudgetNotFoundException(budgetId));

        Category category = new Category();
        category.setName(request.getName());
        category.setColor(request.getColor());
        category.setBudget(budget);

        Category saved = categoryRepository.save(category);

        publisher.publish(
                "category.created",
                new CategoryCreatedEvent(
                        familyId,
                        budgetId,
                        saved.getId(),
                        saved.getName(),
                        saved.getColor(),
                        Instant.now()
                )
        );

        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getCategories(Long familyId, Long budgetId) {
        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        return categoryRepository.findByBudgetIdAndBudgetFamilyId(budgetId, familyId);
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategory(Long familyId, Long budgetId, Long categoryId) {
        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        return categoryRepository.findByIdAndBudgetIdAndBudgetFamilyId(categoryId, budgetId, familyId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    @Override
    public Category updateCategory(Long familyId, Long budgetId, Long categoryId, CategoryRequest request) {
        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        Category category = categoryRepository.findByIdAndBudgetIdAndBudgetFamilyId(categoryId, budgetId, familyId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        if (request.getName() != null) {
            category.setName(request.getName());
        }
        if (request.getColor() != null) {
            category.setColor(request.getColor());
        }

        Category saved = categoryRepository.save(category);

        publisher.publish(
                "category.updated",
                new CategoryUpdatedEvent(
                        familyId,
                        budgetId,
                        saved.getId(),
                        saved.getName(),
                        saved.getColor(),
                        Instant.now()
                )
        );

        return saved;
    }
}
