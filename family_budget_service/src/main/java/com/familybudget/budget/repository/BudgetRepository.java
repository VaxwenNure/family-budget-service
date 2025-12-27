package com.familybudget.budget.repository;

import com.familybudget.budget.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findByFamilyId(Long familyId);

    Optional<Budget> findByIdAndFamilyId(Long id, Long familyId);
}
