package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dtos.ContatoRequestDTO;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.TipoContato;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {
    private ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public List<Contato> getAll() {
        return contatoRepository.getAll();
    }

    public List<Contato> getByIdPessoa(Integer type) {
        return contatoRepository.getByTypo(type);
    }

    public Contato create(ContatoRequestDTO contato) {
        Contato newContato = new Contato();
        newContato.setIdPessoa(contato.getIdPessoa());
        newContato.setTipoContato(contato.getTipoContato());
        newContato.setDescricao(contato.getDescricao());
        return contatoRepository.create(newContato);
    }

    public Contato update(Integer idContato,
                          ContatoRequestDTO contato) throws Exception {
        Contato contatoRecuperado = contatoRepository.getById(idContato);

        contatoRecuperado.setIdPessoa(contato.getIdPessoa());
        contatoRecuperado.setTipoContato(contato.getTipoContato());
        contatoRecuperado.setDescricao(contato.getDescricao());

        return contatoRecuperado;
    }

    public String delete(Integer idContato) throws Exception {
        Contato contatoRecuperado = contatoRepository.getById(idContato);
        contatoRepository.delete(contatoRecuperado);
        return "Contato removido com sucesso!";
    }

}
