package com.hiberate.demo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//����ϵͳ�ļ�  Ĭ�ϼ���property   ͨ��configure�ļ�����ȡxml�ļ�   hiberate��cfg.xml����������  ����Դ�������޸�
//		/**
//		 * Use the mappings and properties specified in an application
//		 * resource named <tt>hibernate.cfg.xml</tt>.
//		 */
//		public Configuration configure() throws HibernateException {
//			configure( "/hibernate.cfg.xml" );
//			return this;
//		}
		Configuration cfg=new Configuration().configure();
		
		
		//sessionFactory ����������й�   �����������󣬴�����ʱ������֮����һ��  ���̰߳�ȫ  
		SessionFactory factory=cfg.buildSessionFactory();
		
		//��connection ��һ��  �����뻺���йأ����̰߳�ȫ   һ��ҵ������openһ��session  ������֮��session�ر�
		Session  session=null;
		
		try {
			session=factory.openSession();
			session.beginTransaction();
			User user=new User();
			user.setName("����ɽ");
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
