package com.erenildo.fakebank.service;

import com.erenildo.fakebank.entity.Account;
import com.erenildo.fakebank.entity.Transaction;
import com.erenildo.fakebank.repository.AccountRepository;
import com.erenildo.fakebank.repository.TrasactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PixService {

    private final AccountRepository accountRepository;
    private final TrasactionRepository trasactionRepository;

    @Transactional
    public void realizarTransacaoPix(String chaveDestino, BigDecimal valor, String idUsuarioOrigem, String descricao) {
        Account origem = accountRepository.findByUsuarioId(idUsuarioOrigem)
                .orElseThrow(() -> new RuntimeException("Conta origem não encontrada."));

        Account destino = accountRepository.findByChavePix(chaveDestino)
                .orElseThrow(() -> new RuntimeException("Chave pix não encontrada ou inexistente."));

        if (origem.getId().equals(destino.getId())) {
            throw new RuntimeException("Não é possível transferir para a mesma conta.");
        }

        if (origem.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente.");
        }

        origem.setSaldo(origem.getSaldo().subtract(valor));
        destino.setSaldo(destino.getSaldo().add(valor));
        accountRepository.save(origem);
        accountRepository.save(destino);

        Transaction transacao = new Transaction();
        transacao.setContaOrigem(origem);
        transacao.setContaDestino(destino);
        transacao.setValor(valor);
        transacao.setDescricao("Transferência PIX");
        transacao.setDataHora(LocalDateTime.now());

        trasactionRepository.save(transacao);

    }
}
