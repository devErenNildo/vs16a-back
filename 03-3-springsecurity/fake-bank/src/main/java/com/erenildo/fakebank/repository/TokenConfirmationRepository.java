package com.erenildo.fakebank.repository;

import com.erenildo.fakebank.entity.TokenConfirmation;
import com.erenildo.fakebank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenConfirmationRepository extends JpaRepository<TokenConfirmation, Long> {
    Optional<TokenConfirmation> findByUserEmail(String userEmail);
}
