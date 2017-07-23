package com.wzf.model;

public class User {
	private String id;
	private String name;
	private String state;
	private String cjphone;
	private String cjname;
	private String cjstate;
	private String cjstatename;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCjstate() {
		return cjstate;
	}
	public void setCjstate(String cjstate) {
		this.cjstate = cjstate;
	}
	public String getCjphone() {
		return cjphone;
	}
	public void setCjphone(String cjphone) {
		this.cjphone = cjphone;
	}
	public String getCjname() {
		return cjname;
	}
	public void setCjname(String cjname) {
		this.cjname = cjname;
	}
	public String getCjstatename() {
		return cjstatename;
	}
	public void setCjstatename(String cjstatename) {
		this.cjstatename = cjstatename;
	}
	

}
