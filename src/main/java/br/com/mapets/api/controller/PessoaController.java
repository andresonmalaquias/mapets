package br.com.mapets.api.controller;

import br.com.mapets.api.dto.PessoaDto;
import br.com.mapets.domain.model.Cidade;
import br.com.mapets.domain.model.CriterioPessoa;
import br.com.mapets.domain.model.Pessoa;
import br.com.mapets.domain.model.enuns.MoradiaEnum;
import br.com.mapets.domain.repository.CidadeRepository;
import br.com.mapets.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public List<Pessoa> listar(){
        return pessoaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscar(@PathVariable Integer id){

        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if(pessoa.isPresent()){
            return ResponseEntity.ok(pessoa.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pessoa> adicionar(@RequestBody PessoaDto pessoaDto){
        Optional<Cidade> cidade = cidadeRepository.findById(pessoaDto.getCidade());

        CriterioPessoa criterioPessoa = new CriterioPessoa(MoradiaEnum.valueOf(pessoaDto.getTipoMoradia()), pessoaDto.getTemQuintalVaranda(), pessoaDto.getQtdSalMinRenda(),
                pessoaDto.getTelado(), pessoaDto.getTemPet(), pessoaDto.getJaTevePet(), pessoaDto.getCriacaoIndoor(), pessoaDto.getAcessoRua());

        Pessoa pessoa = new Pessoa(pessoaDto.getCpf(), pessoaDto.getSexo(), pessoaDto.getTelefone(), pessoaDto.getDtNascimento(), pessoaDto.getEndereco(), cidade.get(), criterioPessoa);

        pessoaRepository.save(pessoa);
        return ResponseEntity.ok(pessoa);
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
