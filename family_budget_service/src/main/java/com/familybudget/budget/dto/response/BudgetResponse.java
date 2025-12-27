package com.familybudget.budget.dto.response;

import com.familybudget.budget.entity.Budget;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BudgetResponse {

    private Long id;
    private String name;
    private BigDecimal limitAmount;
    private BigDecimal spent;

    public BudgetResponse(Budget budget) {
        this.id = budget.getId();
        this.name = budget.getName();
        this.limitAmount = budget.getLimitAmount();
        this.spent = budget.getSpent();
    }
}
