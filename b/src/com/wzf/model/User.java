package com.wzf.model;

public class User {
	private String id;
	private String name;
	private String gender;
	private String timadd;
	
	
	public User(String id) {
		this.id = id;
	}
	public User(String id, String name, String gender, String timadd) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.timadd = timadd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTimadd() {
		return timadd;
	}
	public void setTimadd(String timadd) {
		this.timadd = timadd;
	}

}
