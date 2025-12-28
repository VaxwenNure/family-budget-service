package com.familybudget.budget.repository;

import com.familybudget.budget.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

    List<Salary> findByFamilyId(Long familyId);

    Optional<Salary> findByIdAndFamilyId(Long id, Long familyId);
}
