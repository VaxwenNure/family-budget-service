package com.familybudget.budget.services.impl;

import com.familybudget.budget.dto.request.FamilyMemberRequest;
import com.familybudget.budget.dto.response.FamilyMemberResponse;
import com.familybudget.budget.entity.Family;
import com.familybudget.budget.entity.FamilyMember;
import com.familybudget.budget.exception.FamilyMemberNotFoundException;
import com.familybudget.budget.exception.FamilyNotFoundException;
import com.familybudget.budget.repository.FamilyMemberRepository;
import com.familybudget.budget.repository.FamilyRepository;
import com.familybudget.budget.services.FamilyMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional(readOnly = true)
    public List<FamilyMemberResponse> getFamilyMembers(Long familyId) {
        // optional: validate family exists
        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        return familyMemberRepository.findByFamilyId(familyId).stream().map(member -> {
            FamilyMemberResponse r = new FamilyMemberResponse();
            r.setId(member.getId());
            r.setUserId(member.getUserId());
            r.setRole(member.getRole());
            return r;
        }).toList();
    }

    // NEW -----------------------------------------------------
    @Override
    @Transactional(readOnly = true)
    public FamilyMemberResponse getFamilyMemberById(Long familyId, Long memberId) {

        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        FamilyMember member = familyMemberRepository.findByIdAndFamilyId(memberId, familyId)
                .orElseThrow(() -> new FamilyMemberNotFoundException(memberId));

        FamilyMemberResponse response = new FamilyMemberResponse();
        response.setId(member.getId());
        response.setUserId(member.getUserId());
        response.setRole(member.getRole());
        return response;
    }

    @Override
    @Transactional
    public FamilyMemberResponse updateFamilyMember(Long familyId, Long memberId, FamilyMemberRequest request) {

        familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        FamilyMember member = familyMemberRepository.findByIdAndFamilyId(memberId, familyId)
                .orElseThrow(() -> new FamilyMemberNotFoundException(memberId));

        // update fields (role is usually what you want to update)
        if (request.getRole() != null) {
            member.setRole(request.getRole());
        }
        // optional: allow userId change (you can remove this if you don’t want it)
        if (request.getUserId() != null) {
            member.setUserId(request.getUserId());
        }

        FamilyMember saved = familyMemberRepository.save(member);

        FamilyMemberResponse response = new FamilyMemberResponse();
        response.setId(saved.getId());
        response.setUserId(saved.getUserId());
        response.setRole(saved.getRole());
        return response;
    }
}
