package com.familybudget.budget.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SalaryResponse {
    private Long id;
    private BigDecimal amount;
    private String currency;
    private String source;
    private LocalDateTime receivedAt;
}
