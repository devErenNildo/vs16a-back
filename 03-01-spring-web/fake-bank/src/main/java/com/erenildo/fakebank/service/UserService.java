package com.erenildo.fakebank.service;

import com.erenildo.fakebank.dtos.ConfirmAccountResponseDTO;
import com.erenildo.fakebank.dtos.CreateAccountResponseDTO;
import com.erenildo.fakebank.dtos.UserCreateAccountDTO;
import com.erenildo.fakebank.entity.TokenConfirmation;
import com.erenildo.fakebank.entity.User;
import com.erenildo.fakebank.exception.EmailCadastradoException;
import com.erenildo.fakebank.repository.TokenConfirmationRepository;
import com.erenildo.fakebank.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenConfirmationRepository tokenConfirmationRepository;
    private final AccountService accountService;
    private final TokenConfirmationService tokenConfirmationService;
    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CreateAccountResponseDTO registerUser(UserCreateAccountDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())){
            User userExistente = userRepository.findByEmail(dto.getEmail());
            if(!userExistente.getContaConfirmada()) {
                throw new RuntimeException("Usuário já tem um cadastro esperando confirmação. Solicite um novo token de confirmação ");
            }
            throw new EmailCadastradoException(dto.getEmail());
        }

        User user = objectMapper.convertValue(dto, User.class);
        user.setContaConfirmada(false);
        user.setId(UUID.randomUUID().toString().replace("-", ""));
        user.setSenha(bCryptPasswordEncoder.encode(user.getSenha()));

        userRepository.save(user);

        tokenConfirmationService.gerarTokenConfirmation(user);

        return new CreateAccountResponseDTO("Um token de confirmação foi enviado para o seu email");
    }

    @Transactional
    public ConfirmAccountResponseDTO confirmUser(String email, String token) {
        TokenConfirmation tokenExistente = tokenConfirmationRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Esse email não possui um token de confirmação"));

        if (tokenExistente.getExpiracao().isBefore(LocalDateTime.now()))
            throw new RuntimeException("O código expirou. Solicite um novo.");

        if (!tokenExistente.getCodigo().equals(token))
            throw new RuntimeException("Código inválido.");

        User user = tokenExistente.getUser();
        user.setContaConfirmada(true);
        userRepository.save(user);

        accountService.createAccount(user);

        tokenConfirmationRepository.delete(tokenExistente);

        return new ConfirmAccountResponseDTO(user.getEmail(), "Sua conta foi criada com sucesso");
    }
}
