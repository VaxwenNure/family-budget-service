package com.familybudget.budget.messaging.dto;

import java.time.Instant;

public record FamilyMemberCreatedEvent(
        Long familyId,
        Long memberId,
        String name,
        Instant occurredAt
) {}
