package com.familybudget.budget.services.impl;

import com.familybudget.budget.dto.request.SalaryRequest;
import com.familybudget.budget.entity.Family;
import com.familybudget.budget.entity.Salary;
import com.familybudget.budget.exception.FamilyNotFoundException;
import com.familybudget.budget.exception.SalaryNotFoundException;
import com.familybudget.budget.messaging.dto.SalaryCreatedEvent;
import com.familybudget.budget.messaging.dto.SalaryUpdatedEvent;
import com.familybudget.budget.messaging.publisher.DomainEventPublisher;
import com.familybudget.budget.repository.FamilyRepository;
import com.familybudget.budget.repository.SalaryRepository;
import com.familybudget.budget.services.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;
    private final FamilyRepository familyRepository;
    private final DomainEventPublisher publisher;

    @Override
    public Salary createSalary(Long familyId, SalaryRequest request) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        Salary salary = Salary.builder()
                .family(family)
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .receivedAt(request.getReceivedAt())
                .source(request.getSource())
                .build();

        Salary saved = salaryRepository.save(salary);

        publisher.publish(
                "salary.created",
                new SalaryCreatedEvent(
                        familyId,
                        saved.getId(),
                        saved.getAmount(),
                        saved.getCurrency(),
                        saved.getReceivedAt(),
                        saved.getSource(),
                        Instant.now()
                )
        );

        return saved;
    }

    @Override
    public List<Salary> getSalaries(Long familyId) {
        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        return salaryRepository.findByFamilyId(familyId);
    }

    @Override
    public Salary getSalaryById(Long familyId, Long salaryId) {
        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        return salaryRepository.findByIdAndFamilyId(salaryId, familyId)
                .orElseThrow(() -> new SalaryNotFoundException(salaryId));
    }

    @Override
    public Salary updateSalary(Long familyId, Long salaryId, SalaryRequest request) {
        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        Salary salary = salaryRepository.findByIdAndFamilyId(salaryId, familyId)
                .orElseThrow(() -> new SalaryNotFoundException(salaryId));

        if (request.getAmount() != null) {
            salary.setAmount(request.getAmount());
        }
        if (request.getCurrency() != null) {
            salary.setCurrency(request.getCurrency());
        }
        if (request.getReceivedAt() != null) {
            salary.setReceivedAt(request.getReceivedAt());
        }
        if (request.getSource() != null) {
            salary.setSource(request.getSource());
        }

        Salary saved = salaryRepository.save(salary);

        publisher.publish(
                "salary.updated",
                new SalaryUpdatedEvent(
                        familyId,
                        saved.getId(),
                        saved.getAmount(),
                        saved.getCurrency(),
                        saved.getReceivedAt(),
                        saved.getSource(),
                        Instant.now()
                )
        );

        return saved;
    }
}
