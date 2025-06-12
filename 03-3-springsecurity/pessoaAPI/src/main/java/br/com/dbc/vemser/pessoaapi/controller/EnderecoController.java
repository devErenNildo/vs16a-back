package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dtos.EnderecoRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.EnderecoResponseDTO;
import br.com.dbc.vemser.pessoaapi.service.EnderecoService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> getAll() {
        return ResponseEntity.ok().body(enderecoService.getAll());
    }

    @GetMapping("/{idEndereco}")
    public ResponseEntity<EnderecoResponseDTO> getById(@PathVariable Integer idEndereco) throws Exception {
        return ResponseEntity.ok().body(enderecoService.getById(idEndereco));
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoResponseDTO> update(
            @PathVariable Integer idEndereco,
            @RequestBody @Valid EnderecoRequestDTO endereco
    ) throws Exception {
        return ResponseEntity.ok().body(enderecoService.update(idEndereco, endereco));
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<String> delete(@PathVariable Integer idEndereco) throws Exception {
        return ResponseEntity.ok().body(enderecoService.delete(idEndereco));
    }
}
