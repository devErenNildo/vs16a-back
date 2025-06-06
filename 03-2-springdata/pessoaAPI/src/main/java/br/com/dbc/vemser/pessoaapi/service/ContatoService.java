package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dtos.ContatoRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.ContatoResponseDTO;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContatoService {
    private final ContatoRepository contatoRepository;
    private final PessoaService pessoaService;
    private final ObjectMapper objectMapper;


    public List<ContatoResponseDTO> getAll() {
        List<Contato> contatos = contatoRepository.findAll();

        return objectMapper.convertValue(
                contatos,
                objectMapper.getTypeFactory().constructCollectionType(List.class, ContatoResponseDTO.class)
        );
    }

    public List<ContatoResponseDTO> getByIdPessoa(Integer type) throws Exception {

        Optional<Contato> contatos = contatoRepository.findByTipoContatoTipo(type);


        return objectMapper.convertValue(
                contatos,
                objectMapper.getTypeFactory().constructCollectionType(List.class, ContatoResponseDTO.class)
        );
    }

    public ContatoResponseDTO create(ContatoRequestDTO contato) throws Exception{
//        pessoaService.validarPessoa(contato.getIdPessoa());

        Contato newContato = objectMapper.convertValue(contato, Contato.class);

        newContato = contatoRepository.save(newContato);

        return objectMapper.convertValue(newContato, ContatoResponseDTO.class);
    }

    public ContatoResponseDTO update(
            Integer idContato,
            ContatoRequestDTO contato
    ) throws Exception {
//        pessoaService.validarPessoa(contato.getIdPessoa());
        Contato contatoRecuperado = contatoRepository.getById(idContato);

//        contatoRecuperado.setIdPessoa(contato.getIdPessoa());
        contatoRecuperado.setTipoContato(contato.getTipoContato());
        contatoRecuperado.setDescricao(contato.getDescricao());

        return objectMapper.convertValue(contatoRecuperado, ContatoResponseDTO.class);
    }

    public String delete(Integer idContato) throws Exception {
        Contato contatoRecuperado = contatoRepository.getById(idContato);
        contatoRepository.delete(contatoRecuperado);
        return "Contato removido com sucesso!";
    }

}
