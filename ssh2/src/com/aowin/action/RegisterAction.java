package com.aowin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import com.aowin.service.IUserService;
import com.aowin.service.UserService;

public class RegisterAction{
	
	private IUserService userService;
	private String username;
	private String password;
	private String password2;
	
	
	private File photo;
	private String photoFileName;
	private String photoContentType;


	public File getPhoto() {
		return photo;
	}



	public void setPhoto(File photo) {
		this.photo = photo;
	}



	public String getPhotoFileName() {
		return photoFileName;
	}



	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}



	public String getPhotoContentType() {
		return photoContentType;
	}



	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getPassword2() {
		return password2;
	}



	public void setPassword2(String password2) {
		this.password2 = password2;
	}



	public IUserService getUserService() {
		return userService;
	}



	public void setUserService(IUserService userService) {
		this.userService = userService;
	}



	public String execute(){
		save();
		userService.register(username, password,photoFileName);
		ServletActionContext.getRequest().getSession().setAttribute("username", username);
		return "success";
	}
	
	private void save(){
		String path="E:\\se1203\\fileupload\\";
		try{
			FileInputStream input=new FileInputStream(photo);
			FileOutputStream output=new FileOutputStream(new File(path+photoFileName));
			IOUtils.copy(input, output);
			output.flush();
			input.close();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
