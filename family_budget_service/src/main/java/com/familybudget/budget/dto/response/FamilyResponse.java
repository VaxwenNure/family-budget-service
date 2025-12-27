package com.familybudget.budget.dto.response;

import lombok.Data;
import com.familybudget.budget.entity.Family;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FamilyResponse {
    private Long id;
    private String type;
    private LocalDateTime createdAt;

    public FamilyResponse(Family family) {
        this.id = family.getId();
        this.createdAt = family.getCreatedAt();
        this.type = family.getType();
    }

    private List<FamilyMemberResponse> members;
    private List<SalaryResponse> salaries;
}
