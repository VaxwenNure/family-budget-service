package com.familybudget.budget.controller;

import com.familybudget.budget.dto.request.SalaryRequest;
import com.familybudget.budget.dto.response.SalaryResponse;
import com.familybudget.budget.entity.Salary;
import com.familybudget.budget.services.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/budget/families/{familyId}/salary")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;

    @GetMapping
    public ResponseEntity<List<SalaryResponse>> getSalaries(@PathVariable Long familyId) {
        List<Salary> salaries = salaryService.getSalaries(familyId);

        return ResponseEntity.ok(
                salaries.stream()
                        .map(SalaryResponse::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<SalaryResponse> createSalary(
            @PathVariable Long familyId,
            @RequestBody SalaryRequest request
    ) {
        Salary created = salaryService.createSalary(familyId, request);
        return ResponseEntity.ok(new SalaryResponse(created));
    }

    @GetMapping("/{salaryId}")
    public ResponseEntity<SalaryResponse> getSalaryById(
            @PathVariable Long familyId,
            @PathVariable Long salaryId
    ) {
        Salary salary = salaryService.getSalaryById(familyId, salaryId);
        return ResponseEntity.ok(new SalaryResponse(salary));
    }

    @PutMapping("/{salaryId}")
    public ResponseEntity<SalaryResponse> updateSalary(
            @PathVariable Long familyId,
            @PathVariable Long salaryId,
            @RequestBody SalaryRequest request
    ) {
        Salary updated = salaryService.updateSalary(familyId, salaryId, request);
        return ResponseEntity.ok(new SalaryResponse(updated));
    }
}
