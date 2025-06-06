package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dtos.PessoaRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PessoaResponseDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "pessoaClient", url = "http://localhost:8080")
@Headers("Content-Type: application/json")
public interface DadosPessoaisService {

    @GetMapping("/pessoa")
    List<PessoaResponseDTO> listarTodasAsPessoas();

    @PostMapping("/pessoa")
    PessoaResponseDTO adicionarPessoa(PessoaRequestDTO pessoa);

    @PutMapping("/pessoa/{idPessoa}")
    PessoaResponseDTO alterarPessoa(@PathVariable("idPessoa") Integer id, PessoaRequestDTO pessoa);

    @DeleteMapping("/pessoa/{idPessoa}")
    void deletarPessoa(@PathVariable("idPessoa") Integer id);

}
