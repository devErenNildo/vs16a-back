package com.erenildo.muitaconta.repository;

import com.erenildo.muitaconta.entity.GastoAvulso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastoAvulsoRepository extends JpaRepository<GastoAvulso, Long> {
}
