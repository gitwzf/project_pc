package com.ssh.model;

import java.sql.Timestamp;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Short id;
	private String email;
	private String pass;
	private String name;
	private String telphone;
	private Integer times;
	private Timestamp timAdd;
	private String status;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String pass) {
		this.pass = pass;
	}
	public User(String email, String pass) {
		super();
		this.email = email;
		this.pass = pass;
	}



	public User(String email, String pass, String name, String telphone,
			 Timestamp timAdd) {
		this.email = email;
		this.pass = pass;
		this.name = name;
		this.telphone = telphone;
		this.timAdd = timAdd;
	}
	/** full constructor */
	public User(String email, String pass, String name, String telphone,
			Integer times, Timestamp timAdd, String status) {
		this.email = email;
		this.pass = pass;
		this.name = name;
		this.telphone = telphone;
		this.times = times;
		this.timAdd = timAdd;
		this.status = status;
	}

	// Property accessors

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public Integer getTimes() {
		return this.times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Timestamp getTimAdd() {
		return this.timAdd;
	}

	public void setTimAdd(Timestamp timAdd) {
		this.timAdd = timAdd;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}