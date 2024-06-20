package model.temp;

import model.bean.ProdottoBean;

import java.util.List;

public class OrdineCompletoBean {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nomeUtente;
    private String cognomeUtente;
    private String emailUtente;
    private String indirizzo;
    private String data;
    private float totale;
    private List<ProdottoBean> prodotti;

    public OrdineCompletoBean() {
        id = -1;
        nomeUtente = "";
        cognomeUtente = "";
        emailUtente = "";
        indirizzo = "";
        data = "";
        totale = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailUtente() {
        return emailUtente;
    }

    public void setEmailUtente(String emailUtente) {
        this.emailUtente = emailUtente;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
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

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getCognomeUtente() {
        return cognomeUtente;
    }

    public void setCognomeUtente(String cognomeUtente) {
        this.cognomeUtente = cognomeUtente;
    }

    public List<ProdottoBean> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<ProdottoBean> prodotti) {
        this.prodotti = prodotti;
    }

    @Override
    public String toString() {
        return "OrdineCompletoBean{" +
                "id=" + id +
                ", nomeUtente='" + nomeUtente + '\'' +
                ", cognomeUtente='" + cognomeUtente + '\'' +
                ", emailUtente='" + emailUtente + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", data='" + data + '\'' +
                ", totale=" + totale +
                ", prodotti=" + prodotti +
                '}';
    }
}
