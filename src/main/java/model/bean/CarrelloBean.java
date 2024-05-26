package model.bean;

import java.io.Serializable;

public class CarrelloBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int idUtente;

    public CarrelloBean() {
        id = -1;
        idUtente = -1;
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

    @Override
    public String toString() {
        return "CarrelloBean{" +
                "id=" + id +
                ", idUtente=" + idUtente +
                '}';
    }
}
