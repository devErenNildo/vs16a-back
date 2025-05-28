package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.TipoContato;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ContatoRepository {
    private static List<Contato> listaContatos = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();
    private AtomicInteger NUMBER = new AtomicInteger();

    public ContatoRepository() {
        listaContatos.add(new Contato(novoId(), 1, TipoContato.COMERCIAL, novoNumeroPedido(), novoDescricao()));
    }

    public Contato create(Contato contato) {
        contato.setIdContato(COUNTER.incrementAndGet());
        contato.setNumero(novoNumeroPedido());
        listaContatos.add(contato);
        return contato;
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
