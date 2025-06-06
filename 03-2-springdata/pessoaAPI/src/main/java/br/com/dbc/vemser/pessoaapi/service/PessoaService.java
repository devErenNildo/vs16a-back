package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dtos.EnderecoResponseDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PessoaRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PessoaResponseDTO;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;
//    private final EmailService emailService;

    public PessoaResponseDTO create(PessoaRequestDTO pessoa){
        Pessoa newPessoa = objectMapper.convertValue(pessoa, Pessoa.class);
        newPessoa = pessoaRepository.save(newPessoa);


        emailService.sendEmail(pessoa.getEmail(), pessoa.getNome(), newPessoa.getIdPessoa());

        emailService.sendEmail(pessoa.getEmail(), pessoa.getNome(), newPessoa.getIdPessoa());
        return objectMapper.convertValue(newPessoa, PessoaResponseDTO.class);
    }

    public List<PessoaResponseDTO> list(){
        List<Pessoa> pessoas = pessoaRepository.findAll();

        return objectMapper.convertValue(
                pessoas,
                objectMapper.getTypeFactory().constructCollectionType(List.class, PessoaResponseDTO.class)
        );
    }

    public PessoaResponseDTO update(
            Integer id,
            PessoaRequestDTO pessoaAtualizar
    ) throws Exception {
        Pessoa pessoaRecuperada = getPessoa(id);

        pessoaRecuperada.setCpf(pessoaAtualizar.getCpf());
        pessoaRecuperada.setNome(pessoaAtualizar.getNome());
        pessoaRecuperada.setDataNascimento(pessoaAtualizar.getDataNascimento());

        pessoaRepository.save(pessoaRecuperada);

        return objectMapper.convertValue(pessoaRecuperada, PessoaResponseDTO.class);
    }

    public void delete(Integer id) throws Exception {
        Pessoa pessoaRecuperada = getPessoa(id);
        pessoaRepository.delete(pessoaRecuperada);
    }

    public List<PessoaResponseDTO> listByName(String nome) {
        List<Pessoa> pessoas =  buscarPorNome(nome);

        return objectMapper.convertValue(
                pessoas,
                objectMapper.getTypeFactory().constructCollectionType(List.class, PessoaResponseDTO.class)
        );
    }

    public void validarPessoa(Integer id) throws Exception {
        getPessoa(id);
    }

    private List<Pessoa> buscarPorNome(String nome) {
        return pessoaRepository.findAll().stream()
                .filter(pessoa -> pessoa.getNome() != null && pessoa.getNome().contains(nome))
                .toList();
    }

    private Pessoa getPessoa(Integer id) throws Exception {
        return pessoaRepository.findById(id).get();
    }
}
