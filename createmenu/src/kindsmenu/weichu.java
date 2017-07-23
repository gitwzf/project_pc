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
	         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br> 
	         *  
	         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
	         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
	         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
	         */  
	        Menu menu = new Menu();  
	        menu.setButton(new button[] { mainBtn1,mainBtn2 });  
	  
	        return menu;  
	    }  
}
