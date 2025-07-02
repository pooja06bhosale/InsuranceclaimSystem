package com.statewideinsurance.policyservice.service;

import com.statewideinsurance.policyservice.model.Policy;

public interface PolicyNotificationService {
    void sendPolicyCreatedNotification(Policy policy);
    void sendPolicyUpdatedNotification(Policy policy);
    void sendPolicyDeletedNotification(Policy policy);
}
