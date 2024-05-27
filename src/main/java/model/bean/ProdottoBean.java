package model.bean;

import java.io.Serializable;

public class ProdottoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String descrizione;
    private String isbn;
    private float prezzo;
    private int quantita;
    private String image_path;

    public ProdottoBean() {
        id = -1;
        nome = "";
        descrizione = "";
        isbn = "";
        prezzo = 0;
        quantita = 0;
        image_path = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    @Override
    public String toString() {
        return "ProdottoBean{" +
                "serialVersionUID=" + serialVersionUID +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", isbn='" + isbn + '\'' +
                ", prezzo=" + prezzo +
                ", quantita=" + quantita +
                ", image_path='" + image_path + '\'' +
                '}';
    }
}
