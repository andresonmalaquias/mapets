package br.com.mapets.api.dto.input;

import br.com.mapets.domain.model.Pet;
import br.com.mapets.domain.model.enuns.PersonalidadeEnum;
import br.com.mapets.domain.model.enuns.PorteEnum;
import br.com.mapets.domain.model.enuns.TipoPetEnum;
import br.com.mapets.domain.repository.PessoaRepository;

public class PetInputDto {
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

    private Integer responsavel;

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

    public Integer getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Integer responsavel) {
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
        pet.setResponsavel(pessoaRepository.findById(this.responsavel).get());

        return pet;
    }

    public Pet buildAlterar(Pet pet){
        pet.setNome(this.nome.length() > 0 ? this.nome : pet.getNome());
        pet.setSexo(this.sexo.length() > 0 ? this.sexo : pet.getSexo());
        pet.setIdade_meses(this.idadeMeses != null ? this.idadeMeses : pet.getIdade_meses());
        pet.setTipo(this.tipo.length() > 0 ? TipoPetEnum.valueOf(this.tipo.toUpperCase()) : pet.getTipo());
        pet.setPersonalidade(this.personalidade.length() > 0 ? PersonalidadeEnum.valueOf(this.personalidade.toUpperCase()) : pet.getPersonalidade());
        pet.setPorte(this.porte.length() > 0 ? PorteEnum.valueOf(this.porte.toUpperCase()) : pet.getPorte());
        pet.setIndoor(this.indoor);

        return pet;
    }

    public PetInputDto() {
        //this.personalidade = new ArrayList();
    }
}
