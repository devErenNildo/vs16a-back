package com.erenildo.fakebank.user.kafka;

import com.erenildo.fakebank.user.dtos.EmailDTO;
import com.erenildo.fakebank.user.dtos.UserCreatedEvent;
import com.erenildo.fakebank.user.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producers {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendEmail(EmailDTO dto) {
        try {
            String json = objectMapper.writeValueAsString(dto);
            kafkaTemplate.send("send-email", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void createAccount(User dto) {
        UserCreatedEvent event = objectMapper.convertValue(dto, UserCreatedEvent.class);
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("create-account", json);
        } catch ( JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
