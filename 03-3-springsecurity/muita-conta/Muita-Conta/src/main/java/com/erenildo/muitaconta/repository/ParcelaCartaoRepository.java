package com.erenildo.muitaconta.repository;

import com.erenildo.muitaconta.dtos.cartao.ItemFaturaDTO;
import com.erenildo.muitaconta.entity.ParcelaCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.YearMonth;
import java.util.List;

public interface ParcelaCartaoRepository extends JpaRepository<ParcelaCartao, Long> {

    @Query("""
        SELECT new com.erenildo.muitaconta.dtos.cartao.ItemFaturaDTO(
            g.descricao,
            p.valorParcela,
            p.competencia
        )
        FROM ParcelaCartao p
        JOIN p.gastoCartao g
        WHERE p.competencia = :competencia
          AND (:idCartao IS NULL OR g.cartaoCredito.id = :idCartao)
    """)
    List<ItemFaturaDTO> buscarParcelasDaFatura(@Param("idCartao") Long idCartao,
                                               @Param("competencia") YearMonth competencia);
}
