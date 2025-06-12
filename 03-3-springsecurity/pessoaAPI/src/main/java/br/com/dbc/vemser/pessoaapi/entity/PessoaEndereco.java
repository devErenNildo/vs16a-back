package br.com.dbc.vemser.pessoaapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "PESSOA_X_ENDERECO")

public class PessoaEndereco {

    @EmbeddedId
    private PessoaEnderecoID pessoaEnderecoID;

    @ManyToOne
    @MapsId("idPessoa")
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @ManyToOne
    @MapsId("idEndereco")
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

}
