package model.dao;

import model.bean.ProdottoBean;
import model.bean.ProdottoOrdineBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ProdottoOrdineDao implements IBeanDAO<ProdottoOrdineBean> {

    private static final String TABLE_NAME = "Prodotti_Ordini";
    private DataSource ds;

    public ProdottoOrdineDao(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public void doSave(ProdottoOrdineBean bean) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO " + TABLE_NAME + " (idOrdine, idProdotto, Prezzo, Quantita) VALUES (?, ?, ?, ?);";

        try
        {
            conn = ds.getConnection();

            ps = conn.prepareStatement(sql);

            ps.setInt(1, bean.getIdOrdine());
            ps.setInt(2, bean.getIdProdotto());
            ps.setFloat(3, bean.getPrezzo());
            ps.setInt(4, bean.getQuantita());

            ps.executeUpdate();
        }
        finally
        {
            try
            {
                if (ps != null)
                    ps.close();
            }
            finally
            {
                if (conn != null)
                    conn.close();
            }
        }
    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        int result = 0;

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?;";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            result = ps.executeUpdate();
        }
        finally
        {
            try
            {
                if (ps != null)
                    ps.close();
            }
            finally
            {
                if (conn != null)
                    conn.close();
            }
        }
        return (result != 0);
    }

    @Override
    public ProdottoOrdineBean doRetrieveByKey(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        ProdottoOrdineBean bean = new ProdottoOrdineBean();

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                bean.setId(rs.getInt("id"));
                bean.setIdOrdine(rs.getInt("idOrdine"));
                bean.setIdProdotto(rs.getInt("idProdotto"));
                bean.setPrezzo(rs.getFloat("prezzo"));
                bean.setQuantita(rs.getInt("quantita"));
            }
        }
        finally
        {
            try
            {
                if (ps != null)
                    ps.close();
            }
            finally
            {
                if (conn != null)
                    conn.close();
            }
        }
        return bean;
    }

    @Override
    public Collection<ProdottoOrdineBean> doRetrieveAll(String order) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        Collection<ProdottoOrdineBean> beans = new LinkedList<>();

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE deleted_at IS NULL;";

        if (order != null && !order.isEmpty())
            sql += " ORDER BY ?";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, order);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                ProdottoOrdineBean bean = new ProdottoOrdineBean();

                bean.setId(rs.getInt("id"));
                bean.setIdOrdine(rs.getInt("idOrdine"));
                bean.setIdProdotto(rs.getInt("idProdotto"));
                bean.setPrezzo(rs.getFloat("prezzo"));
                bean.setQuantita(rs.getInt("quantita"));

                beans.add(bean);
            }
        }
        finally
        {
            try
            {
                if (ps != null)
                    ps.close();
            }
            finally
            {
                if (conn != null)
                    conn.close();
            }
        }
        return beans;
    }

    public List<ProdottoBean> doRetrieveProductsByOrderId(int orderId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        List<ProdottoBean> ordini = new LinkedList<>();

        String sql = "SELECT * FROM " + TABLE_NAME + " PO INNER JOIN Prodotti P ON PO.idProdotto = P.id where PO.idOrdine = ?";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                ProdottoBean ordine = new ProdottoBean();

                ordine.setId(rs.getInt("idProdotto"));
                ordine.setNome(rs.getString("Nome"));
                ordine.setDescrizione(rs.getString("Descrizione"));
                ordine.setIsbn(rs.getString("Isbn"));
                ordine.setPrezzo(rs.getFloat("Prezzo"));
                ordine.setQuantita(rs.getInt("Quantita"));
                ordine.setImage_path(rs.getString("image_path"));

                ordini.add(ordine);
            }
        }
        finally
        {
            try
            {
                if (ps != null)
                    ps.close();
            }
            finally
            {
                if (conn != null)
                    conn.close();
            }
        }

        return ordini;
    }
}
