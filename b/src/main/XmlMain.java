package main;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wzf.mail.MailSender;
import com.wzf.method.Ftpcon;
import com.wzf.method.RandomString;
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;  
  
import org.w3c.dom.Attr;  
import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
import org.w3c.dom.NamedNodeMap;  
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;  
import org.xml.sax.SAXException;  
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlMain {
	private static boolean validate = true; 
	
          public static void main(String[] args) {
			//发邮件
//        	  String[] path={"d:/1.txt"};
//      		new MailSender("wonderfeng12@163.com", "a987654321#", "514380916@qq.com","title标题", "内容是是是是是是四十四",path);
//        
//      		//ftp上传下载
//      		Ftpcon ftp=new Ftpcon("112.124.65.68", "shuhuashe", "shuhuashetouclick", "/ftp/", "d://");
//      		ftp.connect();
//      		ftp.download("1111.txt", "1.txt");
//      		ftp.upload("test0.sql", "test.sql");
//      		ftp.disconnect();
//      		
//      		//返回0-9a-Z特殊字符（除选定字符）组成的字符串
//      		RandomString.getRandomString(6, true, true, true, true, "0o");
        	  File file=new File("D:/MyEclipse/Atest/src/a.xml");
        	  boolean validate =new XmlMain().XMLValidateDTD(file);
        	  System.out.println(validate);
		}
          
          public  boolean XMLValidateDTD(File file) {  
              DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
              factory.setValidating(true);  
              try {  
                  DocumentBuilder builder = factory.newDocumentBuilder(); 
                  builder.setErrorHandler(new MyHandler());  
                  builder.parse(file);  
              } catch (ParserConfigurationException e) {  
                  e.printStackTrace();  
              } catch (SAXException e) {  
                  e.printStackTrace();  
              } catch (IOException e) {  
                  e.printStackTrace();  
              }  
              return validate;  
          }  

          
          class MyHandler extends DefaultHandler {
        	  
        	    String errorMessage = null;

        	    public void error(SAXParseException e) throws SAXException {  
        	        errorMessage = e.getMessage();  
        	        System.out.println("一般错误！！！" + errorMessage);  
        	        validate = false;  
        	    }  

        	    public void fatalError(SAXParseException e) throws SAXException {  
        	        errorMessage = e.getMessage();  
        	        System.out.println("严重错误！！！" + errorMessage);  
        	        validate = false;  
        	    }  



        	}

      
}
