package com.erenildo.fakebank.auth.repository;

import com.erenildo.fakebank.auth.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail( String email);

    boolean existsByEmail(String email);
}
