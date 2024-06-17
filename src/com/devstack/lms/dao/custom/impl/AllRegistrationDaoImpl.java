package com.devstack.lms.dao.custom.impl;

import com.devstack.lms.dao.custom.AllRegistrationDao;
import com.devstack.lms.entity.AllRegistration;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class AllRegistrationDaoImpl implements AllRegistrationDao {
    @Override
    public boolean create(AllRegistration allRegistration) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(AllRegistration allRegistration) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public AllRegistration find(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public List<AllRegistration> findAll() throws SQLException, ClassNotFoundException {
        return Collections.emptyList();
    }
}
