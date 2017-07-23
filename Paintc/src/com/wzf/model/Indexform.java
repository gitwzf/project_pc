package com.wzf.model;

public class Indexform {
	private String WIDseller_email;
	private String WIDout_trade_no;
	private String WIDsubject;
	private String WIDtotal_fee;
	
	
	public Indexform(String wIDsellerEmail, String wIDoutTradeNo,
			String wIDsubject, String wIDtotalFee) {
		WIDseller_email = wIDsellerEmail;
		WIDout_trade_no = wIDoutTradeNo;
		WIDsubject = wIDsubject;
		WIDtotal_fee = wIDtotalFee;
	}
	public String getWIDseller_email() {
		return WIDseller_email;
	}
	public void setWIDseller_email(String wIDsellerEmail) {
		WIDseller_email = wIDsellerEmail;
	}
	public String getWIDout_trade_no() {
		return WIDout_trade_no;
	}
	public void setWIDout_trade_no(String wIDoutTradeNo) {
		WIDout_trade_no = wIDoutTradeNo;
	}
	public String getWIDsubject() {
		return WIDsubject;
	}
	public void setWIDsubject(String wIDsubject) {
		WIDsubject = wIDsubject;
	}
	public String getWIDtotal_fee() {
		return WIDtotal_fee;
	}
	public void setWIDtotal_fee(String wIDtotalFee) {
		WIDtotal_fee = wIDtotalFee;
	}

}
