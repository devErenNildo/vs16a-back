package com.erenildo.muitaconta.repository;

import com.erenildo.muitaconta.entity.TokenConfirmacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenConfirmacaoRepository extends JpaRepository<TokenConfirmacao, Long> {
    Optional<TokenConfirmacao> findByUserEmail(String email);
}
