package com.wzf.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Manageruser;
import com.wzf.model.Voteuser;
import com.wzf.pubvari.Variable;

public class Detailwork extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String from=req.getParameter("from");
		String isappinstalled=req.getParameter("isappinstalled");
		String workid=req.getParameter("workid");
		Manageruser man=(Manageruser) req.getSession().getAttribute("manager");
		
		if(workid!=null){
			//排行榜
			String competename=req.getParameter("competename");
			String place=req.getParameter("place");
			if(competename!=null)competename=new String(competename.getBytes("iso-8859-1"),"utf-8");
			else competename="";
			req.setAttribute("competename", competename);
			req.setAttribute("place", place==null?"":place);
			//展厅
			String type=req.getParameter("type");
			req.getSession().setAttribute("type", type);
			
			Dbcon db=new Dbcon();
			req.getSession().setAttribute("detailwork", db.getDetailworkById(workid));
			if(man==null){
			String username = "";
			String userkey="";
			boolean flag=false;
			Cookie[] cookies=req.getCookies();
			if(cookies!=null)
			for(Cookie coo:cookies){
				String client=coo.getValue();
				System.out.println("cooname="+coo.getName());
				if(client.split("&").length==2&&"clientopenid".equals(coo.getName())){
					username=client.split("&")[0].replace("'", "");
					userkey=client.split("&")[1].replace("'", "");
					req.getSession().setAttribute("username", username);
					req.getSession().setAttribute("userkey", userkey);
					
					Voteuser v=Variable.map_actvoteuser.get(username+";"+userkey);
					if(v==null)v=db.getVoteuser(username, userkey);
					if(v!=null){flag=true;
					Variable.map_actvoteuser.put(username+";"+userkey, v);
					req.getSession().setAttribute("voteuser", v);
					}
//					if(db.isDoneuser(username,userkey))
//						req.getSession().setAttribute("noprivlg", "0");
					System.out.println(client);
				}
				
			}
			if(from!=null&&isappinstalled!=null&&!flag){
				System.out.println("加cookie..");
					username=new Date().getTime()+"";//永远不会重复--并发
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
			req.getSession().setAttribute("isvote",db.addVotedetail(workid,username,userkey,false));//投票返回错误或当前票数
			req.getSession().setAttribute("voteuser", Variable.map_actvoteuser.get(username+";"+userkey));
			}else{
				req.getSession().removeAttribute("isvote");
				req.getSession().removeAttribute("voteuser");
			}
			req.getRequestDispatcher("/detailwork.jsp").forward(req, resp);
		}
		else req.getRequestDispatcher("/worklist.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
      
}
