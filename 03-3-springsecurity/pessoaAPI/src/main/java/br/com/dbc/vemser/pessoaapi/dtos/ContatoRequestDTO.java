package br.com.dbc.vemser.pessoaapi.dtos;

import br.com.dbc.vemser.pessoaapi.entity.TipoContato;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContatoRequestDTO {
    @NotNull(message = "ID da pessoa não pode ser nulo")
    private Integer idPessoa;

    @NotNull(message = "Tipo de contato não pode ser nulo")
    private TipoContato tipoContato;

    @NotBlank(message = "Descrição não pode ser nula nem vazia")
    private String descricao;
}
