package com.wzf.model;

import java.util.ArrayList;

public class Tqusans {
	private String usertname;
	private String q_title;
	private String q_info;
	private String q_time;
	private String isAnswer;
	private ArrayList<Tans> tans=new ArrayList<Tans>();
	private String childs;
	public String getUsertname() {
		return usertname;
	}
	public void setUsertname(String usertname) {
		this.usertname = usertname;
	}
	public String getQ_title() {
		return q_title;
	}
	public void setQ_title(String qTitle) {
		q_title = qTitle;
	}
	public String getQ_info() {
		return q_info;
	}
	public void setQ_info(String qInfo) {
		q_info = qInfo;
	}
	public String getQ_time() {
		return q_time;
	}
	public void setQ_time(String qTime) {
		q_time = qTime;
	}
	public String getIsAnswer() {
		return isAnswer;
	}
	public void setIsAnswer(String isAnswer) {
		this.isAnswer = isAnswer;
	}
	
	public String getChilds() {
		return childs;
	}
	public void setChilds(String childs) {
		this.childs = childs;
	}
	public ArrayList<Tans> getTans() {
		return tans;
	}
	public void setTans(ArrayList<Tans> tans) {
		this.tans = tans;
	}

}
