package com.erenildo.fakebank.account.service;

import com.erenildo.fakebank.account.dtos.*;
import com.erenildo.fakebank.account.entity.Account;
import com.erenildo.fakebank.account.entity.Transaction;
import com.erenildo.fakebank.account.entity.User;
import com.erenildo.fakebank.account.exceptions.RegraDeNegocioException;
import com.erenildo.fakebank.account.repository.AccountRepository;
import com.erenildo.fakebank.account.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public PixTransacaoResponseDTO realizarPix (PixTransacaoRequestDTO dto, String idUser) throws Exception{
        Account origem = accountRepository.findByUsuarioId(idUser)
                .orElseThrow(() -> new RegraDeNegocioException("Conta origem não encontrada."));

        Account destino = accountRepository.findByChavePix(dto.getPixDestinatario())
                .orElseThrow(() -> new RegraDeNegocioException("Chave pix não encontrada ou inexistente."));

        if (origem.getId().equals(destino.getId())) {
            throw new RegraDeNegocioException("Não é possível transferir para a sua conta.");
        }

        if (origem.getSaldo().compareTo(dto.getValor()) < 0) {
            throw new RegraDeNegocioException("Saldo insuficiente.");
        }

        BigDecimal taxa = dto.getValor().multiply(BigDecimal.valueOf(0.15));
        BigDecimal valorLiquido = dto.getValor().subtract(taxa);

        origem.setSaldo(origem.getSaldo().subtract(dto.getValor()));
        destino.setSaldo(destino.getSaldo().add(valorLiquido));
        accountRepository.save(origem);
        accountRepository.save(destino);

        Transaction transacao = new Transaction();
        transacao.setContaOrigem(origem);
        transacao.setContaDestino(destino);
        transacao.setValor(dto.getValor());
        transacao.setDescricao(dto.getDescricao());
        transacao.setDataHora(LocalDateTime.now());

        transactionRepository.save(transacao);

        return new PixTransacaoResponseDTO("Pix realizado com sucesso", destino.getUsuario().getFullName(), dto.getValor(), destino.getChavePix());
    }

    @Transactional
    public RegisterPixResponseDTO registerPix (RegisterPixRequestDTO dto, String idUser) throws Exception {
        Account conta = accountRepository.findByUsuarioId(idUser)
                .orElseThrow(() -> new RegraDeNegocioException("Conta não encontrada"));

        if (conta.getChavePix() != null ) {
            throw new RegraDeNegocioException("Essa conta já possui uma chave PIX.");
        }

        if ( accountRepository.existsByChavePix(dto.getChavePix()) ) {
            throw new RegraDeNegocioException("Essa chave PIX já está em uso por outra conta.");
        }

        User usuario = conta.getUsuario();

        boolean chaveValida = dto.getChavePix().equalsIgnoreCase(usuario.getEmail())
                || dto.getChavePix().equals(usuario.getCpf())
                || dto.getChavePix().equals(usuario.getCelular());

        if (!chaveValida) {
            throw new RegraDeNegocioException("A chave PIX deve ser seu email, CPF ou celular.");
        }

        conta.setChavePix(dto.getChavePix());
        accountRepository.save(conta);

        return new RegisterPixResponseDTO("Chave pix: " + dto.getChavePix() + " cadastrada com sucesso.");
    }

    public ConsultaSaldoResponseDTO saldo (String idUser) throws Exception {
        Account contaUser = accountRepository.findByUsuarioId(idUser)
                .orElseThrow(() -> new RegraDeNegocioException("Conta não encontrada"));

        return new ConsultaSaldoResponseDTO(contaUser.getUsuario().getFullName(), contaUser.getSaldo());
    }

}
