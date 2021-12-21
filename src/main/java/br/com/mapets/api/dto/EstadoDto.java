package br.com.mapets.api.dto;

public class EstadoDto {
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoDto(String nome) {
        this.nome = nome;
    }

    public EstadoDto() {
    }
}
