package com.statewideinsurance.claims_service.Services;

import com.statewideinsurance.claims_service.Model.Claim;

import java.util.List;

public interface ClaimService {
    Claim submitClaim(Claim claim);
    Claim getClaimById(Long claimId);
    List<Claim> getClaimsByUserId(Long userId);
}
