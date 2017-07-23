package com.wzf.model;

public class Company {
	private String id;
	private String name;
    private String address;
    private String position_tim;
    
    private String typeoneid;
    private String properties;
    private String scale;
    private String info;
    private String pic_url;
    
    private String connector;
    private String email;
    private String prof_url;
    private String tel;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPosition_tim() {
		return position_tim;
	}
	public void setPosition_tim(String positionTim) {
		position_tim = positionTim;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getTypeoneid() {
		return typeoneid;
	}
	public void setTypeoneid(String typeoneid) {
		this.typeoneid = typeoneid;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String picUrl) {
		pic_url = picUrl;
	}
	public String getConnector() {
		return connector;
	}
	public void setConnector(String connector) {
		this.connector = connector;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProf_url() {
		return prof_url;
	}
	public void setProf_url(String profUrl) {
		prof_url = profUrl;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
    
}
