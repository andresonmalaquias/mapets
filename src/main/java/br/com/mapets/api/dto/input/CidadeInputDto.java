package br.com.mapets.api.dto.input;

import br.com.mapets.domain.model.Cidade;
import br.com.mapets.domain.repository.EstadoRepository;

public class CidadeInputDto {

    private  Integer cod;

    private String nome;

    private Integer estado;

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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Cidade build(EstadoRepository estadoRepository){
        Cidade cidade = new Cidade();
        cidade.setNome(this.nome);
        cidade.setEstado(estadoRepository.findById(estado).get());

        return cidade;
    }

    public Cidade buildAlterar(Cidade cidade, EstadoRepository estadoRepository){
        cidade.setNome(this.nome.length() > 0 ? this.nome : cidade.getNome());
        cidade.setEstado(estadoRepository.findById(this.estado).get());

        return cidade;
    }

    public CidadeInputDto() {
    }
}
