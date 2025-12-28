package com.familybudget.budget.controller;

import com.familybudget.budget.dto.request.FamilyRequest;
import com.familybudget.budget.dto.response.FamilyResponse;
import com.familybudget.budget.entity.Family;
import com.familybudget.budget.services.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/budget/families")
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyService familyService;


    @PostMapping
    public ResponseEntity<FamilyResponse> createFamily(@RequestBody FamilyRequest request) {
        Family created = familyService.createFamily(request);
        return ResponseEntity.ok(new FamilyResponse(created));
    }

    // GET ALL FAMILIES ----------------------------------------------
    @GetMapping
    public ResponseEntity<List<FamilyResponse>> getAllFamilies() {
        List<Family> families = familyService.getAllFamilies();

        List<FamilyResponse> response = families.stream()
                .map(FamilyResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // GET ONE FAMILY BY ID -------------------------------------------
    @GetMapping("/{familyId}")
    public ResponseEntity<FamilyResponse> getFamilyById(@PathVariable Long familyId) {
        Family family = familyService.getFamilyById(familyId);
        return ResponseEntity.ok(new FamilyResponse(family));
    }

    // UPDATE FAMILY ---------------------------------------------------
    @PutMapping("/{familyId}")
    public ResponseEntity<FamilyResponse> updateFamily(@PathVariable Long familyId,
                                                       @RequestBody FamilyRequest request) {
        Family updated = familyService.updateFamily(familyId, request);
        return ResponseEntity.ok(new FamilyResponse(updated));
    }
}
