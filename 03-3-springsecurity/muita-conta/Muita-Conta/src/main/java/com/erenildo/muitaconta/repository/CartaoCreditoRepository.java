package com.erenildo.muitaconta.repository;

import com.erenildo.muitaconta.dtos.cartao.ListagemCartaoResponseDTO;
import com.erenildo.muitaconta.entity.CartaoCredito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {
    Optional<CartaoCredito> findById(Long id);

    Optional<CartaoCredito> findByUserId(String id);

    @Query("""
        SELECT new com.erenildo.muitaconta.dtos.cartao.ListagemCartaoResponseDTO(
            c.id,
            c.nome,
            c.limite,
            c.diaFechamentoFatura,
            c.diaVencimentoFatura
        )
        FROM CartaoCredito c
    """)
    Page<ListagemCartaoResponseDTO> listarCartoes(Pageable pageable);
}
