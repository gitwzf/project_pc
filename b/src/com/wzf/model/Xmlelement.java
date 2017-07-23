package com.wzf.model;

import org.simpleframework.xml.*; 
import org.simpleframework.xml.core.Persister; 

import java.io.File; 
import java.util.List; 
import java.util.ArrayList; 
@Root
public class Xmlelement {
	
	@Element
	private String id;
	@Element
	private String name;
	@Element
	private int age;
	
	public Xmlelement() {
		super();
	}

	public Xmlelement(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	

}
