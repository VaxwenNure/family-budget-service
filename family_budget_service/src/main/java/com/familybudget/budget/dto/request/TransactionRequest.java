package com.familybudget.budget.dto.request;

@Data
public class TransactionRequest {
    private Long categoryId;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
}
