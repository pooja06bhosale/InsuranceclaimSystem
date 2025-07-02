package com.statewideinsurance.policyservice.service.impl;

import com.statewideinsurance.policyservice.model.Policy;
import com.statewideinsurance.policyservice.repository.PolicyRepository;
import com.statewideinsurance.policyservice.service.PolicyNotificationService;
import com.statewideinsurance.policyservice.service.PolicyService;
import com.statewideinsurance.policyservice.service.PolicyValidationservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {
    private final PolicyRepository policyRepository;
    private final PolicyValidationservice policyValidationservice;
    private final PolicyNotificationService policyNotificationService;


    @Override
    public Policy createPolicy(Policy policy) {

        //validate policy
        policyValidationservice.validatePolicy(policy);

        //save the policy
        Policy savedPolicy = policyRepository.save(policy);

        // send kafka message
        policyNotificationService.sendPolicyCreatedNotification(savedPolicy);

        return savedPolicy;
    }
    @Override
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    @Override
    public Optional<Policy> getPolicyById(Long id) {
        return policyRepository.findById(id);
    }

    @Override
    public List<Policy> getPoliciesByUserId(Long userId) {
        return policyRepository.findByUserId(userId);
    }

    @Override
    public Policy updatePolicy(Long id, Policy updatedPolicy) {
        Policy existingPolicy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found with id: " + id));

        existingPolicy.setPolicyNumber(updatedPolicy.getPolicyNumber());
        existingPolicy.setPolicyType(updatedPolicy.getPolicyType());
        existingPolicy.setPremiumAmount(updatedPolicy.getPremiumAmount());
        existingPolicy.setStartDate(updatedPolicy.getStartDate());
        existingPolicy.setEndDate(updatedPolicy.getEndDate());
        existingPolicy.setUserEmail(updatedPolicy.getUserEmail());

        Policy saved = policyRepository.save(existingPolicy);

        // Delegate to notification service
        policyNotificationService.sendPolicyUpdatedNotification(saved);

        return saved;
    }

    @Override
    public void deletePolicy(Long id) {
        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found with id: " + id));

        policyRepository.deleteById(id);

        // Delegate to notification service
        policyNotificationService.sendPolicyDeletedNotification(policy);
    }

}
