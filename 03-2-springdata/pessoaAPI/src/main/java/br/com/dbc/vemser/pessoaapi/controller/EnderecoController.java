package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.controller.docs.EnderecoControllerDocs;
import br.com.dbc.vemser.pessoaapi.dtos.EnderecoRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.EnderecoResponseDTO;
import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.service.EnderecoService;
import jakarta.validation.Valid;
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

//    @GetMapping("/pessoa/{idPessoa}")
//    public ResponseEntity<List<EnderecoResponseDTO>> getByPessoa(@PathVariable Integer idPessoa) {
//        return ResponseEntity.ok().body(enderecoService.getByIdPessoa(idPessoa));
//    }

//    @PostMapping("/{idPessoa}")
//    public ResponseEntity<EnderecoResponseDTO> create(
//            @PathVariable Integer idPessoa,
//            @RequestBody @Valid EnderecoRequestDTO endereco
//    ) throws Exception {
//        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.create(idPessoa, endereco));
//    }

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
