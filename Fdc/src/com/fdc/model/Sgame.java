package com.fdc.model;

public class Sgame {
	private String userid;
	private String name;
	private String status;
	
	
	public Sgame(String userid, String name, String status) {
		super();
		this.userid = userid;
		this.name = name;
		this.status = status;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
