package com.erenildo.muitaconta.repository;

import com.erenildo.muitaconta.dtos.gastos.ListagemGastoAvulsoResponseDTO;
import com.erenildo.muitaconta.entity.GastoAvulso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface GastoAvulsoRepository extends JpaRepository<GastoAvulso, Long> {

    @Query("""
        SELECT new com.erenildo.muitaconta.dtos.gastos.ListagemGastoAvulsoResponseDTO(
            g.descricao,
            g.valor,
            g.dataGasto
        )
        FROM GastoAvulso g
        WHERE g.user.id = :idUser
          AND (:dataMin IS NULL OR g.dataGasto >= :dataMin)
          AND (:dataMax IS NULL OR g.dataGasto <= :dataMax)
    """)
    Page<ListagemGastoAvulsoResponseDTO> listarGastosAvulso(@Param("idUser") String idUser,
                                                            @Param("dataMin") LocalDate dataMin,
                                                            @Param("dataMax") LocalDate dataMax,
                                                            Pageable pageable);
}
