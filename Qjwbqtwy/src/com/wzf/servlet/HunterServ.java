package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.dbconn.Dbcon;
import com.wzf.mail.MailSender;
import com.wzf.model.Page;
import com.wzf.model.User;
import com.wzf.pubvari.Variable;

public class HunterServ extends HttpServlet{
	Variable vari=new Variable();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbcon db=new Dbcon();
		req.getSession().setAttribute("ad", db.getAd());
		String str="/hunterlist.jsp";
		String type=req.getParameter("type");
		String id=req.getParameter("id");
		if(type==null){//猎头列表
		req.getSession().setAttribute("hunterlist", db.getHunterlist(1));
		req.getSession().setAttribute("hpage", new Page("1",db.getHunterlist(-1).size()/vari.p_size+(db.getHunterlist(-1).size()%vari.p_size==0?0:1)+""));
		}
		else if("detail".equals(type)){//猎头详细页
			if(id!=null)
		req.getSession().setAttribute("hunterdetail", db.getHunterDetail(id));
		req.getSession().setAttribute("hunterpositionlist", db.getPositionlist(id, vari.POSITION_TYPE_HUNTER));
		str="/hunterdetail.jsp";
		}
		req.getRequestDispatcher(str).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Dbcon db=new Dbcon();
		String hunterid=req.getParameter("hunterid");//猎头
		String positionid=req.getParameter("positionid");//职位
		String positionname=req.getParameter("positionname");//职位名称
		String companyname=req.getParameter("companyname");
		User u=(User) req.getSession().getAttribute("user");
		String str="00";
		//发送邮件
		System.out.println("hunt="+hunterid);
		if(hunterid!=null){
		if(db.isHaveHPU(hunterid,u.getId(),vari.HUNTID_USERID,positionid)){//System.out.println("1111111");
			str="11";
			String email=db.getEmail(hunterid);
			SendEmail(u.getId(),email,positionname,companyname);
		}}
		else
		if(positionid!=null)
			if(db.isHaveHPU(positionid,u.getId(),vari.POSITIONID_USERID,"")){
				str="11";
				String email=db.getPEmail(positionid);
				SendEmail(u.getId(),email,positionname,companyname);
			}
		System.out.println("send...");
		PrintWriter pw=resp.getWriter();
		pw.write(str);
		pw.flush();
		pw.close();
		
	}
	
	public void SendEmail(String userid,String email,String positionname,String companyname){
		Dbcon db=new Dbcon();
		String[] con=db.getResume(userid);
		String[][] con0=db.getExperience(userid);
		companyname=(companyname==null||companyname.trim().length()==0)?"":("["+companyname+"]");
		positionname=(positionname==null||positionname.trim().length()==0)?(con[0]+"的简历"):("投递职位："+positionname);
		String content=companyname+"<br>"+positionname+"<br>"+"姓名："+con[0]+"<br>生日："+con[13]+"<br>性别："+("1".equals(con[1])?"男":"女")+
		"<br>手机："+con[2]+"<br>电子邮箱："+con[3]+"<br>工作年限："+(vari.map_years.get(con[4])==null?"":vari.map_years.get(con[4]))+
		"<br>毕业学校："+con[5]+"<br>毕业时间："+con[6]+"<br>学历："+(vari.map_degree.get(con[7])==null?"":vari.map_degree.get(con[7]))+
		"<br>专业："+con[8]+"<br>年薪："+(vari.map_salary.get(con[9])==null?"":vari.map_salary.get(con[9]))+"<br>期望年薪："+con[10]+
		"<br>居住地址："+con[11]+"<br>自我评价："+con[12]+"<br><br>";
		content+="工作经历：";
		//System.out.println("内容："+content);
		for(int i=0;i<con0.length;i++){
			content+="<br><br>公司："+con0[i][0]+"   部门："+con0[i][1]+
			"<br>职位："+con0[i][2]+"   薪资："+con0[i][3]+
			"<br>工作描述："+con0[i][4]+
			"<br>开始："+con0[i][5]+"   结束："+con0[i][6];
		}
		//smtp.8531.net 25
		//new MailSender("wonderfeng12@163.com", "a987654321#", email,positionname==null?(con[0]+"的简历"):positionname, content);
	   new MailSender("smtp."+vari.email_name.split("@")[1], "25", vari.email_name, vari.email_pass, email,companyname+ positionname, content, false);
	}
     
}
