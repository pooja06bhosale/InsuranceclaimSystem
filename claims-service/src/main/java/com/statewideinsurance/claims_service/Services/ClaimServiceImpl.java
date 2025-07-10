package com.statewideinsurance.claims_service.Services;

import com.statewideinsurance.claims_service.Kafka.ClaimEventProducer;
import com.statewideinsurance.claims_service.Model.Claim;
import com.statewideinsurance.claims_service.Model.ClaimStatus;
import com.statewideinsurance.claims_service.Model.ClaimStatusUpdateEvent;
import com.statewideinsurance.claims_service.Repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private  ClaimRepository claimRepository;
    @Autowired
    private  ClaimEventProducer claimEventProducer;

    @Override
    public Claim submitClaim(Claim claim) {
        claim.setClaimStatus(ClaimStatus.SUBMITTED);
        claim.setLastUpdated(LocalDate.now());
        Claim savedClaim = claimRepository.save(claim);

        // 🔁 Send Kafka Event after saving claim
        ClaimStatusUpdateEvent event = new ClaimStatusUpdateEvent(
                savedClaim.getId(),
                savedClaim.getUserId(),
                savedClaim.getClaimType().toString(),
                savedClaim.getClaimStatus().toString(),
                LocalDate.now().toString()
        );

        claimEventProducer.sendClaimStatusEvent(event);

        return savedClaim;
    }

    @Override
    public Claim getClaimById(Long claimId) {
        return claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
    }

    @Override
    public List<Claim> getClaimsByUserId(Long userId) {
        return claimRepository.findByUserId(userId);
    }
}