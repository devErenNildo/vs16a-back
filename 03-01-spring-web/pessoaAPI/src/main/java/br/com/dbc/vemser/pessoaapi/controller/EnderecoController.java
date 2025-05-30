package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public List<Endereco> getAll() {
        return enderecoService.getAll();
    }

    @GetMapping("/{idEndereco}")
    public Endereco getById(@PathVariable Integer idEndereco) throws Exception {
        return enderecoService.getById(idEndereco);
    }

    @GetMapping("/pessoa/{idPessoa}")
    public List<Endereco> getByPessoa(@PathVariable Integer idPessoa) {
        return enderecoService.getByIdPessoa(idPessoa);
    }

    @PostMapping("/{idPessoa}")
    public Endereco create(
            @PathVariable Integer idPessoa,
            @RequestBody @Valid Endereco endereco
    ) throws Exception {
        return enderecoService.create(idPessoa, endereco);
    }

    @PutMapping("/{idEndereco}")
    public Endereco update(
            @PathVariable Integer idEndereco,
            @RequestBody @Valid Endereco endereco
    ) throws Exception {
        return enderecoService.update(idEndereco, endereco);
    }

    @DeleteMapping("/{idEndereco}")
    public String delete(@PathVariable Integer idEndereco) throws Exception {
        return enderecoService.delete(idEndereco);
    }
}
