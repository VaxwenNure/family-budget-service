package com.familybudget.budget.controller;
import org.springframework.web.bind.annotation.RestController;


@PostMapping("/{budgetId}/transactions")
public ResponseEntity<Transaction> addTransaction(
        @PathVariable Long budgetId,
        @RequestBody TransactionRequest request
) {
    Transaction transaction = budgetService.addTransaction(budgetId, request);
    return ResponseEntity.ok(transaction);
}
