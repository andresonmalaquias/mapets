package br.com.mapets.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetDto {
    private String nome;

    private String sexo;

    private String nascimento;

    private Integer adotante;

    private Integer interessado;

    private String personalidade;

    private String porte;

    private boolean podeAcessarRua;

    private boolean larEspacoso;

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

    public LocalDateTime getNascimento() {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dateTime = LocalDate.parse(nascimento, parser).atStartOfDay();
        return dateTime;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public Integer getAdotante() {
        return adotante;
    }

    public void setAdotante(Integer adotante) {
        this.adotante = adotante;
    }

    public Integer getInteressado() {
        return interessado;
    }

    public void setInteressado(Integer interessado) {
        this.interessado = interessado;
    }

    public String getPersonalidade() {
        return personalidade;
    }

    public void setPersonalidade(String personalidade) {
        this.personalidade = personalidade;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public boolean getPodeAcessarRua() {
        return podeAcessarRua;
    }

    public void setPodeAcessarRua(boolean podeAcessarRua) {
        this.podeAcessarRua = podeAcessarRua;
    }

    public boolean getLarEspacoso() {
        return larEspacoso;
    }

    public void setLarEspacoso(boolean larEspacoso) {
        this.larEspacoso = larEspacoso;
    }
}
