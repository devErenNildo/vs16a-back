package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dtos.PessoaRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PessoaResponseDTO;
import br.com.dbc.vemser.pessoaapi.service.DadosPessoaisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dados-pessoais")
@RequiredArgsConstructor
public class DadosPessoaisController {

    private final DadosPessoaisService dadosPessoaisService;

    @GetMapping
    public List<PessoaResponseDTO> buscarPessoais() {
        return dadosPessoaisService.listarTodasAsPessoas();
    }

    @PostMapping
    public PessoaResponseDTO adicionarPessoais( @RequestBody PessoaRequestDTO dto) {
        return dadosPessoaisService.adicionarPessoa(dto);
    }

    @PutMapping("/{idPessoa}")
    public PessoaResponseDTO atualizarPessoais(
            @PathVariable("idPessoa") Integer id,
            @RequestBody PessoaRequestDTO dto
    ) {
        return dadosPessoaisService.alterarPessoa(id, dto);
    }

    @DeleteMapping("/{idPessoa}")
    public void removerPessoais(@PathVariable("idPessoa") Integer idPessoa) {
        dadosPessoaisService.deletarPessoa(idPessoa);
    }
}
