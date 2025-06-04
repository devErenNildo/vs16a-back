package com.erenildo.fakebank.dtos;


import lombok.Data;

@Data
public class UserRequestDTO {
    private String fullName;

    private String email;

    private String cpf;

    private String celular;

    private String senha;
}
