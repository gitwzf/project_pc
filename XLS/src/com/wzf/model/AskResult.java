package com.wzf.model;

import java.sql.Timestamp;

/**
 * AskResult entity. @author MyEclipse Persistence Tools
 */

public class AskResult implements java.io.Serializable {

	// Fields

	private String downgoal;
	private String upgoal;
	private String title;
	private String content;
	private String picurl;
public AskResult(String downgoal, String upgoal, String title,
			String content, String picurl) {
		super();
		this.downgoal = downgoal;
		this.upgoal = upgoal;
		this.title = title;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
}