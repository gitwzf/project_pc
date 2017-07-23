package com.wzf.model;

/**
 * AskItems entity. @author MyEclipse Persistence Tools
 */

public class AskItems implements java.io.Serializable {

	// Fields

	private String content;
	private String core;
	private String picurl;

	// Constructors

	/** default constructor */
	public AskItems() {
	}

	/** full constructor */
	public AskItems( String content, String core,
			String picurl) {
		this.content = content;
		this.core = core;
		this.picurl = picurl;
	}

	// Property accessors


	public String getContent() {
		return this.content;
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
		return this.picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

}