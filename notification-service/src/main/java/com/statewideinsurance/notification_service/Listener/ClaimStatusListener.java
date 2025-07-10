package com.statewideinsurance.notification_service.Listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.statewideinsurance.notification_service.DTO.ClaimStatusUpdateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClaimStatusListener {

    @Autowired
    private ObjectMapper objectMapper ;
//consumer


    @KafkaListener(topics = "claim-status-update",groupId = "notification-group")
    public void listen(String message) {
        try{
            ClaimStatusUpdateEvent event = objectMapper.readValue(message,ClaimStatusUpdateEvent.class);
            log.info("Received Claim Ststus Update: {}",event);
        }catch (Exception e){
            log.error("Failed to parse kafka message",e);
        }
    }
}
