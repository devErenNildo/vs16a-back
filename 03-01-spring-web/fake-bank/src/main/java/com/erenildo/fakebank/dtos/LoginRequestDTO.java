package com.erenildo.fakebank.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank
    private String cpf;

    @NotBlank
    private String senha;
}
