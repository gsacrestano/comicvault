package model.bean;

import java.io.Serializable;

public class ProdottoCarrelloBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int idCarrello;
    private int idProdotto;
    private float prezzo;
    private int quantita;

    public ProdottoCarrelloBean() {
        id = -1;
        idCarrello = -1;
        idProdotto = -1;
        prezzo = 0;
        quantita = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(int idCarrello) {
        this.idCarrello = idCarrello;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public String toString() {
        return "ProdottoCarrelloBean{" +
                "id=" + id +
                ", idCarrello=" + idCarrello +
                ", idProdotto=" + idProdotto +
                ", prezzo=" + prezzo +
                ", quantita=" + quantita +
                '}';
    }
}
