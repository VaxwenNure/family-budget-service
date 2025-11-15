package com.familybudget.budget.services.impl;

import com.familybudget.budget.dto.request.BudgetRequest;
import com.familybudget.budget.entity.Budget;
import com.familybudget.budget.exception.BudgetNotFoundException;
import com.familybudget.budget.repository.BudgetRepository;
import com.familybudget.budget.services.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;

    @Override
    public Budget createBudget(BudgetRequest request) {
        Budget budget = new Budget();
        budget.setName(request.getName());
        budget.setLimitAmount(request.getLimitAmount());
        return budgetRepository.save(budget);
    }

    @Override
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    @Override
    public Budget getBudget(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException(id));
    }
}
