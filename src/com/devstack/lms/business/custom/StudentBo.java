package com.devstack.lms.business.custom;

import com.devstack.lms.dto.StudentDto;

import java.sql.SQLException;
import java.util.List;

public interface StudentBo {
    public boolean create(StudentDto dto) throws SQLException, ClassNotFoundException;

    public boolean update(StudentDto dto) throws ClassNotFoundException, SQLException;

    boolean delete(String id) throws ClassNotFoundException, SQLException;

    public StudentDto find(String id) throws ClassNotFoundException, SQLException;

    public List<StudentDto> findAll() throws SQLException, ClassNotFoundException;

    public List<StudentDto> search(String searchText) throws SQLException, ClassNotFoundException;
}
