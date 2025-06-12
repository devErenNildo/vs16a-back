package com.erenildo.fakebank.service;

import com.erenildo.fakebank.dtos.TransactionHistoryDTO;
import com.erenildo.fakebank.entity.Account;
import com.erenildo.fakebank.entity.Transaction;
import com.erenildo.fakebank.exception.RegraDeNegocioRuntimeExpeptions;
import com.erenildo.fakebank.repository.AccountRepository;
import com.erenildo.fakebank.repository.TrasactionRepository;
import com.erenildo.fakebank.security.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TrasactionRepository trasactionRepository;
    private final TokenUtil tokenUtil;
    private final AccountRepository accountRepository;

    public TransactionService(TrasactionRepository trasactionRepository, TokenUtil tokenUtil, AccountRepository accountRepository) {
        this.trasactionRepository = trasactionRepository;
        this.tokenUtil = tokenUtil;
        this.accountRepository = accountRepository;
    }

    public List<TransactionHistoryDTO> buscarPixRealizados() {

        String idUsuarioOrigem = tokenUtil.getUserIdFromToken();

        List<Transaction> transactions;

        accountRepository.findByUsuarioId(idUsuarioOrigem)
                .orElseThrow(() -> new RegraDeNegocioRuntimeExpeptions("Conta origem não encontrada."));

        transactions = trasactionRepository.findByContaOrigemUsuarioId(idUsuarioOrigem)
                .orElse(transactions = null);


        assert transactions != null;
        return transactions.stream().map(transaction -> new TransactionHistoryDTO(
                transaction.getContaDestino().getUsuario().getFullName(),
                transaction.getContaDestino().getChavePix(),
                transaction.getValor(),
                transaction.getDataHora(),
                transaction.getDescricao()
        )).toList();

    }

    public List<TransactionHistoryDTO> buscarPixRecebidos() {
        String idUsuarioOrigem = tokenUtil.getUserIdFromToken();

        List<Transaction> transactions;

        accountRepository.findByUsuarioId(idUsuarioOrigem)
                .orElseThrow(() -> new RegraDeNegocioRuntimeExpeptions("Conta origem não encontrada."));

        transactions = trasactionRepository.findByContaDestinoUsuarioId(idUsuarioOrigem)
                .orElseThrow(() -> new RegraDeNegocioRuntimeExpeptions("Conta origem não encontrada."));

        assert transactions != null;
        return transactions.stream().map(transaction -> new TransactionHistoryDTO(
                transaction.getContaDestino().getUsuario().getFullName(),
                transaction.getContaDestino().getChavePix(),
                transaction.getValor(),
                transaction.getDataHora(),
                transaction.getDescricao()
        )).toList();
    }

    public List<TransactionHistoryDTO> buscarTodosPixRealizados() {
        return trasactionRepository.findAll().stream().map(transaction -> new TransactionHistoryDTO(
                transaction.getContaDestino().getUsuario().getFullName(),
                transaction.getContaDestino().getChavePix(),
                transaction.getValor(),
                transaction.getDataHora(),
                transaction.getDescricao()
        )).toList();
    }
}
