package com.aowin.service;

import com.aowin.model.User;

public interface IUserService {
	public void register(String username,String password,String name,String telphone);
	
	public User login(String username,String password);

}
