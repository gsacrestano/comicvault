package model.dao;

import java.sql.SQLException;
import java.util.Collection;

public interface IBeanDAO<T> {

    public int doSave(T bean) throws SQLException;

    public boolean doDelete(int id) throws SQLException;

    public T doRetrieveByKey(int id) throws SQLException;

    public Collection<T> doRetrieveAll(String order) throws SQLException;
}
