package com.wzf.model;

public class Askquestion {
	private String id;
	private String queurl;
	private String question;
	private Askitems[] askitems;
	
	
	public Askquestion(String id, String queurl, String question) {
		super();
		this.id = id;
		this.queurl = queurl;
		this.question = question;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQueurl() {
		return queurl;
	}
	public void setQueurl(String queurl) {
		this.queurl = queurl;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Askitems[] getAskitems() {
		return askitems;
	}
	public void setAskitems(Askitems[] askitems) {
		this.askitems = askitems;
	}

}
