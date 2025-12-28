package com.familybudget.budget.messaging.dto;

import java.time.Instant;

public record FamilyCreatedEvent(
        Long familyId,
        String name,
        String type,
        Instant occurredAt
) {}
