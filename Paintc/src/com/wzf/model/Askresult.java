package com.wzf.model;

public class Askresult {
    private String id;
	private Askresultitems[] askresultitems;

	
	
	public Askresult(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Askresultitems[] getAskresultitems() {
		return askresultitems;
	}

	public void setAskresultitems(Askresultitems[] askresultitems) {
		this.askresultitems = askresultitems;
	}
	
	
}
