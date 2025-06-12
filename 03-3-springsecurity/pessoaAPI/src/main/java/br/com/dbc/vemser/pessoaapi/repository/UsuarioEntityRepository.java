package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioEntityRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByLogin(String login);
}
