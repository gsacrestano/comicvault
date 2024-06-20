package model.dao;

import model.bean.UtenteIndirizzoBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class UtenteIndirizzoDao implements IBeanDAO<UtenteIndirizzoBean> {

    private static final String TABLE_NAME = "Utenti_Indirizzi";
    private DataSource ds;

    public UtenteIndirizzoDao(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public void doSave(UtenteIndirizzoBean bean) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO " + TABLE_NAME + " (idUtente, idIndirizzo, isDefault) VALUES (?, ?, ?);";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, bean.getIdUtente());
            ps.setInt(2, bean.getIdIndirizzo());
            ps.setInt(3, bean.getIsDefault());

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

    public boolean doDelete(int idIndirizzo) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        int result = 0;

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE idIndirizzo = ?;";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, idIndirizzo);

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
    public UtenteIndirizzoBean doRetrieveByKey(int id) throws SQLException {
        System.err.println("ATTENZIONE: Richiamare il metodo doRetrieveAll(int idUtente, String order)");
        return null;
    }

    public Collection<UtenteIndirizzoBean> doRetrieveAll(int idUtente, String order) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        Collection<UtenteIndirizzoBean> beans = new LinkedList<>();

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE idUtente = ? ";

        if (order != null && !order.isEmpty())
            sql += " ORDER BY ?";

        try
        {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, idUtente);
            ps.setString(2, order);


            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                UtenteIndirizzoBean bean = new UtenteIndirizzoBean();

                bean.setIdUtente(rs.getInt("idUtente"));
                bean.setIdIndirizzo(rs.getInt("idIndirizzo"));
                bean.setIsDefault(rs.getInt("isDefault"));

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
    public Collection<UtenteIndirizzoBean> doRetrieveAll(String order) throws SQLException {
        System.err.println("ATTENZIONE: Richiamare il metodo doRetrieveAll(int idUtente, String order)");
        return null;
    }
}
