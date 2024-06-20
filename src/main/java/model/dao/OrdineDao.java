package model.dao;

import model.bean.OrdineBean;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;


public class OrdineDao implements IBeanDAO<OrdineBean> {

    private static final String TABLE_NAME = "Ordini";
    private DataSource ds;

    public OrdineDao(DataSource ds) {
        this.ds = ds;
    }

    public synchronized int doSave(OrdineBean bean) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;

        String sql = "INSERT INTO " + TABLE_NAME + " (idUtente, idIndirizzo, Data, Totale) VALUES (?, ?, NOW(), ?);";

        try
        {
            conn = ds.getConnection();

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, bean.getIdUtente());
            ps.setInt(2, bean.getIdIndirizzo());
            ps.setDouble(3, bean.getTotale());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating order failed, no ID obtained.");
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

                if (generatedKeys != null)
                    generatedKeys.close();
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
    public synchronized OrdineBean doRetrieveByKey(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        OrdineBean bean = new OrdineBean();

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
                bean.setIdUtente(rs.getInt("idUtente"));
                bean.setIdIndirizzo(rs.getInt("idIndirizzo"));
                bean.setData(rs.getString("Data"));
                bean.setTotale(rs.getFloat("Totale"));
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
    public synchronized Collection<OrdineBean> doRetrieveAll(String order) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        Collection<OrdineBean> beans = new LinkedList<>();

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE deleted_at IS NULL";

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
                OrdineBean bean = new OrdineBean();

                bean.setId(rs.getInt("id"));
                bean.setIdUtente(rs.getInt("idUtente"));
                bean.setIdIndirizzo(rs.getInt("idIndirizzo"));
                bean.setData(rs.getString("Data"));
                bean.setTotale(rs.getFloat("Totale"));

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
