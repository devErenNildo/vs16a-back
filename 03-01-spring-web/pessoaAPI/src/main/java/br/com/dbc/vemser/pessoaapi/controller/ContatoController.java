package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dtos.ContatoRequestDTO;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.TipoContato;
import br.com.dbc.vemser.pessoaapi.service.ContatoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contato")
public class ContatoController {
    private ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @GetMapping
    public List<Contato> getAll() {
        return contatoService.getAll();
    }

    @GetMapping("/pessoa")
    public List<Contato> listByType(@RequestParam("id") Integer type) {
        return contatoService.getByIdPessoa(type);
    }

    @PostMapping
    public Contato create(@RequestBody ContatoRequestDTO contato) {
        return contatoService.create(contato);
    }

    @PutMapping("/{idContato}")
    public Contato update(@PathVariable Integer idContato,
                          @RequestBody ContatoRequestDTO contato) throws Exception {
        return contatoService.update(idContato, contato);
    }

    @DeleteMapping("/{idContato}")
    public String delete(@PathVariable Integer idContato) throws Exception {
        return contatoService.delete(idContato);
    }
}
