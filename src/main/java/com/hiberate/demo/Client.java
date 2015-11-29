package com.hiberate.demo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//加载系统文件  默认加载property   通过configure文件来读取xml文件   hiberate。cfg.xml可以重命名  ，到源代码中修改
//		/**
//		 * Use the mappings and properties specified in an application
//		 * resource named <tt>hibernate.cfg.xml</tt>.
//		 */
//		public Configuration configure() throws HibernateException {
//			configure( "/hibernate.cfg.xml" );
//			return this;
//		}
		Configuration cfg=new Configuration().configure();
		
		
		//sessionFactory 与二级缓存有关   ，重量级对象，创建耗时，做好之创建一次  ，线程安全  
		SessionFactory factory=cfg.buildSessionFactory();
		
		//与connection 不一样  ，还与缓冲有关，非线程安全   一个业务请求open一个session  请求完之后session关闭
		Session  session=null;
		
		try {
			session=factory.openSession();
			session.beginTransaction();
			User user=new User();
			user.setName("王庆山");
			user.setAge(25);
			session.save(user);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session!=null)
				if(session.isOpen())
				{
					session.close();
				}
		}
	}

}
