package model.dao;

import model.bean.CategoriaBean;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class CategoriaDao implements IBeanDAO<CategoriaBean> {

    private static final String TABLE_NAME = "Categorie";
    private DataSource ds;

    public CategoriaDao(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public synchronized void doSave(CategoriaBean bean) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO " + TABLE_NAME + " (Nome, Descrizione) VALUES (?, ?);";

        try
        {
            conn = ds.getConnection();

            ps = conn.prepareStatement(sql);

            ps.setString(1, bean.getNome());
            ps.setString(2, bean.getDescrizione());

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
    public synchronized CategoriaBean doRetrieveByKey(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        CategoriaBean bean = new CategoriaBean();

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ? AND deleted_at IS NULL;";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                bean.setId(rs.getInt("id"));
                bean.setNome(rs.getString("Nome"));
                bean.setDescrizione(rs.getString("Descrizione"));
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
    public synchronized Collection<CategoriaBean> doRetrieveAll(String order) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        Collection<CategoriaBean> beans = new LinkedList<>();

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
                CategoriaBean bean = new CategoriaBean();

                bean.setId(rs.getInt("id"));
                bean.setNome(rs.getString("Nome"));
                bean.setDescrizione(rs.getString("Descrizione"));

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
