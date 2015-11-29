package com.spring.test;

import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
//	    UserDao userDao = (UserDao)ctx.getBean("userDaoImpl3");
//	    userDao.save();
		
		
//		 @SuppressWarnings("resource")
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
//		    System.out.println("=====Singleton test=====");
//		    UserDao userDao1A = (UserDao)ctx.getBean("userDaoImpl");
//		    UserDao userDao1B = (UserDao)ctx.getBean("userDaoImpl");
//		    System.out.println("userDao1A == userDao1B:" + (userDao1A==userDao1B));
//		    System.out.println("=====Prototype test=====");
//		    UserDao userDao2A = (UserDao)ctx.getBean("userDaoImpl2");
//		    UserDao userDao2B = (UserDao)ctx.getBean("userDaoImpl2");
//		    System.out.println("userDao2A == userDao2B:" + (userDao2A==userDao2B));
		
		propertyTest1();
	}
	
	
	private static void propertyTest1()
	{
	    ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	    UserServiceBean userService = (UserServiceBean)ctx.getBean("userService");
	    printUserService(userService);
	}

	private static void printUserService(UserServiceBean userService)
	{
	    System.out.println("编号：" + userService.getUserID());
	    System.out.println("姓名：" + userService.getUserName());
	    System.out.println("爱好：");
	    for(String hobby:userService.getHobbies())
	    {
	        System.out.println(hobby);
	    }
	    System.out.println("学习成绩：");
	    for(Entry<String,Integer> entry:userService.getScores().entrySet())
	    {
	        System.out.println(entry.getKey() + "\t" + entry.getValue());
	    }
	    userService.getUserDao().save();
	}

}
