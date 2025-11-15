package com.familybudget.budget.services.impl;

import com.familybudget.budget.dto.request.CategoryRequest;
import com.familybudget.budget.entity.Budget;
import com.familybudget.budget.entity.Category;
import com.familybudget.budget.exception.BudgetNotFoundException;
import com.familybudget.budget.repository.BudgetRepository;
import com.familybudget.budget.repository.CategoryRepository;
import com.familybudget.budget.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Long budgetId, CategoryRequest request) {

        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new BudgetNotFoundException(budgetId));

        Category category = new Category();
        category.setName(request.getName());
        category.setBudget(budget);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategories(Long budgetId) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new BudgetNotFoundException(budgetId));

        return budget.getCategories();
    }
}
