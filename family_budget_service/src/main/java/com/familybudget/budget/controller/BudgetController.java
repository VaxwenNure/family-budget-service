package com.familybudget.budget.controller;

import com.familybudget.budget.dto.request.BudgetRequest;
import com.familybudget.budget.dto.response.BudgetResponse;
import com.familybudget.budget.entity.Budget;
import com.familybudget.budget.services.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    // CREATE BUDGET -----------------------------------------------------
    @PostMapping
    public ResponseEntity<BudgetResponse> createBudget(@RequestBody BudgetRequest request) {
        Budget created = budgetService.createBudget(request);
        return ResponseEntity.ok(new BudgetResponse(created));
    }

    // GET ALL BUDGETS ---------------------------------------------------
    @GetMapping
    public ResponseEntity<List<BudgetResponse>> getAllBudgets() {
        List<Budget> budgets = budgetService.getAllBudgets();

        List<BudgetResponse> responses = budgets.stream()
                .map(BudgetResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    // GET ONE BUDGET ----------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<BudgetResponse> getBudget(@PathVariable Long id) {
        Budget budget = budgetService.getBudget(id);
        return ResponseEntity.ok(new BudgetResponse(budget));
    }
}
