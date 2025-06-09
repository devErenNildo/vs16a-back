package br.com.dbc.vemser.pessoaapi.dtos;

import br.com.dbc.vemser.pessoaapi.entity.Pet;
import lombok.Data;

@Data
public class PetEditReponseDTO {
    private String msg;

    public PetEditReponseDTO(Pet petAntigo, Pet petNovo) {
        this.msg = "O pet com nome: " + petAntigo.getNome() + " do tipo : " + petNovo.getTipo()
           + " agora se chama: " + petNovo.getNome() + " e do tipo: " + petNovo.getTipo();
    }
}
