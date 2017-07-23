package com.wzf.model;

public class Askpage {
	private Askpaper askpaper;
	private Askresult askresult;
	
	
	public Askpage(Askpaper askpaper, Askresult askresult) {
		super();
		this.askpaper = askpaper;
		this.askresult = askresult;
	}
	public Askpaper getAskpaper() {
		return askpaper;
	}
	public void setAskpaper(Askpaper askpaper) {
		this.askpaper = askpaper;
	}
	public Askresult getAskresult() {
		return askresult;
	}
	public void setAskresult(Askresult askresult) {
		this.askresult = askresult;
	}

}
