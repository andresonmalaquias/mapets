package br.com.mapets.domain.repository;

import br.com.mapets.domain.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    List<Pet> findByNomeContaining(String nome);

    Pet findByNome(String nome);

}
