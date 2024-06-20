package model.dao;

import model.bean.ProdottoCarrelloBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ProdottoCarrelloDao implements IBeanDAO<ProdottoCarrelloBean> {

    private static final String TABLE_NAME = "Prodotti_Carrelli";
    private DataSource ds;

    public ProdottoCarrelloDao(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public synchronized void doSave(ProdottoCarrelloBean bean) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO " + TABLE_NAME + " (idCarrello, idProdotto, Prezzo, Quantita) VALUES (?, ?, ?, ?);";

        try
        {
            conn = ds.getConnection();

            ps = conn.prepareStatement(sql);

            ps.setInt(1, bean.getIdCarrello());
            ps.setInt(2, bean.getIdProdotto());
            ps.setDouble(3, bean.getPrezzo());
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
        return false;
    }

    public synchronized boolean doDelete(int idCarrello, int idProdotto) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        int result = 0;

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE idCarrello = ? AND idProdotto = ?;";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, idCarrello);
            ps.setInt(2, idProdotto);

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
    public ProdottoCarrelloBean doRetrieveByKey(int id) throws SQLException {
        return null;
    }

    public synchronized ProdottoCarrelloBean doRetrieveByKey(int idCarrello, int idProdotto) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        ProdottoCarrelloBean bean = null;

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE idCarrello = ? AND idProdotto = ?;";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, idCarrello);
            ps.setInt(2, idProdotto);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                bean = new ProdottoCarrelloBean();
                bean.setId(rs.getInt("id"));
                bean.setIdCarrello(rs.getInt("idCarrello"));
                bean.setIdProdotto(rs.getInt("idProdotto"));
                bean.setPrezzo(rs.getFloat("Prezzo"));
                bean.setQuantita(rs.getInt("Quantita"));
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
    public synchronized Collection<ProdottoCarrelloBean> doRetrieveAll(String order) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        Collection<ProdottoCarrelloBean> beans = new LinkedList<>();

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
                ProdottoCarrelloBean bean = new ProdottoCarrelloBean();

                bean.setId(rs.getInt("id"));
                bean.setIdCarrello(rs.getInt("idCarrello"));
                bean.setIdProdotto(rs.getInt("idProdotto"));
                bean.setPrezzo(rs.getFloat("Prezzo"));
                bean.setQuantita(rs.getInt("Quantita"));

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

    public synchronized void doUpdate(ProdottoCarrelloBean bean) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE " + TABLE_NAME + " SET Quantita = ?, Prezzo = ? WHERE idCarrello = ? AND idProdotto = ?;";

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, bean.getQuantita());
            ps.setDouble(2, bean.getPrezzo());
            ps.setInt(3, bean.getIdCarrello());
            ps.setInt(4, bean.getIdProdotto());

            ps.executeUpdate();

        } finally {
            try {
                if (ps != null)
                    ps.close();
            } finally {
                if (conn != null)
                    conn.close();
            }
        }
    }

    public synchronized List<ProdottoCarrelloBean> doRetrieveAllByCartKey(int idCarrello) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE idCarrello = ?;";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, idCarrello);

            ResultSet rs = ps.executeQuery();

            List<ProdottoCarrelloBean> beans = new LinkedList<>();

            while (rs.next())
            {
                ProdottoCarrelloBean bean = new ProdottoCarrelloBean();

                bean.setId(rs.getInt("id"));
                bean.setIdCarrello(rs.getInt("idCarrello"));
                bean.setIdProdotto(rs.getInt("idProdotto"));
                bean.setPrezzo(rs.getFloat("Prezzo"));
                bean.setQuantita(rs.getInt("Quantita"));

                beans.add(bean);
            }

            return beans;
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

}
