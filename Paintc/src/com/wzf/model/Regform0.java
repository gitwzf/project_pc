package com.wzf.model;

public class Regform0 {
	private String isTrain;
	private String st;
	private String phone;
	private String teachername;
	private String qq;
	private String weixin;
	private String email;
	
	public Regform0(String isTrain, String st, String phone,
			String teachername, String qq, String weixin, String email) {
		this.isTrain = isTrain;
		this.st = st;
		this.phone = phone;
		this.teachername = teachername;
		this.qq = qq;
		this.weixin = weixin;
		this.email = email;
	}
	public String getIsTrain() {
		return isTrain;
	}
	public void setIsTrain(String isTrain) {
		this.isTrain = isTrain;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
