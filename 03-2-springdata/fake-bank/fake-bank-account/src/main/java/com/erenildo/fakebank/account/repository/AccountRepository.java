package com.erenildo.fakebank.account.repository;

import com.erenildo.fakebank.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsuarioId(String idUser);

    boolean existsByChavePix(String chavePix);

    Optional<Account> findByChavePix(String pixDestinatario);
}
