package com.aowin.cache;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.aowin.model.Student;
import com.aowin.util.HibernateUtil;

public class Test {
	public static void main(String[] args) {
		Session session=HibernateUtil.getSession();
		//get  load方法获取数据前会查询缓存
//		Student student=(Student) session.get(Student.class, 1);
//		Student student=(Student) session.load(Student.class, 1);
//		System.out.println(student.getName());
		
//		Student student2=(Student) session.get(Student.class, 1);
//		Student student2=(Student) session.load(Student.class, 1);
//		System.out.println(student2.getName());
		
		//list()方法会将查询出的数据放入缓存，但是本身不会读取缓存的数据
//		Query query=session.createQuery("from Student");
//		List<Student> list=query.list();		
//		for(Student stu:list){
//			System.out.println(stu.getName());
//		}
//		System.out.println("....................................");
//		List<Student> list2=query.list();		
//		for(Student stu:list2){
//			System.out.println(stu.getName());
//		}
//		System.out.println("....................................");
//		Student student2=(Student) session.get(Student.class, 1);
//		System.out.println(student2.getName());
		
		//iterator()方法会读取缓存中的数据，但是存在n+1问题
//		Query query=session.createQuery("from Student");
//		Iterator iterator=query.iterate();
//		while(iterator.hasNext()){
//			Student stu=(Student) iterator.next();
//			System.out.println(stu.getName());
//		}
//		//clear()方法清空session中缓存的数据
//		session.clear();
//		System.out.println("..........................");
//		Iterator iterator2=query.iterate();
//		while(iterator2.hasNext()){
//			Student stu=(Student) iterator2.next();
//			System.out.println(stu.getName());
//		}
		
		session.beginTransaction();
		Student student=(Student) session.get(Student.class, 1);
		student.setAge(20);
//		session.save(student);
		//flush()强制将session中的数据持久化---更新数据库的数据
		session.flush();
//		session.getTransaction().commit();
	}

}
