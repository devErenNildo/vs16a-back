package com.erenildo.fakebank.user.service;

import com.erenildo.fakebank.user.clients.AccountClient;
import com.erenildo.fakebank.user.dtos.*;
import com.erenildo.fakebank.user.entity.TokenConfirmation;
import com.erenildo.fakebank.user.entity.User;
import com.erenildo.fakebank.user.exceptions.RegraDeNegocioException;
import com.erenildo.fakebank.user.kafka.Producers;
import com.erenildo.fakebank.user.repository.TokenConfirmationRepository;
import com.erenildo.fakebank.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenConfirmationService tokenConfirmationService;
    private final TokenConfirmationRepository tokenConfirmationRepository;
    private final Producers producers;


    public UserCreateAccontResponseDTO registerUser(UserCreateAccountDTO dto) throws Exception {
        if (userRepository.existsByEmail(dto.getEmail())) {
            User userExistente = userRepository.findByEmail(dto.getEmail());
            if (!userExistente.getContaConfirmada())
                throw new RegraDeNegocioException("Usuário já tem um cadastro esperando confirmação");

            throw new RegraDeNegocioException("O email: " + dto.getEmail() + " já tem um cadastrado ativo");
        }

        User user = objectMapper.convertValue(dto, User.class);
        user.setContaConfirmada(false);
        user.setId(UUID.randomUUID().toString().replace("-", ""));
        user.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));

        userRepository.save(user);

        tokenConfirmationService.gerarTokenConfirmation(user);

        return new UserCreateAccontResponseDTO("Um token de confirmação foi enviado para o seu email");

    }

    public ConfirmAccountResponseDTO confirmUser(ConfirmAccountRequestDTO dto) throws Exception {
        TokenConfirmation tokenExistente = tokenConfirmationRepository.findByUserEmail(dto.getEmail())
                .orElseThrow(() -> new RegraDeNegocioException("Esse email não possui um token de confirmação"));


        if (!tokenExistente.getCodigo().equals(dto.getToken()))
            throw new RegraDeNegocioException("Código inválido.");

        if (tokenExistente.getExpiracao().isBefore(LocalDateTime.now()))
            throw new RegraDeNegocioException("O código expirou. Solicite um novo.");

        User user = tokenExistente.getUser();
        user.setContaConfirmada(true);
        userRepository.save(user);

        producers.createAccount(user);

        return new ConfirmAccountResponseDTO(user.getEmail(), "Sua conta foi criada com sucesso");
    }
}
