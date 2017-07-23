package model;

public class Buttonview extends Button{
    private String type="view";
    private String url;
    private Button[] sub_button;
    
	public Buttonview(String url,String name) {
		super();
		super.setName(name);
		this.url = url;
	}
	
	
	public Buttonview() {
		super();
	}


	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
