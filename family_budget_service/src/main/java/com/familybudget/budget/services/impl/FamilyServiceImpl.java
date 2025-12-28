package com.familybudget.budget.services.impl;

import com.familybudget.budget.dto.request.FamilyRequest;
import com.familybudget.budget.entity.Family;
import com.familybudget.budget.exception.FamilyNotFoundException;
import com.familybudget.budget.repository.FamilyRepository;
import com.familybudget.budget.services.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;


    @Override
    public Family createFamily(FamilyRequest request) {
        Family family = new Family();
        family.setType(request.getType());

        family.setCreatedAt(LocalDateTime.now());

        return familyRepository.save(family);
    }

    @Override
    public List<Family> getAllFamilies() {
        return familyRepository.findAll();
    }

    @Override
    public Family getFamilyById(Long familyId) {
        return familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));
    }

    @Override
    public Family updateFamily(Long familyId, FamilyRequest request) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(familyId));

        if (request.getType() != null) {
            family.setType(request.getType());
        }

        return familyRepository.save(family);
    }
}
