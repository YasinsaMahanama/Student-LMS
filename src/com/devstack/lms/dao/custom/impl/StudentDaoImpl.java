package com.devstack.lms.dao.custom.impl;

import com.devstack.lms.dao.custom.StudentDao;
import com.devstack.lms.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public boolean create(Student student) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
        String sql = "INSERT INTO student values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, student.getStudentId());
        preparedStatement.setString(2, student.getStudentName());
        preparedStatement.setString(3, student.getAddress());
        preparedStatement.setInt(4, student.getAge());
        preparedStatement.setString(5, student.getEmail());

        int affectedRowCount = preparedStatement.executeUpdate();

        if (affectedRowCount > 0) {
            return true;
        }
        return false;
    }


    @Override
    public boolean update(Student student) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
        String sql = "UPDATE student SET student_name = ?,address = ?, email = ?, age = ? WHERE student_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, student.getStudentName());
        preparedStatement.setString(2, student.getAddress());
        preparedStatement.setString(3, student.getEmail());
        preparedStatement.setInt(4, student.getAge());
        preparedStatement.setString(5, student.getStudentId());

        int affectedRowCount = preparedStatement.executeUpdate();

        if (affectedRowCount > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
        String sql = "DELETE FROM student WHERE student_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);

        int affectedRowCount = preparedStatement.executeUpdate();

        if (affectedRowCount > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Student find(String s) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return Collections.emptyList();
    }

    @Override
    public List<Student> search(String searchText) throws ClassNotFoundException, SQLException {
        searchText = "%" + searchText + "%";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
        String sql = "SELECT * FROM student WHERE student_name LIKE ? OR email LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, searchText);
        preparedStatement.setString(2, searchText);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Student> studentList = new ArrayList<>();

        while (resultSet.next()) {
            studentList.add(new Student(
                    resultSet.getString(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(5), resultSet.getInt(4)
            ));
        }
        return studentList;
    }
}
