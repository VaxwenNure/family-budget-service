package com.familybudget.budget.messaging.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public record SalaryUpdatedEvent(
        Long familyId,
        Long salaryId,
        BigDecimal amount,
        String currency,
        LocalDateTime receivedAt,
        String source,
        Instant occurredAt
) {}
