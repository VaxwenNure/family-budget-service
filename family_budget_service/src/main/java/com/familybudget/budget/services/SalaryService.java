package com.familybudget.budget.services;

import com.familybudget.budget.dto.request.SalaryRequest;
import com.familybudget.budget.dto.response.SalaryResponse;

import java.util.List;

public interface SalaryService {
    SalaryResponse addSalary(Long familyId, SalaryRequest request);
    List<SalaryResponse> getSalaries(Long familyId);
}
