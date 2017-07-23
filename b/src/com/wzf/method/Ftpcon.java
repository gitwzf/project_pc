package com.wzf.method;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
 
import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;
 
public class Ftpcon {
	//ʵ�� Ӧ�����۱������  �� ������ �޷�����    ��Ҫjavaʵ�� ���ط��ʷ�������ftp  cmd   db
	  String server = "112.124.65.68";  
	  String user = "shuhuashe";  
	  String password = "shuhuashetouclick";  
	  String server_dir = "/ftp/";  
	  String local_dir="d://";
	
	public Ftpcon(String server, String user, String password,
			String serverDir, String localDir) {
		super();
		this.server = server;
		this.user = user;
		this.password = password;
		server_dir = serverDir;
		local_dir = localDir;
	}

	FtpClient  ftpClient=null;
//	public static void main(String[] args) {
//		  String server = "112.124.65.68";  
//		  String user = "shuhuashe";  
//		  String password = "shuhuashetouclick";  
//		  String server_dir = "/ftp/";  
//		  String local_dir="d://";
//		   // server��FTP��������IP��ַ��user:��¼FTP���������û���  
//		   // password����¼FTP���������û����Ŀ��path��FTP�������ϵ�·��  
//		 FtpClient  ftpClient = new FtpClient(); 
//		   try{
//		   ftpClient.openServer(server);  
//		   ftpClient.login(user, password);  
//		   // path��ftp��������Ŀ¼����Ŀ¼  
//		   if (server_dir.length() != 0)  
//		    ftpClient.cd(server_dir);  
//		   // ��2�����ϴ�  
//		   ftpClient.binary(); 
//		   } catch (Exception e) {  
//			   e.printStackTrace();  
//			  } 
//		   //�ϴ�
//	//	  upload(ftpClient,"test0.sql","test.sql");
//		   //����
////		  download(ftpClient,"1.txt","1.txt");  
//	}
	public FtpClient connect(){
		ftpClient = new FtpClient(); 
		try{
			   ftpClient.openServer(server);  //�ɼӲ���port
			   ftpClient.login(user, password);  
			   if (server_dir.length() != 0)  
			    ftpClient.cd(server_dir);  
				   ftpClient.binary(); 
			   } catch (Exception e) {  
				   e.printStackTrace();  
				  }
			   return ftpClient;
	}
	
	
	public  void upload(String newname,String file){
		TelnetOutputStream os = null;  
		   FileInputStream is = null;  
		   try{
		   try {  
		    os = ftpClient.put(newname);  
		    java.io.File file_in = new java.io.File(local_dir+file);  
		    if (file_in.length() == 0) {  
		     throw new Exception("�ϴ��ļ�Ϊ��!");  
		    }  
		    is = new FileInputStream(file_in);  
		    byte[] bytes = new byte[1024];  
		    int c;  
		    while ((c = is.read(bytes)) != -1) {  
		     os.write(bytes, 0, c);  
		    }  
		   } finally {  
		    if (is != null) {  
		     is.close();  
		    }  
		    if (os != null) {  
		     os.close();  
		    }  
		   }  } catch (Exception e) {  
			   e.printStackTrace();  
			  }   
		   System.out.println("�ϴ��ļ��ɹ�!");
		
	}
	
	public  void download(String newname,String file){
		   FileOutputStream os = null;  
		   TelnetInputStream is = null; 
		   try{
		   try {  
		    // "descfile.txt"��ftp�ϴ�������ļ���  
		    is = ftpClient.get(server_dir+file);  
		    os = new FileOutputStream(local_dir+newname);  
		    byte[] bytes = new byte[1024];  
		    int c;  
		    while ((c = is.read(bytes)) != -1) {  
		     os.write(bytes, 0, c);  
		    }  
		   } finally {  
		    if (is != null) {  
		     is.close();  
		    }  
		    if (os != null) {  
		     os.close();  
		    }  
		   } 
		   System.out.println("�����ļ��ɹ�!");  
		  } catch (Exception e) {  
		   e.printStackTrace();  
		  }  
		
	}
	
	public void disconnect(){
		try {
			ftpClient.closeServer();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
