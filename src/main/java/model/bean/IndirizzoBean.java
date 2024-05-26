package model.bean;

import java.io.Serializable;

public class IndirizzoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String via;
    private String citta;
    private String provincia;
    private String cap;
    private String nazione;

    public IndirizzoBean() {
        id = -1;
        via = "";
        citta = "";
        provincia = "";
        cap = "";
        nazione = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    @Override
    public String toString() {
        return "IndirizzoBean{" +
                "id=" + id +
                ", via='" + via + '\'' +
                ", citta='" + citta + '\'' +
                ", provincia='" + provincia + '\'' +
                ", cap='" + cap + '\'' +
                ", nazione='" + nazione + '\'' +
                '}';
    }
}
