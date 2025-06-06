package br.com.dbc.vemser.pessoaapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PessoaResponseDTO {
    @NotBlank(message = "Nome não pode ser vazio nem nulo")
    private String nome;

    @NotBlank(message = "Nome não pode ser vazio nem nulo")
    private String email;
}
