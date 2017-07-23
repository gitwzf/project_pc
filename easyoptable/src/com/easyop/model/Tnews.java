package com.easyop.model;

import java.sql.Timestamp;

/**
 * Tnews entity. @author MyEclipse Persistence Tools
 */

public class Tnews implements java.io.Serializable {

	// Fields

	private Long id;
	private String title;
	private String titshlder;
	private String titsec;
	private Timestamp time;
	private String source;
	private String writer;
	private String picUrl;
	private String abstract_;
	private String content;
	private Integer makerId;
	private Timestamp timemake;
	private String makeinfo;
	private Integer checkrId;
	private Timestamp timecheck;
	private String checkres;
	private String checkinfo;
	private Integer puberId;
	private String rankId;
	private Timestamp timpub;
	private String status;

	// Constructors

	/** default constructor */
	public Tnews() {
	}

	/** minimal constructor */
	public Tnews(String title, Timestamp time, String source, String writer) {
		this.title = title;
		this.time = time;
		this.source = source;
		this.writer = writer;
	}

	/** full constructor */
	public Tnews(String title, String titshlder, String titsec, Timestamp time,
			String source, String writer, String picUrl, String abstract_,
			String content, Integer makerId, Timestamp timemake,
			String makeinfo, Integer checkrId, Timestamp timecheck,
			String checkres, String checkinfo, Integer puberId, String rankId,
			Timestamp timpub, String status) {
		this.title = title;
		this.titshlder = titshlder;
		this.titsec = titsec;
		this.time = time;
		this.source = source;
		this.writer = writer;
		this.picUrl = picUrl;
		this.abstract_ = abstract_;
		this.content = content;
		this.makerId = makerId;
		this.timemake = timemake;
		this.makeinfo = makeinfo;
		this.checkrId = checkrId;
		this.timecheck = timecheck;
		this.checkres = checkres;
		this.checkinfo = checkinfo;
		this.puberId = puberId;
		this.rankId = rankId;
		this.timpub = timpub;
		this.status = status;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitshlder() {
		return this.titshlder;
	}

	public void setTitshlder(String titshlder) {
		this.titshlder = titshlder;
	}

	public String getTitsec() {
		return this.titsec;
	}

	public void setTitsec(String titsec) {
		this.titsec = titsec;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getWriter() {
		return this.writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getAbstract_() {
		return this.abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getMakerId() {
		return this.makerId;
	}

	public void setMakerId(Integer makerId) {
		this.makerId = makerId;
	}

	public Timestamp getTimemake() {
		return this.timemake;
	}

	public void setTimemake(Timestamp timemake) {
		this.timemake = timemake;
	}

	public String getMakeinfo() {
		return this.makeinfo;
	}

	public void setMakeinfo(String makeinfo) {
		this.makeinfo = makeinfo;
	}

	public Integer getCheckrId() {
		return this.checkrId;
	}

	public void setCheckrId(Integer checkrId) {
		this.checkrId = checkrId;
	}

	public Timestamp getTimecheck() {
		return this.timecheck;
	}

	public void setTimecheck(Timestamp timecheck) {
		this.timecheck = timecheck;
	}

	public String getCheckres() {
		return this.checkres;
	}

	public void setCheckres(String checkres) {
		this.checkres = checkres;
	}

	public String getCheckinfo() {
		return this.checkinfo;
	}

	public void setCheckinfo(String checkinfo) {
		this.checkinfo = checkinfo;
	}

	public Integer getPuberId() {
		return this.puberId;
	}

	public void setPuberId(Integer puberId) {
		this.puberId = puberId;
	}

	public String getRankId() {
		return this.rankId;
	}

	public void setRankId(String rankId) {
		this.rankId = rankId;
	}

	public Timestamp getTimpub() {
		return this.timpub;
	}

	public void setTimpub(Timestamp timpub) {
		this.timpub = timpub;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}