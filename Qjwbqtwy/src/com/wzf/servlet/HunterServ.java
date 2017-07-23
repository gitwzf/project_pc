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
		if(type==null){//��ͷ�б�
		req.getSession().setAttribute("hunterlist", db.getHunterlist(1));
		req.getSession().setAttribute("hpage", new Page("1",db.getHunterlist(-1).size()/vari.p_size+(db.getHunterlist(-1).size()%vari.p_size==0?0:1)+""));
		}
		else if("detail".equals(type)){//��ͷ��ϸҳ
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
		String hunterid=req.getParameter("hunterid");//��ͷ
		String positionid=req.getParameter("positionid");//ְλ
		String positionname=req.getParameter("positionname");//ְλ����
		String companyname=req.getParameter("companyname");
		User u=(User) req.getSession().getAttribute("user");
		String str="00";
		//�����ʼ�
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
		positionname=(positionname==null||positionname.trim().length()==0)?(con[0]+"�ļ���"):("Ͷ��ְλ��"+positionname);
		String content=companyname+"<br>"+positionname+"<br>"+"������"+con[0]+"<br>���գ�"+con[13]+"<br>�Ա�"+("1".equals(con[1])?"��":"Ů")+
		"<br>�ֻ���"+con[2]+"<br>�������䣺"+con[3]+"<br>�������ޣ�"+(vari.map_years.get(con[4])==null?"":vari.map_years.get(con[4]))+
		"<br>��ҵѧУ��"+con[5]+"<br>��ҵʱ�䣺"+con[6]+"<br>ѧ����"+(vari.map_degree.get(con[7])==null?"":vari.map_degree.get(con[7]))+
		"<br>רҵ��"+con[8]+"<br>��н��"+(vari.map_salary.get(con[9])==null?"":vari.map_salary.get(con[9]))+"<br>������н��"+con[10]+
		"<br>��ס��ַ��"+con[11]+"<br>�������ۣ�"+con[12]+"<br><br>";
		content+="����������";
		//System.out.println("���ݣ�"+content);
		for(int i=0;i<con0.length;i++){
			content+="<br><br>��˾��"+con0[i][0]+"   ���ţ�"+con0[i][1]+
			"<br>ְλ��"+con0[i][2]+"   н�ʣ�"+con0[i][3]+
			"<br>����������"+con0[i][4]+
			"<br>��ʼ��"+con0[i][5]+"   ������"+con0[i][6];
		}
		//smtp.8531.net 25
		//new MailSender("wonderfeng12@163.com", "a987654321#", email,positionname==null?(con[0]+"�ļ���"):positionname, content);
	   new MailSender("smtp."+vari.email_name.split("@")[1], "25", vari.email_name, vari.email_pass, email,companyname+ positionname, content, false);
	}
     
}
