package br.com.dbc.vemser.pessoaapi.dtos.relatorio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRelatorio {
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
}
