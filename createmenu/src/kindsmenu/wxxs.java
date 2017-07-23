package kindsmenu;

import creamenu.CommonButton;
import creamenu.ComplexButton;
import creamenu.Menu;
import creamenu.button;

public class wxxs {
	   public static String appid="wxe87dd822068caaf3";
	   public static String appsecret="7d22188c3fe342b038de07d396e222bc";
	

	public static  Menu getMenu() {   //view  click
	        CommonButton btn11 = new CommonButton();  
	        btn11.setName("下沙资讯");  
	        btn11.setType("click");  
	        btn11.setKey("11");
	        
	        CommonButton btn12 = new CommonButton();  
	        btn12.setName("微杂志");  
	        btn12.setType("click");  
	        btn12.setKey("12");
	        
	        
	        CommonButton btn21 = new CommonButton();  
	        btn21.setName("下沙购");  
	        btn21.setType("click");  
	        btn21.setKey("21");
	        
	        CommonButton btn22 = new CommonButton();  
	        btn22.setName("美食汇");  
	        btn22.setType("click");  
	        btn22.setKey("22");
	           
	        CommonButton btn31 = new CommonButton();  
	        btn31.setName("神吐槽");  
	        btn31.setType("click");  
	        btn31.setKey("31");
	        
	        CommonButton btn32 = new CommonButton();  
	        btn32.setName("听我读下沙");  
	        btn32.setType("click");  
	        btn32.setKey("32");
	        
	        CommonButton btn33 = new CommonButton();  
	        btn33.setName("幸运大转轮");  
	        btn33.setType("click");  
	        btn33.setKey("33");
	        
	        CommonButton btn34 = new CommonButton();  
	        btn34.setName("微投票");  
	        btn34.setType("view");  
	        btn34.setUrl("http://xiasha.touclick.com/mobile/vote/ListVote.aspx");
	       
	        
	      
	        ComplexButton mainBtn1 = new ComplexButton();  
	        mainBtn1.setName("微阅读");  
	        mainBtn1.setSub_button(new CommonButton[] { btn11,btn12}); 
	        
	        
	        ComplexButton mainBtn2 = new ComplexButton();  
	        mainBtn2.setName("微生活");  
	        mainBtn2.setSub_button(new CommonButton[] { btn21,btn22}); 
	                                         
	        ComplexButton mainBtn3 = new ComplexButton();  
	        mainBtn3.setName("微互动");  
	        mainBtn3.setSub_button(new CommonButton[] { btn31,btn32,btn33,btn34}); 
	    
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
}
