package com.familybudget.budget.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SalaryRequest {
    private BigDecimal amount;
    private String currency;
    private LocalDateTime receivedAt;
    private String source;
}
