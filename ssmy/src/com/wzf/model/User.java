package com.wzf.model;

public class User {
	private String phone;
    private String name;
    private String status="1";
    
    
	public User() {
		super();
	}
	public User(String phone, String name, String status) {
		super();
		this.phone = phone;
		this.name = name;
		this.status = status;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
}
