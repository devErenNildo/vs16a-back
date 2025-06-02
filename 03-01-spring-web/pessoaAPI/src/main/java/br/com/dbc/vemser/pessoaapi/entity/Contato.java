package br.com.dbc.vemser.pessoaapi.entity;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class Contato {
    private Integer idContato;

    private Integer idPessoa;

    private TipoContato tipoContato;

    private String numero;

    private String descricao;
}
