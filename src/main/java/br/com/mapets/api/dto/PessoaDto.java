package br.com.mapets.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PessoaDto {

    private  String cpf;

    private String sexo;

    private  String telefone;

    private String dtNascimento;

    private String endereco;

    private Integer cidade;

    private String tipoMoradia;

    private boolean temQuintalVaranda;

    private int qtdSalMinRenda;

    private boolean telado;

    private boolean temPet;

    private boolean jaTevePet;

    private boolean criacaoIndoor;

    private boolean acessoRua;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDtNascimento() {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dateTime = LocalDate.parse(dtNascimento, parser).atStartOfDay();
        return dateTime;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getCidade() {
        return cidade;
    }

    public void setCidade(Integer cidade) {
        this.cidade = cidade;
    }

    public String getTipoMoradia() {
        return tipoMoradia;
    }

    public void setTipoMoradia(String tipoMoradia) {
        this.tipoMoradia = tipoMoradia;
    }

    public boolean getTemQuintalVaranda() {
        return temQuintalVaranda;
    }

    public void setTemQuintalVaranda(boolean temQuintalVaranda) {
        this.temQuintalVaranda = temQuintalVaranda;
    }

    public int getQtdSalMinRenda() {
        return qtdSalMinRenda;
    }

    public void setQtdSalMinRenda(int qtdSalMinRenda) {
        this.qtdSalMinRenda = qtdSalMinRenda;
    }

    public boolean getTelado() {
        return telado;
    }

    public void setTelado(boolean telado) {
        this.telado = telado;
    }

    public boolean getTemPet() {
        return temPet;
    }

    public void setTemPet(boolean temPet) {
        this.temPet = temPet;
    }

    public boolean getJaTevePet() {
        return jaTevePet;
    }

    public void setJaTevePet(boolean jaTevePet) {
        this.jaTevePet = jaTevePet;
    }

    public boolean getCriacaoIndoor() {
        return criacaoIndoor;
    }

    public void setCriacaoIndoor(boolean criacaoIndoor) {
        this.criacaoIndoor = criacaoIndoor;
    }

    public boolean getAcessoRua() {
        return acessoRua;
    }

    public void setAcessoRua(boolean acessoRua) {
        this.acessoRua = acessoRua;
    }
}
