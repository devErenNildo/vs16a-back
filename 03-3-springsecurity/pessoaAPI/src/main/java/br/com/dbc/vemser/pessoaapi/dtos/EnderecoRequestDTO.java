package br.com.dbc.vemser.pessoaapi.dtos;

import br.com.dbc.vemser.pessoaapi.entity.TipoEndereco;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoRequestDTO {
    @NotNull(message = "Tipo de endereço não pode ser nulo")
    private TipoEndereco tipo;

    @NotBlank(message = "Logradouro não pode ser nulo nem vazio")
    @Size(max = 250, message = "Logradouro deve conter no máximo 250 caracteres")
    private String logradouro;

    @NotNull(message = "Número não pode ser nulo")
    private Integer numero;

    @Size(max = 250, message = "Complemento deve conter no máximo 250 caracteres")
    private String complemento;

    @NotBlank(message = "CEP não pode ser nulo nem vazio")
    @Size(max = 8, message = "CEP deve conter no máximo 8 caracteres")
    private String cep;

    @NotBlank(message = "Cidade não pode ser nula nem vazia")
    @Size(max = 250, message = "Cidade deve conter no máximo 250 caracteres")
    private String cidade;

    @NotNull(message = "Estado não pode ser nulo")
    private String estado;

    @NotNull(message = "País não pode ser nulo")
    private String pais;
}
