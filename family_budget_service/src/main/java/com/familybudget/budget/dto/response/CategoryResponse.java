package com.familybudget.budget.dto.response;

import com.familybudget.budget.entity.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CategoryResponse {

    private Long id;
    private String name;
    private BigDecimal spent;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.spent = category.getSpent();
    }
}
