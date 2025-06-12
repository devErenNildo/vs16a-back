package br.com.dbc.vemser.pessoaapi.controller.docs;

import br.com.dbc.vemser.pessoaapi.dtos.ContatoRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.ContatoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ContatoControllerDocs {

    @Operation(summary = "Listar contatos", description = "Lista todos os contatos.")
    @ApiResponse(responseCode = "200", description = "Lista retornada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ContatoResponseDTO.class)))
    ResponseEntity<List<ContatoResponseDTO>> getAll();

    @Operation(summary = "Listar contatos por pessoa", description = "Lista contatos de uma pessoa.")
    @ApiResponse(responseCode = "200", description = "Contatos retornados",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ContatoResponseDTO.class)))
    ResponseEntity<List<ContatoResponseDTO>> listByType(@RequestParam("id") Integer type) throws Exception;

    @Operation(summary = "Criar contato", description = "Cria um novo contato.")
    @ApiResponse(responseCode = "201", description = "Contato criado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ContatoResponseDTO.class)))
    ResponseEntity<ContatoResponseDTO> create(@RequestBody ContatoRequestDTO contato) throws Exception;

    @Operation(summary = "Atualizar contato", description = "Atualiza um contato existente.")
    @ApiResponse(responseCode = "200", description = "Contato atualizado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ContatoResponseDTO.class)))
    ResponseEntity<ContatoResponseDTO> update(@PathVariable Integer idContato,
                                              @RequestBody ContatoRequestDTO contato) throws Exception;

    @Operation(summary = "Deletar contato", description = "Remove um contato.")
    @ApiResponse(responseCode = "200", description = "Contato removido",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class)))
    ResponseEntity<String> delete(@PathVariable Integer idContato) throws Exception;
}
