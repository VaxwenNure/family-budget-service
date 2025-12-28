package com.familybudget.budget.messaging.dto;

import java.time.Instant;

public record CategoryCreatedEvent(
        Long familyId,
        Long budgetId,
        Long categoryId,
        String name,
        String color,
        Instant occurredAt
) {}
