package com.devstack.lms.dao.custom.impl;

import com.devstack.lms.dao.CrudUtil;
import com.devstack.lms.dao.custom.StudentDao;
import com.devstack.lms.db.DbConnection;
import com.devstack.lms.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public boolean create(Student student) throws SQLException, ClassNotFoundException {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");

//        String sql = "INSERT INTO student values(?,?,?,?,?)";
//        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
//        //PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, student.getStudentId());
//        preparedStatement.setString(2, student.getStudentName());
//        preparedStatement.setString(3, student.getAddress());
//        preparedStatement.setInt(4, student.getAge());
//        preparedStatement.setString(5, student.getEmail());
//
//        int affectedRowCount = preparedStatement.executeUpdate();
//
//        if (affectedRowCount > 0) {
//            return true;
//        }
//        return false;
        return CrudUtil.execute("INSERT INTO student values(?,?,?,?,?)", student.getStudentId(),student.getStudentName(),student.getAddress(),student.getAge(), student.getEmail());

    }


    @Override
    public boolean update(Student student) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");

//        String sql = "UPDATE student SET student_name = ?,address = ?, email = ?, age = ? WHERE student_id = ?";
//        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//        preparedStatement.setString(1, student.getStudentName());
//        preparedStatement.setString(2, student.getAddress());
//        preparedStatement.setString(3, student.getEmail());
//        preparedStatement.setInt(4, student.getAge());
//        preparedStatement.setString(5, student.getStudentId());
//
//        int affectedRowCount = preparedStatement.executeUpdate();
//
//        if (affectedRowCount > 0) {
//            return true;
//        }
//        return false;

        return CrudUtil.execute("UPDATE student SET student_name = ?,address = ?, email = ?, age = ? WHERE student_id = ?", student.getStudentName(), student.getAddress(),student.getEmail(),student.getAge(), student.getStudentId());
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
//        String sql = "DELETE FROM student WHERE student_id = ?";
//
//        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//        preparedStatement.setString(1, id);
//
//        int affectedRowCount = preparedStatement.executeUpdate();
//
//        if (affectedRowCount > 0) {
//            return true;
//        }
//        return false;

        return CrudUtil.execute("DELETE FROM student WHERE student_id = ?", id);
    }

    @Override
    public Student find(String studentId) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");

//        String sql = "SELECT * FROM student WHERE student_id = ?";
//        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, studentId);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        if (resultSet.next()) {
//            return new Student(
//                    resultSet.getString(1), resultSet.getString(2),
//                    resultSet.getString(3), resultSet.getString(5),
//                    resultSet.getInt(4)
//            );
//        }
//
//        return null;
        return CrudUtil.execute("SELECT * FROM student WHERE student_id = ?", studentId);
    }

    @Override
    public List<Student> findAll() {
        return Collections.emptyList();
    }

    @Override
    public List<Student> search(String searchText) throws ClassNotFoundException, SQLException {
        searchText = "%" + searchText + "%";

//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");

//        String sql = "SELECT * FROM student WHERE student_name LIKE ? OR email LIKE ?";
//        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, searchText);
//        preparedStatement.setString(2, searchText);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        List<Student> studentList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            studentList.add(new Student(
//                    resultSet.getString(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(5), resultSet.getInt(4)
//            ));
//        }
//        return studentList;

        return CrudUtil.execute("SELECT * FROM student WHERE student_name LIKE ? OR email LIKE ?", searchText, searchText);
    }
}
