package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dbconnection.Dbconn;

public class le13Serv extends HttpServlet{
	Logger log = Logger.getLogger("logfile");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbconn db=new Dbconn(); 
		String pub=(String) req.getSession().getAttribute("pub");
		String[] s = null;
		try {
			s = db.getPubUserPassName(pub);
			db.setDatabase(s[2]);
		} catch (SQLException e) {
			
			log.error(e);
		}
		InputStream inputStream = req.getInputStream();  
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader); 
        String str0=null;
        String str1 = null,str2 = null;
        while ((str0 = bufferedReader.readLine()) != null){
        	if(str0.endsWith("\"welcome\"")){
        		bufferedReader.readLine();
        		str1=bufferedReader.readLine();
        	}
        	if(str0.endsWith("\"default\"")){
        		bufferedReader.readLine();
        		str2=bufferedReader.readLine();
        	}
        }
//        log.info("str1="+str1);
//        log.info("str2="+str2);
        try {
        	if(str1!=null)
			db.addTxtrekey("-1","event", "subscribe", "1", str1);
        	if(str2!=null)
    			db.addTxtrekey("-1","event", "default", "1", str2);
		} catch (SQLException e) {
			
			log.error(e);
		}
        resp.sendRedirect("/weEngine/le13.jsp");
	}
         
}
