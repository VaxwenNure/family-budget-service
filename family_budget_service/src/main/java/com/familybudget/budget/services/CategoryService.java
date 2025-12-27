package com.familybudget.budget.services;

import com.familybudget.budget.dto.request.CategoryRequest;
import com.familybudget.budget.entity.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(Long familyId, Long budgetId, CategoryRequest request);

    List<Category> getCategories(Long familyId, Long budgetId);

    Category getCategory(Long familyId, Long budgetId, Long categoryId);

    Category updateCategory(Long familyId, Long budgetId, Long categoryId, CategoryRequest request);
}
