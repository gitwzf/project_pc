package kindsmenu;

import creamenu.CommonButton;
import creamenu.ComplexButton;
import creamenu.Menu;
import creamenu.button;

public class weichu {
	   public static String appid="wxdca8720ec5644763";
	   public static String appsecret="c8390e1461b0d63ba2e262313569d21c";
	

	public static  Menu getMenu() {   //view  click
	        CommonButton btn11 = new CommonButton();  
	        btn11.setName("base");  
	        btn11.setType("view");  
	        btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdca8720ec5644763&redirect_uri="+
					"http%3A%2F%2Fxiaowangzi.touclick.com%2FAskdk%2Fask.jsp&response_type=code&scope=snsapi_base&state=base#wechat_redirect");
	        
	        
	        CommonButton btn21 = new CommonButton();  
	        btn21.setName("info");  
	        btn21.setType("view");  
	        btn21.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdca8720ec5644763&redirect_uri="+
					"http%3A%2F%2Fxiaowangzi.touclick.com%2FAskdk%2Fask.jsp&response_type=code&scope=snsapi_userinfo&state=userinfo#wechat_redirect");
	     
	      
	        ComplexButton mainBtn1 = new ComplexButton();  
	        mainBtn1.setName("base");  
	        mainBtn1.setSub_button(new CommonButton[] { btn11}); 
	        
	        
	        ComplexButton mainBtn2 = new ComplexButton();  
	        mainBtn2.setName("info");  
	        mainBtn2.setSub_button(new CommonButton[] { btn21}); 
	                                         
	    
	        /** 
	         * ���ǹ��ں�xiaoqrobotĿǰ�Ĳ˵��ṹ��ÿ��һ���˵����ж����˵���<br> 
	         *  
	         * ��ĳ��һ���˵���û�ж����˵��������menu����ζ����أ�<br> 
	         * ���磬������һ���˵���ǡ��������顱����ֱ���ǡ���ĬЦ��������ômenuӦ���������壺<br> 
	         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
	         */  
	        Menu menu = new Menu();  
	        menu.setButton(new button[] { mainBtn1,mainBtn2 });  
	  
	        return menu;  
	    }  
}
