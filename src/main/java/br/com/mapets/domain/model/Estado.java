package br.com.mapets.domain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estado {

    @Id
    @GeneratedValue
    private Integer cod;

    private String nome;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "estado")
    private List<Cidade> cidades;

    public Integer getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(Cidade cidade) {
        if(!this.cidades.contains(cidade)){
            this.cidades.add(cidade);
            cidade.setEstado(this);
        }
    }

    public Estado() {
        this.cidades = new ArrayList();
    }

    public Estado(String nome) {
        this.nome = nome;
        this.cidades = new ArrayList();
    }
}
