package com.aowin.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.aowin.model.User;

public class UserDao extends HibernateDaoSupport{
	
	public void addUser(User user){
		Session session=this.getSession();
		session.save(user);
	}
	
	public User isUser(String username,String password){
		Session session=this.getSession();
		Query query=session.createQuery("from User where email=:emaill and pass=:passs");
		query.setParameter("emaill", username,Hibernate.STRING);
		query.setParameter("passs", password,Hibernate.STRING);
		List<User> list=query.list();
		if(list.size()==1){
			User u=list.get(0);
			u.setTimes(u.getTimes()+1);
			session.update(u);
			System.out.println(username+"≥…π¶∑√Œ °£°£°£");
			return u;}
		return null;
	}

}
