package br.com.mapets.api.dto.output;

import br.com.mapets.domain.model.Estado;

import java.util.ArrayList;
import java.util.List;

public class EstadoOutputDto {
    private Integer cod;

    private String nome;

    private List<CidadeOutputDto> cidades;

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<CidadeOutputDto> getCidades() {
        return cidades;
    }

    public void setCidades(List<CidadeOutputDto> cidades) {
        this.cidades = cidades;
    }

    public Estado build(){
        Estado estado = new Estado();
        estado.setNome(this.nome);

        return estado;
    }

    public static List<EstadoOutputDto> buildComCidadesDto(List<Estado> estados){
        List<EstadoOutputDto> estadosDto = new ArrayList();
        for (Estado e: estados) {
            EstadoOutputDto estadoInputDto = new EstadoOutputDto(e);
            estadoInputDto.setCidades(CidadeOutputDto.buildCidadesDto(e.getCidades()));
            estadosDto.add(estadoInputDto);
        }

        return estadosDto;
    }

    public EstadoOutputDto(Estado estado) {
        this.cidades = new ArrayList();
        this.cod = estado.getCod();
        this.nome = estado.getNome();
        this.cidades = CidadeOutputDto.buildCidadesDto(estado.getCidades());
    }

    public EstadoOutputDto() {
        this.cidades = new ArrayList();
    }
}
