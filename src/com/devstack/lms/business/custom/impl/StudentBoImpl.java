package com.devstack.lms.business.custom.impl;

import com.devstack.lms.business.custom.StudentBo;
import com.devstack.lms.dto.StudentDto;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class StudentBoImpl implements StudentBo {
    @Override
    public boolean create(StudentDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(StudentDto dto) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(StudentDto dto) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public StudentDto find(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public List<StudentDto> findAll() throws SQLException, ClassNotFoundException {
        return Collections.emptyList();
    }
}
