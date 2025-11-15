package com.familybudget.budget.services;

import com.familybudget.budget.dto.request.BudgetRequest;
import com.familybudget.budget.entity.Budget;

import java.util.List;

public interface BudgetService {

    Budget createBudget(BudgetRequest request);

    List<Budget> getAllBudgets();

    Budget getBudget(Long id);
}
