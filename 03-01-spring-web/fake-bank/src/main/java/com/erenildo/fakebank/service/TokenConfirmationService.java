package com.erenildo.fakebank.service;

import com.erenildo.fakebank.entity.TokenConfirmation;
import com.erenildo.fakebank.entity.User;
import com.erenildo.fakebank.repository.TokenConfirmationRepository;
import com.erenildo.fakebank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TokenConfirmationService {
    private final TokenConfirmationRepository tokenConfirmationRepository;
    private final EmailService emailService;
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
        emailService.sendEmailConfirmationToken(user.getFullName(), tokenConfirmation.getCodigo(), user.getEmail());
    }

    public void reenviarToken (String email) {
        if (!userRepository.existsByEmail(email))
            throw new RuntimeException("Email não encontrado.");

        User userEncontrado = userRepository.findByEmail(email);

        if (userEncontrado.getContaConfirmada())
            throw new RuntimeException("Conta já está confirmada. Faça login.");

        gerarTokenConfirmation(userEncontrado);
    }
}
