/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.fdc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fdc.dbconn.Dbcon;
import com.fdc.model.Apply;
import com.fdc.model.Sgame;
import com.fdc.model.User;
import com.fdc.pubvari.Variable;
import com.wzf.method.ConvertTheOther;
import com.wzf.method.Session;

/** 
 * MyEclipse Struts
 * Creation date: 07-29-2014
 * 
 * XDoclet definition:
 * @struts.action parameter="type" validate="true"
 */
public class Action extends DispatchAction {
	/*
	 * Generated Methods
	 */
    Dbcon db=new Dbcon();
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
    //访问
	public ActionForward visit(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) {
		
		String actid=(String) req.getSession().getAttribute("actid");
		if(actid==null){actid=req.getParameter("actid");
		    if(actid!=null)req.getSession().setAttribute("actid", actid);//签到标记
		  //  log.info("actid放入session="+actid);
		}
		
		User user=(User) req.getSession().getAttribute("user");
		if(user==null){//session已过期
			String username;
			String password;
			Cookie[] cookies=req.getCookies();
			if(cookies!=null)
			for(Cookie coo:cookies){
				String client=coo.getValue();
				if(client.split("\\|").length==2&&"client".equals(coo.getName())){
					username=client.split("\\|")[0];
					password=client.split("\\|")[1];
					if(username!=null)username=ConvertTheOther.DecodeUtf8(username);
					if(Variable.map_actuser.containsKey(username))
						if((user=Variable.map_actuser.get(username)).getName().equals(password))
						{  if(user==null)user=db.getUser(username,password);
							
						if(user!=null)	{
							Variable.map_actuser.put(username, user);
							req.getSession().setAttribute("user", user);
						if(actid==null)
						{   
							return mapping.findForward("success");}
							else {
								if(Variable.map_apply.containsKey(user.getId()+";"+actid))//签到
								{req.getSession().setAttribute("apply", Variable.map_apply.get(user.getId()+";"+actid));
									return mapping.findForward("success2");}
							else{ 
							req.setAttribute("info",ConvertTheOther.EncodeUtf8("<center>未报名该活动</center>"));
								return mapping.findForward("error2");//未报名
							}
							}		
								
							}
						
						}
				}
			}
		}else//session未过期
		{	if(actid==null)
		return mapping.findForward("success");
		else {
			if(Variable.map_apply.containsKey(user.getId()+";"+actid))//签到
			{req.getSession().setAttribute("apply", Variable.map_apply.get(user.getId()+";"+actid));
				return mapping.findForward("success2");}
		else {
			req.setAttribute("info",ConvertTheOther.EncodeUtf8("<center>未报名该活动</center>"));
			return mapping.findForward("error2");//未报名
		}
		}
		}
		
		return mapping.findForward("error");
	}
	
	
	//签到操作
	public ActionForward signup(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) {
		
		User user=(User) req.getSession().getAttribute("user");
		String actid=(String) req.getSession().getAttribute("actid");
		if(user!=null&&actid!=null){
			if(Variable.map_apply.get(user.getId()+";"+actid)!=null){
			if(Variable.map_apply.get(user.getId()+";"+actid).getIssign().equals("0"))
			{
			String tim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());	
			if(db.signUp(user.getId(),actid,tim)){
			Variable.map_apply.get(user.getId()+";"+actid).setIssign("1");
			Variable.map_apply.get(user.getId()+";"+actid).setSigntim(tim);}
			}
			req.getSession().setAttribute("apply", Variable.map_apply.get(user.getId()+";"+actid));
			return mapping.findForward("success2");
			}
			else//未报名
			{	req.setAttribute("info",ConvertTheOther.EncodeUtf8("<center>未报名该活动</center>"));
				return mapping.findForward("error2");}
		}

			return mapping.findForward("error");
	}
	//登陆
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) {
		
		String actid=(String) req.getSession().getAttribute("actid");
		User user=(User) req.getSession().getAttribute("user");
		if(user==null){
		String name=req.getParameter("name");
		name=ConvertTheOther.ISO88591ToUtf8(name);
		String tel=req.getParameter("tel");
		if(Variable.map_actuser.containsKey(tel)&&Variable.map_actuser.get(tel).getName().equals(name))
			user=Variable.map_actuser.get(tel);
		if(user==null)user=db.getUser(tel, name);
		if(user!=null)
			{
			Variable.map_actuser.put(tel, user);
			req.getSession().setAttribute("user", user);
			Cookie cook=new Cookie("client", tel+"|"+ConvertTheOther.EncodeUtf8(name));//放入cookie
			cook.setMaxAge(365*24*3600);
			resp.addCookie(cook);
			if(actid==null)
				return mapping.findForward("success");
				else {
					if(Variable.map_apply.containsKey(user.getId()+";"+actid))//签到
					{req.getSession().setAttribute("apply", Variable.map_apply.get(user.getId()+";"+actid));
						return mapping.findForward("success2");}
				else {
					req.setAttribute("info",ConvertTheOther.EncodeUtf8("<center>未报名该活动</center>"));
					return mapping.findForward("error2");//未报名
				}
				}
			}
		}else
			if(actid==null)
				return mapping.findForward("success");
				else {
					if(Variable.map_apply.containsKey(user.getId()+";"+actid))//签到
					{req.getSession().setAttribute("apply", Variable.map_apply.get(user.getId()+";"+actid));
						return mapping.findForward("success2");}
				else{
					req.setAttribute("info",ConvertTheOther.EncodeUtf8("<center>未报名该活动</center>"));
					return mapping.findForward("error2");//未报名
				}
				}
			
		return mapping.findForward("error");

	}
	//注册
	public ActionForward register(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) {
		
//		User user=(User) req.getSession().getAttribute("user");
//		if(user==null){
		String actid=(String) req.getSession().getAttribute("actid");
		User user=null;
		String name=req.getParameter("name");
		name=ConvertTheOther.ISO88591ToUtf8(name);
		//String gender=req.getParameter("gender");
		//String iswish=req.getParameter("iswish");
		String tel=req.getParameter("tel");
		String yzm0=req.getParameter("yzm");
		String yzm1=(String) req.getSession().getAttribute("yzm");
		
		
		//if(yzm0.equals(yzm1))
		if(db.getUser(tel, null)!=null){
			user=db.getUser(tel, name);
		}
		else
		if(!Variable.map_actuser.containsKey(tel))//非注册用户
		user=db.addUser(name,tel,null,null);
		
		if(user!=null){
			Variable.map_actuser.put(tel, user);
		req.getSession().setAttribute("user", user);
		Cookie cook=new Cookie("client", tel+"|"+ConvertTheOther.EncodeUtf8(name));//放入cookie
		cook.setMaxAge(365*24*3600);
		resp.addCookie(cook);
		
		  Sgame sg=(Sgame) req.getSession().getAttribute("sgame");
		  if(sg!=null){
			  if(db.addSgame(user.getId(),sg.getUserid()))req.getSession().removeAttribute("sgame");
			  return mapping.findForward("success1");//游戏页
		  }
		  else
		if(actid==null)
			return mapping.findForward("success");
			else {
				if(Variable.map_apply.containsKey(user.getId()+";"+actid))//签到
				{req.getSession().setAttribute("apply", Variable.map_apply.get(user.getId()+";"+actid));
					return mapping.findForward("success2");}
			else{
				req.setAttribute("info",ConvertTheOther.EncodeUtf8("<center>未报名该活动</center>"));
				return mapping.findForward("error2");//未报名
			}
			}
		}else
		   return mapping.findForward("error0");
	}
	
	
	//登高比赛注册
	public ActionForward registertoo(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) {
		
//		if(req.getHeader("User-Agent").contains("MicroMessenger")){
			req.getSession().setAttribute("status", "micro");
		User user=(User) req.getSession().getAttribute("user");
		if(user==null){//从cookie取
		String tel,name;
		String cookie=Session.getCookie(req,"client");
		if(!cookie.equals("")){
		tel=cookie.split("\\|")[0];
		name=cookie.split("\\|")[1];
		name=ConvertTheOther.DecodeUtf8(name);
		if(Variable.map_actuser.containsKey(tel)&&Variable.map_actuser.get(tel).getName().equals(name))
			user=Variable.map_actuser.get(tel);
		 if(user==null)user=db.getUser(tel,name);
		  }
		}
		String name=ConvertTheOther.ISO88591ToUtf8(req.getParameter("name"));
		String tel=req.getParameter("tel");
//		String yzm0=req.getParameter("yzm");
//		String yzm1=(String) req.getSession().getAttribute("yzm");
		if(user==null){//从参数取
			//if(yzm0.equals(yzm1))
			if(db.getUser(tel, null)!=null){
				user=db.getUser(tel, name);
			}
			else
			if(!Variable.map_actuser.containsKey(tel))//非注册用户
			user=db.addUser(name,tel,null,null);
			}

		
		String openid=req.getParameter("openid");
		String sname=ConvertTheOther.ISO88591ToUtf8(req.getParameter("sname"));
		Sgame sgame=(Sgame) req.getSession().getAttribute("sgame");
		if(sgame==null)
		if(openid!=null&&sname!=null){
		sgame=new Sgame(openid,sname,"00");
		}
		if(sgame!=null)req.getSession().setAttribute("sgame", sgame);
		
		
		
		
		if(user!=null){
			req.setAttribute("userid", user.getId());
			req.setAttribute("sname",ConvertTheOther.EncodeUtf8(user.getName()));
			log.info("Action retoo user ="+user.getTel()+" name="+user.getName());
			Variable.map_actuser.put(tel, user);
		req.getSession().setAttribute("user", user);
		Session.addCookie(resp,user.getTel()+"|"+ConvertTheOther.EncodeUtf8(user.getName()),"client",365*24*3600);
		
		
		  Sgame sg=(Sgame) req.getSession().getAttribute("sgame");
			  if(sg!=null){
			  if(db.addSgame(user.getId(),sg.getUserid())){
				  req.setAttribute("info",ConvertTheOther.EncodeUtf8("助力成功"));
				  req.getSession().removeAttribute("sgame");}
			  else
			  req.setAttribute("info",ConvertTheOther.EncodeUtf8("助力失败，你已助力过"));
			 
		  }
			  
			  return mapping.findForward("success1");//游戏页
		}else
			return mapping.findForward("error1");
//		}
//		else{
//			 req.setAttribute("info",ConvertTheOther.EncodeUtf8("请从微信端访问"));
//			return mapping.findForward("error2");
//		}
	}
	
	//异步判断电话唯一
	public ActionForward istelunique(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) {
		String tel=ConvertTheOther.ISO88591ToUtf8(req.getParameter("tel"));
		String str="11";
		if(Variable.map_actuser.containsKey(tel)||db.getUser(tel, null)!=null)
		      str="00";//已有这个用户
		
		req.setAttribute("info",ConvertTheOther.EncodeUtf8(str));
	   return mapping.findForward("error2");
	}
	
	//报名
	public ActionForward apply(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) {
		
		String actid=req.getParameter("actid");
		String action=req.getParameter("action");
		String str="";
		User user=(User) req.getSession().getAttribute("user");
		if(user!=null&&actid!=null&&action!=null){
			if(action.equals("yes"))
			{Apply a;//没报名并且添加成功
			if(!Variable.map_apply.containsKey(user.getId()+";"+actid)&&(a=db.addApply(user.getId(),actid))!=null){
			Variable.map_apply.put(user.getId()+";"+actid, a);
//			str="报名成功";
			}
			}
			if(action.equals("no"))
				{
				if(db.delApply(user.getId(),actid)){
				Variable.map_apply.remove(user.getId()+";"+actid);
//				str="取消报名成功";
				}
				}
			
//			EncodeInfoU8(req,str);
			return mapping.findForward("success");
		}
		
		return mapping.findForward("error");
	}
	//管理员登陆
	public ActionForward managerlogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		String tel=req.getParameter("tel");
		System.out.println(name);
		if(name!=null&&tel!=null)
		if(Variable.manager_user.containsKey(tel)&&Variable.manager_user.get(tel).equals(name)){
			 try {
				resp.sendRedirect("./crudServ");
			} catch (IOException e) {
				
				log.error("error:", e);
			}
		}
		
		return mapping.findForward("managerlogin");
	}
	
	
	
	
}