package com.familybudget.budget.messaging.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record BudgetCreatedEvent(
        Long familyId,
        Long budgetId,
        String name,
        BigDecimal limitAmount,
        Instant occurredAt
) {}
