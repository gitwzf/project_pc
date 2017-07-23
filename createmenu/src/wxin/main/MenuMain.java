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
        // 第三方用户唯一凭证  
        //String appId = "000000000000000000";  
        // 第三方用户唯一凭证密钥  
        //String appSecret = "00000000000000000000000000000000";  
  
        // 调用接口获取access_token  
//      AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);  
//  
//        if (null != at) {  
    	
    	//书画社     wxbf26dc1597ac9bf9    b6ac03444c2edc32b238895fc9a4d835
    	//今日早报   wx3f92899ab1b7c706   bbdda1d4233e47420d1f5c1a20be1e30
    	//钱江晚报   wx9cd6dae77dac3eb7    768afece63f0ce7a8afb85ce3d14c752
    	//今日下沙  wxe87dd822068caaf3      7d22188c3fe342b038de07d396e222bc
    	
            // 调用接口创建菜单            https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
            int result = wxinUtil.createmenu(qjwbqtwy.getMenu(),getToken(qjwbqtwy.appid,qjwbqtwy.appsecret));  
        
            // 判断菜单创建结果  
            if (0 == result)  
                log.info("菜单创建成功！");  
            else  
                log.info("菜单创建失败，错误码：" + result);  
        }  
    
  
    /** 
     * 组装菜单数据 
     *  
     * @return 
     */  
    private static  Menu getMenu() {   //view  click
        CommonButton btn11 = new CommonButton();  
        btn11.setName("高端人才");  
        btn11.setType("click");  
        btn11.setKey("11");
        
        CommonButton btn12 = new CommonButton();  
        btn12.setName("社会招聘");  
        btn12.setType("click");  
        btn12.setKey("12");
        
        CommonButton btn21 = new CommonButton();  
        btn21.setName("高校招聘");  
        btn21.setType("click");  
        btn21.setKey("21");
        
        CommonButton btn22 = new CommonButton();  
        btn22.setName("专场招聘");  
        btn22.setType("click");  
        btn22.setKey("22");
  
        CommonButton btn31 = new CommonButton();  
        btn31.setName("我的简历");  
        btn31.setType("click");  
        btn31.setKey("31");
        
        CommonButton btn32 = new CommonButton();  
        btn32.setName("工作经历");  
        btn32.setType("click");  
        btn32.setKey("32");
        
        CommonButton btn33 = new CommonButton();  
        btn33.setName("我的应聘");  
        btn33.setType("click");  
        btn33.setKey("33");
        
        CommonButton btn34 = new CommonButton();  
        btn34.setName("戳我有奖");  
        btn34.setType("click");  
        btn34.setKey("01");
    
      
        ComplexButton mainBtn1 = new ComplexButton();  
        mainBtn1.setName("微应聘");  
        mainBtn1.setSub_button(new CommonButton[] { btn11,btn12}); 
        
        
        ComplexButton mainBtn2 = new ComplexButton();  
        mainBtn2.setName("微公告");  
        mainBtn2.setSub_button(new CommonButton[] { btn21,btn22}); 
        
        ComplexButton mainBtn3 = new ComplexButton();  
        mainBtn3.setName("微服务");  
        mainBtn3.setSub_button(new CommonButton[] { btn31,btn32,btn33}); 
    
        /** 
         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br> 
         *  
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
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
