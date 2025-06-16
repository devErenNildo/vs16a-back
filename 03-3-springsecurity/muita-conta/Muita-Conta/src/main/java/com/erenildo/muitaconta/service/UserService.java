package com.erenildo.muitaconta.service;

import com.erenildo.muitaconta.dtos.user.ConfirmarContaRequestDTO;
import com.erenildo.muitaconta.dtos.user.ConfirmarContaResponseDTO;
import com.erenildo.muitaconta.dtos.user.CriarContaRequestDTO;
import com.erenildo.muitaconta.dtos.user.CriarContaResponseDTO;
import com.erenildo.muitaconta.entity.TokenConfirmacao;
import com.erenildo.muitaconta.entity.User;
import com.erenildo.muitaconta.exceptions.RegraDeNegocioException;
import com.erenildo.muitaconta.repository.TokenConfirmacaoRepository;
import com.erenildo.muitaconta.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final TokenConfirmacaoService tokenConfirmacaoService;
    private final TokenConfirmacaoRepository tokenConfirmacaoRepository;
    private final LoginService loginService;

    public UserService(
            UserRepository userRepository,
            ObjectMapper objectMapper,
            TokenConfirmacaoService tokenConfirmacaoService,
            TokenConfirmacaoRepository tokenConfirmacaoRepository,
            LoginService loginService
    ) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.tokenConfirmacaoService = tokenConfirmacaoService;
        this.tokenConfirmacaoRepository = tokenConfirmacaoRepository;
        this.loginService = loginService;
    }


    public CriarContaResponseDTO criarConta (CriarContaRequestDTO dto) throws Exception {
        if (userRepository.existsByEmail(dto.getEmail())) {
            User userExistente = userRepository.findByEmail(dto.getEmail())
                    .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado, tente novamente"));

            if (!userExistente.getContaConfirmada()) {
                throw new RegraDeNegocioException("Usuário já tem um cadastro esperando confirmação. Solicite um novo token de confirmação");
            }

            throw new RegraDeNegocioException("O email " + dto.getEmail() + " já possui um cadastro ativo");
        }

        User user = objectMapper.convertValue(dto, User.class);
        user.setContaConfirmada(false);
        user.setId(UUID.randomUUID().toString().replace("-", ""));
        user.setDataCadastro(LocalDateTime.now());

        userRepository.save(user);

        tokenConfirmacaoService.gerarTokenConfirmacao(user);

        return new CriarContaResponseDTO("Um token de confirmação foi enviado para o seu email");
    }

    public ConfirmarContaResponseDTO confirmarConta (ConfirmarContaRequestDTO dto) throws Exception {
        TokenConfirmacao tokenExistente = tokenConfirmacaoRepository.findByUserEmail(dto.getEmail())
                .orElseThrow(() -> new RegraDeNegocioException("Esse email não possui um token de confirmação"));

        if (!tokenExistente.getCodigo().equals(dto.getToken()))
            throw new RegraDeNegocioException("Código inválido.");

        if (tokenExistente.getExpiracao().isBefore(LocalDateTime.now()))
            throw new RegraDeNegocioException("O código expirou. Solicite um novo.");

        User user = tokenExistente.getUser();
        user.setContaConfirmada(true);
        userRepository.save(user);

        loginService.criarLogin(user, dto.getSenha());

        return new ConfirmarContaResponseDTO(user.getEmail(), "Sua conta foi confirmada com sucesso");
    }
}
