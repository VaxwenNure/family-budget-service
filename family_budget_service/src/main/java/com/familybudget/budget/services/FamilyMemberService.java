package com.familybudget.budget.services;

import com.familybudget.budget.dto.request.FamilyMemberRequest;
import com.familybudget.budget.dto.response.FamilyMemberResponse;

import java.util.List;

public interface FamilyMemberService {
    FamilyMemberResponse addFamilyMember(Long familyId, FamilyMemberRequest request);
    List<FamilyMemberResponse> getFamilyMembers(Long familyId);
}
