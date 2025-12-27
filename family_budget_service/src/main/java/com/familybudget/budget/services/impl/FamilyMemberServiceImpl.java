package com.familybudget.budget.services.impl;

import com.familybudget.budget.dto.request.FamilyMemberRequest;
import com.familybudget.budget.dto.response.FamilyMemberResponse;
import com.familybudget.budget.entity.Family;
import com.familybudget.budget.entity.FamilyMember;
import com.familybudget.budget.exception.FamilyNotFoundException;
import com.familybudget.budget.repository.FamilyMemberRepository;
import com.familybudget.budget.repository.FamilyRepository;
import com.familybudget.budget.services.FamilyMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FamilyMemberServiceImpl implements FamilyMemberService {

    private final FamilyRepository familyRepository;
    private final FamilyMemberRepository familyMemberRepository;

    @Override
    @Transactional
    public FamilyMemberResponse addFamilyMember(Long familyId, FamilyMemberRequest request) {

        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        FamilyMember member = FamilyMember.builder()
                .family(family)
                .userId(request.getUserId())
                .role(request.getRole())
                .build();

        FamilyMember saved = familyMemberRepository.save(member);

        FamilyMemberResponse response = new FamilyMemberResponse();
        response.setId(saved.getId());
        response.setUserId(saved.getUserId());
        response.setRole(saved.getRole());

        return response;
    }

    @Override
    public java.util.List<FamilyMemberResponse> getFamilyMembers(Long familyId) {
        return familyMemberRepository.findByFamilyId(familyId).stream().map(member -> {
            FamilyMemberResponse r = new FamilyMemberResponse();
            r.setId(member.getId());
            r.setUserId(member.getUserId());
            r.setRole(member.getRole());
            return r;
        }).toList();
    }
}
