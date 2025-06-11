package com.erenildo.fakebank.account.repository;

import com.erenildo.fakebank.account.dtos.PixTransactionHistoryDTO;
import com.erenildo.fakebank.account.dtos.relatorios.RelatorioContaSemMovimentacaoDTO;
import com.erenildo.fakebank.account.dtos.relatorios.RelatorioTransactionDTO;
import com.erenildo.fakebank.account.dtos.relatorios.RelatorioUserAccountDTO;
import com.erenildo.fakebank.account.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("""
        SELECT new com.erenildo.fakebank.account.dtos.PixTransactionHistoryDTO(
            t.contaDestino.usuario.fullName,
            t.contaDestino.chavePix,
            t.valor,
            t.dataHora,
            t.descricao
        )
        FROM Transaction t
        WHERE t.contaDestino.usuario.id = :userId
        ORDER BY t.dataHora DESC
    """)
    Page<PixTransactionHistoryDTO> buscarPixRecebidos(@Param("userId") String userId, Pageable pageable);

    @Query("""
        SELECT new com.erenildo.fakebank.account.dtos.PixTransactionHistoryDTO(
            t.contaDestino.usuario.fullName,
            t.contaDestino.chavePix,
            t.valor,
            t.dataHora,
            t.descricao
        )
        FROM Transaction t
        WHERE t.contaOrigem.usuario.id = :userId
        ORDER BY t.dataHora DESC
    """)
    Page<PixTransactionHistoryDTO> buscarPixEnviados(@Param("userId") String userId, Pageable pageable);


    @Query("""
        SELECT new com.erenildo.fakebank.account.dtos.relatorios.RelatorioTransactionDTO(
                u.fullName,
                t.valor,
                t.dataHora,
                t.descricao
        )
        FROM Transaction t
        INNER JOIN t.contaOrigem c
        INNER JOIN c.usuario u
        ORDER BY t.dataHora DESC
    """)
    Page<RelatorioTransactionDTO> relatorioTransactions(Pageable pageable);


    @Query("""
        SELECT new com.erenildo.fakebank.account.dtos.relatorios.RelatorioUserAccountDTO(
                u.fullName,
                c.numeroAgencia,
                c.numeroConta,
                c.chavePix
        )
        FROM User u
        LEFT JOIN u.account c
    """)
    Page<RelatorioUserAccountDTO> relatorioUserAccounts(Pageable pageable);


    @Query("""
        SELECT new com.erenildo.fakebank.account.dtos.relatorios.RelatorioContaSemMovimentacaoDTO(
                u.fullName,
                c.numeroAgencia,
                c.numeroConta,
                c.chavePix
        )
        FROM Account c
        JOIN c.usuario u
        LEFT JOIN Transaction tOrigem ON tOrigem.contaOrigem = c
        LEFT JOIN Transaction tDestino ON tDestino.contaDestino = c
        WHERE tOrigem.id IS NULL AND tDestino.id IS NULL
    """)
    Page<RelatorioContaSemMovimentacaoDTO> relatorioContaSemMovimentacoes(Pageable pageable);
}
