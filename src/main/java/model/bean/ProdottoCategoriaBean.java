package model.bean;

import java.io.Serializable;

public class ProdottoCategoriaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idProdotto;
    private int idCategoria;

    public ProdottoCategoriaBean() {
        idProdotto = -1;
        idCategoria = -1;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "ProdottoCategoriaBean{" +
                "idProdotto=" + idProdotto +
                ", idCategoria=" + idCategoria +
                '}';
    }
}
