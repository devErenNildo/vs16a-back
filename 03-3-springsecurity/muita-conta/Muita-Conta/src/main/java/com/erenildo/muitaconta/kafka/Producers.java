package com.erenildo.muitaconta.kafka;

import com.erenildo.muitaconta.dtos.email.EmailTokenRequestDTO;
import com.erenildo.muitaconta.exceptions.RegraDeNegocioException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producers {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public Producers(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void enviarTokenConfirmacao(EmailTokenRequestDTO dto) throws Exception {
        try {
            String json = objectMapper.writeValueAsString(dto);
            kafkaTemplate.send("send-token", json);
        } catch (JsonProcessingException e) {
            throw new RegraDeNegocioException("Erro ao enviar o token de confirmacao, tente novamente");
        }
    }
}
