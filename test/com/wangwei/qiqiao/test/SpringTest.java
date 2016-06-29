package com.wangwei.qiqiao.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	@Test
	public void testSessionFactory() throws Exception {
		SessionFactory sessionFactory = (SessionFactory)ac.getBean("sessionFactory");
		System.out.println(sessionFactory);
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		for(int i=0;i<25;i++) {
//			User user = new User();
//			user.setLoginName("wangwei"+i);
//			user.setName("王威"+i);
//			user.setPassword("asda");
//			user.setDescription("我的名字叫顺溜");
//			session.save(user);
//		}
//		session.getTransaction().commit();
	}
	
}
