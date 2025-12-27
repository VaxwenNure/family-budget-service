package com.familybudget.budget.services.impl;

import com.familybudget.budget.dto.request.BudgetRequest;
import com.familybudget.budget.entity.Budget;
import com.familybudget.budget.entity.Family;
import com.familybudget.budget.exception.BudgetNotFoundException;
import com.familybudget.budget.exception.FamilyNotFoundException;
import com.familybudget.budget.repository.BudgetRepository;
import com.familybudget.budget.repository.FamilyRepository;
import com.familybudget.budget.services.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final FamilyRepository familyRepository;

    @Override
    public Budget createBudget(Long familyId, BudgetRequest request) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        Budget budget = new Budget();
        budget.setName(request.getName());
        budget.setLimitAmount(request.getLimitAmount());
        budget.setFamily(family);

        return budgetRepository.save(budget);
    }

    @Override
    public List<Budget> getAllBudgets(Long familyId) {
        // Ensure family exists (so you return 404 if familyId is wrong)
        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        // Only budgets for this family
        return budgetRepository.findByFamilyId(familyId);
    }

    @Override
    public Budget getBudget(Long familyId, Long id) {
        // Ensure family exists
        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        // Only return if budget belongs to this family
        return budgetRepository.findByIdAndFamilyId(id, familyId)
                .orElseThrow(() -> new BudgetNotFoundException(id));
    }

    @Override
    public Budget updateBudget(Long familyId, Long id, BudgetRequest request) {
        // Ensure family exists
        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        Budget budget = budgetRepository.findByIdAndFamilyId(id, familyId)
                .orElseThrow(() -> new BudgetNotFoundException(id));

        // Update only provided fields (safe partial update behavior)
        if (request.getName() != null) {
            budget.setName(request.getName());
        }
        if (request.getLimitAmount() != null) {
            budget.setLimitAmount(request.getLimitAmount());
        }

        return budgetRepository.save(budget);
    }
}
