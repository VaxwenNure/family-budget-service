package com.familybudget.budget.repository;

import com.familybudget.budget.entity.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {

    List<FamilyMember> findByFamilyId(Long familyId);

    Optional<FamilyMember> findByIdAndFamilyId(Long id, Long familyId);
}
