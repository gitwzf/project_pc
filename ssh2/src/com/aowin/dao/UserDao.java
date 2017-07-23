package com.aowin.dao;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.aowin.model.User;

public class UserDao extends HibernateDaoSupport{
	
	public void addUser(User user){
		Session session=this.getSession();
		session.save(user);
	}

}
