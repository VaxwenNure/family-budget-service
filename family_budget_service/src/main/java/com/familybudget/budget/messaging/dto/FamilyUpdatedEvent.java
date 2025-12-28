package com.familybudget.budget.messaging.dto;

import java.time.Instant;

public record FamilyUpdatedEvent(
        Long familyId,
        String name,
        String type,
        Instant occurredAt
) {}
