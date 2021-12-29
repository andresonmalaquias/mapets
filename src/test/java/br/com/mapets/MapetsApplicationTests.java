package br.com.mapets;

import br.com.mapets.domain.model.Cidade;
import br.com.mapets.domain.model.Estado;
import br.com.mapets.domain.model.Pessoa;
import br.com.mapets.domain.model.Pet;
import br.com.mapets.domain.model.enuns.PersonalidadeEnum;
import br.com.mapets.domain.model.enuns.PorteEnum;
import br.com.mapets.domain.model.enuns.TipoPessoaEnum;
import br.com.mapets.domain.model.enuns.TipoPetEnum;
import br.com.mapets.domain.repository.CidadeRepository;
import br.com.mapets.domain.repository.EstadoRepository;
import br.com.mapets.domain.repository.PessoaRepository;
import br.com.mapets.domain.repository.PetRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.*;

@SpringBootTest
class MapetsTest {

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Test
    @DisplayName("TC-01: Cadastrar uma cidade com Estado junto")
    public void CadastroCidadeComEstado() {

        Estado e = new Estado();
        e.setNome("Amazonas");

        Cidade c = new Cidade();
        c.setEstado(e);
        c.setNome("Manaus");

        cidadeRepository.save(c);
        estadoRepository.save(e);

        Assert.assertNotNull(c.getCod());
        Assert.assertNotNull(e.getCod());
    }

    @Test
    @DisplayName("TC-02: Deve procurar Cidade pelo código")
    void ProcurarCidadePorCod(){

        Cidade cidade = cidadeRepository.findByCod(9);
        assertThat(cidade).isNotNull();

    }

    @Test
    @DisplayName("TC-03: Procurar Cidade pelo nome")
    void ProcurarCidadePorNome(){

        Cidade cidade = cidadeRepository.findByNome("Manaus");
        assertThat(cidade).isNotNull();
    }

    @Test
    @DisplayName("TC-04: Deletar uma Cidade pelo Id")
    void DeletarCidadePorId(){

        Cidade cidade = cidadeRepository.findByCod(1);
        cidadeRepository.delete(cidade);

        Cidade cidade1 = null;

        Optional<Cidade> optionalCidade = cidadeRepository.findById(1);

        if(optionalCidade.isPresent()){
            cidade1 = optionalCidade.get();
        }

        assertThat(cidade1).isNull();

    }

    @Autowired
    PetRepository petRepository;

    @Test
    @DisplayName("TC-05: Cadastrar Pet com Dono no Sistema")
    public void CadastrarPet(){


        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("222222222");

        Pet pet = new Pet();
        pet.setNome("Greg");
        pet.setSexo("MACHO");
        pet.setIdade_meses(15);
        pet.setTipo(TipoPetEnum.CACHORRO);
        pet.setPersonalidade(PersonalidadeEnum.COMILAO);
        pet.setPorte(PorteEnum.MEDIO);
        pet.setIndoor(true);
        pet.setResponsavel(pessoa);


        petRepository.save(pet);
        Assert.assertNotNull(pet.getId());

        Pessoa p2 = new Pessoa();
        p2.setCpf("333333333");

        Pet p = new Pet();
        p.setNome("Pipoquinha");
        p.setSexo("FEMEA");
        p.setIdade_meses(8);
        p.setTipo(TipoPetEnum.GATO);
        p.setPersonalidade(PersonalidadeEnum.CALMO);
        p.setPorte(PorteEnum.PEQUENO);
        p.setIndoor(false);
        p.setResponsavel(p2);

        petRepository.save(p);
        Assert.assertNotNull(pet.getId());

    }

    @Test
    @DisplayName("TC-06: Procurar Pet pelo nome")
    void procurarPetPorNome(){

        Pet pet = petRepository.findByNome("Greg");
        assertThat(pet).isNotNull();

    }

    @Test
    @DisplayName("TC-07: Deletar pet pelo id")
    void deletePetPorId(){

        Pet pet = petRepository.findById(1).get();
        petRepository.delete(pet);

        Pet pet1 = null;

        Optional<Pet> optionalPet = petRepository.findById(1);

        if(optionalPet.isPresent()){
            pet1 = optionalPet.get();
        }

        assertThat(pet1).isNull();

    }




}

