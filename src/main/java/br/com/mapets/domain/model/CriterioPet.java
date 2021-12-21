package br.com.mapets.domain.model;

import br.com.mapets.domain.model.enuns.PorteEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

//@Embeddable
public class CriterioPet {

    @Enumerated(EnumType.STRING)
    private PorteEnum porte;

    private boolean podeAcessarRua;

    private boolean larEspacoso;

    public CriterioPet(PorteEnum porte, boolean podeAcessarRua, boolean larEspacoso) {
        this.porte = porte;
        this.podeAcessarRua = podeAcessarRua;
        this.larEspacoso = larEspacoso;
    }

    public CriterioPet() {
    }
}
