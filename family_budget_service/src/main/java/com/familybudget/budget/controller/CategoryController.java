package com.familybudget.budget.controller;

import com.familybudget.budget.dto.request.CategoryRequest;
import com.familybudget.budget.dto.response.CategoryResponse;
import com.familybudget.budget.entity.Category;
import com.familybudget.budget.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/budgets/{budgetId}/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // Create category inside a budget
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
            @PathVariable Long budgetId,
            @RequestBody CategoryRequest request
    ) {
        Category created = categoryService.addCategory(budgetId, request);
        return ResponseEntity.ok(new CategoryResponse(created));
    }

    // Get all categories for a budget
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories(@PathVariable Long budgetId) {
        List<Category> categories = categoryService.getCategories(budgetId);
        List<CategoryResponse> response = categories.stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
