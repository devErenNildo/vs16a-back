package com.erenildo.muitaconta.repository;

import com.erenildo.muitaconta.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargosRepository extends JpaRepository<Cargo, Integer> {

    Optional<Cargo> findByIdCargo(Integer id);
}
