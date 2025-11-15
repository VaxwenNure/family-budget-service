package com.familybudget.budget.services;

import com.familybudget.budget.dto.request.CategoryRequest;
import com.familybudget.budget.entity.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Long budgetId, CategoryRequest request);
    List<Category> getCategories(Long budgetId);
}
