package com.erenildo.muitaconta.repository;

import com.erenildo.muitaconta.dtos.gastos.GastoCartaoResponseDTO;
import com.erenildo.muitaconta.entity.GastoCartao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GastoCartaoRepository extends JpaRepository<GastoCartao, Long> {

    @Query("""
    SELECT new com.erenildo.muitaconta.dtos.gastos.GastoCartaoResponseDTO(
        g.descricao,
        g.valorTotal,
        g.quantidadeParcelas,
        g.dataCompra
    )
    FROM GastoCartao g
    WHERE (:idCartao IS NULL OR g.cartaoCredito.id = :idCartao)
    ORDER BY g.dataCompra DESC
    """)
    Page<GastoCartaoResponseDTO> listarGastosCartao(@Param("idCartao") Long idCartao, Pageable pageable);

    Optional<GastoCartao> findById(Long id);
}
