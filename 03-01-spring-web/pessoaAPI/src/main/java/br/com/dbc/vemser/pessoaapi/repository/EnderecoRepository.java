package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.entity.TipoEndereco;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class EnderecoRepository {
    private static List<Endereco> listaEnderecos = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger(1);

    public EnderecoRepository() {
        listaEnderecos.add(criaExemplo());
    }

    public List<Endereco> getAll() {
        return listaEnderecos;
    }

    public Endereco getById(Integer id) throws Exception {
        return listaEnderecos.stream()
                .filter(e -> e.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Endereço não encontrado"));
    }

    public List<Endereco> getByIdPessoa(Integer idPessoa) {
        return listaEnderecos.stream()
                .filter(e -> e.getIdPessoa().equals(idPessoa))
                .collect(Collectors.toList());
    }

    public Endereco create(Endereco endereco) {
        endereco.setIdEndereco(COUNTER.incrementAndGet());
        listaEnderecos.add(endereco);
        return endereco;
    }

    public Endereco update(Integer id, Endereco enderecoAtualizado) throws Exception {
        Endereco enderecoRecuperado = getById(id);

        enderecoRecuperado.setTipo(enderecoAtualizado.getTipo());
        enderecoRecuperado.setLogradouro(enderecoAtualizado.getLogradouro());
        enderecoRecuperado.setNumero(enderecoAtualizado.getNumero());
        enderecoRecuperado.setComplemento(enderecoAtualizado.getComplemento());
        enderecoRecuperado.setCep(enderecoAtualizado.getCep());
        enderecoRecuperado.setCidade(enderecoAtualizado.getCidade());
        enderecoRecuperado.setEstado(enderecoAtualizado.getEstado());
        enderecoRecuperado.setPais(enderecoAtualizado.getPais());

        return enderecoRecuperado;
    }

    public void delete(Integer id) throws Exception {
        Endereco enderecoRecuperado = getById(id);
        listaEnderecos.remove(enderecoRecuperado);
    }

    private Endereco criaExemplo() {
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(COUNTER.incrementAndGet());
        endereco.setIdPessoa(1);
        endereco.setTipo(TipoEndereco.RESIDENCIAL);
        endereco.setLogradouro("Rua Exemplo");
        endereco.setNumero(123);
        endereco.setComplemento("Apto 101");
        endereco.setCep("00000-000");
        endereco.setCidade("Cidade Exemplo");
        endereco.setEstado("Estado Exemplo");
        endereco.setPais("Brasil");
        return endereco;
    }
}
