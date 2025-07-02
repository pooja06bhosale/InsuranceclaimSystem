package com.statewideinsurance.claims_service.Controller;

import com.statewideinsurance.claims_service.Model.Claim;
import com.statewideinsurance.claims_service.Repository.ClaimRepository;
import com.statewideinsurance.claims_service.Services.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/claims")
public class ClaimController {
    private final ClaimService claimService;

    @PostMapping
    public ResponseEntity<Claim> submitClaim(@RequestBody Claim claim) {
        return new ResponseEntity<>(claimService.submitClaim(claim), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable Long id) {
        return ResponseEntity.ok(claimService.getClaimById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Claim>> getClaimsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(claimService.getClaimsByUserId(userId));
    }
}
