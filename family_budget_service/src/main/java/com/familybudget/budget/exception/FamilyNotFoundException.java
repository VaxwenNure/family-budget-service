package com.familybudget.budget.exception;

public class FamilyNotFoundException extends RuntimeException {
    public FamilyNotFoundException(Long id) {
        super("Family not found with id: " + id);
    }
}
