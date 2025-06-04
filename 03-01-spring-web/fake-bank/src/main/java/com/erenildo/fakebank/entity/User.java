package com.erenildo.fakebank.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_user")
public class User {

    @Id
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

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Account account;
}
