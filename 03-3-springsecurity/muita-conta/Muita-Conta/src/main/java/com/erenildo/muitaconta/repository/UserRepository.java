package com.erenildo.muitaconta.repository;

import com.erenildo.muitaconta.dtos.relatorios.RelatorioDividasMesDTO;
import com.erenildo.muitaconta.dtos.user.ListagemUserResonseDTO;
import com.erenildo.muitaconta.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByEmail(String email);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    @Query("""
    SELECT new com.erenildo.muitaconta.dtos.relatorios.RelatorioDividasMesDTO(
        COALESCE((
            SELECT SUM(p.valorParcela)
            FROM ParcelaCartao p
            WHERE p.competencia = :competencia
              AND p.gastoCartao.cartaoCredito.user.id = :idUser
        ), 0),

        COALESCE((
            SELECT SUM(b.valor)
            FROM Boleto b
            WHERE b.dataVencimento BETWEEN :inicio AND :fim
              AND b.user.id = :idUser
        ), 0),

        COALESCE((
            SELECT SUM(c.valor)
            FROM ContaMensal c
            WHERE c.mes BETWEEN :inicio AND :fim
              AND c.user.id = :idUser
        ), 0),

        COALESCE((
            SELECT SUM(g.valor)
            FROM GastoAvulso g
            WHERE g.dataGasto BETWEEN :inicio AND :fim
              AND g.user.id = :idUser
        ), 0)
    )
    FROM User u
    WHERE u.id = :idUser
""")
    RelatorioDividasMesDTO getRelatorioDividasMesAtual(@Param("idUser") String idUser,
                                                       @Param("competencia") YearMonth competencia,
                                                       @Param("inicio") LocalDate inicio,
                                                       @Param("fim") LocalDate fim);

    @Query("""
        SELECT new com.erenildo.muitaconta.dtos.user.ListagemUserResonseDTO(
            u.nome,
            u.email,
            u.dataCadastro,
            u.contaConfirmada
        )
        FROM User u
        WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))
          AND u.dataCadastro >= :dataMin
          AND u.dataCadastro <= :dataMax
    """)
    Page<ListagemUserResonseDTO> listarUsusarios(@Param("nome") String nome,
                                                 @Param("dataMin") LocalDateTime dataMin,
                                                 @Param("dataMax") LocalDateTime dataMax,
                                                 Pageable pageable);
}
