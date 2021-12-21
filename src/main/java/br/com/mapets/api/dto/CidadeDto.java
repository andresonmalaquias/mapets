package br.com.mapets.api.dto;

import br.com.mapets.domain.model.Cidade;

public class CidadeDto {

    private String nome;

    private Integer estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public CidadeDto(Cidade cidade){
        this.nome = cidade.getNome();
        this.estado = cidade.getEstado().getCod();
    }

    public CidadeDto() {
    }
}
