package br.com.mapets.api.controller;

import br.com.mapets.api.dto.input.PetInputDto;
import br.com.mapets.api.dto.output.PetOutputDto;
import br.com.mapets.domain.model.Pet;
import br.com.mapets.domain.repository.PessoaRepository;
import br.com.mapets.domain.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public List<PetOutputDto> listar(){
        return PetOutputDto.buildPetDto(petRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetOutputDto> buscar(@PathVariable Integer id){

        Optional<Pet> pet = petRepository.findById(id);

        if(pet.isPresent()){
            PetOutputDto petOutputDto = new PetOutputDto(pet.get());
            return ResponseEntity.ok(petOutputDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/busca/{nome}")
    public List<PetOutputDto> buscarLike(@PathVariable String nome){

        List<Pet> pets = petRepository.findByNomeContaining(nome);
        List<PetOutputDto> petsDto = PetOutputDto.buildPetDto(pets);

        return petsDto;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PetOutputDto> adicionarPet(@RequestBody PetInputDto petInputDto,
                                                  UriComponentsBuilder uriBuilder){
        try {
            Pet pet = petInputDto.build(pessoaRepository);

            petRepository.save(pet);
            PetOutputDto petOutputDto = new PetOutputDto(pet);

            URI path = uriBuilder.path("pet/{id}").buildAndExpand(pet.getId()).toUri();
            return ResponseEntity.created(path).body(petOutputDto);
        }catch (Exception e){
            System.err.println(e);
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<PetOutputDto> editar(@RequestBody PetInputDto petInputDto,
                                                  @PathVariable Integer id){

        try {
            Pet pet = petRepository.findById(id).get();
            pet = petInputDto.buildAlterar(pet);

            petRepository.save(pet);
            PetOutputDto petOutputDto = new PetOutputDto(pet);

            return ResponseEntity.ok(petOutputDto);
        }catch (Exception e){
            System.err.println(e);
            return ResponseEntity.notFound().build();
        }
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
