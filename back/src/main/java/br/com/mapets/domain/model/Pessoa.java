package br.com.mapets.domain.model;

import br.com.mapets.domain.model.enuns.TipoPessoaEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue
    private Integer id;

    private  String cpf;

    private String nome;

    private String sexo;

    private  String telefone;

    private LocalDateTime dtNascimento;

    private String endereco;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Pet> pet;

    @Enumerated(EnumType.STRING)
    private TipoPessoaEnum tipoPessoaEnum;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cidade cidade;

    public Integer getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDtNascimento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ROOT);
        return dtNascimento.format(formatter);
    }

    public void setDtNascimento(String dtNascimento) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dtNascimento = LocalDate.parse(dtNascimento, parser).atStartOfDay();
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
        }
    }

    public TipoPessoaEnum getTipoPessoaEnum() {
        return tipoPessoaEnum;
    }

    public void setTipoPessoaEnum(TipoPessoaEnum tipoPessoaEnum) {
        this.tipoPessoaEnum = tipoPessoaEnum;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
        this.cidade.setPessoas(this);
    }

    public Pessoa() {
        this.pet = new ArrayList();
    }

    public Pessoa(String cpf, String nome, String sexo, String telefone, LocalDateTime dtNascimento, String endereco, TipoPessoaEnum tipoPessoaEnum, Cidade cidade) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.telefone = telefone;
        this.dtNascimento = dtNascimento;
        this.endereco = endereco;
        this.pet = new ArrayList();
        this.tipoPessoaEnum = tipoPessoaEnum;
        this.cidade = cidade;
    }

    public Pessoa(String cpf, String nome, String endereco, TipoPessoaEnum tipoPessoaEnum, String sexo, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoPessoaEnum = tipoPessoaEnum;
        this.sexo = sexo;
        this.telefone = telefone;

    }
}
