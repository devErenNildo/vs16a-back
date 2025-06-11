package com.erenildo.fakebank.user.repository;

import com.erenildo.fakebank.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByCpf(String cpf);

    boolean existsByEmail(String email);
    User findByEmail(String email);
}
