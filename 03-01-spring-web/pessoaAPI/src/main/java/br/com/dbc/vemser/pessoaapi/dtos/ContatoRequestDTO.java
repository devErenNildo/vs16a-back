package br.com.dbc.vemser.pessoaapi.dtos;

import br.com.dbc.vemser.pessoaapi.entity.TipoContato;

public class ContatoRequestDTO {
    private Integer idPessoa;
    private TipoContato tipoContato;
    private String descricao;

    public ContatoRequestDTO() {

    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
