package com.erenildo.fakebank.account.service;

import com.erenildo.fakebank.account.dtos.PixTransacaoRequestDTO;
import com.erenildo.fakebank.account.dtos.PixTransacaoResponseDTO;
import com.erenildo.fakebank.account.dtos.relatorios.RelatorioContaSemMovimentacaoDTO;
import com.erenildo.fakebank.account.entity.Account;
import com.erenildo.fakebank.account.entity.User;
import com.erenildo.fakebank.account.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class AccountConsumerService {

    private final AccountRepository accountRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "create-account", groupId = "account-group")
    public void createAccount(String msg) throws Exception {
        User user = objectMapper.readValue(msg, User.class);

        Account account = new Account();
        account.setUsuario(user);
        account.setSaldo(BigDecimal.valueOf(10_000));
        account.setCriadaEm(LocalDateTime.now());
        account.setNumeroConta(gerarNumeroConta());
        account.setNumeroAgencia(gerarNumeroAgencia());

        accountRepository.save(account);
    }

    private String gerarNumeroConta() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    private String gerarNumeroAgencia() {
        return String.format("%04d", new Random().nextInt(9999));
    }
}
