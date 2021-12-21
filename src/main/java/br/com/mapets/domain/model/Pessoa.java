package br.com.mapets.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue
    private Integer id;

    private  String cpf;

    private String sexo;

    private  String telefone;

    @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dtNascimento;

    private String endereco;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "adotante")
    private List<Pet> pet;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cidade cidade;

    @Embedded
    private CriterioPessoa criterio;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDateTime dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Pet> getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        if(!this.pet.contains(pet)){
            this.pet.add(pet);
            pet.setAdotante(this);
        }

    }

    public CriterioPessoa getCriterio() {
        return criterio;
    }

    public void setCriterio(CriterioPessoa criterio) {
        this.criterio = criterio;
    }

    /*public Pessoa() {
        this.pet = new ArrayList();
    }*/

    public Pessoa(String cpf, String sexo, String telefone, LocalDateTime dtNascimento, String endereco, Cidade cidade, CriterioPessoa criterio) {
        this.cpf = cpf;
        this.sexo = sexo;
        this.telefone = telefone;
        this.dtNascimento = dtNascimento;
        this.endereco = endereco;
        this.cidade = cidade;
        this.criterio = criterio;
    }

    public Pessoa() {
    }

    public Pessoa(String cpf, String sexo, String telefone, LocalDateTime dtNascimento, String endereco) {
        this.cpf = cpf;
        this.sexo = sexo;
        this.telefone = telefone;
        this.dtNascimento = dtNascimento;
        this.endereco = endereco;
    }
}
