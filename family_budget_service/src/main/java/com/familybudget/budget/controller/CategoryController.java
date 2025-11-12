package com.familybudget.budget.controller;

@PostMapping("/{categoryId}/transactions")
public ResponseEntity<Transaction> addTransaction(
        @PathVariable Long categoryId,
        @RequestBody Transaction transaction
) {
    Transaction saved = categoryService.addTransaction(categoryId, transaction);
    return ResponseEntity.ok(saved);
}
