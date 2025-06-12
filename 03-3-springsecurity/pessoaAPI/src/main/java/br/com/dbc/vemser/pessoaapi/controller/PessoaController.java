package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dtos.PessoaCompletaResponseDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PessoaRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PessoaResponseDTO;
import br.com.dbc.vemser.pessoaapi.dtos.relatorio.RelatorioPersonalizadoDTO;
import br.com.dbc.vemser.pessoaapi.service.PessoaService;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pessoa")
@RequiredArgsConstructor
public class PessoaController  {
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

    @GetMapping("/completo/{idPessoa}")
    public ResponseEntity<PessoaCompletaResponseDTO> listarPessoaCompleta(
            @PathParam("idPessoa") Integer idPessoa,
            @RequestParam(required = false, defaultValue = "false") boolean enderecos,
            @RequestParam(required = false, defaultValue = "false") boolean contatos,
            @RequestParam(required = false, defaultValue = "false") boolean pets) throws Exception {

        PessoaCompletaResponseDTO dto = pessoaService.pessoaCompleta(enderecos, contatos, pets, idPessoa);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/pessoa-completo")
    public ResponseEntity<List<RelatorioPersonalizadoDTO>> listarRelatorio(
            @RequestParam(required = false) Integer idPessoa) {
        return ResponseEntity.ok(pessoaService.gerarRelatorioPersonalizado(idPessoa));
    }
}
