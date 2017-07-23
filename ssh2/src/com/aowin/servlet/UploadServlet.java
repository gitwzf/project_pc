package com.aowin.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

public class UploadServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String username=request.getParameter("username");
//		String password=request.getParameter("password");
//		String photo=request.getParameter("photo");
//		System.out.println(username+"   "+password+"    "+photo);
		
		
//		InputStream input=request.getInputStream();
//		StringBuffer sb=new StringBuffer();
//		int b=0;
//		while((b=input.read())!=-1){
////			System.out.print((char)b);
//			sb.append((char)b);
//		}
		HttpSession session=request.getSession();
		try{
			FileItemFactory factory=new DiskFileItemFactory();
			ServletFileUpload upload=new ServletFileUpload(factory);
			List<FileItem> list=upload.parseRequest(request);
			
			for(FileItem item:list){
				if(item.isFormField()){
					System.out.println(item.getFieldName()+"   "+item.getString());
					session.setAttribute(item.getFieldName(), item.getString());
				}
				else{
					//нд╪Ч
					String path="E:\\se1203\\fileupload";
					String filename=item.getName();
					session.setAttribute(item.getFieldName(), filename);
					FileOutputStream output=new FileOutputStream(new File(path+"\\"+filename));
					InputStream input=item.getInputStream();
					
					IOUtils.copy(input, output);
					
//					int b=0;
//					while((b=input.read())!=-1){
//						output.write(b);
//					}
//					
					output.flush();
					output.close();
					input.close();
					
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		response.sendRedirect("/ssh2/welcome.jsp");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
