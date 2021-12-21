package br.com.mapets.api.controller;

import br.com.mapets.api.dto.CidadeDto;
import br.com.mapets.domain.model.Cidade;
import br.com.mapets.domain.model.Estado;
import br.com.mapets.domain.repository.CidadeRepository;
import br.com.mapets.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<Cidade> listar(){
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable Integer id){

        Optional<Cidade> estado = cidadeRepository.findById(id);

        if(estado.isPresent()){
            return ResponseEntity.ok(estado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cidade> adicionar(@RequestBody CidadeDto cidadeDto){
        Optional<Estado> estado = estadoRepository.findById(cidadeDto.getEstado());

        if(!estado.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Cidade cidade = new Cidade(cidadeDto.getNome(), estado.get());

        cidadeRepository.save(cidade);
        return ResponseEntity.ok(cidade);
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
