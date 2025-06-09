package br.com.dbc.vemser.pessoaapi.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaEnderecoID implements Serializable {

    private Integer idPessoa;

    private Integer idEndereco;
}
