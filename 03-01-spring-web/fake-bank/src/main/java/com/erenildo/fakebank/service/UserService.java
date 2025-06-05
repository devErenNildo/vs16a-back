package com.erenildo.fakebank.service;

import com.erenildo.fakebank.dtos.ConfirmAccountResponseDTO;
import com.erenildo.fakebank.dtos.CreateAccountResponseDTO;
import com.erenildo.fakebank.dtos.UserCreateAccountDTO;
import com.erenildo.fakebank.entity.Role;
import com.erenildo.fakebank.entity.TokenConfirmation;
import com.erenildo.fakebank.entity.User;
import com.erenildo.fakebank.exception.EmailCadastradoException;
import com.erenildo.fakebank.exception.RegraDeNegocioRuntimeExpeptions;
import com.erenildo.fakebank.repository.RoleRepository;
import com.erenildo.fakebank.repository.TokenConfirmationRepository;
import com.erenildo.fakebank.repository.UserRepository;
import com.erenildo.fakebank.security.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
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
    private final RoleRepository roleRepository;
    private final TokenUtil tokenUtil;

    public CreateAccountResponseDTO registerUser(UserCreateAccountDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())){
            User userExistente = userRepository.findByEmail(dto.getEmail());
            if(!userExistente.getContaConfirmada()) {
                throw new RegraDeNegocioRuntimeExpeptions("Usuário já tem um cadastro esperando confirmação. Solicite um novo token de confirmação ");
            }
            throw new EmailCadastradoException(dto.getEmail());
        }

        Role roleBasic = roleRepository.findByRoleId(Role.Values.BASIC.getRoleId());

        User user = objectMapper.convertValue(dto, User.class);
        user.setContaConfirmada(false);
        user.setId(UUID.randomUUID().toString().replace("-", ""));
        user.setSenha(bCryptPasswordEncoder.encode(user.getSenha()));
        user.setRoles(Set.of(roleBasic));

        userRepository.save(user);

        tokenConfirmationService.gerarTokenConfirmation(user);

        return new CreateAccountResponseDTO("Um token de confirmação foi enviado para o seu email");
    }

    @Transactional
    public ConfirmAccountResponseDTO confirmUser(String email, String token) {
        TokenConfirmation tokenExistente = tokenConfirmationRepository.findByUserEmail(email)
                .orElseThrow(() -> new RegraDeNegocioRuntimeExpeptions("Esse email não possui um token de confirmação"));

        if (tokenExistente.getExpiracao().isBefore(LocalDateTime.now()))
            throw new RegraDeNegocioRuntimeExpeptions("O código expirou. Solicite um novo.");

        if (!tokenExistente.getCodigo().equals(token))
            throw new RegraDeNegocioRuntimeExpeptions("Código inválido.");

        User user = tokenExistente.getUser();
        user.setContaConfirmada(true);
        userRepository.save(user);

        accountService.createAccount(user);

        tokenConfirmationRepository.delete(tokenExistente);

        return new ConfirmAccountResponseDTO(user.getEmail(), "Sua conta foi criada com sucesso");
    }
}
