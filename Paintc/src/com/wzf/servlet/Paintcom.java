package com.wzf.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Manageruser;
import com.wzf.model.Page;
import com.wzf.model.Voteuser;
import com.wzf.model.Workss;
import com.wzf.pubvari.Variable;

public class Paintcom extends HttpServlet{
    Variable vari=new Variable();
    Logger log=Logger.getLogger("logfile");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Voteuser v=(Voteuser) req.getSession().getAttribute("voteuser");
		Dbcon db=new Dbcon();
		if(v==null){
		String username;
		String userkey;
		boolean flag=false;
		Cookie[] cookies=req.getCookies();
		if(cookies!=null)
		for(Cookie coo:cookies){
			String client=coo.getValue();
			if(client.split("&").length==2&&"clientopenid".equals(coo.getName())){
				username=client.split("&")[0].replace("'", "");
				userkey=client.split("&")[1].replace("'", "");
				 v=Variable.map_actvoteuser.get(username+";"+userkey);
				if(v==null)v=db.getVoteuser(username, userkey);
					if(v!=null){flag=true;
					Variable.map_actvoteuser.put(username+";"+userkey, v);
					req.getSession().setAttribute("voteuser", v);
					}
			}
			
		}
		if(!flag){System.out.println("没cookies加cookie..");
			username=new Date().getTime()+"";
			userkey=(long)(Math.random()*89998)+10001+"";
			int ips=0;    //获取ip
			String ip=req.getRemoteAddr();
			String ip_headers[]={"x-forwarded-for","Proxy-Client-IP","WL-Proxy-Client-IP","HTTP_CLIENT_IP","HTTP_X_FORWARDED_FOR"};
			 while(ips<ip_headers.length&&(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)||"127.0.0.1".equals(ip))){
			   ip = req.getHeader(ip_headers[ips++]); 
			    }
			Variable.map_actvoteuser.put(username+";"+userkey, db.addVoteuser(username,userkey,ip));
			Cookie cook=new Cookie("clientopenid", username+"&"+userkey);//放入cookie
			cook.setMaxAge(365*24*3600);
			resp.addCookie(cook);
		}
		
		}
		ArrayList<Workss> array=new ArrayList<Workss>();
		String pageid=req.getParameter("pageid");
		String name=req.getParameter("modelname");
		int pagenumber=1,maxp;
		if(pageid!=null&&pageid.matches("\\d+"))
			pagenumber=Integer.parseInt(pageid);
		
//		if(name==null||"".equals(name))
//		{req.getSession().setAttribute("paintlist", getPaintlist(pagenumber));//批次  和  条目数
//		maxp=Variable.array_competepaint.size()/vari.items_pc+(Variable.array_competepaint.size()%vari.items_pc==0?0:1);
//			}
//		else
//		{	req.getSession().setAttribute("paintlist",array= getPaintlist(name));
//		maxp=array.size()/vari.items_pc+(array.size()%vari.items_pc==0?0:1);
//		
//		}
//		req.getSession().setAttribute("page",new Page(pagenumber+"",vari.items_pc+"",(maxp==0?1:maxp)+""));
//		req.setAttribute("i0", (pagenumber-1)*vari.items_pc+"");
		if(name==null||"".equals(name)){
			req.getSession().setAttribute("competerlist", getCompeterlist(pagenumber));//批次  和  条目数
		maxp=Variable.map_compete_user.size()/vari.items_pc+(Variable.map_compete_user.size()%vari.items_pc==0?0:1);
			}
			else
			{
				name=new String(name.getBytes("iso8859-1"),"utf-8");
				req.getSession().setAttribute("competerlist",getCompeterlist(pagenumber,name));
			   req.setAttribute("modelname",URLEncoder.encode(name, "utf-8"));
			maxp=1;
			
			}
		
		req.getSession().setAttribute("page",new Page(pagenumber+"",vari.items_pc+"",(maxp==0?1:maxp)+""));
		req.setAttribute("i0", (pagenumber-1)*vari.items_pc+"");
		
		req.getRequestDispatcher("/competerlist.jsp").forward(req, resp);
		
		
	}

	
   
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
	 public List<Map.Entry<String, Manageruser>> getSortCompeteUser(){
		 List<Map.Entry<String, Manageruser>> infoIds =
		        new ArrayList<Map.Entry<String, Manageruser>>(Variable.map_compete_user.entrySet());
		    Collections.sort(infoIds, new Comparator<Map.Entry<String, Manageruser>>() {
		        public int compare(Map.Entry<String, Manageruser> o1, Map.Entry<String, Manageruser> o2) {
		            return (o1.getValue()).compareTo(o2.getValue());
		        }
		    }); 
		    return infoIds;
		 
	 }

	 public ArrayList<Manageruser> getCompeterlist(int pageid){
		 List<Map.Entry<String, Manageruser>> infoIds =getSortCompeteUser();
		    ArrayList<Manageruser> array=new ArrayList<Manageruser>();
		    for(int i=(pageid-1)*vari.items_pc;i<infoIds.size()&&i<pageid*vari.items_pc;i++)
		    	array.add(infoIds.get(i).getValue());
		return array;
	}
	public ArrayList<Manageruser> getCompeterlist(int pageid,String name){
		 List<Map.Entry<String, Manageruser>> infoIds =getSortCompeteUser();
		 ArrayList<Manageruser> array=new ArrayList<Manageruser>();
		 int i=(pageid-1)*vari.items_pc;
		 for(Map.Entry<String, Manageruser> map:infoIds)
			 if(map.getValue().getStname().matches("\\S*"+name+"\\S*"))
				 {
				 array.add(map.getValue());
				 if(++i>=pageid*vari.items_pc)break;
				 }
		return array;
	}

	public ArrayList<Workss> getPaintlist(int pageid){//页码查询
		 ArrayList<Workss> array=new ArrayList<Workss>();
		 for(int i=(pageid-1)*vari.items_pc;
		 i<(vari.array_competepaint.size()>pageid*vari.items_pc?pageid*vari.items_pc:vari.array_competepaint.size());i++)
			 array.add(vari.array_competepaint.get(i));
		 return array;
	 }
	
	
	public ArrayList<Workss> getPaintlist(String name){//名字查询
		 ArrayList<Workss> array=new ArrayList<Workss>();
		 for(int i=0;i<vari.array_competepaint.size();i++)
		  if(vari.array_competepaint.get(i).getTname().matches("\\S*"+name+"\\S*"))
			 array.add(vari.array_competepaint.get(i));
		 return array;
	 }
	
}
