package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dtos.EnderecoRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.EnderecoResponseDTO;
import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {
//    private final EnderecoRepository enderecoRepository;
//    private final PessoaService pessoaService;
//    private final ObjectMapper objectMapper;
//
//    public List<EnderecoResponseDTO> getAll() {
//
//        List<Endereco> enderecos = enderecoRepository.getAll();
//
//        return objectMapper.convertValue(
//          enderecos,
//          objectMapper.getTypeFactory().constructCollectionType(List.class, EnderecoResponseDTO.class)
//        );
//    }
//
//    public EnderecoResponseDTO getById(Integer idEndereco) throws Exception {
//        Endereco endereco = enderecoRepository.getById(idEndereco);
//        return objectMapper.convertValue(endereco, EnderecoResponseDTO.class);
//    }
//
//    public List<EnderecoResponseDTO> getByIdPessoa(Integer idPessoa) {
//        List<Endereco> enderecos = enderecoRepository.getByIdPessoa(idPessoa);
//
//        return objectMapper.convertValue(
//                enderecos,
//                objectMapper.getTypeFactory().constructCollectionType(List.class, EnderecoResponseDTO.class)
//        );
//    }
//
//    public EnderecoResponseDTO create(Integer idPessoa, EnderecoRequestDTO endereco) throws Exception {
//        pessoaService.validarPessoa(idPessoa);
//        Endereco newEndereco = objectMapper.convertValue(endereco, Endereco.class);
//        newEndereco.setIdPessoa(idPessoa);
//        newEndereco = enderecoRepository.create(newEndereco);
//
//        return objectMapper.convertValue(newEndereco, EnderecoResponseDTO.class);
//    }
//
//    public EnderecoResponseDTO update(Integer idPessoa, EnderecoRequestDTO endereco) throws Exception {
//        pessoaService.validarPessoa(idPessoa);
//
//        Endereco newEndereco = objectMapper.convertValue(endereco, Endereco.class);
//        Endereco enderecocUpdate = enderecoRepository.update(idPessoa, newEndereco);
//
//        return objectMapper.convertValue(enderecocUpdate, EnderecoResponseDTO.class);
//    }
//
//    public String delete(Integer idEndereco) throws Exception {
//        enderecoRepository.delete(idEndereco);
//        return "Endereço removido com sucesso!";
//    }
}
