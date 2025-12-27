package com.familybudget.budget.controller;

import com.familybudget.budget.dto.request.TransactionRequest;
import com.familybudget.budget.dto.response.TransactionResponse;
import com.familybudget.budget.entity.Transaction;
import com.familybudget.budget.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/budget/families/{familyId}/budgets/{budgetId}/categories/{categoryId}/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> addTransaction(
            @PathVariable Long familyId,
            @PathVariable Long budgetId,
            @PathVariable Long categoryId,
            @RequestBody TransactionRequest request
    ) {
        Transaction transaction = transactionService.addTransaction(familyId, budgetId, categoryId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new TransactionResponse(transaction));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactions(
            @PathVariable Long familyId,
            @PathVariable Long budgetId,
            @PathVariable Long categoryId
    ) {
        List<Transaction> list = transactionService.getTransactions(familyId, budgetId, categoryId);

        List<TransactionResponse> response = list.stream()
                .map(TransactionResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // GET ONE TRANSACTION ----------------------------------------------
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> getTransaction(
            @PathVariable Long familyId,
            @PathVariable Long budgetId,
            @PathVariable Long categoryId,
            @PathVariable Long transactionId
    ) {
        Transaction transaction = transactionService.getTransaction(familyId, budgetId, categoryId, transactionId);
        return ResponseEntity.ok(new TransactionResponse(transaction));
    }

    // UPDATE ONE TRANSACTION -------------------------------------------
    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> updateTransaction(
            @PathVariable Long familyId,
            @PathVariable Long budgetId,
            @PathVariable Long categoryId,
            @PathVariable Long transactionId,
            @RequestBody TransactionRequest request
    ) {
        Transaction updated = transactionService.updateTransaction(familyId, budgetId, categoryId, transactionId, request);
        return ResponseEntity.ok(new TransactionResponse(updated));
    }
}
