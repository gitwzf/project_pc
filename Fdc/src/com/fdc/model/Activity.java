package com.fdc.model;

public class Activity {
	private String id;
	private String title;
	private String concate;
	private String begtim;
	private String endtim;
	
	
	public Activity(String id, String title, String concate, String begtim,
			String endtim) {
		super();
		this.id = id;
		this.title = title;
		this.concate = concate;
		this.begtim = begtim;
		this.endtim = endtim;
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
	public String getConcate() {
		return concate;
	}
	public void setConcate(String concate) {
		this.concate = concate;
	}
	public String getBegtim() {
		return begtim;
	}
	public void setBegtim(String begtim) {
		this.begtim = begtim;
	}
	public String getEndtim() {
		return endtim;
	}
	public void setEndtim(String endtim) {
		this.endtim = endtim;
	}
	
	public int compareTo(Activity value) {
		
		if(id==null)return -1;
		if(value.getId()==null)return 1;
		
		if(Long.parseLong(id)>Long.parseLong(value.getId()))return -1;
		if(Long.parseLong(id)<Long.parseLong(value.getId()))return 1;
		return 0;
	}

}
