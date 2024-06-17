package com.devstack.lms.dao.custom.impl;

import com.devstack.lms.dao.CrudUtil;
import com.devstack.lms.dao.custom.UserDao;
import com.devstack.lms.db.DbConnection;
import com.devstack.lms.entity.User;
import com.devstack.lms.utill.PasswordManager;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean create(User user) throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstack_lms", "root", "1234");
//        String sql = "INSERT INTO user_table values(?,?,?)";
//        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, user.getUserId());
//        preparedStatement.setString(2, user.getUsername());
//        preparedStatement.setString(3, user.getPassword());
//
//        int affectedRowCount = preparedStatement.executeUpdate();
//
//        if (affectedRowCount > 0) {
//            return true;
//        }
//        return false;

        return CrudUtil.execute("INSERT INTO user_table values(?,?,?)", user.getUserId(), user.getUsername(), PasswordManager.hash(user.getPassword()));
    }

    @Override
    public boolean update(User user) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public User find(String s) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return Collections.emptyList();
    }

    @Override
    public boolean logIn(String username, String password) throws SQLException, ClassNotFoundException {
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
}
