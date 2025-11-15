package com.familybudget.budget.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * The limit/allowed amount for this budget
     */
    private BigDecimal limitAmount = BigDecimal.ZERO;

    /**
     * How much is spent inside all categories
     */
    private BigDecimal spent = BigDecimal.ZERO;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>();
}
