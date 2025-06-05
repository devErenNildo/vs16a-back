package com.erenildo.fakebank.repository;

import com.erenildo.fakebank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);

    User findByEmail(String email);

    Optional<User> findByCpf(String cpf);
}
