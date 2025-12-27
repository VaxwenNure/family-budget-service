package com.familybudget.budget.repository;

import com.familybudget.budget.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
    List<Salary> findByFamilyId(Long familyId);
}
