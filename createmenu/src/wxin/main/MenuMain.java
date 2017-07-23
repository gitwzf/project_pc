package wxin.main;

import java.awt.Button;
import java.io.IOException;

import kindsmenu.dk;
import kindsmenu.jrzb;
import kindsmenu.paintc;
import kindsmenu.qjwbqtwy;
import kindsmenu.weichu;
import kindsmenu.wxxs;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import creamenu.CommonButton;
import creamenu.ComplexButton;
import creamenu.Menu;
import creamenu.button;

import security.util.wxinUtil;

public class MenuMain {
	private static Logger log = LoggerFactory.getLogger(MenuMain.class);  
	  
    public static void main(String[] args) {  
        // �������û�Ψһƾ֤  
        //String appId = "000000000000000000";  
        // �������û�Ψһƾ֤��Կ  
        //String appSecret = "00000000000000000000000000000000";  
  
        // ���ýӿڻ�ȡaccess_token  
//      AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);  
//  
//        if (null != at) {  
    	
    	//�黭��     wxbf26dc1597ac9bf9    b6ac03444c2edc32b238895fc9a4d835
    	//�����籨   wx3f92899ab1b7c706   bbdda1d4233e47420d1f5c1a20be1e30
    	//Ǯ����   wx9cd6dae77dac3eb7    768afece63f0ce7a8afb85ce3d14c752
    	//������ɳ  wxe87dd822068caaf3      7d22188c3fe342b038de07d396e222bc
    	
            // ���ýӿڴ����˵�            https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
            int result = wxinUtil.createmenu(qjwbqtwy.getMenu(),getToken(qjwbqtwy.appid,qjwbqtwy.appsecret));  
        
            // �жϲ˵��������  
            if (0 == result)  
                log.info("�˵������ɹ���");  
            else  
                log.info("�˵�����ʧ�ܣ������룺" + result);  
        }  
    
  
    /** 
     * ��װ�˵����� 
     *  
     * @return 
     */  
    private static  Menu getMenu() {   //view  click
        CommonButton btn11 = new CommonButton();  
        btn11.setName("�߶��˲�");  
        btn11.setType("click");  
        btn11.setKey("11");
        
        CommonButton btn12 = new CommonButton();  
        btn12.setName("�����Ƹ");  
        btn12.setType("click");  
        btn12.setKey("12");
        
        CommonButton btn21 = new CommonButton();  
        btn21.setName("��У��Ƹ");  
        btn21.setType("click");  
        btn21.setKey("21");
        
        CommonButton btn22 = new CommonButton();  
        btn22.setName("ר����Ƹ");  
        btn22.setType("click");  
        btn22.setKey("22");
  
        CommonButton btn31 = new CommonButton();  
        btn31.setName("�ҵļ���");  
        btn31.setType("click");  
        btn31.setKey("31");
        
        CommonButton btn32 = new CommonButton();  
        btn32.setName("��������");  
        btn32.setType("click");  
        btn32.setKey("32");
        
        CommonButton btn33 = new CommonButton();  
        btn33.setName("�ҵ�ӦƸ");  
        btn33.setType("click");  
        btn33.setKey("33");
        
        CommonButton btn34 = new CommonButton();  
        btn34.setName("�����н�");  
        btn34.setType("click");  
        btn34.setKey("01");
    
      
        ComplexButton mainBtn1 = new ComplexButton();  
        mainBtn1.setName("΢ӦƸ");  
        mainBtn1.setSub_button(new CommonButton[] { btn11,btn12}); 
        
        
        ComplexButton mainBtn2 = new ComplexButton();  
        mainBtn2.setName("΢����");  
        mainBtn2.setSub_button(new CommonButton[] { btn21,btn22}); 
        
        ComplexButton mainBtn3 = new ComplexButton();  
        mainBtn3.setName("΢����");  
        mainBtn3.setSub_button(new CommonButton[] { btn31,btn32,btn33}); 
    
        /** 
         * ���ǹ��ں�xiaoqrobotĿǰ�Ĳ˵��ṹ��ÿ��һ���˵����ж����˵���<br> 
         *  
         * ��ĳ��һ���˵���û�ж����˵��������menu����ζ����أ�<br> 
         * ���磬������һ���˵���ǡ��������顱����ֱ���ǡ���ĬЦ��������ômenuӦ���������壺<br> 
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
         */  
        Menu menu = new Menu();  
        menu.setButton(new button[] { mainBtn1,mainBtn2,mainBtn3 });  
  
        return menu;  
    }  
    
    
    public static String getToken(String appid,String appsecret ){
    	String url =" https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		url=url.replace("APPID", appid).replace("APPSECRET", appsecret);
    	HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https",
				new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		PostMethod postMethod = new PostMethod(url);
			try {
				client.executeMethod(postMethod);
			
			String str = postMethod.getResponseBodyAsString();
			JSONObject json=JSONObject.fromObject(str);
			System.out.println(json.get("access_token"));
			str=(String) json.get("access_token");
			return str;
			} catch (HttpException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
    	return "00";
    }
}  
