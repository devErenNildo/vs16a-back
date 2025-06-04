package com.erenildo.fakebank.repository;

import com.erenildo.fakebank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrasactionRepository extends JpaRepository<Transaction, Long> {
}
