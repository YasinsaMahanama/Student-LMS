package com.devstack.lms.business.custom;

import com.devstack.lms.dto.CourseDto;

import java.sql.SQLException;
import java.util.List;

public interface CourseBo {
    public boolean create(com.devstack.lms.dto.CourseDto dto) throws SQLException, ClassNotFoundException;

    public boolean update(com.devstack.lms.dto.CourseDto dto) throws ClassNotFoundException, SQLException;

    public boolean delete(com.devstack.lms.dto.CourseDto dto) throws ClassNotFoundException, SQLException;

    public com.devstack.lms.dto.CourseDto find(String id) throws ClassNotFoundException, SQLException;

    public List<com.devstack.lms.dto.CourseDto> findAll() throws SQLException, ClassNotFoundException;
}
