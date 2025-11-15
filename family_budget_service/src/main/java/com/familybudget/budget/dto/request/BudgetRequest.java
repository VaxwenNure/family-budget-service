package com.familybudget.budget.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BudgetRequest {
    private String name;
    private BigDecimal limitAmount;
}
