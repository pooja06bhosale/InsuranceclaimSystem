package com.statewideinsurance.policyservice.repository;

import com.statewideinsurance.policyservice.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
    List<Policy> findByUserId(Long userId);
    List<Policy> findByPolicyNumber(String policyNumber);
    List<Policy> findByPolicyType(String policyType);
    List<Policy> findByPolicyNumberAndPolicyType(String policyNumber, String policyType);
    List<Policy> findByPolicyTypeAndPolicyNumber(String policyType, String policyNumber);
}
