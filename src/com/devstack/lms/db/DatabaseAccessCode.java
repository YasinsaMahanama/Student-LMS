package com.devstack.lms.db;

import com.devstack.lms.entity.*;
import com.devstack.lms.utill.PaymentType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccessCode {

    //Student Management..........

//    public boolean saveStudent(Student student) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
//        String sql = "INSERT INTO student values(?,?,?,?,?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
//    }
//
//    public List<Student> findAllStudents(String searchText) throws ClassNotFoundException, SQLException {
//        searchText = "%" + searchText + "%";
//
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
//        String sql = "SELECT * FROM student WHERE student_name LIKE ? OR email LIKE ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, searchText);
//        preparedStatement.setString(2, searchText);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        List<Student> studentList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            studentList.add(new Student(
//               resultSet.getString(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(5), resultSet.getInt(4)
//            ));
//        }
//        return studentList;
//    }
//
//    public boolean deleteStudent(String id) throws ClassNotFoundException, SQLException {
//
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
//        String sql = "DELETE FROM student WHERE student_id = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, id);
//
//        int affectedRowCount = preparedStatement.executeUpdate();
//
//        if (affectedRowCount > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    public boolean updateStudent(Student student) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
//        String sql = "UPDATE student SET student_name = ?,address = ?, email = ?, age = ? WHERE student_id = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
//    }

    //Student Management..........



    //User Management.........


    public boolean signUp(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
        String sql = "INSERT INTO user_table values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUserId());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getPassword());

        int affectedRowCount = preparedStatement.executeUpdate();

        if (affectedRowCount > 0) {
            return true;
        }
        return false;
    }

    public boolean logIn(String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");

        String sql = "SELECT * FROM user_table WHERE user_name = '"+username+"' AND password = '"+password+"' ";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            return true;
        }
        return false;
    }


    //User Management.........


    //Course Management.......

    public boolean saveCourse(Course course) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
        String sql = "INSERT INTO course values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, course.getCourseId());
        preparedStatement.setString(2, course.getCourseName());
        preparedStatement.setDouble(3, course.getCourseFee());

        int affectedRowCount = preparedStatement.executeUpdate();

        if (affectedRowCount > 0) {
            return true;
        }
        return false;
    }

    public List<Course> findAllCourse(String searchText) throws ClassNotFoundException, SQLException {
        searchText = "%" + searchText + "%";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
        String sql = "SELECT * FROM course WHERE course_name LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, searchText);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Course> courseList = new ArrayList<>();

        while (resultSet.next()) {
            courseList.add(new Course(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3)
            ));
        }
        return courseList;
    }

    public boolean deleteCourse(String id) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
        String sql = "DELETE FROM course WHERE course_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);

        int affectedRowCount = preparedStatement.executeUpdate();

        if (affectedRowCount > 0) {
            return true;
        }
        return false;

    }

    public boolean updateCourse(Course course) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
        String sql = "UPDATE course SET course_name = ?,fee = ? WHERE course_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, course.getCourseName());
        preparedStatement.setDouble(2, course.getCourseFee());
        preparedStatement.setString(3, course.getCourseId());

        int affectedRowCount = preparedStatement.executeUpdate();

        if (affectedRowCount > 0) {
            return true;
        }
        return false;
    }

    //Course Management...........


    //Registration Management........

    public List<Course> findAllCourse() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
        String sql = "SELECT * FROM course";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Course> courseList = new ArrayList<>();

        while (resultSet.next()) {
            courseList.add(new Course(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3)
            ));
        }
        return courseList;
    }

    public Course findCourse(String courseId) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
        String sql = "SELECT * FROM course WHERE course_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, courseId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Course(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3)
            );
        }

        return null;
    }

    public List<Student> findAllStudents() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
        String sql = "SELECT * FROM student";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Student> studentList = new ArrayList<>();

        while (resultSet.next()) {
            studentList.add(new Student(
                    resultSet.getString(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(5), resultSet.getInt(4)
            ));
        }
        return studentList;
    }

    public Student findStudent(String studentId) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
        String sql = "SELECT * FROM student WHERE student_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, studentId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Student(
                    resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(5),
                    resultSet.getInt(4)
            );
        }

        return null;
    }

    public boolean registerCourse(Registration registration) throws ClassNotFoundException, SQLException {
       // Class.forName("com.mysql.cj.jdbc.Driver");
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");


        String sql = "INSERT INTO registration values(?,?,?,?,?,?)";

        //PreparedStatement preparedStatement = connection.prepareStatement(sql);

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, registration.getRegistrationId());
        preparedStatement.setObject(2, registration.getRegisteredDate());
        preparedStatement.setObject(3, registration.getNic());
        preparedStatement.setString(4, registration.getPaymentType().name());
        preparedStatement.setString(5, registration.getStudent());
        preparedStatement.setString(6, registration.getCourse());

        return preparedStatement.executeUpdate()>0;

        //me code eka simple krla thyenne anith ewa simple krla na. connection eka wenama class ekaka hdgena mekta aragen.
        //if dala anith ewage ghpu eka meke return dala thani peliykta aragena

    }

    //Registration Management........


    //AllRegistration Management

    public List<AllRegistration> loadAllDetails(String courseId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT r.registered_date, s.student_name, r.paymentType " +
                "FROM registration r " +
                "JOIN student s ON r.student = s.student_id " +
                "WHERE r.course = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, courseId);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<AllRegistration> registrationList = new ArrayList<>();
        while (resultSet.next()) {
            registrationList.add(new AllRegistration(
                    resultSet.getDate(1),
                    resultSet.getString(2),
                    PaymentType.valueOf(resultSet.getString(3))
            ));
        }
        return registrationList;
    }

 //   public List<RegisterDetails> showAllDetails(String courseId, String studentId) throws ClassNotFoundException, SQLException {
//        String sql = "SELECT s.student_name, s.address, s.age, s.email, r.registered_date, " +
//                "c.course_name, c.fee, r.paymentType " +
//                "FROM registration r " +
//                "JOIN student s ON r.student = s.student_id " +
//                "JOIN course c ON r.course = c.course_id " +
//                "WHERE r.course = ? AND r.student = ?";
//
//        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
//        preparedStatement.setString(1, courseId);
//        preparedStatement.setString(2, studentId);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//        List<RegisterDetails> registrationList = new ArrayList<>();
//        while (resultSet.next()) {
//            registrationList.add(new RegisterDetails(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getInt(3),
//                    resultSet.getString(4),
//                    resultSet.getDate(5),
//                    resultSet.getString(6),
//                    resultSet.getString(7),
//                    PaymentType.valueOf(resultSet.getString(8))
//            ));
//        }
//        return registrationList;
//    }

    //AllRegistration Management


}
