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
@RequestMapping("/budget/families/{familyId}/budgets/{budgetId}/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // CREATE CATEGORY ---------------------------------------------------
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
            @PathVariable Long familyId,
            @PathVariable Long budgetId,
            @RequestBody CategoryRequest request
    ) {
        Category created = categoryService.addCategory(familyId, budgetId, request);
        return ResponseEntity.ok(new CategoryResponse(created));
    }

    // GET ALL CATEGORIES ------------------------------------------------
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories(
            @PathVariable Long familyId,
            @PathVariable Long budgetId
    ) {
        List<Category> categories = categoryService.getCategories(familyId, budgetId);

        List<CategoryResponse> response = categories.stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // GET ONE CATEGORY --------------------------------------------------
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategory(
            @PathVariable Long familyId,
            @PathVariable Long budgetId,
            @PathVariable Long categoryId
    ) {
        Category category = categoryService.getCategory(familyId, budgetId, categoryId);
        return ResponseEntity.ok(new CategoryResponse(category));
    }

    // UPDATE ONE CATEGORY ----------------------------------------------
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Long familyId,
            @PathVariable Long budgetId,
            @PathVariable Long categoryId,
            @RequestBody CategoryRequest request
    ) {
        Category updated = categoryService.updateCategory(familyId, budgetId, categoryId, request);
        return ResponseEntity.ok(new CategoryResponse(updated));
    }
}
