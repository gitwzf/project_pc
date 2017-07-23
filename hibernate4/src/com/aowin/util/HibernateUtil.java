package com.aowin.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory factory;
	
	static{
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		factory=cfg.buildSessionFactory();
	}
	
	public static Session getSession(){
		Session session=factory.openSession();
		return session;		
	}
	
	
	public static Session getCurrentSession(){
		Session session=factory.getCurrentSession();
		return session;
	}
	

}
