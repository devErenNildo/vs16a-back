package com.erenildo.fakebank.repository;

import com.erenildo.fakebank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsuarioId(String usuarioId);

    Optional<Account> findByChavePix(String chavePix);

    boolean existsByChavePix(String pix);
}
