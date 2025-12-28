package com.familybudget.budget.dto.response;

import com.familybudget.budget.entity.Salary;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SalaryResponse {

    private Long id;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime receivedAt;
    private String source;
    private Long familyId;

    public SalaryResponse(Salary salary) {
        this.id = salary.getId();
        this.amount = salary.getAmount();
        this.currency = salary.getCurrency();
        this.receivedAt = salary.getReceivedAt();
        this.source = salary.getSource();
        this.familyId = salary.getFamily().getId();
    }
}