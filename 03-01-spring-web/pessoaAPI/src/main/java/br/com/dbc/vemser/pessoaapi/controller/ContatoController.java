package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.controller.docs.ContatoControllerDocs;
import br.com.dbc.vemser.pessoaapi.dtos.ContatoRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.ContatoResponseDTO;
import br.com.dbc.vemser.pessoaapi.service.ContatoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contato")
@RequiredArgsConstructor
public class ContatoController implements ContatoControllerDocs {
    private final ContatoService contatoService;

    @GetMapping
    public ResponseEntity<List<ContatoResponseDTO>> getAll() {
        return ResponseEntity.ok().body(contatoService.getAll());
    }

    @GetMapping("/pessoa")
    public ResponseEntity<List<ContatoResponseDTO>> listByType(
            @RequestParam("id") Integer type
    ) throws Exception {
        return ResponseEntity.ok().body(contatoService.getByIdPessoa(type));
    }

    @PostMapping
    public ResponseEntity<ContatoResponseDTO> create(
            @RequestBody @Valid ContatoRequestDTO contato
    ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.create(contato));
    }

    @PutMapping("/{idContato}")
    public ResponseEntity<ContatoResponseDTO> update(
            @PathVariable Integer idContato,
            @RequestBody @Valid ContatoRequestDTO contato
    ) throws Exception {
        return ResponseEntity.ok().body(contatoService.update(idContato, contato));
    }

    @DeleteMapping("/{idContato}")
    public ResponseEntity<String> delete(@PathVariable Integer idContato) throws Exception {
        return ResponseEntity.ok().body(contatoService.delete(idContato));
    }
}
