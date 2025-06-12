package com.erenildo.fakebank.repository;

import com.erenildo.fakebank.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaEntityRepository extends JpaRepository<PessoaEntity, Integer> {

    Optional<PessoaEntity> findByLogin(String login);

}
