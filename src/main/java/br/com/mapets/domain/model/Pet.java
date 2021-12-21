package br.com.mapets.domain.model;

import br.com.mapets.domain.model.enuns.PersonalidadeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pet {

    @Id
    @GeneratedValue
    private Integer id;

    private String nome;

    private String sexo;

    @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime nascimento;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Pessoa adotante;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Pessoa> interessados;

    @ElementCollection(targetClass = PersonalidadeEnum.class)
    @Enumerated(EnumType.STRING)
    private List<PersonalidadeEnum> personalidade;

    @Embedded
    private CriterioPet criterio;

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

    public LocalDateTime getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDateTime nascimento) {
        this.nascimento = nascimento;
    }

    public Pessoa getAdotante() {
        return adotante;
    }

    public void setAdotante(Pessoa adotante) {
        this.adotante = adotante;
        adotante.setPet(this);
    }

    public List<Pessoa> getInteressados() {
        return interessados;
    }

    public void setInteressados(Pessoa interessado) {
        this.interessados.add(interessado);
    }

    public List<PersonalidadeEnum> getPersonalidade() {
        return personalidade;
    }

    public void setPersonalidade(PersonalidadeEnum personalidade) {
        this.personalidade.add(personalidade);
    }

    public CriterioPet getCriterio() {
        return criterio;
    }

    public void setCriterio(CriterioPet criterio) {
        this.criterio = criterio;
    }

    public Pet(String nome, String sexo, LocalDateTime nascimento, Pessoa adotante, Pessoa interessado, PersonalidadeEnum personalidade, CriterioPet criterio) {
        this.interessados = new ArrayList();
        this.personalidade = new ArrayList();
        this.nome = nome;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.adotante = adotante;
        this.interessados.add(interessado);
        this.personalidade.add(personalidade);
        this.criterio = criterio;
    }

    public Pet() {
        this.interessados = new ArrayList();
        this.personalidade = new ArrayList();
    }


}
