package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dtos.EnderecoRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.EnderecoResponseDTO;
import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEndereco;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEnderecoID;
import br.com.dbc.vemser.pessoaapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import br.com.dbc.vemser.pessoaapi.repository.PessoaEnderecoRepository;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final PessoaEnderecoRepository pessoaEnderecoRepository;
    private final PessoaService pessoaService;
    private final ObjectMapper objectMapper;

    public List<EnderecoResponseDTO> getAll() {

        List<Endereco> enderecos = enderecoRepository.findAll();

        return objectMapper.convertValue(
          enderecos,
          objectMapper.getTypeFactory().constructCollectionType(List.class, EnderecoResponseDTO.class)
        );
    }

    public EnderecoResponseDTO getById(Integer idEndereco) throws Exception {
        Endereco endereco = enderecoRepository.getById(idEndereco);
        return objectMapper.convertValue(endereco, EnderecoResponseDTO.class);
    }

    public List<EnderecoResponseDTO> getByIdPessoa(Integer idPessoa) {
        List<Endereco> enderecos = pessoaEnderecoRepository.findByPessoaIdPessoa(idPessoa)
                .stream()
                .map(PessoaEndereco::getEndereco)
                .toList();

        return objectMapper.convertValue(
                enderecos,
                objectMapper.getTypeFactory().constructCollectionType(List.class, EnderecoResponseDTO.class)
        );
    }

    public EnderecoResponseDTO create(Integer idPessoa, EnderecoRequestDTO endereco) throws Exception {
        pessoaService.validarPessoa(idPessoa);
        Endereco newEndereco = objectMapper.convertValue(endereco, Endereco.class);

        newEndereco = enderecoRepository.save(newEndereco);

        Pessoa pessoa = pessoaService.getPessoa(idPessoa);

        PessoaEndereco pessoaEndereco = new PessoaEndereco();
        pessoaEndereco.setPessoaEnderecoID(new PessoaEnderecoID(pessoa.getIdPessoa(), newEndereco.getIdEndereco()));
        pessoaEndereco.setPessoa(pessoa);
        pessoaEndereco.setEndereco(newEndereco);

        pessoaEnderecoRepository.save(pessoaEndereco);

        return objectMapper.convertValue(newEndereco, EnderecoResponseDTO.class);
    }

    public EnderecoResponseDTO update(Integer idPessoa, EnderecoRequestDTO endereco) throws Exception {
        pessoaService.validarPessoa(idPessoa);

        Endereco newEndereco = objectMapper.convertValue(endereco, Endereco.class);
        Endereco enderecocUpdate = enderecoRepository.save(newEndereco);

        return objectMapper.convertValue(enderecocUpdate, EnderecoResponseDTO.class);
    }

    public String delete(Integer idEndereco) throws Exception {
        Endereco endereco = enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new RegraDeNegocioException("Endeço não encontrado"));
        enderecoRepository.delete(endereco);
        return "Endereço removido com sucesso!";
    }
}
