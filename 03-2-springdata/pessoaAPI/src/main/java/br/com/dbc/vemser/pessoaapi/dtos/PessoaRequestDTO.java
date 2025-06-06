package br.com.dbc.vemser.pessoaapi.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaRequestDTO {
    @NotBlank(message = "Nome não pode ser vazio nem nulo")
    private String nome;

    @NotBlank(message = "E-mail não pode estar em branco")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @NotNull(message = "Data de nascimento não pode ser nula")
    @Past(message = "Data de nascimento deve estar no passado")
    private LocalDate dataNascimento;

    @NotBlank(message = "CPF não pode ser vazio nem nulo")
    @Size(min = 11, max = 11, message = "CPF deve conter exatamente 11 caracteres")
    private String cpf;
}
