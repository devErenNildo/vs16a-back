package br.com.dbc.vemser.pessoaapi.controller.docs;

import br.com.dbc.vemser.pessoaapi.dtos.PessoaRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PessoaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PessoaControllerDocs {

    @Operation(summary = "Listar pessoas", description = "Retorna todas as pessoas cadastradas.")
    @ApiResponse(responseCode = "200", description = "Pessoas listadas com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PessoaResponseDTO.class)))
    ResponseEntity<List<PessoaResponseDTO>> list();

    @Operation(summary = "Listar pessoas por nome", description = "Retorna pessoas cujo nome contenha o texto informado.")
    @ApiResponse(responseCode = "200", description = "Pessoas encontradas com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PessoaResponseDTO.class)))
    ResponseEntity<List<PessoaResponseDTO>> listByName(@RequestParam("nome") String nome);

    @Operation(summary = "Criar pessoa", description = "Cria uma nova pessoa.")
    @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PessoaResponseDTO.class)))
    ResponseEntity<PessoaResponseDTO> create(@RequestBody PessoaRequestDTO pessoa);

    @Operation(summary = "Atualizar pessoa", description = "Atualiza os dados de uma pessoa existente.")
    @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PessoaResponseDTO.class)))
    ResponseEntity<PessoaResponseDTO> update(@PathVariable("idPessoa") Integer id,
                                             @RequestBody PessoaRequestDTO pessoaAtualizar) throws Exception;

    @Operation(summary = "Deletar pessoa", description = "Remove uma pessoa pelo ID.")
    @ApiResponse(responseCode = "200", description = "Pessoa deletada com sucesso")
    void delete(@PathVariable("idPessoa") Integer id) throws Exception;
}
