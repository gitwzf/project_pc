package com.wzf.model;

import java.util.ArrayList;
import org.simpleframework.xml.*;
@Root
public class FunXmlModelList {
	@ElementList
	private ArrayList<FunXmlModel> funXmlModelList;

	public ArrayList<FunXmlModel> getFunXmlModelList() {
		return funXmlModelList;
	}

	public void setFunXmlModelList(ArrayList<FunXmlModel> funXmlModelList) {
		this.funXmlModelList = funXmlModelList;
	}
	

}
