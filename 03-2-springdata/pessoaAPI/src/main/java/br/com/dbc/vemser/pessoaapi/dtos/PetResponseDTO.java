package br.com.dbc.vemser.pessoaapi.dtos;

import br.com.dbc.vemser.pessoaapi.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PetResponseDTO {
    private String msg;

    public PetResponseDTO(Pet pet) {
        this.msg = "O pet " + pet.getNome() + " agora é de " + pet.getPessoa().getNome();
    }
}
