package com.familybudget.budget.services.impl;

import com.familybudget.budget.dto.request.SalaryRequest;
import com.familybudget.budget.dto.response.SalaryResponse;
import com.familybudget.budget.entity.Family;
import com.familybudget.budget.entity.Salary;
import com.familybudget.budget.exception.FamilyNotFoundException;
import com.familybudget.budget.repository.FamilyRepository;
import com.familybudget.budget.repository.SalaryRepository;
import com.familybudget.budget.services.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;
    private final FamilyRepository familyRepository;

    @Override
    @Transactional
    public SalaryResponse addSalary(Long familyId, SalaryRequest request) {

        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        Salary salary = Salary.builder()
                .family(family)
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .source(request.getSource())
                .receivedAt(request.getReceivedAt())
                .build();

        Salary saved = salaryRepository.save(salary);

        SalaryResponse response = new SalaryResponse();

        response.setId(saved.getId());
        response.setAmount(saved.getAmount());
        response.setCurrency(saved.getCurrency());
        response.setSource(saved.getSource());
        response.setReceivedAt(saved.getReceivedAt());

        return response;
    }

    @Override
    public java.util.List<SalaryResponse> getSalaries(Long familyId) {

        return salaryRepository.findByFamilyId(familyId).stream().map(salary -> {
            SalaryResponse r = new SalaryResponse();
            r.setId(salary.getId());
            r.setAmount(salary.getAmount());
            r.setCurrency(salary.getCurrency());
            r.setSource(salary.getSource());
            r.setReceivedAt(salary.getReceivedAt());
            return r;
        }).toList();
    }
}
