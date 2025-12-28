package com.familybudget.budget.messaging.dto;

import java.time.Instant;

public record CategoryUpdatedEvent(
        Long familyId,
        Long budgetId,
        Long categoryId,
        String name,
        String color,
        Instant occurredAt
) {}
