package com.devstack.lms.business;

import com.devstack.lms.dao.custom.impl.CourseDaoImpl;
import com.devstack.lms.dao.custom.impl.RegistrationDaoImpl;
import com.devstack.lms.dao.custom.impl.StudentDaoImpl;
import com.devstack.lms.dao.custom.impl.UserDaoImpl;

public class BoFactory {
    private BoFactory boFactory;
    private BoFactory(){}

    public  enum BoType{
        COURSE,REGISTRATION,STUDENT,USER
    }

//    public static SuperDao getDao(DaoType daoType){
//        switch (daoType){
//            case COURSE:
//                return new CourseDaoImpl();
//            case REGISTRATION:
//                return new RegistrationDaoImpl();
//            case STUDENT:
//                return new StudentDaoImpl();
//            case USER:
//                return new UserDaoImpl();
//            default:
//                return null;
//        }
//    }

    public static <T> T getBo(BoFactory.BoType boType){
        switch (boType){
            case COURSE:
                return (T) new CourseDaoImpl();
            case REGISTRATION:
                return (T) new RegistrationDaoImpl();
            case STUDENT:
                return (T) new StudentDaoImpl();
            case USER:
                return (T) new UserDaoImpl();
            default:
                return null;
        }
    }
}
