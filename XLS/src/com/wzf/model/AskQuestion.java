package com.wzf.model;

import java.util.ArrayList;

/**
 * AskQuestion entity. @author MyEclipse Persistence Tools
 */

public class AskQuestion implements java.io.Serializable {

	// Fields

	private String queurl;
	private String question;
	private ArrayList<AskItems> askitems;

	// Constructors

	/** default constructor */
	public AskQuestion() {
	}

	/** full constructor */
	public AskQuestion( String queurl, String question) {
		this.queurl = queurl;
		this.question = question;
	}

	// Property accessors

	

	public String getQueurl() {
		return this.queurl;
	}

	public void setQueurl(String queurl) {
		this.queurl = queurl;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<AskItems> getAskitems() {
		return askitems;
	}

	public void setAskitems(ArrayList<AskItems> askitems) {
		this.askitems = askitems;
	}


}