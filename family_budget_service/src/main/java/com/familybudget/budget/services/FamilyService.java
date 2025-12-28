package com.familybudget.budget.services;

import com.familybudget.budget.dto.request.FamilyRequest;
import com.familybudget.budget.entity.Family;

import java.util.List;

public interface FamilyService {

    Family createFamily(FamilyRequest request);
    
    List<Family> getAllFamilies();

    Family getFamilyById(Long familyId);

    Family updateFamily(Long familyId, FamilyRequest request);
}
