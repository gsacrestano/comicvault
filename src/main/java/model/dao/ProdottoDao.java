package model.dao;

import model.bean.ProdottoBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class ProdottoDao implements IBeanDAO<ProdottoBean> {

    private static final String TABLE_NAME = "Prodotti";
    private DataSource ds;

    public ProdottoDao(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public synchronized void doSave(ProdottoBean bean) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO " + TABLE_NAME + " (Nome, Descrizione, Isbn, Prezzo, Quantita, image_path) VALUES (?, ?, ?, ?, ?, ?);";

        try
        {
            conn = ds.getConnection();

            ps = conn.prepareStatement(sql);

            ps.setString(1, bean.getNome());
            ps.setString(2, bean.getDescrizione());
            ps.setString(3, bean.getIsbn());
            ps.setDouble(4, bean.getPrezzo());
            ps.setInt(5, bean.getQuantita());
            ps.setString(6, bean.getImage_path());

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
    public synchronized boolean doDelete(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        int result = 0;

        String sql = "UPDATE " + TABLE_NAME + " SET deleted_at = NOW() WHERE id = ?;";

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
    public synchronized ProdottoBean doRetrieveByKey(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        ProdottoBean bean = new ProdottoBean();

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ? AND deleted_at IS NULL;";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                bean.setId(id);
                bean.setNome(rs.getString("Nome"));
                bean.setDescrizione(rs.getString("Descrizione"));
                bean.setIsbn(rs.getString("Isbn"));
                bean.setPrezzo(rs.getFloat("Prezzo"));
                bean.setQuantita(rs.getInt("Quantita"));
                bean.setImage_path(rs.getString("image_path"));
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
    public synchronized Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        Collection<ProdottoBean> beans = new LinkedList<>();

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
                ProdottoBean bean = new ProdottoBean();

                bean.setId(rs.getInt("id"));
                bean.setNome(rs.getString("Nome"));
                bean.setDescrizione(rs.getString("Descrizione"));
                bean.setIsbn(rs.getString("Isbn"));
                bean.setPrezzo(rs.getFloat("Prezzo"));
                bean.setQuantita(rs.getInt("Quantita"));
                bean.setImage_path(rs.getString("image_path"));

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
}
