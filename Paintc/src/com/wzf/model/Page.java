package com.wzf.model;

public class Page {
	private String p;//Åú
	private String items;
	private String maxp;
	
	
	public Page(String p, String items, String maxp) {
		this.p = p;
		this.items = items;
		this.maxp = maxp;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getMaxp() {
		return maxp;
	}
	public void setMaxp(String maxp) {
		this.maxp = maxp;
	}

}
