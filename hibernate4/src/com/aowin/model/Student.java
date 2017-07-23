package com.aowin.model;

import java.util.Date;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer stuno;
	private String name;
	private String sex;
	private Integer age;
	private Integer score1;
	private Integer score2;
	private Date createdate;

	// Constructors

	/** default constructor */
	public Student() {
	}
	
	
	

	public Student(Integer stuno, String name, String sex, Integer age) {
		this.stuno = stuno;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}




	/** full constructor */
	public Student(Integer stuno, String name, String sex, Integer age,
			Integer score1, Integer score2, Date createdate) {
		this.stuno = stuno;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.score1 = score1;
		this.score2 = score2;
		this.createdate = createdate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStuno() {
		return this.stuno;
	}

	public void setStuno(Integer stuno) {
		this.stuno = stuno;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getScore1() {
		return this.score1;
	}

	public void setScore1(Integer score1) {
		this.score1 = score1;
	}

	public Integer getScore2() {
		return this.score2;
	}

	public void setScore2(Integer score2) {
		this.score2 = score2;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

}