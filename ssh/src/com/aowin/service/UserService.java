package com.aowin.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aowin.dao.UserDao;
import com.aowin.model.User;

public class UserService implements IUserService{
	
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

//	public void register(String username,String password){
//		User user=new User(username,password,Timestamp.valueOf( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
//		userDao.addUser(user);
//	}

	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.isUser(username, password);
	}

	public void register(String username, String password, String name,
			String telphone) {
		// TODO Auto-generated method stub
		User user=new User(username,password,name,telphone,Timestamp.valueOf( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		userDao.addUser(user);
	}

}
