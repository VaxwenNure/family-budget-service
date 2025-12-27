package com.familybudget.budget.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionRequest {

    private BigDecimal amount;
    private String description;
    private LocalDate date;

}
