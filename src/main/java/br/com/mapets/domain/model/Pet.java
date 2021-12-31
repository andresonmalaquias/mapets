package br.com.mapets.domain.model;

import br.com.mapets.domain.model.enuns.PersonalidadeEnum;
import br.com.mapets.domain.model.enuns.PorteEnum;
import br.com.mapets.domain.model.enuns.TipoPetEnum;

import javax.persistence.*;

@Entity
public class Pet {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sexo;

    @Column(nullable = false)
    private int idade_meses;

    @Column(nullable = false)
    private TipoPetEnum tipo;

    /*@ElementCollection(targetClass = PersonalidadeEnum.class)
    @Enumerated(EnumType.STRING)
    private List<PersonalidadeEnum> personalidade;*/

    @Column(nullable = false)
    private PersonalidadeEnum personalidade;

    @Column(nullable = false)
    private PorteEnum porte;

    @Column(nullable = false)
    private boolean indoor;

    @OneToOne(cascade = CascadeType.PERSIST, optional = false)
    private Pessoa responsavel;

    public Integer getId() {
        return id;
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

    public int getIdade_meses() {
        return idade_meses;
    }

    public void setIdade_meses(int idade_meses) {
        this.idade_meses = idade_meses;
    }

    public TipoPetEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoPetEnum tipo) {
        this.tipo = tipo;
    }

    /*public List<PersonalidadeEnum> getPersonalidade() {
        return personalidade;
    }

    public void setPersonalidade(List<PersonalidadeEnum> personalidade) {
        this.personalidade = personalidade;
    }*/

    public PersonalidadeEnum getPersonalidade() {
        return personalidade;
    }

    public void setPersonalidade(PersonalidadeEnum personalidade) {
        this.personalidade = personalidade;
    }

    public PorteEnum getPorte() {
        return porte;
    }

    public void setPorte(PorteEnum porte) {
        this.porte = porte;
    }

    public boolean getIndoor() {
        return indoor;
    }

    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }

    public Pessoa getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Pessoa responsavel) {
        this.responsavel = responsavel;
    }

    public Pet() {
        //this.personalidade = new ArrayList();
    }

    public Pet(String nome, String sexo, int idade_meses, TipoPetEnum tipo, PersonalidadeEnum personalidade, PorteEnum porte, boolean indoor, Pessoa responsavel) {
        //this.personalidade = new ArrayList();
        this.nome = nome;
        this.sexo = sexo;
        this.idade_meses = idade_meses;
        this.tipo = tipo;
        this.personalidade = personalidade;
        this.porte = porte;
        this.indoor = indoor;
        this.responsavel = responsavel;
    }
}
