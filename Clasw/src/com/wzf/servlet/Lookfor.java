package com.wzf.servlet;

public class Lookfor {
	private String period;
	private String group;
	private String time;
	private String class_addr;
	
	
	public Lookfor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Lookfor(String period, String group, String time, String classAddr) {
		this.period = period;
		this.group = group;
		this.time = time;
		this.class_addr = classAddr;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getClass_addr() {
		return class_addr;
	}
	public void setClass_addr(String classAddr) {
		this.class_addr = classAddr;
	}
	

}
