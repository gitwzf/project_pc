package com.wzf.action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.News;
import model.Retxt;
import model.Simplestyle;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import dbconnection.Dbconn;

import pubvari.Variable;

public class EditAction {
	Logger log = Logger.getLogger("logfile");
    public Variable vari=new Variable();
    
    public String le11(){
    	HttpServletRequest request=ServletActionContext.getRequest();
    	try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(request.getMethod().equals("GET"))
    		le11Get(request);
    	else
    		le11Post(request);
    		
    		return "le11";
    	
    }
    
    
	public String le111(){
		HttpServletRequest request=ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Dbconn db=new Dbconn();
		String name = null;
		String act=request.getParameter("act");
		String word=request.getParameter("word");
		if("delete".equals(act))
			try {
				
				log.info("delname="+name);
				db.delKeyword(name);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			else if("setwelcome".equals(act)){
				try {
					db.setWelcome(name);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}
				request.getSession().setAttribute("info","设置欢迎消息成功！");
			}
			else if("setdefault".equals(act)){
				try {
					db.setDefault(name);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}
				request.getSession().setAttribute("info","设置默认消息成功！");
				}
			else if("delwelcome".equals(act)){
				try {
					db.setWelcome("");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}
				request.getSession().setAttribute("info","取消欢迎消息成功！");
			}
			else if("deldefault".equals(act)){
				try {
					db.setDefault("");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}
				request.getSession().setAttribute("info","取消默认消息成功！");
				}
		String kind=request.getParameter("kind");
		request.getSession().setAttribute("kinds",kind);
			
			
			
		try {
			request.getSession().setAttribute("rearray",db.getRearray());
			if(word!=null)request.getSession().setAttribute("rearray",db.getRearray(word));
//			log.info("txtarray:"+db.getTxtarray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return "le111";
		
	}

	public void le11Get(HttpServletRequest req){
		Dbconn db=new Dbconn();
		String name=req.getParameter("name");
		String type=req.getParameter("type");
	//null	log.info("name="+new String(name.getBytes("iso8859-1"),"utf-8"));
//		log.info("type="+type);
		if(name!=null)
		try {
			Retxt object=db.getTxtarrayByname(name);	
		if("1".equals(type))object=db.getNewsarrayByname(name);
		if("2".equals(type))object= db.getMusarrayByname(name);
		
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
				log.info("鏍囬="+str[0]);
				sb.setMain(str[1]);
				sb.setUrlo(str[2]);
				sb.setPty(str[3]);
				sb.setPid(str[4]);
				if(!str[5].endsWith("http"))sb.setUrl(str[5]);
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
	}
	
	public void le11Post(HttpServletRequest req){
		Dbconn db=new Dbconn();
		String name=req.getParameter("name");
        String module=req.getParameter("module");
        String key[]=req.getParameterValues("keyword-name-new[]");
        
        String keystyle[]=req.getParameterValues("keyword-type-new[]");
        	
       
        if("basic".equals(module)){
        	String content=req.getParameter("basic-content-new[]");
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
        	
            String nurlo[]=req.getParameterValues("news-picture-new[news-wrap-item-0][]");
            	
            String nmain[]=req.getParameterValues("news-description-new[news-wrap-item-0][]");
            String ntype[]=req.getParameterValues("news-type[]");

            String ntypeid[]=req.getParameterValues("news-typeid[]");

            String nurl[]=req.getParameterValues("news-url-new[news-wrap-item-0][]");
            try {
        	for(int i=0;i<pic_count;i++)
        		nurl[i]+="?pty="+ntype[i]+"&pid="+ntypeid[i]; 
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
             String murlo=req.getParameter("music-url-new[]");

             String murl=req.getParameter("music-hqurl-new[]");

             String mmain=req.getParameter("music-description-new[]");
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
        	         for(int i=0;i<key.length;i++)
        					try {
        						if(!"".equals(key[i]))
        						db.addTxtrekey("-1",name,key[i],keystyle[i],content.split(";")[0]);
        					} catch (SQLException e) {
        						// TODO Auto-generated catch block
        						log.error(e);
        					}
        	
        }
        req.getSession().removeAttribute("bynamearray");
	}
	
}
