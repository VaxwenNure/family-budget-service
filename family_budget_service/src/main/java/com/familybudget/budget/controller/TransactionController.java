package com.familybudget.budget.controller;

@RestController
@RequestMapping("/api/budgets/{budgetId}/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> addTransaction(
            @PathVariable Long budgetId,
            @RequestBody TransactionRequest request
    ) {
        Transaction transaction = transactionService.addTransaction(budgetId, request);
        return ResponseEntity.ok(new TransactionResponse(transaction));
    }
}
