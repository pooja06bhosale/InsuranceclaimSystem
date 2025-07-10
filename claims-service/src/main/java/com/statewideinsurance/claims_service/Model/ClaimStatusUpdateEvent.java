package com.statewideinsurance.claims_service.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimStatusUpdateEvent {
    // put this class into lisner as dto
    private Long claimId;
    private Long userId;
    private String claimType;
    private String claimStatus;
    private String timestamp;
}
