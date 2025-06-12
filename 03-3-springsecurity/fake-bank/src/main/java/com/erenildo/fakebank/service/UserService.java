package com.erenildo.fakebank.service;

import com.erenildo.fakebank.dtos.*;
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
import javax.transaction.Transactional;
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
    private final RoleRepository roleRepository;
    private final TokenUtil tokenUtil;

    public UserService(UserRepository userRepository, TokenConfirmationRepository tokenConfirmationRepository, AccountService accountService, TokenConfirmationService tokenConfirmationService, ObjectMapper objectMapper, RoleRepository roleRepository, TokenUtil tokenUtil) {
        this.userRepository = userRepository;
        this.tokenConfirmationRepository = tokenConfirmationRepository;
        this.accountService = accountService;
        this.tokenConfirmationService = tokenConfirmationService;
        this.objectMapper = objectMapper;
        this.roleRepository = roleRepository;
        this.tokenUtil = tokenUtil;
    }

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
        user.setRoles(Set.of(roleBasic));

        userRepository.save(user);

        tokenConfirmationService.gerarTokenConfirmation(user);

        return new CreateAccountResponseDTO("Um token de confirmação foi enviado para o seu email");
    }

    public MsgResponseDefaltDTO atualizarSenha(AtualizarUserDTO dto) {
        String idUser = tokenUtil.getUserIdFromToken();
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RegraDeNegocioRuntimeExpeptions("Usuario não encontrado"));



        userRepository.save(user);

        return new MsgResponseDefaltDTO("Senha atualizada com sucesso");
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
