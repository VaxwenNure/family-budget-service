package com.familybudget.budget.services.impl;

import com.familybudget.budget.dto.request.FamilyRequest;
import com.familybudget.budget.entity.Family;
import com.familybudget.budget.exception.FamilyNotFoundException;
import com.familybudget.budget.messaging.dto.FamilyCreatedEvent;
import com.familybudget.budget.messaging.dto.FamilyUpdatedEvent;
import com.familybudget.budget.messaging.publisher.DomainEventPublisher;
import com.familybudget.budget.repository.FamilyRepository;
import com.familybudget.budget.services.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;
    private final DomainEventPublisher publisher;

    @Override
    public Family createFamily(FamilyRequest request) {
        Family family = new Family();
        family.setType(request.getType());
        family.setCreatedAt(LocalDateTime.now());

        Family saved = familyRepository.save(family);

        publisher.publish(
                "family.created",
                new FamilyCreatedEvent(
                        saved.getId(),
                        saved.getType(),
                        saved.getCreatedAt().toString(),
                        Instant.now()
                )
        );

        return saved;
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

        Family saved = familyRepository.save(family);

        publisher.publish(
                "family.updated",
                new FamilyUpdatedEvent(
                        saved.getId(),
                        saved.getType(),
                        saved.getCreatedAt().toString(),
                        Instant.now()
                )
        );

        return saved;
    }
}
