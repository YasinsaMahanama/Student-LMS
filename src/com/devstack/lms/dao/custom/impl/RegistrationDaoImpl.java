package com.devstack.lms.dao.custom.impl;

import com.devstack.lms.dao.custom.RegistrationDao;
import com.devstack.lms.entity.Registration;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class RegistrationDaoImpl implements RegistrationDao {
    @Override
    public boolean create(Registration registration) {
        return false;
    }

    @Override
    public boolean update(Registration registration) throws ClassNotFoundException, SQLException {
        return false;
    }


    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public Registration find(String s) {
        return null;
    }

    @Override
    public List<Registration> findAll() {
        return Collections.emptyList();
    }
}
