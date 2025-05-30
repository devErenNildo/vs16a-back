package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dtos.EnderecoDTO;
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
            @RequestBody @Valid EnderecoDTO endereco
    ) throws Exception {
        Endereco novoEndereco = new Endereco();

        novoEndereco.setIdPessoa(idPessoa);
        novoEndereco.setTipo(endereco.getTipo());
        novoEndereco.setLogradouro(endereco.getLogradouro());
        novoEndereco.setNumero(endereco.getNumero());
        novoEndereco.setComplemento(endereco.getComplemento());
        novoEndereco.setCep(endereco.getCep());
        novoEndereco.setCidade(endereco.getCidade());
        novoEndereco.setEstado(endereco.getEstado());
        novoEndereco.setPais(endereco.getPais());

        return enderecoService.create(idPessoa, novoEndereco);
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
