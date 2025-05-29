package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> getAll() {
        return enderecoRepository.getAll();
    }

    public Endereco getById(Integer idEndereco) throws Exception {
        return enderecoRepository.getById(idEndereco);
    }

    public List<Endereco> getByIdPessoa(Integer idPessoa) {
        return enderecoRepository.getByIdPessoa(idPessoa);
    }

    public Endereco create(Integer idPessoa, Endereco endereco) {
        endereco.setIdPessoa(idPessoa);
        return enderecoRepository.create(endereco);
    }

    public Endereco update(Integer idEndereco, Endereco endereco) throws Exception {
        return enderecoRepository.update(idEndereco, endereco);
    }

    public String delete(Integer idEndereco) throws Exception {
        enderecoRepository.delete(idEndereco);
        return "Endereço removido com sucesso!";
    }
}
