package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.TipoContato;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ContatoRepository {
    private static List<Contato> listaContatos = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();
    private AtomicInteger NUMBER = new AtomicInteger();

    public ContatoRepository() {
        listaContatos.add(new Contato(novoId(), 1, TipoContato.COMERCIAL, novoNumeroPedido(), novoDescricao()));
    }

    public List<Contato> getAll() {
        return listaContatos;
    }

    public List<Contato> getByTypo(Integer idPessoa) {
        return listaContatos.stream()
                .filter(contato -> contato.getIdPessoa().equals(idPessoa))
                .collect(Collectors.toList());
    }

    public Contato create(Contato contato) {
        contato.setIdContato(COUNTER.incrementAndGet());
        contato.setNumero(novoNumeroPedido());
        listaContatos.add(contato);
        return contato;
    }

    public Contato getById(Integer id) throws Exception{
        return listaContatos.stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Contato não encontrado"));
    }

    public void delete(Contato contato) {
        listaContatos.remove(contato);
    }

    private Integer novoId() {
        return COUNTER.getAndIncrement();
    }

    private String novoNumeroPedido () {
        return String.valueOf(NUMBER.getAndIncrement());
    }

    private String novoDescricao() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    }
}
