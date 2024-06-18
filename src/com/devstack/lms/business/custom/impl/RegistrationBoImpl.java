package com.devstack.lms.business.custom.impl;

import com.devstack.lms.business.custom.RegistrationBo;
import com.devstack.lms.dto.RegistrationDto;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class RegistrationBoImpl implements RegistrationBo {
    @Override
    public boolean create(RegistrationDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(RegistrationDto dto) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public RegistrationDto find(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public List<RegistrationDto> findAll() throws SQLException, ClassNotFoundException {
        return Collections.emptyList();
    }
}
