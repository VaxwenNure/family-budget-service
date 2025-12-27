package com.familybudget.budget.services;

import com.familybudget.budget.dto.request.BudgetRequest;
import com.familybudget.budget.entity.Budget;

import java.util.List;

public interface BudgetService {

    Budget createBudget(Long familyId, BudgetRequest request);

    List<Budget> getAllBudgets(Long familyId);

    Budget getBudget(Long familyId, Long id);

    Budget updateBudget(Long familyId, Long id, BudgetRequest request);
}
