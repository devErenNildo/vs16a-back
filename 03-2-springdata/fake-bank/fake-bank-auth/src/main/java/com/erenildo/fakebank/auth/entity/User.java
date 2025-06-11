package com.erenildo.fakebank.auth.entity;

import com.erenildo.fakebank.auth.dtos.LoginRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Data
@Entity
@Table(name = "TB_USER_AUTH")
public class User {
    @Id
    @Column(name = "user_id")
    private String id;

    private String email;

    private String senha;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public boolean isLoginCorrect(LoginRequestDTO login, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(login.getSenha(), this.senha);
    }

}