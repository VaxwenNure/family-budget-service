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
@RequestMapping("/budget/families/{familyId}/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    // CREATE BUDGET -----------------------------------------------------
    @PostMapping
    public ResponseEntity<BudgetResponse> createBudget(@PathVariable Long familyId,
                                                       @RequestBody BudgetRequest request) {
        Budget created = budgetService.createBudget(familyId, request);
        return ResponseEntity.ok(new BudgetResponse(created));
    }

    // GET ALL BUDGETS (FOR FAMILY) -------------------------------------
    @GetMapping
    public ResponseEntity<List<BudgetResponse>> getAllBudgets(@PathVariable Long familyId) {
        List<Budget> budgets = budgetService.getAllBudgets(familyId);

        List<BudgetResponse> responses = budgets.stream()
                .map(BudgetResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    // GET ONE BUDGET (FOR FAMILY) --------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<BudgetResponse> getBudget(@PathVariable Long familyId,
                                                    @PathVariable Long id) {
        Budget budget = budgetService.getBudget(familyId, id);
        return ResponseEntity.ok(new BudgetResponse(budget));
    }

    // UPDATE BUDGET -----------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<BudgetResponse> updateBudget(@PathVariable Long familyId,
                                                       @PathVariable Long id,
                                                       @RequestBody BudgetRequest request) {
        Budget updated = budgetService.updateBudget(familyId, id, request);
        return ResponseEntity.ok(new BudgetResponse(updated));
    }
}
