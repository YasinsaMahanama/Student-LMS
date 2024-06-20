package com.devstack.lms.dao;

import com.devstack.lms.dao.custom.CourseDao;
import com.devstack.lms.dao.custom.impl.CourseDaoImpl;
import com.devstack.lms.dao.custom.impl.RegistrationDaoImpl;
import com.devstack.lms.dao.custom.impl.StudentDaoImpl;
import com.devstack.lms.dao.custom.impl.UserDaoImpl;

public class DaoFactory {
    private DaoFactory daoFactory;
    private DaoFactory(){}

    public  enum DaoType{
        COURSE,REGISTRATION,STUDENT,USER
    }

    public static SuperDao getDao(DaoType daoType){
        switch (daoType){
            case COURSE:
                return new CourseDaoImpl();
            case REGISTRATION:
                return new RegistrationDaoImpl();
            case STUDENT:
                return new StudentDaoImpl();
            case USER:
                return new UserDaoImpl();
            default:
                return null;
        }
    }
}
