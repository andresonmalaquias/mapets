package br.com.mapets.domain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cidade {

    @Id
    @GeneratedValue
    private Integer cod;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    private Estado estado;

    @OneToMany(mappedBy = "cidade",cascade = CascadeType.PERSIST)
    private List<Pessoa> pessoas;

    public Integer getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(Pessoa pessoa) {
        if(!this.pessoas.contains(pessoa)){
            this.pessoas.add(pessoa);
            pessoa.setCidade(this);
        }
        this.pessoas = pessoas;
    }

    public Cidade(String nome, Estado estado) {
        this.nome = nome;
        this.estado = estado;
        this.pessoas = new ArrayList();
    }

    public Cidade() {
        this.pessoas = new ArrayList();
    }
}
