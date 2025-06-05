package com.erenildo.fakebank.repository;

import com.erenildo.fakebank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrasactionRepository extends JpaRepository<Transaction, Long> {

    Optional<List<Transaction>> findByContaOrigemUsuarioId(String id);
}
