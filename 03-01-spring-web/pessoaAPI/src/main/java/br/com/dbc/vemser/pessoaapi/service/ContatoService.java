package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dtos.ContatoRequestDTO;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;

import java.util.List;

public class ContatoService {
    private ContatoRepository contatoRepository;

    public ContatoService() {
        this.contatoRepository = new ContatoRepository();
    }

    public List<Contato> getAll() {
        return contatoRepository.getAll();
    }

    public Contato create(ContatoRequestDTO contato) {
        Contato newContato = new Contato();
        newContato.setIdPessoa(contato.getIdPessoa());
        newContato.setTipoContato(contato.getTipoContato());
        newContato.setDescricao(contato.getDescricao());
        return contatoRepository.create(newContato);
    }
}
