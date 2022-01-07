package br.com.mapets.api.dto.output;

import br.com.mapets.domain.model.Pet;
import br.com.mapets.domain.model.enuns.PersonalidadeEnum;
import br.com.mapets.domain.model.enuns.PorteEnum;
import br.com.mapets.domain.model.enuns.TipoPetEnum;
import br.com.mapets.domain.repository.PessoaRepository;

import java.util.ArrayList;
import java.util.List;

public class PetOutputDto {
    private Integer id;

    private String nome;

    private String sexo;

    private Integer idadeMeses;

    private String tipo;

    /*@JsonProperty("personalidade")
    private ArrayList<String> personalidade;*/

    private String personalidade;

    private String porte;

    private boolean indoor;

    private String responsavel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getIdadeMeses() {
        return idadeMeses;
    }

    public void setIdadeMeses(Integer idadeMeses) {
        this.idadeMeses = idadeMeses;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /*public ArrayList<String> getPersonalidade() {
        return personalidade;
    }

    public void setPersonalidade(String personalidade) {
        this.personalidade.add(personalidade);
    }*/

    public String getPersonalidade() {
        return personalidade;
    }

    public void setPersonalidade(String personalidade) {
        this.personalidade = personalidade;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public boolean getIndoor() {
        return indoor;
    }

    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Pet build(PessoaRepository pessoaRepository){
        Pet pet = new Pet();

        pet.setNome(this.nome);
        pet.setSexo(this.sexo);
        pet.setIdade_meses(this.idadeMeses);
        pet.setTipo(TipoPetEnum.valueOf(this.tipo.toUpperCase()));
        /*for (String p: this.personalidade) {
            pet.setPersonalidade(PersonalidadeEnum.valueOf(p.toUpperCase()));
        }*/
        pet.setPersonalidade(PersonalidadeEnum.valueOf(this.personalidade.toUpperCase()));
        pet.setPorte(PorteEnum.valueOf(this.porte.toUpperCase()));
        pet.setIndoor(this.indoor);
        pet.setResponsavel(pessoaRepository.findPessoa(this.responsavel));

        return pet;
    }

    public static List<PetOutputDto> buildPetDto(List<Pet> pets){
        List<PetOutputDto> petsDto = new ArrayList();
        for (Pet p: pets) {
            PetOutputDto petInputDto = new PetOutputDto(p);
            petsDto.add(petInputDto);
        }

        return petsDto;
    }


    public PetOutputDto(Pet pet) {
        //this.personalidade = new ArrayList();
        this.id = pet.getId();
        this.nome = pet.getNome();
        this.sexo = pet.getSexo();
        this.idadeMeses = pet.getIdade_meses();
        this.tipo = pet.getTipo().toString();
        /*for (PersonalidadeEnum p: pet.getPersonalidade()) {
            this.personalidade.add(p.toString());
        }*/
        this.personalidade = pet.getPersonalidade().toString();
        this.porte = pet.getPorte().toString();
        this.indoor = pet.getIndoor();
        this.responsavel = pet.getResponsavel().getNome();
    }

    public PetOutputDto() {
        //this.personalidade = new ArrayList();
    }
}
