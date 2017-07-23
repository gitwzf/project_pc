package com.aowin.service;

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

	public void register(String username,String password,String photo){
		User user=new User(username,password,new Date(),photo);
		userDao.addUser(user);
	}

}
