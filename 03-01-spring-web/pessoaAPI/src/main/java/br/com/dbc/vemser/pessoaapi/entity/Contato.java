package br.com.dbc.vemser.pessoaapi.entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contato {
    private Integer idContato;

    private Integer idPessoa;

    private TipoContato tipoContato;

    private String numero;

    private String descricao;
}
