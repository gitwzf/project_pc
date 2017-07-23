package com.wzf.model;

public class Askitems {
	private String questionid;
	private String content;
	private String core;
	private String picurl;
	
	
	public Askitems(String questionid, String content, String core,
			String picurl) {
		super();
		this.questionid = questionid;
		this.content = content;
		this.core = core;
		this.picurl = picurl;
	}
	public String getQuestionid() {
		return questionid;
	}
	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCore() {
		return core;
	}
	public void setCore(String core) {
		this.core = core;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

}
