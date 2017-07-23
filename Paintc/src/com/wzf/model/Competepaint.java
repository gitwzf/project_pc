package com.wzf.model;

public class Competepaint {
	private String id;
	private String name;
	private String begtim;
	private String endtim;
	
	
	
	public Competepaint(String id, String name, String begtim, String endtim) {
		super();
		this.id = id;
		this.name = name;
		this.begtim = begtim;
		this.endtim = endtim;
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
	

}
