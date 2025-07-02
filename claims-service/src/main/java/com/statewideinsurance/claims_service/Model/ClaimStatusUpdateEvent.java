package com.statewideinsurance.claims_service.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimStatusUpdateEvent {
    private Long claimId;
    private Long userId;
    private String claimType;
    private String claimStatus;
    private String timestamp;
}
