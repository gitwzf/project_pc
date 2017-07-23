package com.ssh.service;

import java.util.List;

import com.ssh.model.User;

public interface Inservice {
	public List<User> getUser(String username,String password);
	
	public boolean saveUser(String email,String pass,String name,String telphone);

}
