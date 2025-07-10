package com.statewideinsurance.policyservice.controller;


import com.statewideinsurance.policyservice.model.Policy;
import com.statewideinsurance.policyservice.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
public class PolicyController {


    @Autowired
    private  PolicyService policyService;

    @PostMapping
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
        Policy savedPolicy = policyService.createPolicy(policy);
        return new ResponseEntity<>(savedPolicy, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Policy> getPolicyById(@PathVariable Long id) {
        return policyService.getPolicyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Policy> getPoliciesByUserId(@PathVariable Long userId) {
        return policyService.getPoliciesByUserId(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Policy> updatePolicy(@PathVariable Long id) {
        return policyService.getPolicyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
        try {
            policyService.deletePolicy(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }



}
