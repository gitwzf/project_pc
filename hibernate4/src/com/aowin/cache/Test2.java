package com.aowin.cache;

import org.hibernate.CacheMode;
import org.hibernate.Session;

import com.aowin.model.Student;
import com.aowin.util.HibernateUtil;

public class Test2 {
	public static void main(String[] args) {
		Session session=HibernateUtil.getSession();
//		session.setCacheMode(CacheMode.GET); //session会读取二级缓存中的数据，但是不会存入数据
//		session.setCacheMode(CacheMode.PUT); //session会向二级缓存存入数据，但是不会读取数据，refresh类似
//		session.setCacheMode(CacheMode.NORMAL); //默认，session会写也会读
		session.setCacheMode(CacheMode.IGNORE); //当前session既不会读也不会写
		Student student=(Student) session.get(Student.class, 1);
		System.out.println(student.getName());
		
		
		Session session2=HibernateUtil.getSession();
//		session2.setCacheMode(CacheMode.PUT);
		Student student2=(Student) session2.get(Student.class, 1);
		System.out.println(student2.getName());
	}

}
