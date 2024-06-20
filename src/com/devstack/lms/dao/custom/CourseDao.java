package com.devstack.lms.dao.custom;

import com.devstack.lms.dao.CrudDao;
import com.devstack.lms.dao.SuperDao;
import com.devstack.lms.entity.Course;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;

public interface CourseDao extends CrudDao<Course, String>, SuperDao {
    public List<Course> search(String searchText) throws SQLException, ClassNotFoundException;
}
