package model.dao;

import model.bean.UtenteBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class UtenteDao implements IBeanDAO<UtenteBean> {

    private static final String TABLE_NAME = "Utenti";
    private DataSource ds;

    public UtenteDao(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public void doSave(UtenteBean bean) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO " + TABLE_NAME + " (Nome, Cognome, Email, Password, Telefono, isAdmin) VALUES (?, ?, ?, ?, ?, ?);";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, bean.getNome());
            ps.setString(2, bean.getCognome());
            ps.setString(3, bean.getEmail());
            ps.setString(4, bean.getPassword());
            ps.setString(5, bean.getTelefono());
            ps.setInt(6, bean.getIsAdmin());

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
    public UtenteBean doRetrieveByKey(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        UtenteBean bean = new UtenteBean();

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
                bean.setCognome(rs.getString("Cognome"));
                bean.setEmail(rs.getString("Email"));
                bean.setPassword(rs.getString("Password"));
                bean.setTelefono(rs.getString("Telefono"));
                bean.setIsAdmin(rs.getInt("isAdmin"));
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

    public UtenteBean doRetrieveByEmail(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        UtenteBean bean = new UtenteBean();

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE email = ? AND deleted_at IS NULL;";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                bean.setId(rs.getInt("Id"));
                bean.setNome(rs.getString("Nome"));
                bean.setCognome(rs.getString("Cognome"));
                bean.setEmail(rs.getString("Email"));
                bean.setPassword(rs.getString("Password"));
                bean.setTelefono(rs.getString("Telefono"));
                bean.setIsAdmin(rs.getInt("isAdmin"));
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
    public Collection<UtenteBean> doRetrieveAll(String order) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        Collection<UtenteBean> beans = new LinkedList<>();

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
                UtenteBean bean = new UtenteBean();

                bean.setId(rs.getInt("id"));
                bean.setNome(rs.getString("Nome"));
                bean.setCognome(rs.getString("Cognome"));
                bean.setEmail(rs.getString("Email"));
                bean.setPassword(rs.getString("Password"));
                bean.setTelefono(rs.getString("Telefono"));
                bean.setIsAdmin(rs.getInt("isAdmin"));

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
