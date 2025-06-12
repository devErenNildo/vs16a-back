package br.com.dbc.vemser.pessoaapi.dtos.relatorio;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlatRelatorioDTO {
    private Integer idPessoa;
    private String nome;
    private String email;

    private String numeroContato;

    private String cep;
    private String cidade;
    private String estado;
    private String pais;

    private String nomePet;
}