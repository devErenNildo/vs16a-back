package com.erenildo.muitaconta.repository;

import com.erenildo.muitaconta.entity.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {
    Optional<CartaoCredito> findById(Long id);
}
