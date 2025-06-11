package com.erenildo.fakebank.auth.service;

import com.erenildo.fakebank.auth.entity.User;
import com.erenildo.fakebank.auth.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConsumerService {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @KafkaListener(topics = "create-account", groupId = "auth-group")
    public void createAccount(String message) throws Exception {
        User user = objectMapper.readValue(message, User.class);

        userRepository.save(user);
    }
}
