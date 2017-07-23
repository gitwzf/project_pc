package com.fdc.model;

public class Apply {
	private String id;
	private String userid;
	private String actid;
	private String actname;
	private String issign;
	private String signtim;
	
	
	public Apply(String id, String userid, String actid, String actname,
			String issign, String signtim) {
		super();
		this.id = id;
		this.userid = userid;
		this.actid = actid;
		this.actname = actname;
		this.issign = issign;
		this.signtim = signtim;
	}
	public String getActname() {
		return actname;
	}
	public void setActname(String actname) {
		this.actname = actname;
	}
	public String getIssign() {
		return issign;
	}
	public void setIssign(String issign) {
		this.issign = issign;
	}
	public String getSigntim() {
		return signtim;
	}
	public void setSigntim(String signtim) {
		this.signtim = signtim;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getActid() {
		return actid;
	}
	public void setActid(String actid) {
		this.actid = actid;
	}
	
	
}
