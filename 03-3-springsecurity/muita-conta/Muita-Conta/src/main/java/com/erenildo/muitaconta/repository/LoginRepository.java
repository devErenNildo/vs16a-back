package com.erenildo.muitaconta.repository;

import com.erenildo.muitaconta.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    Optional<Login> findByLogin(String login);
    Optional<Login> findById(String id);
}
