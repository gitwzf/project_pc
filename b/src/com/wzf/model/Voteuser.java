package com.wzf.model;

public class Voteuser {
	private String username;
	private String voteuserkey;
	
	
	public Voteuser(String username, String voteuserkey) {
		super();
		this.username = username;
		this.voteuserkey = voteuserkey;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getVoteuserkey() {
		return voteuserkey;
	}
	public void setVoteuserkey(String voteuserkey) {
		this.voteuserkey = voteuserkey;
	}


}
