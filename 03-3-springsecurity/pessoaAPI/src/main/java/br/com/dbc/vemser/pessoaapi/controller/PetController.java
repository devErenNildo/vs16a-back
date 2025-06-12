package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dtos.PetEditReponseDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PetListDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PetRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PetResponseDTO;
import br.com.dbc.vemser.pessoaapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;

    @GetMapping
    public ResponseEntity<List<PetListDTO>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @PostMapping("/{idPessoa}")
    public ResponseEntity<PetResponseDTO> createPet (
            @PathVariable Integer idPessoa,
            @RequestBody PetRequestDTO dto
    ) throws RegraDeNegocioException {
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.petCreate(dto, idPessoa));
    }

    @PutMapping("/{idPet}")
    public ResponseEntity<PetEditReponseDTO> updatePet (
            @PathVariable Integer idPet,
            @RequestBody PetRequestDTO dto
    ) throws RegraDeNegocioException {
        return ResponseEntity.ok(petService.petUpdate(dto, idPet));
    }
}
