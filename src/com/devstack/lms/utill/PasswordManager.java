package com.devstack.lms.utill;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordManager  {
    public static String hash(String passwordText) {
//        String hashed = BCrypt.hashpw(passwordText, BCrypt.gensalt());
//        return hashed;
        return BCrypt.hashpw(passwordText, BCrypt.gensalt());
    }
}
