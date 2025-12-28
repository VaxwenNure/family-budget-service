package com.familybudget.budget.services;

import com.familybudget.budget.dto.request.SalaryRequest;
import com.familybudget.budget.entity.Salary;

import java.util.List;

public interface SalaryService {

    Salary createSalary(Long familyId, SalaryRequest request);

    List<Salary> getSalaries(Long familyId);

    Salary getSalaryById(Long familyId, Long salaryId);

    Salary updateSalary(Long familyId, Long salaryId, SalaryRequest request);
}
