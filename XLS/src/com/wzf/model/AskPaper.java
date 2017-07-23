package com.wzf.model;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * AskPaper entity. @author MyEclipse Persistence Tools
 */

public class AskPaper implements java.io.Serializable {

	// Fields

	private String title;
	private String questions;
	private String begTim;
	private String endTim;
	private String score;
	private String counttime;
	private String url;
	private String info;
	private ArrayList<AskQuestion> askquestin;

	// Constructors

	/** default constructor */
	public AskPaper() {
	}

	public AskPaper(String title, String questions, String begTim,
			String endTim, String score, String counttime, String url,
			String info) {
		super();
		this.title = title;
		this.questions = questions;
		this.begTim = begTim;
		this.endTim = endTim;
		this.score = score;
		this.counttime = counttime;
		this.url = url;
		this.info = info;
		}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public String getBegTim() {
		return begTim;
	}

	public void setBegTim(String begTim) {
		this.begTim = begTim;
	}

	public String getEndTim() {
		return endTim;
	}

	public void setEndTim(String endTim) {
		this.endTim = endTim;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getCounttime() {
		return counttime;
	}

	public void setCounttime(String counttime) {
		this.counttime = counttime;
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

	public ArrayList<AskQuestion> getAskquestin() {
		return askquestin;
	}

	public void setAskquestin(ArrayList<AskQuestion> askquestin) {
		this.askquestin = askquestin;
	}



}