package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dtos.ContatoRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.ContatoResponseDTO;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.TipoContato;
import br.com.dbc.vemser.pessoaapi.service.ContatoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contato")
@RequiredArgsConstructor
public class ContatoController {
    private ContatoService contatoService;

    @GetMapping
    public List<ContatoResponseDTO> getAll() {
        return contatoService.getAll();
    }

    @GetMapping("/pessoa")
    public List<ContatoResponseDTO> listByType(@RequestParam("id") Integer type) throws Exception {
        return contatoService.getByIdPessoa(type);
    }

    @PostMapping
    public Contato create(@RequestBody @Valid ContatoRequestDTO contato) throws Exception {
        return contatoService.create(contato);
    }

    @PutMapping("/{idContato}")
    public Contato update(
            @PathVariable Integer idContato,
            @RequestBody @Valid ContatoRequestDTO contato
    ) throws Exception {
        return contatoService.update(idContato, contato);
    }

    @DeleteMapping("/{idContato}")
    public String delete(@PathVariable Integer idContato) throws Exception {
        return contatoService.delete(idContato);
    }
}
