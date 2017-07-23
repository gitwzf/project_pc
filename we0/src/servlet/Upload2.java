package servlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pubvari.Variable;

import com.oreilly.servlet.MultipartRequest;
import com.sun.org.apache.commons.digester.rss.Image;


public class Upload2 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Variable vari=new Variable();
		MultipartRequest mr=new MultipartRequest(req, vari.up_audio_path,10485760);
		String npath=req.getSession().getServletContext().getRealPath("/")+"/"+vari.up_audio_path;
		File str = mr.getFile("attachFile");
		File str0;
		str.renameTo(str0=new File(npath+"/"+new Date().getTime()+".mp3"));
		Writer out = resp.getWriter();	
		System.out.println("str00="+str0.getAbsolutePath());
       out.write("<html><body bgcolor='green'><form name='news'><input type='text' name='_picture' id='apath' value='"+str0.getAbsolutePath().replace("\\", "/").replace(npath.split("/file")[0], vari.URL)+"'/></form></body></html>");
       out.flush();
		out.close();
	}
    
}
