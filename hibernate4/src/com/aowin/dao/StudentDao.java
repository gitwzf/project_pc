package com.aowin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.aowin.model.Student;
import com.aowin.util.HibernateUtil;

public class StudentDao {
	
	public void save(Student student){
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		try{
			session.save(student);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		session.close();
	}
	
	public Student findById(int id){
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		Student stu=null;
		try{
			stu=(Student) session.get(Student.class, id);
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		session.close();
		return stu;
	}
	public Student findByNo(int no){
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		Student stu=null;
		try{
			String sql="from Student where stuno="+no;
			Query query=session.createQuery(sql);
			List<Student> list=query.list();
			stu=list.get(0);
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		session.close();
		return stu;
	}

}
