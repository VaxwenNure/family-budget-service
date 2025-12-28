package com.familybudget.budget.services.impl;

import com.familybudget.budget.dto.request.BudgetRequest;
import com.familybudget.budget.entity.Budget;
import com.familybudget.budget.entity.Family;
import com.familybudget.budget.exception.BudgetNotFoundException;
import com.familybudget.budget.exception.FamilyNotFoundException;
import com.familybudget.budget.messaging.dto.BudgetCreatedEvent;
import com.familybudget.budget.messaging.dto.BudgetUpdatedEvent;
import com.familybudget.budget.messaging.publisher.DomainEventPublisher;
import com.familybudget.budget.repository.BudgetRepository;
import com.familybudget.budget.repository.FamilyRepository;
import com.familybudget.budget.services.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final FamilyRepository familyRepository;
    private final DomainEventPublisher publisher;

    @Override
    public Budget createBudget(Long familyId, BudgetRequest request) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        Budget budget = new Budget();
        budget.setName(request.getName());
        budget.setLimitAmount(request.getLimitAmount());
        budget.setFamily(family);

        Budget saved = budgetRepository.save(budget);

        publisher.publish(
                "budget.created",
                new BudgetCreatedEvent(
                        familyId,
                        saved.getId(),
                        saved.getName(),
                        saved.getLimitAmount(),
                        Instant.now()
                )
        );

        return saved;
    }

    @Override
    public List<Budget> getAllBudgets(Long familyId) {
        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        return budgetRepository.findByFamilyId(familyId);
    }

    @Override
    public Budget getBudget(Long familyId, Long id) {
        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        return budgetRepository.findByIdAndFamilyId(id, familyId)
                .orElseThrow(() -> new BudgetNotFoundException(id));
    }

    @Override
    public Budget updateBudget(Long familyId, Long id, BudgetRequest request) {
        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        Budget budget = budgetRepository.findByIdAndFamilyId(id, familyId)
                .orElseThrow(() -> new BudgetNotFoundException(id));

        if (request.getName() != null) {
            budget.setName(request.getName());
        }
        if (request.getLimitAmount() != null) {
            budget.setLimitAmount(request.getLimitAmount());
        }

        Budget saved = budgetRepository.save(budget);

        publisher.publish(
                "budget.updated",
                new BudgetUpdatedEvent(
                        familyId,
                        saved.getId(),
                        saved.getName(),
                        saved.getLimitAmount(),
                        Instant.now()
                )
        );

        return saved;
    }
}
