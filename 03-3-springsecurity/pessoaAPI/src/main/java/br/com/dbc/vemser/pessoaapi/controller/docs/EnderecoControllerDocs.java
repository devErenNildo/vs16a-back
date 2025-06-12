package br.com.dbc.vemser.pessoaapi.controller.docs;

import br.com.dbc.vemser.pessoaapi.dtos.EnderecoRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.EnderecoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EnderecoControllerDocs {

    @Operation(summary = "Listar todos os endereços", description = "Retorna todos os endereços cadastrados.")
    @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EnderecoResponseDTO.class)))
    ResponseEntity<List<EnderecoResponseDTO>> getAll();

    @Operation(summary = "Buscar endereço por ID", description = "Retorna um endereço pelo seu ID.")
    @ApiResponse(responseCode = "200", description = "Endereço encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EnderecoResponseDTO.class)))
    ResponseEntity<EnderecoResponseDTO> getById(@PathVariable Integer idEndereco) throws Exception;

    @Operation(summary = "Listar endereços por pessoa", description = "Retorna todos os endereços associados a uma pessoa.")
    @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EnderecoResponseDTO.class)))
    ResponseEntity<List<EnderecoResponseDTO>> getByPessoa(@PathVariable Integer idPessoa);

    @Operation(summary = "Criar endereço para uma pessoa", description = "Cria um novo endereço vinculado a uma pessoa.")
    @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EnderecoResponseDTO.class)))
    ResponseEntity<EnderecoResponseDTO> create(@PathVariable Integer idPessoa,
                                               @RequestBody EnderecoRequestDTO endereco) throws Exception;

    @Operation(summary = "Atualizar endereço", description = "Atualiza os dados de um endereço.")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EnderecoResponseDTO.class)))
    ResponseEntity<EnderecoResponseDTO> update(@PathVariable Integer idEndereco,
                                               @RequestBody EnderecoRequestDTO endereco) throws Exception;

    @Operation(summary = "Deletar endereço", description = "Remove um endereço do sistema.")
    @ApiResponse(responseCode = "200", description = "Endereço removido com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class)))
    ResponseEntity<String> delete(@PathVariable Integer idEndereco) throws Exception;

}