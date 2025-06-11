package com.erenildo.fakebank.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent {
    private String id;
    private String fullName;
    private String email;
    private String cpf;
    private String celular;
    private String senha;
    private Boolean contaConfirmada;
}

