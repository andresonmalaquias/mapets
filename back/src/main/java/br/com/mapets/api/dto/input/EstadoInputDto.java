package br.com.mapets.api.dto.input;

import br.com.mapets.domain.model.Estado;

public class EstadoInputDto {
    private Integer cod;

    private String nome;

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

    public Estado build(){
        Estado estado = new Estado();
        estado.setNome(this.nome);

        return estado;
    }

    public Estado buildAlterar(Estado estado){
        estado.setNome(this.nome.length() > 0 ? this.nome : estado.getNome());

        return estado;
    }

    public EstadoInputDto() {
    }
}
