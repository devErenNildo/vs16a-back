package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dtos.ContatoRequestDTO;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.service.ContatoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contato")
public class ContatoController {
    private ContatoService contatoService;

    public ContatoController() {
        this.contatoService = new ContatoService();
    }

    @GetMapping
    public List<Contato> getAll() {
        return contatoService.getAll();
    }

    @PostMapping
    public Contato create(@RequestBody ContatoRequestDTO contato) {
        return contatoService.create(contato);
    }
}
