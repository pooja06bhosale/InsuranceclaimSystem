package com.statewideinsurance.policyservice.service.impl;

import com.statewideinsurance.policyservice.model.Policy;
import com.statewideinsurance.policyservice.service.PolicyNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PolicyServiceNotificationImpl implements PolicyNotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendPolicyCreatedNotification(Policy policy) {
        String message = "Policy created for " + policy.getUserEmail() +
                " with policy number " + policy.getPolicyNumber();
        kafkaTemplate.send("notification-topic", message);
    }
    @Override
    public void sendPolicyUpdatedNotification(Policy policy) {
        String message = "Policy updated for: " + policy.getUserEmail() +
                ", Policy No: " + policy.getPolicyNumber();
        kafkaTemplate.send("notification-topic", message);
    }

    @Override
    public void sendPolicyDeletedNotification(Policy policy) {
        String message = "Policy deleted for: " + policy.getUserEmail() +
                ", Policy No: " + policy.getPolicyNumber();
        kafkaTemplate.send("notification-topic", message);
    }
}
