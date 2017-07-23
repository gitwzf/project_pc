package main;

import http.Wmain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Do extends java.util.TimerTask implements ActionListener {
	public String[] fileurl=new String[1];
	public String fileouturl;
	public String key1;
	public String key2;
	public String r;
	public String start;
	public String temp;
	public Wmain wm=new Wmain();
	
	public void logerror(){
		for(int i=0;i<fileurl.length;i++){
			boolean isSecurity=true;
		System.out.println("123..."+fileurl[i]);
		try {
		  File file=new File(fileurl[i]);
		  FileReader fr=new FileReader(file);
		  BufferedReader br=new BufferedReader(fr);
		  StringBuffer sb=new StringBuffer();
		  String ss;
		  int a,b,c=(r.equals("")?0:Integer.parseInt(r))*10;
		  String fpath="";
		  String str0="^[a-zA-Z]:([/|\\\\][\\d\\D]+){1}";
	    	Matcher m=Pattern.compile(str0).matcher(fpath);//去直接目录
	    	if(!m.matches())fpath=fileurl[i].substring(0, fileurl[i].lastIndexOf("/"));
	    	System.out.println("fpath="+fpath);
	    	fpath=fpath.substring(fpath.lastIndexOf("/"));
		  File ff=new File(fileouturl+"the_"+i+"th_record"+fpath+"/error.txt");
		    File ff0=new File(fileouturl+"the_"+i+"th_record"+fpath);
		  System.out.println(ff0.mkdirs());
			while((ss=br.readLine())!=null){
				boolean flag = false;
				try {
					if(ss.split(",")[0].startsWith(key1.split("-")[0]))
					flag = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ss.split(",")[0]).after(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(key1));
					} catch (ParseException e) {
					e.printStackTrace();JOptionPane.showMessageDialog(null,"1"+e);
				}
				if(flag&&ss.indexOf(key2)!=-1&&c==0){
					isSecurity=false;
					System.out.println(ss);
//				FileOutputStream fos = new FileOutputStream(ff, true);
//				fos.write(("\n"+ss).getBytes());
//				fos.flush();
//				fos.close();
				FileWriter fw=new FileWriter(ff,true);
				fw.write(ss+"\r\n");
				fw.close();
				wm.sendmessage(fpath+":"+ss);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				}
				sb.append(ss+"\n");
			}
		  ss=sb.toString();
		 //发送微博   access_token 1.20号获取  ，将在4.20号过期https://open.t.qq.com/cgi-bin/oauth2/access_token?client_id=APP_KEY&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
		  String re;
//		  if(c!=0)
//			while((a=ss.indexOf(key1))!=-1&&(b=ss.indexOf(key2))!=-1&&ss.length()>c){
//				System.out.println("a="+a+" b="+b);
//				if((Math.abs(a-b)<=c)&&a!=-1&&b!=-1)
//				{  re=ss.substring(a>b?b:a,a>b?a:b);
//				//	File ff=new File("D://error.txt");
//					FileWriter fw=new FileWriter(ff);
//					fw.write(re);
//					fw.close();
//					JOptionPane.showMessageDialog(null,re);
//					
//				}
//				ss=ss.substring(a+1);
//			}
		  if(isSecurity){JOptionPane.showMessageDialog(null,fpath+"安全");
		  wm.sendmessage(fpath+":安全");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		  }
		  else JOptionPane.showMessageDialog(null,fpath+"有错误，请及时修改");
		} catch (IOException e) {
			e.printStackTrace();JOptionPane.showMessageDialog(null,"2"+e);
		}
		
	}
		
	}
	
	@Override
	public void run() {
		
		logerror();
	}
	 private JPanel jp;
		
		public  Do(JPanel jp){
			this.jp=jp;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String event=e.getActionCommand();
			System.out.println(event);
			System.out.println(((JTextField)jp.getComponent(6)).getText());
			if(event.equals("清空"))
			for(int i=0;i<6;i++)
				((JTextField)jp.getComponent(i+6)).setText("");
			else if(event.equals("执行")){
				JOptionPane.showMessageDialog(null,"开始执行。。。");
				InputStream i=this.getClass().getResourceAsStream("/base.properties");
				Properties p=new Properties();
				try {
					p.load(i);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				 fileurl[0]=((JTextField)jp.getComponent(6)).getText();
				 key1=((JTextField)jp.getComponent(7)).getText();
				 key2=((JTextField)jp.getComponent(8)).getText();
				 r=((JTextField)jp.getComponent(9)).getText();//字符间隔关联0-3  4-6    7-9
				 start=((JTextField)jp.getComponent(10)).getText();
				 temp=((JTextField)jp.getComponent(11)).getText();

				 if("".equals(fileurl[0]))fileurl=p.getProperty("fileurl").split(",");
				 System.out.println("fileurl="+fileurl[0]);
				 if("".equals(fileurl[0])){
					 JOptionPane.showMessageDialog(null,"访问文件路径需设置");
					 return;
				 }
				 if("".equals(key2))key2=p.getProperty("key2");
				 if("".equals(r))r=p.getProperty("r");
				 if("".equals(start))start=p.getProperty("start");
				 if("".equals(temp))temp=p.getProperty("temp");
				fileouturl=p.getProperty("fileouturl");
				 
                Timer t=new Timer();
                key1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()-Integer.parseInt(temp)*1000);
                System.out.println("key1="+key1);
        		t.schedule(this, Integer.parseInt(start)*1000,Integer.parseInt(temp)*1000);
				
				
			}
		}
}
