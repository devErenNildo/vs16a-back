package com.erenildo.fakebank.service;

import com.erenildo.fakebank.dtos.ConsultaSaldoResponseDTO;
import com.erenildo.fakebank.entity.Account;
import com.erenildo.fakebank.entity.User;
import com.erenildo.fakebank.exception.RegraDeNegocioRuntimeExpeptions;
import com.erenildo.fakebank.repository.AccountRepository;
import com.erenildo.fakebank.security.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TokenUtil tokenUtil;

    public void createAccount(User user) {
        Account account = new Account();
        account.setUsuario(user);
        account.setSaldo(BigDecimal.valueOf(10_000));
        account.setCriadaEm(LocalDateTime.now());
        account.setNumeroConta(gerarNumeroConta());
        account.setNumeroAgencia(gerarNumeroAgencia());

        accountRepository.save(account);
    }

    public ConsultaSaldoResponseDTO saldo() {
        String idUser = tokenUtil.getUserIdFromToken();

        Account contaUser = accountRepository.findByUsuarioId(idUser)
                .orElseThrow(() -> new RegraDeNegocioRuntimeExpeptions("Usuario não encontrado"));

        return new ConsultaSaldoResponseDTO(contaUser.getSaldo());
    }

    private String gerarNumeroConta() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    private String gerarNumeroAgencia() {
        return String.format("%04d", new Random().nextInt(9999));
    }
}
