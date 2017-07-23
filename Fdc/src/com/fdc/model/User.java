package com.fdc.model;

public class User {
	private String id;
	private String name;
	private String tel;
	private String gender;
	private String iswish;
	private String fuserid;
	private String sstatus;
	private String numbersvote;
	
	
	
	public User(String id, String name, String tel, String gender,
			String iswish, String fuserid, String sstatus) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.gender = gender;
		this.iswish = iswish;
		this.fuserid = fuserid;
		this.sstatus = sstatus;
	}
	
	public String getNumbersvote() {
		return numbersvote;
	}

	public void setNumbersvote(String numbersvote) {
		this.numbersvote = numbersvote;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIswish() {
		return iswish;
	}
	public void setIswish(String iswish) {
		this.iswish = iswish;
	}
	public String getFuserid() {
		return fuserid;
	}
	public void setFuserid(String fuserid) {
		this.fuserid = fuserid;
	}
	public String getSstatus() {
		return sstatus;
	}
	public void setSstatus(String sstatus) {
		this.sstatus = sstatus;
	}

}
