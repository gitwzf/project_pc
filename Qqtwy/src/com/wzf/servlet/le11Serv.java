package com.wzf.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wzf.pubvari.Variable;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Retxt;
import com.wzf.model.News;
import com.wzf.model.Simplestyle;


public class le11Serv extends HttpServlet{
	Logger log = Logger.getLogger("logfile");
	public Variable vari=new Variable();
    public String project=vari.project;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Dbcon db=new Dbcon();
//		String pub=(String) req.getSession().getAttribute("pub");
//		String[] sss = null;
//		try {
//			sss = db.getPubUserPassName(pub);
//			db.setDatabase(sss[2]);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			log.error(e);
//		}
		String name=req.getParameter("name");
		String type=req.getParameter("type");
	//null	log.info("name="+new String(name.getBytes("iso8859-1"),"utf-8"));
//		log.info("type="+type);
		if(name!=null)
		try {
			String Nname=new String(name.getBytes("iso8859-1"),"utf-8");
			Retxt object=db.getTxtarrayByname(Nname);	
		if("1".equals(type))object=db.getNewsarrayByname(Nname);
		if("2".equals(type))object= db.getMusarrayByname(Nname);
		
//		log.info(object.name);
		Simplestyle ss[]=new Simplestyle[object.key.length];
		for(int i=0;i<object.key.length;i++)
		{ log.info(object.key[i]);
		  Simplestyle s=new Simplestyle();
			s.setKey(object.key[i]);
			s.setStyle(object.keystyle[i]);
			ss[i]=s;
		}
		object.setSstyle(ss);
		if("1".equals(type)||"2".equals(type)){
			String str[]=new String[object.content.split(";").length];
			News sback[]=new News[object.content.split(";").length];
			for(int i=0;i<object.content.split(";").length;i++)
				{str=object.content.split(";")[i].split("\\|");
				News sb=new News();
				sb.setTitle(str[0]);
				log.info("标题="+str[0]);
				sb.setMain(str[1]);
				sb.setUrlo(str[2]);
				sb.setPty(str[3]);
				sb.setPid(str[4]);
				sb.setUrl(str[5]);
				sback[i]=sb;
				}
			object.setNews(sback);
		}
		
		req.getSession().setAttribute("bynamearray", object);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		//log.info("bynaenarray.name="+);
		else
		req.getSession().removeAttribute("bynamearray");
		resp.sendRedirect("/"+project+"/le11.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Dbcon db=new Dbcon();
//		String pub=(String) req.getSession().getAttribute("pub");
//		log.info("encode="+req.getCharacterEncoding());
//		log.info("pub="+pub);
//		String[] s = null;
//		try {
//			s = db.getPubUserPassName(pub);
//			db.setDatabase(s[2]);
//			log.info("database0="+s[2]);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			log.error(e);
//		}
		String name=req.getParameter("name");
		       name=new String(name.getBytes("iso8859-1"),"utf-8");
        String module=req.getParameter("module");
        	
   //module 
        
        String key[]=req.getParameterValues("keyword-name-new[]");
              for(int i=0;i<key.length;i++)
            	  key[i]=new String(key[i].getBytes("iso8859-1"),"utf-8");
        
        String keystyle[]=req.getParameterValues("keyword-type-new[]");
        	
       
        if("basic".equals(module)){
        	String content=req.getParameter("basic-content-new[]");
            content=new String(content.getBytes("iso8859-1"),"utf-8");	
         for(int i=0;i<key.length;i++)
				try {
					if(!"".equals(key[i]))
					db.addTxtrekey("0",name,key[i],keystyle[i],content.split(";")[0]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}		
        }
        if("news".equals(module)){
        	int pic_count=0;
        	if(req.getParameter("pic-count")!=null)
        		pic_count=Integer.parseInt(req.getParameter("pic-count"));
        	else pic_count=Integer.parseInt(req.getParameter("pic-count1"));
        	
        	String ntitle[]=req.getParameterValues("news-title-new[news-wrap-item-0][]");
        	for(int i=0;i<pic_count;i++)
        		ntitle[i]=new String(ntitle[i].getBytes("iso8859-1"),"utf-8");
        	
            String nurlo[]=req.getParameterValues("news-picture-new[news-wrap-item-0][]");
            	
            String nmain[]=req.getParameterValues("news-description-new[news-wrap-item-0][]");
            for(int i=0;i<pic_count;i++)
            	nmain[i]=new String(nmain[i].getBytes("iso8859-1"),"utf-8");
            String ntype[]=req.getParameterValues("news-type[]");

          String ntypeid[]=req.getParameterValues("news-typeid[]");

            String nurl[]=req.getParameterValues("news-url-new[news-wrap-item-0][]");
            try {
        //	for(int i=0;i<pic_count;i++)
        		//nurl[i]+="?pty="+ntype[i]+"&pid="+ntypeid[i]; 
                	String n_id=db.addNesItem("1", ntitle,nmain,nurlo,ntype,ntypeid,nurl,pic_count);
		        	
					for(int i=0;i<key.length;i++)
						if(!"".equals(key[i]))
					db.addNewsrekey(name,key[i],keystyle[i],n_id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}
        }
        if("music".equals(module)){
        	 String mtitle=req.getParameter("music-title-new[]");
        	 mtitle=new String(mtitle.getBytes("iso8859-1"),"utf-8");
             String murlo=req.getParameter("music-url-new[]");

             String murl=req.getParameter("music-hqurl-new[]");

             String mmain=req.getParameter("music-description-new[]");
             mmain=new String(mmain.getBytes("iso8859-1"),"utf-8");	
			try {
				int nm_id=db.addMusItem("2", mtitle, mmain, murlo, murl);
				for(int i=0;i<key.length;i++)
					if(!"".equals(key[i]))
				db.addMusrekey(name,key[i],keystyle[i],nm_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e);
			}
        	
        	
        }
        if("userapi".equals(module)){
        	String content=req.getParameter("basic-content-new[]");
            content=new String(content.getBytes("iso8859-1"),"utf-8");	
        	         for(int i=0;i<key.length;i++)
        					try {
        						if(!"".equals(key[i]))
        						db.addTxtrekey("-1",name,key[i],keystyle[i],content.split(";")[0]);
        					} catch (SQLException e) {
        						// TODO Auto-generated catch block
        						log.error(e);
        					}
        	
        }
				
				
				
        // log.info("con="+con[0]+"  "+con[1]);
        req.getSession().removeAttribute("bynamearray");
        resp.sendRedirect("/"+project+"/le11.jsp");
	}
}
