package br.com.dbc.vemser.pessoaapi.entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    private Integer idPessoa;

    private String nome;

    private LocalDate dataNascimento;

    private String cpf;
}
