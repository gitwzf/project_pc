package com.wzf.model;

import java.util.HashSet;
import java.util.Set;

/**
 * AskSuit entity. @author MyEclipse Persistence Tools
 */

public class AskSuit implements java.io.Serializable {

	// Fields

	private String title;
	private String status;

	// Constructors

	/** default constructor */
	public AskSuit() {
	}

	/** full constructor */
	public AskSuit(String title, String status) {
		this.title = title;
		this.status = status;
		}

	// Property accessors


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}