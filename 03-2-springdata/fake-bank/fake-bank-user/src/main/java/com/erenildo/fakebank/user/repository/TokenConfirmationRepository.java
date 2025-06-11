package com.erenildo.fakebank.user.repository;

import com.erenildo.fakebank.user.entity.TokenConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenConfirmationRepository extends JpaRepository<TokenConfirmation, Long> {
    Optional<TokenConfirmation> findByUserEmail(String email);
}
