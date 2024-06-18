package com.devstack.lms.business.custom;

import com.devstack.lms.dto.RegistrationDto;

import java.sql.SQLException;
import java.util.List;

public interface RegistrationBo {
    public boolean create(RegistrationDto dto) throws SQLException, ClassNotFoundException;

    public boolean update(RegistrationDto dto) throws ClassNotFoundException, SQLException;

    public boolean delete(RegistrationDto dto) throws ClassNotFoundException, SQLException;

    public RegistrationDto find(String id) throws ClassNotFoundException, SQLException;

    public List<RegistrationDto> findAll() throws SQLException, ClassNotFoundException;
}
