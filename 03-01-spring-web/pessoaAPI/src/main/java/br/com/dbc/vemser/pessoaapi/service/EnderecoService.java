package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final PessoaService pessoaService;

    public EnderecoService(
            EnderecoRepository enderecoRepository,
            PessoaService pessoaService
    ) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaService = pessoaService;
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

    public Endereco create(Integer idPessoa, Endereco endereco) throws Exception {
        pessoaService.validarPessoa(idPessoa);
        endereco.setIdPessoa(idPessoa);
        return enderecoRepository.create(endereco);
    }

    public Endereco update(Integer idPessoa, Endereco endereco) throws Exception {
        pessoaService.validarPessoa(idPessoa);
        return enderecoRepository.update(idPessoa, endereco);
    }

    public String delete(Integer idEndereco) throws Exception {
        enderecoRepository.delete(idEndereco);
        return "Endereço removido com sucesso!";
    }
}
