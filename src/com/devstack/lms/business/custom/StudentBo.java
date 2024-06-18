package com.devstack.lms.business.custom;

import java.sql.SQLException;
import java.util.List;

public interface StudentBo {
    public boolean create(com.devstack.lms.dto.StudentDto dto) throws SQLException, ClassNotFoundException;

    public boolean update(com.devstack.lms.dto.StudentDto dto) throws ClassNotFoundException, SQLException;

    public boolean delete(com.devstack.lms.dto.StudentDto dto) throws ClassNotFoundException, SQLException;

    public com.devstack.lms.dto.StudentDto find(String id) throws ClassNotFoundException, SQLException;

    public List<com.devstack.lms.dto.StudentDto> findAll() throws SQLException, ClassNotFoundException;
}
