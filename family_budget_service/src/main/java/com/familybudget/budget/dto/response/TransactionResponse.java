package com.familybudget.budget.dto.response;

import com.familybudget.budget.entity.Transaction;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionResponse {

    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private Long categoryId;

    public TransactionResponse(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
        if (transaction.getCategory() != null) {
            this.categoryId = transaction.getCategory().getId();
        }
    }

    public TransactionResponse() {}
}
