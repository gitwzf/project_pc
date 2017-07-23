package kindsmenu;

import creamenu.CommonButton;
import creamenu.ComplexButton;
import creamenu.Menu;
import creamenu.button;

public class qjwbqtwy {
	public static String appid="wx9cd6dae77dac3eb7";
    public static String appsecret="768afece63f0ce7a8afb85ce3d14c752";
	   
	   
	 public static  Menu getMenu() {   //view  click
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
	        btn34.setName("无忧吧");  
	        btn34.setType("view");  
	        btn34.setUrl("http://wx.wsq.qq.com/234815794");
	        
	        CommonButton btn35 = new CommonButton();  
	        btn35.setName("实习基地");  
	        btn35.setType("view");  
	        btn35.setUrl("http://115.236.173.81/mobileInPage!home.htm");
	     
	    
	      
	        ComplexButton mainBtn1 = new ComplexButton();  
	        mainBtn1.setName("微应聘");  
	        mainBtn1.setSub_button(new CommonButton[] { btn11,btn12,btn34}); 
	        
	        
	        ComplexButton mainBtn2 = new ComplexButton();  
	        mainBtn2.setName("微公告");  
	        mainBtn2.setSub_button(new CommonButton[] { btn22}); 
	                                         
	        ComplexButton mainBtn3 = new ComplexButton();  
	        mainBtn3.setName("微服务");  
	        mainBtn3.setSub_button(new CommonButton[] { btn31,btn32,btn33,btn35}); 
	    
	        /** 
	         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br> 
	         *  
	         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
	         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
	         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
	         */  
	        Menu menu = new Menu();  
	        menu.setButton(new button[] { mainBtn1,btn22,mainBtn3 });  
	  
	        return menu;  
	    }  
}
