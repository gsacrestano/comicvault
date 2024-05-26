package model.bean;

import java.io.Serializable;

public class OrdineBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int idUtente;
    private int idIndirizzo;
    private String data;
    private float totale;

    public OrdineBean() {
        id = -1;
        idUtente = -1;
        idIndirizzo = -1;
        data = "";
        totale = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }

    @Override
    public String toString() {
        return "OrdineBean{" +
                "id=" + id +
                ", idUtente=" + idUtente +
                ", idIndirizzo=" + idIndirizzo +
                ", data='" + data + '\'' +
                ", totale=" + totale +
                '}';
    }
}
