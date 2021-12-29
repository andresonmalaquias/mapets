package br.com.mapets.api.controller;

import br.com.mapets.api.dto.input.EstadoInputDto;
import br.com.mapets.api.dto.output.EstadoOutputDto;
import br.com.mapets.domain.model.Estado;
import br.com.mapets.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<EstadoOutputDto> listar(){
        return EstadoOutputDto.buildComCidadesDto(estadoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoOutputDto> buscar(@PathVariable Integer id){

        Optional<Estado> estado = estadoRepository.findById(id);

        if(estado.isPresent()){
            EstadoOutputDto estadoOutputDto = new EstadoOutputDto(estado.get());
            return ResponseEntity.ok(estadoOutputDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/busca/{nome}")
    public List<EstadoOutputDto> buscarLike(@PathVariable String nome){

        List<Estado> estados = estadoRepository.findByNomeContaining(nome);
        List<EstadoOutputDto> estadosDto = EstadoOutputDto.buildComCidadesDto(estados);

        return estadosDto;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoOutputDto> adicionar(@RequestBody EstadoInputDto estadoInputDto,
                                                    UriComponentsBuilder uriBuilder){
        Estado estado = estadoInputDto.build();
        estadoRepository.save(estado);
        EstadoOutputDto estadoOutputDto = new EstadoOutputDto(estado);

        URI path = uriBuilder.path("estado/{id}").buildAndExpand(estado.getCod()).toUri();
        return ResponseEntity.created(path).body(estadoOutputDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoOutputDto> editar(@RequestBody EstadoInputDto estadoInputDto,
                                                  @PathVariable Integer id){

        try {
            Estado estado = estadoRepository.findById(id).get();
            estado = estadoInputDto.buildAlterar(estado);

            estadoRepository.save(estado);
            EstadoOutputDto estadoOutputDto = new EstadoOutputDto(estado);

            return ResponseEntity.ok(estadoOutputDto);
        }catch (Exception e){
            System.err.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id){

        if(!estadoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        estadoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
