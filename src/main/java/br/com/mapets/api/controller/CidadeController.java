package br.com.mapets.api.controller;

import br.com.mapets.api.dto.input.CidadeInputDto;
import br.com.mapets.api.dto.output.CidadeOutputDto;
import br.com.mapets.domain.model.Cidade;
import br.com.mapets.domain.repository.CidadeRepository;
import br.com.mapets.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<CidadeOutputDto> listar(){
        return CidadeOutputDto.buildCidadesDto(cidadeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidadeOutputDto> buscar(@PathVariable Integer id){

        Optional<Cidade> cidade = cidadeRepository.findById(id);
        if(cidade.isPresent()){
            CidadeOutputDto cidadeInputDto = new CidadeOutputDto(cidade.get());
            return ResponseEntity.ok(cidadeInputDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/busca/{nome}")
    public List<CidadeOutputDto> buscarLike(@PathVariable String nome){

        List<Cidade> cidades = cidadeRepository.findByNomeContaining(nome);
        List<CidadeOutputDto> cidadesDto = CidadeOutputDto.buildCidadesDto(cidades);

        return cidadesDto;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CidadeOutputDto> adicionar(@RequestBody CidadeInputDto cidadeInputDto,
                                                    UriComponentsBuilder uriBuilder){
        try {
            Cidade cidade = cidadeInputDto.build(estadoRepository);

            cidadeRepository.save(cidade);
            CidadeOutputDto cidadeOutputDto = new CidadeOutputDto(cidade);

            URI path = uriBuilder.path("cidade/{id}").buildAndExpand(cidade.getCod()).toUri();
            return ResponseEntity.created(path).body(cidadeOutputDto);
        }catch (Exception e){
            System.err.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeOutputDto> editar(@RequestBody CidadeInputDto cidadeInputDto,
                                                  @PathVariable Integer id){

        try {
            Cidade cidade = cidadeRepository.findById(id).get();
            cidade = cidadeInputDto.buildAlterar(cidade, estadoRepository);

            cidadeRepository.save(cidade);
            CidadeOutputDto cidadeOutputDto = new CidadeOutputDto(cidade);

            return ResponseEntity.ok(cidadeOutputDto);
        }catch (Exception e){
            System.err.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id){

        if(!cidadeRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        cidadeRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
