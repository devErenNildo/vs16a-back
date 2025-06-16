package com.erenildo.muitaconta.messages.kafka;

import com.erenildo.muitaconta.messages.dtos.EmailTokenDTO;
import com.erenildo.muitaconta.messages.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumers {

    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public Consumers(
            ObjectMapper objectMapper,
            EmailService emailService
    ) {
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "send-token", groupId = "email-group")
    public void emailTokenConsumer(String message) throws Exception {
        EmailTokenDTO dto = objectMapper.readValue(message, EmailTokenDTO.class);
        emailService.enviarTokenConfirmacao(dto);
    }
}
