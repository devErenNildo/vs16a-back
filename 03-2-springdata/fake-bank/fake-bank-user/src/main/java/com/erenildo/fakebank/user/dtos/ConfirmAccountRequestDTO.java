package com.erenildo.fakebank.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ConfirmAccountRequestDTO {

    @NotBlank(message = "O token é obrigatório")
    private String token;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;
}
