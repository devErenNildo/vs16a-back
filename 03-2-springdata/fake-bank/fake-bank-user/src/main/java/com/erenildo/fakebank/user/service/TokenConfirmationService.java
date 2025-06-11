package com.erenildo.fakebank.user.service;

import com.erenildo.fakebank.user.dtos.EmailDTO;
import com.erenildo.fakebank.user.entity.TokenConfirmation;
import com.erenildo.fakebank.user.entity.User;
import com.erenildo.fakebank.user.kafka.Producers;
import com.erenildo.fakebank.user.repository.TokenConfirmationRepository;
import com.erenildo.fakebank.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TokenConfirmationService {

    private final TokenConfirmationRepository tokenConfirmationRepository;
    private final Producers producers;
    private final UserRepository userRepository;

    public void gerarTokenConfirmation(User user) {
        tokenConfirmationRepository.findByUserEmail(user.getEmail())
                .ifPresent(tokenConfirmationRepository::delete);


        String codigo = String.format("%06d", new Random().nextInt(999999));

        TokenConfirmation tokenConfirmation = new TokenConfirmation();
        tokenConfirmation.setCodigo(codigo);
        tokenConfirmation.setUser(user);
        tokenConfirmation.setExpiracao(LocalDateTime.now().plusMinutes(10));
        tokenConfirmationRepository.save(tokenConfirmation);

        producers.sendEmail(new EmailDTO(user.getEmail(), codigo, user.getFullName()));
    }

}
