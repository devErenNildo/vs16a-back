package br.com.dbc.vemser.pessoaapi.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PessoaResponseDTO {
    @NotBlank(message = "Nome não pode ser vazio nem nulo")
    private String nome;

    @NotBlank(message = "Nome não pode ser vazio nem nulo")
    private String email;
}
