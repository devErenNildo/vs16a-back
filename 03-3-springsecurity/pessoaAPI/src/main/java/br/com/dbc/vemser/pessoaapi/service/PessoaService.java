package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dtos.PessoaCompletaResponseDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PessoaRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PessoaResponseDTO;
import br.com.dbc.vemser.pessoaapi.dtos.relatorio.*;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEndereco;
import br.com.dbc.vemser.pessoaapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.PessoaEnderecoRepository;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;
    private final PessoaEnderecoRepository pessoaEnderecoRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public List<RelatorioPersonalizadoDTO> gerarRelatorioPersonalizado(Integer idPessoa) {
        List<FlatRelatorioDTO> flatList = pessoaRepository.buscarPessoaRelatorio(idPessoa);

        return flatList.stream().map(flat -> {
            RelatorioPersonalizadoDTO relatorio = new RelatorioPersonalizadoDTO();
            relatorio.setIdPessoa(flat.getIdPessoa());
            relatorio.setNome(flat.getNome());
            relatorio.setEmail(flat.getEmail());


            if (flat.getNumeroContato() != null) {
                ContatoRelatorio contato = new ContatoRelatorio();
                contato.setNumero(flat.getNumeroContato());
                relatorio.setContato(contato);
            }

            if (flat.getCep() != null) {
                EnderecoRelatorio endereco = new EnderecoRelatorio();
                endereco.setCep(flat.getCep());
                endereco.setCidade(flat.getCidade());
                endereco.setEstado(flat.getEstado());
                endereco.setPais(flat.getPais());
                relatorio.setEndereco(endereco);
            }

            if (flat.getNomePet() != null) {
                PetRelatorio pet = new PetRelatorio();
                pet.setNome(flat.getNomePet());
                relatorio.setPet(pet);
            }

            return relatorio;
        }).toList();
    }

    public PessoaResponseDTO create(PessoaRequestDTO pessoa){
        Pessoa newPessoa = objectMapper.convertValue(pessoa, Pessoa.class);
        newPessoa = pessoaRepository.save(newPessoa);


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

    public Pessoa getPessoa(Integer id) throws Exception {
        return pessoaRepository.findById(id).
                orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado"));
    }

    public PessoaCompletaResponseDTO pessoaCompleta(Boolean enderecos, Boolean contatos, Boolean pets, Integer idPssoa) throws Exception {
        Pessoa pessoa = getPessoa(idPssoa);
        PessoaCompletaResponseDTO responseDTO = objectMapper.convertValue(pessoa, PessoaCompletaResponseDTO.class);

        if (enderecos) {
            responseDTO.setEnderecos(pessoaEnderecoRepository.findByPessoaIdPessoa(idPssoa)
                    .stream()
                    .map(PessoaEndereco::getEndereco)
                    .toList());
        }

        if (contatos) {
            responseDTO.setContatos(pessoa.getContatos());
        }

        if (pets) {
            responseDTO.setPet(pessoa.getPet());
        }

        return responseDTO;
    }
}
