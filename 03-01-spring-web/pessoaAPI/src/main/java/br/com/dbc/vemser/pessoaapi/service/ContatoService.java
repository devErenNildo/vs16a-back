package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dtos.ContatoRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.ContatoResponseDTO;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.TipoContato;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatoService {
    private final ContatoRepository contatoRepository;
    private final PessoaService pessoaService;
    private final ObjectMapper objectMapper;


    public List<ContatoResponseDTO> getAll() {
        List<Contato> contatos = contatoRepository.getAll();

        return objectMapper.convertValue(
                contatos,
                objectMapper.getTypeFactory().constructCollectionType(List.class, ContatoResponseDTO.class)
        );
    }

    public List<ContatoResponseDTO> getByIdPessoa(Integer type) throws Exception {
        List<Contato> contatos = contatoRepository.getByTypo(type);

        return objectMapper.convertValue(
                contatos,
                objectMapper.getTypeFactory().constructCollectionType(List.class, ContatoResponseDTO.class)
        );
    }

    public Contato create(ContatoRequestDTO contato) throws Exception{
        pessoaService.validarPessoa(contato.getIdPessoa());

        Contato newContato = new Contato();
        newContato.setIdPessoa(contato.getIdPessoa());
        newContato.setTipoContato(contato.getTipoContato());
        newContato.setDescricao(contato.getDescricao());
        return contatoRepository.create(newContato);
    }

    public Contato update(
            Integer idContato,
            ContatoRequestDTO contato
    ) throws Exception {
        pessoaService.validarPessoa(contato.getIdPessoa());
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
