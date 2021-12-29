package br.com.mapets.api.controller;

import br.com.mapets.api.dto.input.PessoaInputDto;
import br.com.mapets.api.dto.output.PessoaOutputDto;
import br.com.mapets.domain.model.Pessoa;
import br.com.mapets.domain.model.Pet;
import br.com.mapets.domain.repository.CidadeRepository;
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
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private PetRepository petRepository;

    @GetMapping
    public List<PessoaOutputDto> listar(){
        return PessoaOutputDto.buildPessoaDto(pessoaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaOutputDto> buscar(@PathVariable Integer id){

        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if(pessoa.isPresent()){
            PessoaOutputDto pessoaOutputDto = new PessoaOutputDto(pessoa.get());
            return ResponseEntity.ok(pessoaOutputDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/busca/{nome}")
    public List<PessoaOutputDto> buscarLike(@PathVariable String nome){

        List<Pessoa> pessoas = pessoaRepository.findByNomeContaining(nome);
        List<PessoaOutputDto> pessoasDto = PessoaOutputDto.buildPessoaDto(pessoas);

        return pessoasDto;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaOutputDto> adicionar(@RequestBody PessoaInputDto pessoaInputDto,
                                                    UriComponentsBuilder uriBuilder){

        try{
            Pessoa pessoa = pessoaInputDto.build(cidadeRepository);

            pessoaRepository.save(pessoa);
            PessoaOutputDto pessoaOutputDto = new PessoaOutputDto(pessoa);

            URI path = uriBuilder.path("pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
            return ResponseEntity.created(path).body(pessoaOutputDto);

        }catch (Exception e){
            System.err.println(e);
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping(path = "/{idPessoa}/{idPet}")
    public ResponseEntity<PessoaOutputDto> adicionarPet(@PathVariable Integer idPessoa, @PathVariable Integer idPet){

        try {
            Pessoa pessoa = pessoaRepository.findById(idPessoa).get();

            Pet pet = petRepository.findById(idPet).get();

            pessoa.setPet(pet);

            petRepository.save(pet);

            PessoaOutputDto pessoaOutputDto = new PessoaOutputDto(pessoa);

            return ResponseEntity.ok(pessoaOutputDto);
        }catch (Exception e){
            System.err.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaOutputDto> editar(@RequestBody PessoaInputDto pessoaInputDto,
                                                  @PathVariable Integer id){

        try {
            Pessoa pessoa = pessoaRepository.findById(id).get();
            pessoa = pessoaInputDto.buildAlterar(pessoa, cidadeRepository);

            pessoaRepository.save(pessoa);
            PessoaOutputDto pessoaOutputDto = new PessoaOutputDto(pessoa);

            return ResponseEntity.ok(pessoaOutputDto);
        }catch (Exception e){
            System.err.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id){

        if(!pessoaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        pessoaRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
