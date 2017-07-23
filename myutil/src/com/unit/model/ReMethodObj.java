package com.unit.model;

public class ReMethodObj {
	private Integer status;
	private String message;
	private Object reAnswer;
	
	public ReMethodObj() {
		super();
	}

	public ReMethodObj(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public ReMethodObj(Integer status, Object reAnswer) {
		super();
		this.status = status;
		this.reAnswer = reAnswer;
	}

	public ReMethodObj(Integer status, String message, Object reAnswer) {
		super();
		this.status = status;
		this.message = message;
		this.reAnswer = reAnswer;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Object getReAnswer() {
		return reAnswer;
	}
	public void setReAnswer(Object reAnswer) {
		this.reAnswer = reAnswer;
	}
	

}
