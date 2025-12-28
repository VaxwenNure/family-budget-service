package com.familybudget.budget.controller;

import com.familybudget.budget.dto.request.FamilyMemberRequest;
import com.familybudget.budget.dto.response.FamilyMemberResponse;
import com.familybudget.budget.services.FamilyMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budget/families/{familyId}/members")
@RequiredArgsConstructor
public class FamilyMemberController {

    private final FamilyMemberService familyMemberService;

    @PostMapping
    public ResponseEntity<FamilyMemberResponse> addMember(
            @PathVariable Long familyId,
            @RequestBody FamilyMemberRequest request
    ) {
        return ResponseEntity.ok(familyMemberService.addFamilyMember(familyId, request));
    }

    @GetMapping
    public ResponseEntity<List<FamilyMemberResponse>> getMembers(
            @PathVariable Long familyId
    ) {
        return ResponseEntity.ok(familyMemberService.getFamilyMembers(familyId));
    }

    // NEW: GET ONE MEMBER
    @GetMapping("/{memberId}")
    public ResponseEntity<FamilyMemberResponse> getMemberById(
            @PathVariable Long familyId,
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(familyMemberService.getFamilyMemberById(familyId, memberId));
    }

    // NEW: UPDATE MEMBER
    @PutMapping("/{memberId}")
    public ResponseEntity<FamilyMemberResponse> updateMember(
            @PathVariable Long familyId,
            @PathVariable Long memberId,
            @RequestBody FamilyMemberRequest request
    ) {
        return ResponseEntity.ok(familyMemberService.updateFamilyMember(familyId, memberId, request));
    }
}
