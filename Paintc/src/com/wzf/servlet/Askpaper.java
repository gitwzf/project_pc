package com.wzf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.wzf.dbconn.Dbcon;
import com.wzf.pubvari.Variable;

public class Askpaper extends HttpServlet
{
    Dbcon db=new Dbcon();
    Logger log=Logger.getLogger("logfile");
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String pageid=req.getParameter("pageid");
		String str="00";
		if(Variable.map_askpaper.get(pageid)!=null){
			String times=Variable.map_askpaper.get(pageid).getAskpaper().getClicktimes();
			Variable.map_askpaper.get(pageid).getAskpaper().setClicktimes(Long.parseLong(times)+1+"");
			str=JSONObject.fromObject(Variable.map_askpaper.get(pageid)).toString();
		}
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw=resp.getWriter();
		pw.write(str);System.out.println(str);
		pw.flush();
		pw.close();
	}

	public void init(){
		Timer t=new Timer();
		t.schedule(new Time(),1000,5*60*1000);
	}

	class Time extends TimerTask{

		@Override
		public void run() {
			
			//更新点击数
			db.updateAskpaper(Variable.map_askpaper);
			Variable.map_askpaper=db.getAllAskPaper();
			log.info("加载问卷数："+Variable.map_askpaper.size());
//			System.out.println("问卷1:"+JSONObject.fromObject(Variable.map_askpaper.get("1")));
		}
		
		
	}
}
