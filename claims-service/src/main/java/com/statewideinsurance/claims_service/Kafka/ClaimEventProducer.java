package com.statewideinsurance.claims_service.Kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.statewideinsurance.claims_service.Model.ClaimStatusUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClaimEventProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private  ObjectMapper objectMapper ;

    public void sendClaimStatusEvent(ClaimStatusUpdateEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("claim-status-update", message);
            log.info("✅ Kafka Event Sent: {}", message);
        } catch (JsonProcessingException e) {
            log.error("❌ Kafka Serialization Failed", e);
        }
    }
}
