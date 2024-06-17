package com.devstack.lms.dao.custom.impl;

import com.devstack.lms.dao.CrudUtil;
import com.devstack.lms.dao.custom.CourseDao;
import com.devstack.lms.db.DbConnection;
import com.devstack.lms.entity.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    @Override
    public boolean create(Course course) throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
//        String sql = "INSERT INTO course values(?,?,?)";
//        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, course.getCourseId());
//        preparedStatement.setString(2, course.getCourseName());
//        preparedStatement.setDouble(3, course.getCourseFee());
//
//        int affectedRowCount = preparedStatement.executeUpdate();
//
//        if (affectedRowCount > 0) {
//            return true;
//        }
//        return false;

        return CrudUtil.execute("INSERT INTO course values(?,?,?)",course.getCourseId(),course.getCourseName(),course.getCourseFee());
    }

    @Override
    public boolean update(Course course) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");

//        String sql = "UPDATE course SET course_name = ?,fee = ? WHERE course_id = ?";
//        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//        preparedStatement.setString(1, course.getCourseName());
//        preparedStatement.setDouble(2, course.getCourseFee());
//        preparedStatement.setString(3, course.getCourseId());
//
//        int affectedRowCount = preparedStatement.executeUpdate();
//
//        if (affectedRowCount > 0) {
//            return true;
//        }
//        return false;

        return CrudUtil.execute("UPDATE course SET course_name = ?,fee = ? WHERE course_id = ?",course.getCourseName(),course.getCourseFee(),course.getCourseId());
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");

//        String sql = "DELETE FROM course WHERE course_id = ?";
//        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, id);
//
//        int affectedRowCount = preparedStatement.executeUpdate();
//
//        if (affectedRowCount > 0) {
//            return true;
//        }
//        return false;

        return CrudUtil.execute("DELETE FROM course WHERE course_id = ?",id);
    }

    @Override
    public Course find(String courseId) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");

//        String sql = "SELECT * FROM course WHERE course_id = ?";
//        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, courseId);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        if (resultSet.next()) {
//            return new Course(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getDouble(3)
//            );
//        }
//
//        return null;
        return CrudUtil.execute("SELECT * FROM course WHERE course_id = ?",courseId);
    }

    @Override
    public List<Course> findAll() {
        return Collections.emptyList();
    }

    @Override
    public List<Course> search(String searchText) throws SQLException, ClassNotFoundException {
        searchText = "%" + searchText + "%";

//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");

//        String sql = "SELECT * FROM course WHERE course_name LIKE ?";
//        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, searchText);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        List<Course> courseList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            courseList.add(new Course(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getDouble(3)
//            ));
//        }
//        return courseList;

        return CrudUtil.execute("SELECT * FROM course WHERE course_name LIKE ?",searchText);
    }
}
