package model.temp;

public class OrdineCompletoBean {

    private static final long serialVersionUID = 1L;

    private int id;
    private String emailUtente;
    private String indirizzo;
    private String data;
    private float totale;

    public OrdineCompletoBean() {
        id = -1;
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

    @Override
    public String toString() {
        return "OrdineCompletoBean{" +
                "id=" + id +
                ", emailUtente=" + emailUtente +
                ", indirizzo=" + indirizzo +
                ", data='" + data + '\'' +
                ", totale=" + totale +
                '}';
    }
}
