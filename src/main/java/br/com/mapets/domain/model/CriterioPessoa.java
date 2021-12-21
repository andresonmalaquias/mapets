package br.com.mapets.domain.model;

import br.com.mapets.domain.model.enuns.MoradiaEnum;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class CriterioPessoa {

    @Enumerated(EnumType.STRING)
    private MoradiaEnum tipoMoradia;

    private boolean temQuintalVaranda;

    private int qtdSalMinRenda;

    private boolean telado;

    private boolean temPet;

    private boolean jaTevePet;

    private boolean criacaoIndoor;

    private boolean acessoRua;

    public MoradiaEnum getTipoMoradia() {
        return tipoMoradia;
    }

    public void setTipoMoradia(MoradiaEnum tipoMoradia) {
        this.tipoMoradia = tipoMoradia;
    }

    public boolean isTemQuintalVaranda() {
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

    public boolean isTelado() {
        return telado;
    }

    public void setTelado(boolean telado) {
        this.telado = telado;
    }

    public boolean isTemPet() {
        return temPet;
    }

    public void setTemPet(boolean temPet) {
        this.temPet = temPet;
    }

    public boolean isJaTevePet() {
        return jaTevePet;
    }

    public void setJaTevePet(boolean jaTevePet) {
        this.jaTevePet = jaTevePet;
    }

    public boolean isCriacaoIndoor() {
        return criacaoIndoor;
    }

    public void setCriacaoIndoor(boolean criacaoIndoor) {
        this.criacaoIndoor = criacaoIndoor;
    }

    public boolean isAcessoRua() {
        return acessoRua;
    }

    public void setAcessoRua(boolean acessoRua) {
        this.acessoRua = acessoRua;
    }

    public CriterioPessoa(MoradiaEnum tipoMoradia, boolean temQuintalVaranda, int qtdSalMinRenda, boolean telado, boolean temPet, boolean jaTevePet, boolean criacaoIndoor, boolean acessoRua) {
        this.tipoMoradia = tipoMoradia;
        this.temQuintalVaranda = temQuintalVaranda;
        this.qtdSalMinRenda = qtdSalMinRenda;
        this.telado = telado;
        this.temPet = temPet;
        this.jaTevePet = jaTevePet;
        this.criacaoIndoor = criacaoIndoor;
        this.acessoRua = acessoRua;
    }

    public CriterioPessoa() {
    }
}
