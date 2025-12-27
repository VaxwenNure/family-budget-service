package com.familybudget.budget.repository;

import com.familybudget.budget.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByIdAndCategoryIdAndCategoryBudgetIdAndCategoryBudgetFamilyId(
            Long id,
            Long categoryId,
            Long budgetId,
            Long familyId
    );
}
