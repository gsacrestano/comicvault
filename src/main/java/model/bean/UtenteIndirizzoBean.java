package model.bean;

import java.io.Serializable;

public class UtenteIndirizzoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idUtente;
    private int idIndirizzo;
    private int isDefault;

    public UtenteIndirizzoBean() {
        idUtente = -1;
        idIndirizzo = -1;
        isDefault = 0;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdIndirizzo() {
        return idIndirizzo;
    }

    public void setIdIndirizzo(int idIndirizzo) {
        this.idIndirizzo = idIndirizzo;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "UtenteIndirizzoBean{" +
                "idUtente=" + idUtente +
                ", idIndirizzo=" + idIndirizzo +
                ", isDefault=" + isDefault +
                '}';
    }
}
