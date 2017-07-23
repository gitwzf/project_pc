package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.Voteuser;
import com.wzf.model.Workss;
import com.wzf.pubvari.Variable;

public class Vote extends HttpServlet{
	Logger log = Logger.getLogger("logfile");
	public Variable vari=new Variable();//空的？！
    public String project=vari.project;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		{
		
		boolean flag=false;//返回类型
		String phone=req.getParameter("phone");
		String competeid=req.getParameter("competeid");
		String action=req.getParameter("action");
	
		Dbcon db=new Dbcon();
		Voteuser v=(Voteuser) req.getSession().getAttribute("voteuser");
		String username = null;
		String userkey = null;
		if(v==null){
			Cookie[] cookies=req.getCookies();
			if(cookies!=null)
			for(Cookie coo:cookies){
				String client=coo.getValue();
				if(client.split("&").length==2&&"clientopenid".equals(coo.getName())){
					
					 username=client.split("&")[0].replace("'", "");
					 userkey=client.split("&")[1].replace("'", "");
					System.out.println("cooname="+coo.getName()+" "+username+" "+userkey);
					 v=Variable.map_actvoteuser.get(username+";"+userkey);
					if(v==null)v=db.getVoteuser(username, userkey);
					if(v!=null){
					Variable.map_actvoteuser.put(username+";"+userkey, v);
					
					}
					
					
				}
			}
			if(v==null)
			{System.out.println("没cookies加cookie..");
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
			
			req.getSession().setAttribute("voteuser", v=Variable.map_actvoteuser.get(username+";"+userkey));
			
		}
		log.info("Vote para: phone="+phone+" competeid="+competeid+" action="+action+"  v="+v);
		  String url="/Models";
		    if(v!=null&&action!=null)//存在该用户
		    {	if(action.equals("paint")){
		    		String str="00";
		    	   if(!v.getVotepaintnumbers()
		        		  .equals("0")){//有票
		    		if(competeid!=null&&competeid.matches("\\d+")){
		    		//画作加票
		    		  setPaintVote(competeid);
		    		//用户减票
		    		  int number=Integer.parseInt(v.getVotepaintnumbers());
		    		v.setVotepaintnumbers(--number+"");	
		    		str=v.getVotepaintnumbers();
		    		Variable.map_actvoteuser.put(v.getUsername()+";"+v.getVoteuserkey(),v);
		    	
		    	req.getSession().setAttribute("voteuser", v);
		    		}
		    		}
		    		PrintWriter pw = null;
					try {
						pw = resp.getWriter();
					} catch (IOException e) {
						
						log.error("error:", e);
					}
					flag=true;
		    		pw.write(str);
		    		pw.flush();
		    		pw.close();
		    	}
		    	else
					if(!db.isDoneuser(v.getUsername(),v.getVoteuserkey())){//该用户有票
						if(action.equals("model")&&phone!=null){
						//	req.getSession().setAttribute("isvote",db.addVote(phone,competeid,v.getUsername(),v.getVoteuserkey()));//返回错误或当前票数
							req.setAttribute("isvote",db.addVote(phone,competeid,v.getUsername(),v.getVoteuserkey()));
							req.getSession().setAttribute("models", db.getModellist(1l,vari.items,null));
						}else if(action.equals("detail")){
							String workid=req.getParameter("workid");
								req.setAttribute("isvote",db.addVotedetail(workid,v.getUsername(),v.getVoteuserkey(),true));//返回错误或当前票数
							System.out.println("phone="+phone+"  workid="+workid);
							
							req.getSession().setAttribute("detailwork", db.getDetailworkById(workid));
							url="/detailwork.jsp";
						}
						req.getSession().setAttribute("voteuser",v);
						
						}else {req.getSession().removeAttribute("isvote");
						}
		    }
					if(!flag){
						try {
							req.getRequestDispatcher(url).forward(req, resp);
						} catch (ServletException e) {
							
							log.error("error:", e);
						}
					 catch (IOException e) {
						
						log.error("error:", e);
					}
					}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			{
		
		String isvote="00";
		String phone=req.getParameter("phone");
		String competeid=req.getParameter("competeid");
		String action=req.getParameter("action");
		String username=req.getParameter("username");
		String userkey=req.getParameter("userkey");
			Dbcon db=new Dbcon();
		Voteuser v=(Voteuser) req.getSession().getAttribute("voteuser");
		
		    if(v!=null&&v.getUsername().equals(username)&&v.getVoteuserkey().equals(userkey))//存在该用户
					if(!db.isDoneuser(v.getUsername(),v.getVoteuserkey())){//该用户有票
						if(action.equals("model")&&phone!=null){
						//	req.getSession().setAttribute("isvote",db.addVote(phone,competeid,v.getUsername(),v.getVoteuserkey()));//返回错误或当前票数
						isvote=db.addVote(phone,competeid,v.getUsername(),v.getVoteuserkey());
							req.getSession().setAttribute("models", db.getModellist(1l,vari.items,null));
							if(!isvote.equals("00"))v.setVotenumbers(Integer.parseInt(v.getVotenumbers())-1+"");
						}else if(action.equals("detail")){
							String workid=req.getParameter("workid");
						//		req.getSession().setAttribute("isvote",db.addVotedetail(workid,v.getUsername(),v.getVoteuserkey(),true));//返回错误或当前票数
						isvote=db.addVotedetail(workid,v.getUsername(),v.getVoteuserkey(),true);
						if(!isvote.equals("00"))v.setVotedetailnumbers(Integer.parseInt(v.getVotedetailnumbers())-1+"");
							req.getSession().setAttribute("detailwork", db.getDetailworkById(workid));
						}
						
						req.getSession().setAttribute("voteuser", v);
						
						}else {
						}
				
		    if(v==null){System.out.println("补cookie...");
				Cookie[] cookies=req.getCookies();
				if(cookies!=null)
				for(Cookie coo:cookies){
					String client=coo.getValue();
					if(client.split("&").length==2&&"clientopenid".equals(coo.getName())){
						 username=client.split("&")[0].replace("'", "");
						 userkey=client.split("&")[1].replace("'", "");
						 v=Variable.map_actvoteuser.get(username+";"+userkey);
						if(v==null)v=db.getVoteuser(username, userkey);
						if(v!=null){
						Variable.map_actvoteuser.put(username+";"+userkey, v);
						req.getSession().setAttribute("voteuser", v);
						}
					}
				}
			}
		    if(isvote.equals("00"))log.info("投票失败  模特票数："+v.getVotenumbers()+" 详细页票数："+v.getVotedetailnumbers()+" v="+v+" username="+username+" userkey="+userkey+" phone="+phone+" competeid="+competeid+" action="+action);
		    
		    PrintWriter out = null;
			try {
				out = resp.getWriter();
				out.print(isvote); 
		         out.flush(); 
			} catch (IOException e) {
				
				log.error("error:", e);
			} finally{
		          out.close();
			}
	         
	}
	
	public void setPaintVote(String workid){
	for(int i=0;i<Variable.array_competepaint.size();i++){
			if(Variable.array_competepaint.get(i).getWorkid().equals(workid)){
				int vote=Integer.parseInt(Variable.array_competepaint.get(i).getScore());
				Variable.array_competepaint.get(i).setScore(vote+1+"");
				Variable.number_vote++;
				break;
			}
		
		}
	}
    
}
