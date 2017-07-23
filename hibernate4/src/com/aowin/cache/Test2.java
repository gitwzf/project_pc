package com.aowin.cache;

import org.hibernate.CacheMode;
import org.hibernate.Session;

import com.aowin.model.Student;
import com.aowin.util.HibernateUtil;

public class Test2 {
	public static void main(String[] args) {
		Session session=HibernateUtil.getSession();
//		session.setCacheMode(CacheMode.GET); //session���ȡ���������е����ݣ����ǲ����������
//		session.setCacheMode(CacheMode.PUT); //session�����������������ݣ����ǲ����ȡ���ݣ�refresh����
//		session.setCacheMode(CacheMode.NORMAL); //Ĭ�ϣ�session��дҲ���
		session.setCacheMode(CacheMode.IGNORE); //��ǰsession�Ȳ����Ҳ����д
		Student student=(Student) session.get(Student.class, 1);
		System.out.println(student.getName());
		
		
		Session session2=HibernateUtil.getSession();
//		session2.setCacheMode(CacheMode.PUT);
		Student student2=(Student) session2.get(Student.class, 1);
		System.out.println(student2.getName());
	}

}
