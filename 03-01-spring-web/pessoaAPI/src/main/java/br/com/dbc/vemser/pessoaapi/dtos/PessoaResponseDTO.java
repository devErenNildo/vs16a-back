package br.com.dbc.vemser.pessoaapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PessoaResponseDTO {
    @NotBlank(message = "Nome não pode ser vazio nem nulo")
    private String nome;

    @NotBlank(message = "CPF não pode ser vazio nem nulo")
    @Size(min = 11, max = 11, message = "CPF deve conter exatamente 11 caracteres")
    private String cpf;
}
