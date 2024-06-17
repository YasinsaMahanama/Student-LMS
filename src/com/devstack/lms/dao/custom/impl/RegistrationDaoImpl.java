package com.devstack.lms.dao.custom.impl;

import com.devstack.lms.dao.CrudUtil;
import com.devstack.lms.dao.custom.RegistrationDao;
import com.devstack.lms.db.DbConnection;
import com.devstack.lms.entity.Registration;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class RegistrationDaoImpl implements RegistrationDao {
    @Override
    public boolean create(Registration registration) throws SQLException, ClassNotFoundException {
//        String sql = "INSERT INTO registration values(?,?,?,?,?,?)";
//
//        //PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
//        preparedStatement.setString(1, registration.getRegistrationId());
//        preparedStatement.setObject(2, registration.getRegisteredDate());
//        preparedStatement.setObject(3, registration.getNic());
//        preparedStatement.setString(4, registration.getPaymentType().name());
//        preparedStatement.setString(5, registration.getStudent());
//        preparedStatement.setString(6, registration.getCourse());
//
//        return preparedStatement.executeUpdate()>0;

        return CrudUtil.execute("INSERT INTO registration values(?,?,?,?,?,?)", registration.getRegistrationId(), registration.getRegisteredDate(), registration.getNic(), registration.getPaymentType(), registration.getStudent(), registration.getCourse());

    }

    @Override
    public boolean update(Registration registration) throws ClassNotFoundException, SQLException {
        return false;
    }


    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public Registration find(String s) {
        return null;
    }

    @Override
    public List<Registration> findAll() {
        return Collections.emptyList();
    }
}
