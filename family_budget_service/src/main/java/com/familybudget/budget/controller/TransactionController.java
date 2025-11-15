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
@RequestMapping("/budgets/{budgetId}/categories/{categoryId}/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> addTransaction(
            @PathVariable Long budgetId,
            @PathVariable Long categoryId,
            @RequestBody TransactionRequest request
    ) {
        Transaction transaction = transactionService.addTransaction(budgetId, categoryId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new TransactionResponse(transaction));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactions(
            @PathVariable Long budgetId,
            @PathVariable Long categoryId
    ) {
        List<Transaction> list = transactionService.getTransactions(budgetId, categoryId);

        List<TransactionResponse> response = list.stream()
                .map(TransactionResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
