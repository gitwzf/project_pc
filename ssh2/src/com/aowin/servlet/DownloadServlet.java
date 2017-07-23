package com.aowin.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class DownloadServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path="E:\\se1203\\fileupload";
		String filename=request.getParameter("filename");
		
		FileInputStream input=new FileInputStream(new File(path+"\\"+filename));
		
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename="+filename);
		
		OutputStream output=response.getOutputStream();
		IOUtils.copy(input, output);
		output.flush();
		input.close();
		output.close();
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
