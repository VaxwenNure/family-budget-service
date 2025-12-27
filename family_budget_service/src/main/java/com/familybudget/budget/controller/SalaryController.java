package com.familybudget.budget.controller;

import com.familybudget.budget.dto.request.SalaryRequest;
import com.familybudget.budget.dto.response.SalaryResponse;
import com.familybudget.budget.services.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budget/families/{familyId}/salary")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;

    @PostMapping
    public ResponseEntity<SalaryResponse> addSalary(
            @PathVariable Long familyId,
            @RequestBody SalaryRequest request
    ) {
        return ResponseEntity.ok(salaryService.addSalary(familyId, request));
    }

    @GetMapping
    public ResponseEntity<List<SalaryResponse>> getSalaries(
            @PathVariable Long familyId
    ) {
        return ResponseEntity.ok(salaryService.getSalaries(familyId));
    }
}
