package com.familybudget.budget.exception;

public class SalaryNotFoundException extends RuntimeException {
    public SalaryNotFoundException(Long id) {
        super("Salary not found with id = " + id);
    }
}
