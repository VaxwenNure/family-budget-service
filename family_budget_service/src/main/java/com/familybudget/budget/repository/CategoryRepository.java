package com.familybudget.budget.repository;

import com.familybudget.budget.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByIdAndBudgetIdAndBudgetFamilyId(Long id, Long budgetId, Long familyId);

    List<Category> findByBudgetIdAndBudgetFamilyId(Long budgetId, Long familyId);
}
