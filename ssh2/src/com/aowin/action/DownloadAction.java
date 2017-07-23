package com.aowin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DownloadAction {
	
	private String filename;


	private String contentType;
	private String contentDisposition;
//	private String inputName = "inputStream";
	
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getContentType() {
		return "image/jpeg";
	}

	public String getContentDisposition() {
		return "attachement;filename="+filename;
	}

//	public String getInputName() {
//		return inputName;
//	}
	
	public InputStream getInputStream() throws FileNotFoundException{
		String path="E:\\se1203\\fileupload\\";
		FileInputStream input=new FileInputStream(new File(path+filename));
		return input;
	}

	public String execute(){
		return "success";
	}

}
