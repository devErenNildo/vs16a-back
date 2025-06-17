package com.erenildo.muitaconta.repository;

import com.erenildo.muitaconta.entity.GastoCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastoCartaoRepository extends JpaRepository<GastoCartao, Long> {
}
