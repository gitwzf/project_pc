package com.ssh.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ssh.dao.UserDAO;
import com.ssh.model.User;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

public class Service{
     private UserDAO  userDao;
	public UserDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

   
	public List<User> getUser(String username, String password) {
		
		return userDao.findByExample(new User(username,password));
	}
	public boolean saveUser(String email,String pass,String name,String telphone) {
		
		if(email==null||pass==null)return false;
		return userDao.save(new User(email,pass,name,telphone,Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))));
	  
	}

}
