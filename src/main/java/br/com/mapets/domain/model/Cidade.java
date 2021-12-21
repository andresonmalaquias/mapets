package br.com.mapets.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cidade {

    @Id
    @GeneratedValue
    private Integer cod;

    private String nome;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Estado estado;

    @OneToMany(mappedBy = "cidade",cascade = CascadeType.PERSIST)
    private List<Pessoa> pessoas;

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

    public Cidade(String nome, Estado estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public Cidade() {
    }
}
