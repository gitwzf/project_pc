package com.wzf.model;

public class Buttonclick extends Button{
	private String key;
    private String type="click";
    private Button[] sub_button;
  
    
	public Buttonclick(String key,String name) {
		super();
		super.setName(name);
		this.key = key;
	}
	
	
	public Buttonclick() {
		super();
	}




	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Button[] getSub_button() {
		return sub_button;
	}


	public void setSub_button(Button[] subButton) {
		sub_button = subButton;
	}
	





    
}
