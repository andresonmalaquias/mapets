package br.com.mapets.domain.repository;

import br.com.mapets.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    @Query("select e from Estado e where e.nome = :pNome")
    Estado findEstado(@Param("pNome") String nome);

    List<Estado> findByNomeContaining(String nome);

}
