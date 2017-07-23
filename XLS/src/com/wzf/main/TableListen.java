package com.wzf.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.wzf.dbconn.Dbcon;

public class TableListen  implements ActionListener{
     private JPanel jp;
	
	public  TableListen(JPanel jp){
		this.jp=jp;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String event=e.getActionCommand();
		System.out.println(event);
//		for(int i=0;i<jp.getComponentCount();i++){
//			System.out.println(jp.getComponent(i));
//		}
		if(event.equals("清空"))
		for(int i=0;i<5;i++)
			((JTextField)jp.getComponent(i+5)).setText("");
		else if(event.indexOf("执行")>0){
			String port=((JTextField)jp.getComponent(5)).getText();
			String user=((JTextField)jp.getComponent(6)).getText();
			String pass=((JTextField)jp.getComponent(7)).getText();
			String database=((JTextField)jp.getComponent(8)).getText();
			String sql=((JTextField)jp.getComponent(9)).getText();
			//Dbcon db=new Dbcon();
			Dbcon db=new Dbcon(database,"127.0.0.1",port,user,pass);
			if(event.equals("method执行")){
		//	db.methodvotes();
				Class class2;
				try {
					class2 = Class.forName("com.wzf.dbconn.Dbcon");
					Object object=(Dbcon)class2.newInstance();
			    	Method method = class2.getMethod(sql);
			        method.invoke(object);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				
			}
			else
				if(event.equals("sql执行"))
					db.upDate(sql);
			
			//System.out.println("数据库："+db.getDatabase());
		}
	}


}
