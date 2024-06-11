package com.devstack.lms.dao;

import java.util.List;

public interface CrudDao<T,ID>{
    public boolean create(T t);

    public boolean update(T t, ID id);

    public boolean delete(ID id);

    public T find(ID id);

    public List<T> findAll();

}
