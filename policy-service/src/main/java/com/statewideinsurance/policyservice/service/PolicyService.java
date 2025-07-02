package com.statewideinsurance.policyservice.service;

import com.statewideinsurance.policyservice.model.Policy;

import java.util.List;
import java.util.Optional;

public interface PolicyService {
    Policy createPolicy(Policy policy);
    List<Policy> getAllPolicies();
    Optional<Policy> getPolicyById(Long id);
    List<Policy> getPoliciesByUserId(Long userId);
    Policy updatePolicy(Long id, Policy updatedPolicy);  // ✅ Add this

    void deletePolicy(Long id);

}
