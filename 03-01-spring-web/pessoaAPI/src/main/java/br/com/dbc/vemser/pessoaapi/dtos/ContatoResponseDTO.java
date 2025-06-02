package br.com.dbc.vemser.pessoaapi.dtos;

import br.com.dbc.vemser.pessoaapi.entity.TipoContato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContatoResponseDTO {

    @NotNull(message = "ID da pessoa não pode ser nulo")
    private Integer idPessoa;

    @NotNull(message = "Tipo de contato não pode ser nulo")
    private TipoContato tipoContato;

    @NotBlank(message = "Número não pode ser nulo nem vazio")
    @Size(max = 13, message = "Número deve conter no máximo 13 caracteres")
    private String numero;

    @NotBlank(message = "Descrição não pode ser nula nem vazia")
    private String descricao;
}
