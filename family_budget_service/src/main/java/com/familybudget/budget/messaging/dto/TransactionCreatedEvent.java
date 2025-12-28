package com.familybudget.budget.messaging.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record TransactionCreatedEvent(
        Long familyId,
        Long budgetId,
        Long categoryId,
        Long transactionId,
        BigDecimal amount,
        String description,
        LocalDate date,
        Instant occurredAt
) {}
