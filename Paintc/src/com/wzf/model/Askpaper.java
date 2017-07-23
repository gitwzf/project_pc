package com.wzf.model;

public class Askpaper {
	private String id;
	private String title;
	private String url;
	private String info;
	private String clicktimes;
	private Askquestion[] askquestion;
	
	
	public Askpaper(String id,String title, String url, String info, String clicktimes) {
		super();
		this.id=id;
		this.title = title;
		this.url = url;
		this.info = info;
		this.clicktimes = clicktimes;
	}
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getClicktimes() {
		return clicktimes;
	}
	public void setClicktimes(String clicktimes) {
		this.clicktimes = clicktimes;
	}
	public Askquestion[] getAskquestion() {
		return askquestion;
	}
	public void setAskquestion(Askquestion[] askquestion) {
		this.askquestion = askquestion;
	}
	

}
