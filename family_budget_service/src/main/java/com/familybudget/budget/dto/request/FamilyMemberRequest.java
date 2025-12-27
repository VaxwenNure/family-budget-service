package com.familybudget.budget.dto.request;

import lombok.Data;

@Data
public class FamilyMemberRequest {
    private Long userId;
    private String role;
}
