package com.spring.test;

public class UserDaoFactory2 {
    public UserDao getUserDao()
    {
        return new UserDaoImpl();
    }
}
