package com.wzf.model;
import org.simpleframework.xml.*;
@Root
public class FunXmlModel {
	@Element
	private String name;
	@Element
	private String description;
	@Element
	private String path;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	

}
