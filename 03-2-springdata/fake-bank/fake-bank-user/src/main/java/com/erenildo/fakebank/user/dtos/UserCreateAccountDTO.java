package com.erenildo.fakebank.user.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserCreateAccountDTO {

    @NotBlank(message = "O nome completo é obrigatório")
    private String fullName;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos")
    private String cpf;

    @NotBlank(message = "O número de celular é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O celular deve conter exatamente 11 dígitos numéricos (DDD + número)")
    private String celular;


    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 5, message = "A senha deve ter no mínimo 5 caracteres")
    private String senha;
}
