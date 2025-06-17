package com.erenildo.muitaconta.service;

import com.erenildo.muitaconta.dtos.email.EmailTokenRequestDTO;
import com.erenildo.muitaconta.entity.TokenConfirmacao;
import com.erenildo.muitaconta.entity.User;
import com.erenildo.muitaconta.exceptions.RegraDeNegocioException;
import com.erenildo.muitaconta.kafka.Producers;
import com.erenildo.muitaconta.repository.TokenConfirmacaoRepository;
import com.erenildo.muitaconta.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class TokenConfirmacaoService {

    private final TokenConfirmacaoRepository tokenConfirmacaoRepository;
    private final Producers producers;
    private final UserRepository userRepository;

    public TokenConfirmacaoService(
            TokenConfirmacaoRepository tokenConfirmacaoRepository,
            Producers producers,
            UserRepository userRepository
    ) {
        this.tokenConfirmacaoRepository = tokenConfirmacaoRepository;
        this.producers = producers;
        this.userRepository = userRepository;
    }

    public void gerarTokenConfirmacao(User user) throws Exception {

        if (!userRepository.existsByEmail(user.getEmail()))
            throw new RegraDeNegocioException("Usuário não encontrado tente novamente");

        tokenConfirmacaoRepository.findByUserEmail(user.getEmail())
                .ifPresent(tokenConfirmacaoRepository::delete);


        String codigo = String.format("%06d", new Random().nextInt(999999));

        TokenConfirmacao tokenConfirmation = new TokenConfirmacao();
        tokenConfirmation.setCodigo(codigo);
        tokenConfirmation.setUser(user);
        tokenConfirmation.setExpiracao(LocalDateTime.now().plusMinutes(10));
        tokenConfirmacaoRepository.save(tokenConfirmation);

        producers.enviarTokenConfirmacao(new EmailTokenRequestDTO(user.getEmail(), codigo, user.getNome()));
    }
}
