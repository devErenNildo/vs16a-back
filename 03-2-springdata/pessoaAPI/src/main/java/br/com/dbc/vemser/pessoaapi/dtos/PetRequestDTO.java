package br.com.dbc.vemser.pessoaapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetRequestDTO {

    private String nome;
    private String tipo;
}
