package com.erenildo.fakebank.user.entity;

import com.erenildo.fakebank.user.dtos.LoginRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Data
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true, length = 11)
    private String celular;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private Boolean contaConfirmada = false;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Account account;

}
