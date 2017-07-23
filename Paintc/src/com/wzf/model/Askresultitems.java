package com.wzf.model;

public class Askresultitems {
	private String title;
	private String downgoal;
	private String upgoal;
	private String content;
	private String picurl;
	
	public Askresultitems(String title, String downgoal, String upgoal,
			String content, String picurl) {
		super();
		this.title = title;
		this.downgoal = downgoal;
		this.upgoal = upgoal;
		this.content = content;
		this.picurl = picurl;
	}
	public String getDowngoal() {
		return downgoal;
	}
	public void setDowngoal(String downgoal) {
		this.downgoal = downgoal;
	}
	public String getUpgoal() {
		return upgoal;
	}
	public void setUpgoal(String upgoal) {
		this.upgoal = upgoal;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
