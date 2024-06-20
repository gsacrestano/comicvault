package model.dao;

import model.bean.IndirizzoBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class IndirizzoDao implements IBeanDAO<IndirizzoBean> {

    private static final String TABLE_NAME = "Indirizzi";
    private DataSource ds;

    public IndirizzoDao(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public synchronized void doSave(IndirizzoBean bean) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO " + TABLE_NAME + " (Via, Citta, Provincia, Cap, Nazione) VALUES (?, ?, ?, ?, ?);";

        try
        {
            conn = ds.getConnection();

            ps = conn.prepareStatement(sql);

            ps.setString(1, bean.getVia());
            ps.setString(2, bean.getCitta());
            ps.setString(3, bean.getProvincia());
            ps.setString(4, bean.getCap());
            ps.setString(5, bean.getNazione());

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
    public synchronized IndirizzoBean doRetrieveByKey(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        IndirizzoBean bean = new IndirizzoBean();

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
                bean.setVia(rs.getString("Via"));
                bean.setCitta(rs.getString("Citta"));
                bean.setProvincia(rs.getString("Provincia"));
                bean.setCap(rs.getString("Cap"));
                bean.setNazione(rs.getString("Nazione"));
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
    public synchronized Collection<IndirizzoBean> doRetrieveAll(String order) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        Collection<IndirizzoBean> beans = new LinkedList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

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
                IndirizzoBean bean = new IndirizzoBean();

                bean.setId(rs.getInt("id"));
                bean.setVia(rs.getString("Via"));
                bean.setCitta(rs.getString("Citta"));
                bean.setProvincia(rs.getString("Provincia"));
                bean.setCap(rs.getString("Cap"));
                bean.setNazione(rs.getString("Nazione"));

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

    public synchronized void doUpdate(IndirizzoBean updatedAddress) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE " + TABLE_NAME + " SET Via = ?, Citta = ?, Provincia = ?, Cap = ?, Nazione = ? WHERE id = ?";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, updatedAddress.getVia());
            ps.setString(2, updatedAddress.getCitta());
            ps.setString(3, updatedAddress.getProvincia());
            ps.setString(4, updatedAddress.getCap());
            ps.setString(5, updatedAddress.getNazione());
            ps.setInt(6, updatedAddress.getId());

            ps.executeUpdate();
        } finally
        {
            if (ps != null)
                ps.close();

            if (conn != null)
                conn.close();
        }
    }
}
