package com.devstack.lms.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T,ID>{
    public boolean create(T t) throws SQLException, ClassNotFoundException;

    public boolean update(T t) throws ClassNotFoundException, SQLException;

    public boolean delete(ID id) throws ClassNotFoundException, SQLException;

    public T find(ID id) throws ClassNotFoundException, SQLException;

    public List<T> findAll() throws SQLException, ClassNotFoundException;

}
