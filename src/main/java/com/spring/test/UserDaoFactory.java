package com.spring.test;

public class UserDaoFactory {
	public static UserDao getUserDao()
	{
		return new UserDaoImpl();
	}
}
