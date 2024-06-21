package model.dao;

import model.bean.CarrelloBean;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class CarrelloDao implements IBeanDAO<CarrelloBean> {

    private static final String TABLE_NAME = "Carrelli";
    private DataSource ds;

    public CarrelloDao(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public synchronized int doSave(CarrelloBean bean) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;

        String sql = "INSERT INTO " + TABLE_NAME + " (idUtente) VALUES (?);";

        try
        {
            conn = ds.getConnection();

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, bean.getIdUtente());

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

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE idUtente = ?;";

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
    public synchronized CarrelloBean doRetrieveByKey(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        CarrelloBean bean = new CarrelloBean();

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE idUtente = ?;";

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
            }

            return bean;
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
    public synchronized Collection<CarrelloBean> doRetrieveAll(String order) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        Collection<CarrelloBean> beans = new LinkedList<>();

        String sql = "SELECT * FROM " + TABLE_NAME + ";";

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
                CarrelloBean bean = new CarrelloBean();

                bean.setId(rs.getInt("id"));
                bean.setIdUtente(rs.getInt("idUtente"));

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
