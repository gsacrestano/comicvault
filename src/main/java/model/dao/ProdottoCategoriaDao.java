package model.dao;

import model.bean.ProdottoCategoriaBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class ProdottoCategoriaDao implements IBeanDAO<ProdottoCategoriaBean> {

    private static final String TABLE_NAME = "Prodotti_Categorie";
    private DataSource ds;

    public ProdottoCategoriaDao(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public synchronized void doSave(ProdottoCategoriaBean bean) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO " + TABLE_NAME + " (idProdotto, idCategoria) VALUES (?, ?);";

        try
        {
            conn = ds.getConnection();

            ps = conn.prepareStatement(sql);

            ps.setInt(1, bean.getIdProdotto());
            ps.setInt(2, bean.getIdCategoria());

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

    public synchronized boolean doDelete(ProdottoCategoriaBean bean) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        int result = 0;

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE idProdotto = ? AND idCategoria = ?;";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, bean.getIdProdotto());
            ps.setInt(2, bean.getIdCategoria());

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
    public synchronized boolean doDelete(int id) throws SQLException {
        System.err.println("ATTENZIONE: Richiamare il metodo doDelete(ProdottoCategoriaBean bean)");
        return false;
    }

    @Override
    public synchronized ProdottoCategoriaBean doRetrieveByKey(int id) throws SQLException {
        System.err.println("ATTENZIONE: Richiamare il metodo doRetrieveAll(int idProdotto, String order)");
        return null;
    }

    public synchronized Collection<ProdottoCategoriaBean> doRetrieveAll(int idProdotto, String order) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        Collection<ProdottoCategoriaBean> beans = new LinkedList<>();

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE idProdotto = ?;";

        if (order != null && !order.isEmpty())
            sql += " ORDER BY ?";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, idProdotto);
            ps.setString(2, order);


            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                ProdottoCategoriaBean bean = new ProdottoCategoriaBean();

                bean.setIdProdotto(rs.getInt("idProdotto"));
                bean.setIdCategoria(rs.getInt("idCategoria"));

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

    @Override
    public synchronized Collection<ProdottoCategoriaBean> doRetrieveAll(String order) throws SQLException {
        System.err.println("ATTENZIONE: Richiamare il metodo doRetrieveAll(int idProdotto, String order)");
        return null;
    }
}
