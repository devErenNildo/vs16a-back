package com.erenildo.fakebank.service;

import com.erenildo.fakebank.entity.Account;
import com.erenildo.fakebank.entity.User;
import com.erenildo.fakebank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void createAccount(User user) {
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
