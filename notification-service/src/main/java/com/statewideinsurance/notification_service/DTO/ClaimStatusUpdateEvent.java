package com.statewideinsurance.notification_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimStatusUpdateEvent {
    private Long claimId;
    private Long userId;
    private String claimType;
    private String claimStatus;
    private String timestamp;

}
