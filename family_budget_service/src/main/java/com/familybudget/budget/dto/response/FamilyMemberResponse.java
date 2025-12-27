package com.familybudget.budget.dto.response;

import lombok.Data;

@Data
public class FamilyMemberResponse {
    private Long id;
    private Long userId;
    private String role;
}
