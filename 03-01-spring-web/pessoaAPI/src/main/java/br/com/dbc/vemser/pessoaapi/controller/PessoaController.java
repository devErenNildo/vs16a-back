package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.controller.docs.PessoaControllerDocs;
import br.com.dbc.vemser.pessoaapi.dtos.PessoaRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PessoaResponseDTO;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.service.PessoaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pessoa")
@RequiredArgsConstructor
public class PessoaController implements PessoaControllerDocs {
    private final PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<PessoaResponseDTO>> list() {
        return ResponseEntity.ok().body(pessoaService.list());
    }

    @GetMapping("/byname")
    public ResponseEntity<List<PessoaResponseDTO>> listByName( @RequestParam("nome") String nome) {
        return ResponseEntity.ok().body(pessoaService.listByName(nome));
    }

    @PostMapping
    public ResponseEntity<PessoaResponseDTO>  create(@RequestBody @Valid PessoaRequestDTO pessoa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.create(pessoa));
    }

    @PutMapping("/{idPessoa}")
    public ResponseEntity<PessoaResponseDTO> update(
            @PathVariable("idPessoa") Integer id,
            @RequestBody @Valid PessoaRequestDTO pessoaAtualizar
    ) throws Exception {
        return ResponseEntity.ok().body(pessoaService.update(id, pessoaAtualizar));
    }

    @DeleteMapping("/{idPessoa}")
    public void delete(@PathVariable("idPessoa") Integer id) throws Exception {
        pessoaService.delete(id);
    }
}
