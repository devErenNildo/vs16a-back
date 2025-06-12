package br.com.dbc.vemser.pessoaapi.dtos.relatorio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioPersonalizadoDTO {
    private Integer idPessoa;
    private String nome;
    private String email;

    private ContatoRelatorio contato;

    private EnderecoRelatorio endereco;

    private PetRelatorio pet;


}
