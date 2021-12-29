package br.com.mapets.api.dto.output;

import br.com.mapets.domain.model.Cidade;

import java.util.ArrayList;
import java.util.List;

public class CidadeOutputDto {

    private  Integer cod;

    private String nome;

    private String estado;

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public static List<CidadeOutputDto> buildCidadesDto(List<Cidade> cidades){
        List<CidadeOutputDto> cidadesDto = new ArrayList();
        for (Cidade c: cidades) {
            CidadeOutputDto cidadeOutputDto = new CidadeOutputDto(c);
            cidadesDto.add(cidadeOutputDto);
        }

        return cidadesDto;
    }

    public CidadeOutputDto(Cidade cidade){
        this.cod = cidade.getCod();
        this.nome = cidade.getNome();
        this.estado = cidade.getEstado().getNome();
    }

    public CidadeOutputDto() {
    }
}
