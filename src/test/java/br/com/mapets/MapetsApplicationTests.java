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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.*;

@SpringBootTest
class MapetsTest {

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    Pessoa pessoa;
    Cidade cidade;


    @Test
    @DisplayName("TC-01: Cadastrar uma cidade junto com Estado")
    public void CadastroCidadeComEstado() {

        Estado e = new Estado("Amazonas");

        Cidade c = new Cidade("Manaus",e);

        if (cidadeRepository.findCidade(c.getNome())==null) {
            cidadeRepository.save(c);
            System.out.println("Cidade "+c.getNome()+" Cadastrada com Sucesso!");
            System.out.println("Estado "+e.getNome()+" Cadastrada com Sucesso!");
        }else {
            System.out.println("Cidade "+c.getNome()+" já Cadastrada!");
            c = cidadeRepository.findCidade(c.getNome());
            System.out.println("Estado "+e.getNome()+" já Cadastrada!");
            e = estadoRepository.findEstado(e.getNome());
        }

        Assert.assertNotNull(c.getCod());
        Assert.assertNotNull(e.getCod());
    }

    @Test
    @DisplayName("TC-02: Deve procurar Cidade pelo código")
    public void ProcurarCidadePorCod(){

        Cidade cidade = cidadeRepository.findByCod(2);

        assertThat(cidade).isNotNull();

        System.out.println("Codigo:"+cidade.getCod()+" Nome: "+cidade.getNome());
    }

    @Test
    @DisplayName("TC-03: Procurar Cidade pelo nome")
    public void ProcurarCidadePorNome(){

        Cidade cidade = cidadeRepository.findByNome("Sao Paulo");

        assertThat(cidade).isNotNull();

        System.out.println("Cidade encontrada com sucesso!\nCodigo: "+cidade.getCod()+" Nome: "+cidade.getNome());
    }

    @Test
    @DisplayName("TC-04: Deletar uma Cidade pelo Id")
    public void DeletarCidadePorId(){

        cidade = cidadeRepository.findByCod(1);
        cidadeRepository.delete(cidade);

        System.out.println("Deletada a Cidade com o Cod:" +cidade.getCod()+" Nome: "+cidade.getNome());

        Cidade cidade1 = null;
        Optional<Cidade> optionalCidade = cidadeRepository.findById(cidade.getCod());

        if(optionalCidade.isPresent()){
            cidade1 = optionalCidade.get();
        }

        assertThat(cidade1).isNull();

    }

    @Test
    @DisplayName("TC-05: Cadastrar Pet com Dono no Sistema")
    public void CadastrarPet(){

        pessoa = new Pessoa();
        pessoa.setCpf("12312312312");
        pessoa.setNome("Victor");
        pessoa.setSexo("MASCULINO");
        pessoa.setTelefone("92992031498");
        pessoa.setDtNascimento("29/11/1992");
        pessoa.setEndereco("Rua Sao lazaro, 12");
        pessoa.setTipoPessoaEnum(TipoPessoaEnum.DOADOR);

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
        System.out.println("Pet Cadastrado com Sucesso! Nome:"+pet.getNome());
        Assert.assertNotNull(pet.getId());


    }

    @Test
    @DisplayName("TC-06: Procurar Pet pelo nome")
    public void procurarPetPorNome(){

        Pet pet = petRepository.findByNome("Rax");
        if (pet.getNome().equals("Rax")){
            System.out.println("Pet encontrado com Sucesso!");
        }
        assertThat(pet).isNotNull();

    }

    @Test
    @DisplayName("TC-07: Deletar pet pelo id")
    public void deletePetPorId(){

        Pet pet = petRepository.findById(5).get();
        petRepository.delete(pet);

        Pet pet1 = null;

        Optional<Pet> optionalPet = petRepository.findById(4);

        if(optionalPet.isPresent()){
            pet1 = optionalPet.get();
        }
        System.out.println("Deletado o Pet com o Cod:" +pet.getId()+" Nome: "+pet.getNome());
        assertThat(pet1).isNull();

    }

    @Test
    @DisplayName("TC-08: Cadastrar Pessoa")
    public void cadastarPessoa(){
        pessoa = new Pessoa();
        pessoa.setCpf("45645645645");
        pessoa.setNome("Rafael");
        pessoa.setSexo("MASCULINO");
        pessoa.setTelefone("9288887777");
        pessoa.setDtNascimento("25/11/1992");
        pessoa.setEndereco("Rua Sao lazaro, 10");
        pessoa.setTipoPessoaEnum(TipoPessoaEnum.ADOTANTE);

        if (pessoaRepository.findPessoa(pessoa.getNome())==null) {
            pessoaRepository.save(pessoa);
        }
        System.out.println("Pessoa cadastrada com Sucesso! Nome:"+pessoa.getNome());
        assertThat(pessoa).isNotNull();
    }

    @Test
    @DisplayName("TC-09: Procurar pessoa pelo Nome")
    public void procurarPessoaPorNome() {
        pessoa = pessoaRepository.findPessoa("Rafael");
        if (pessoa.getNome().equals("Rafael")){
            System.out.println("Pessoa encontrada com Sucesso! Nome:"+pessoa.getNome());
        }
        assertThat(pessoa).isNotNull();
    }

    @Test
    @DisplayName("TC-10: Deletar pessoa pelo Id")
    public void deletarPessoaPorId(){
        pessoa = pessoaRepository.findById(4).get();
        pessoaRepository.delete(pessoa);

        Pessoa pessoa1 = null;

        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(4);

        if(optionalPessoa.isPresent()){
            pessoa1 = optionalPessoa.get();
        }
        System.out.println("Deletada a pessoa com o codigo:" +pessoa.getId()+" Nome: "+pessoa.getNome());
        assertThat(pessoa1).isNull();
    }

}
