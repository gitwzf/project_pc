package com.wzf.model;

public class Manageruser {
	private String tname;
	private String gender;
	private String phone;
	private String pass;
	private String privlg;
	private String id;
	private String teacherphone;
	private String teacherid;
	private String teachername;
	private String parentsname;
	private String schoolid;
	private String isTrain;
	private String stname;
	private String account;
	private String uptimes;
	private String biryear;
	private String birmonth;
	private String birday;
	private String isyearvip;
	private String headimg;
	private String ex1;
	private String status;
	private String competepaintids;
	private String age;
	private int vote=0;
	
	
	
	public Manageruser() {
		super();
	}

	public Manageruser(String competepaintids, int vote) {
		super();
		this.competepaintids = competepaintids;
		this.vote = vote;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUptimes() {
		return uptimes;
	}
	public void setUptimes(String uptimes) {
		this.uptimes = uptimes;
	}
	
	
	
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getIsyearvip() {
		return isyearvip;
	}
	public void setIsyearvip(String isyearvip) {
		this.isyearvip = isyearvip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTeacherphone() {
		return teacherphone;
	}
	public void setTeacherphone(String teacherphone) {
		this.teacherphone = teacherphone;
	}
	public String getParentsname() {
		return parentsname;
	}
	public void setParentsname(String parentsname) {
		this.parentsname = parentsname;
	}
	public String getBiryear() {
		return biryear;
	}
	public void setBiryear(String biryear) {
		this.biryear = biryear;
	}
	public String getBirmonth() {
		return birmonth;
	}
	public void setBirmonth(String birmonth) {
		this.birmonth = birmonth;
	}
	public String getBirday() {
		return birday;
	}
	public void setBirday(String birday) {
		this.birday = birday;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getSchoolid() {
		return schoolid;
	}
	public void setSchoolid(String schoolid) {
		this.schoolid = schoolid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPrivlg() {
		return privlg;
	}
	public void setPrivlg(String privlg) {
		this.privlg = privlg;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public String getIsTrain() {
		return isTrain;
	}
	public void setIsTrain(String isTrain) {
		this.isTrain = isTrain;
	}
	public String getStname() {
		return stname;
	}
	public void setStname(String stname) {
		this.stname = stname;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public String getEx1() {
		return ex1;
	}
	public void setEx1(String ex1) {
		this.ex1 = ex1;
	}
	public String getCompetepaintids() {
		return competepaintids;
	}
	public void setCompetepaintids(String competepaintids) {
		this.competepaintids = competepaintids;
	}
	public int compareTo(Manageruser value) {
		
		if(Integer.parseInt(this.id)>=Integer.parseInt(value.id))
			return 1;
		else return -1;
		
	}
	

}
