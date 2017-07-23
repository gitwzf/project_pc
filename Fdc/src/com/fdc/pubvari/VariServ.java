package com.fdc.pubvari;

import java.util.Timer;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.fdc.dbconn.Dbcon;
import com.fdc.pubvari.Variable;

public class VariServ extends HttpServlet{
	Logger log=Logger.getLogger("logfile");
	Dbcon db=new Dbcon();
	public void init(){
		Variable.list_sgameuser=db.getAllSgameUser();
		 Variable.map_activ=db.getAllActiv();
    	 Variable.map_apply=db.getAllApply();
//    	 log.info("初始化：活动数："
//    			 +Variable.map_activ.size()+"  所有活动报名数："+Variable.map_apply.size());
		Timer t=new Timer();
		t.schedule(new Time(), 1000,60*1000);
		Timer t0=new Timer();
		t0.schedule(new Time0(), 1000,3600*1000);
	}
	 class Time extends java.util.TimerTask{

			@Override
			public void run() {
				
				Variable.list_sgameuser=db.getAllSgameUser();		 
		    	
			}
			  
			  
		  }
	 class Time0 extends java.util.TimerTask{

			@Override
			public void run() {
				
					
				log.info("Fdc  上周期活动用户数："+Variable.map_actuser.size());
//				 db.updateActUser(Variable.map_actuser);
				 Variable.map_actuser.clear();
				 
		    	
			}
			  
			  
		  }
}
