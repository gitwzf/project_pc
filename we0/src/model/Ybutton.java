package model;
public class Ybutton extends Button{
	private Button[] sub_button;

	
	
	public Ybutton(String name,Button[] subButton) {
		super();
		super.setName(name);
		sub_button = subButton;
	}



	public Button[] getSub_button() {
		return sub_button;
	}



	public void setSub_button(Button[] subButton) {
		sub_button = subButton;
	}

	
}
