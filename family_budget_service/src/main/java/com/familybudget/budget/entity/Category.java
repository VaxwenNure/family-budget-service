package com.familybudget.budget.entity;


@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Transaction> transactions = new ArrayList<>();
