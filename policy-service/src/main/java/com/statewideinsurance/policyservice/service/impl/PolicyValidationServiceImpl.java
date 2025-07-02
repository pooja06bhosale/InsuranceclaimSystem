package com.statewideinsurance.policyservice.service.impl;

import com.statewideinsurance.policyservice.model.Policy;
import com.statewideinsurance.policyservice.service.PolicyValidationservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PolicyValidationServiceImpl implements PolicyValidationservice {

    @Override
    public void validatePolicy(Policy policy) {
        if (policy.getPremiumAmount() <= 0){
            throw new IllegalArgumentException("Premium amount must positive");
        }
        if(policy.getStartDate().isAfter(policy.getEndDate())){
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
    }
}
