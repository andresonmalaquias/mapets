package br.com.mapets.api.controller;

import br.com.mapets.api.dto.PetDto;
import br.com.mapets.domain.model.CriterioPet;
import br.com.mapets.domain.model.Pessoa;
import br.com.mapets.domain.model.Pet;
import br.com.mapets.domain.model.enuns.PersonalidadeEnum;
import br.com.mapets.domain.model.enuns.PorteEnum;
import br.com.mapets.domain.repository.PessoaRepository;
import br.com.mapets.domain.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PessoaRepository pessoaRepository;


    @GetMapping
    public List<Pet> listar(){
        return petRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> buscar(@PathVariable Integer id){

        Optional<Pet> pet = petRepository.findById(id);

        if(pet.isPresent()){
            return ResponseEntity.ok(pet.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pet> adicionar(@RequestBody PetDto petDto){
        Optional<Pessoa> interessado = pessoaRepository.findById(petDto.getInteressado());

        Optional<Pessoa> adotante = pessoaRepository.findById(petDto.getAdotante());

        CriterioPet criterioPet = new CriterioPet(PorteEnum.valueOf(petDto.getPorte()), petDto.getPodeAcessarRua(), petDto.getLarEspacoso());

        Pet pet = new Pet(petDto.getNome(), petDto.getSexo(), petDto.getNascimento(), adotante.get(), interessado.get(), PersonalidadeEnum.valueOf(petDto.getPersonalidade()), criterioPet);

        petRepository.save(pet);
        return ResponseEntity.ok(pet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id){

        if(!petRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        petRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
