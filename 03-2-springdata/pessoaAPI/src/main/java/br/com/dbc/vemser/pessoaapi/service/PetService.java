package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dtos.PetEditReponseDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PetListDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PetRequestDTO;
import br.com.dbc.vemser.pessoaapi.dtos.PetResponseDTO;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.entity.Pet;
import br.com.dbc.vemser.pessoaapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import br.com.dbc.vemser.pessoaapi.repository.PetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;

    public PetResponseDTO petCreate(PetRequestDTO dto, Integer idPessoa) throws RegraDeNegocioException {

        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada"));

        Pet pet = objectMapper.convertValue(dto, Pet.class);
        pet.setPessoa(pessoa);

        petRepository.save(pet);

        return new PetResponseDTO(pet);

    }

    public List<PetListDTO> getAllPets() {
        List<Pet> pets = petRepository.findAll();

        return objectMapper.convertValue(
                pets,
                objectMapper.getTypeFactory().constructCollectionType(List.class, PetListDTO.class)
        );
    }

    public PetEditReponseDTO petUpdate(PetRequestDTO  dto, Integer idPet) throws RegraDeNegocioException {
        Pet petEncontrado = petRepository.findById(idPet)
                .orElseThrow(() -> new RegraDeNegocioException("Pet não encontrado"));

        Pet petAntigo = new Pet();
        petAntigo.setNome(petEncontrado.getNome());
        petAntigo.setTipo(petEncontrado.getTipo());

        petEncontrado.setNome(dto.getNome());
        petEncontrado.setTipo(dto.getTipo());

        petRepository.save(petEncontrado);

        return new PetEditReponseDTO(petAntigo, petEncontrado);
    }
}
