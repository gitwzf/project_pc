package kindsmenu;

import creamenu.CommonButton;
import creamenu.ComplexButton;
import creamenu.Menu;
import creamenu.button;

public class jrzb {
	 public static  Menu getMenu() {   //view  click
	        CommonButton btn11 = new CommonButton();  
	        btn11.setName("浙企世界杯");  
	        btn11.setType("click");  
	        btn11.setKey("11");
	        
	        CommonButton btn12 = new CommonButton();  
	        btn12.setName("巴西世界杯");  
	        btn12.setType("click");  
	        btn12.setKey("12");
	        
	        CommonButton btn21 = new CommonButton();  
	        btn21.setName("大转盘");  
	        btn21.setType("click");  
	        btn21.setKey("21");
	        
	        CommonButton btn22 = new CommonButton();  
	        btn22.setName("刮刮乐");  
	        btn22.setType("click");  
	        btn22.setKey("22");
	        
	        CommonButton btn23 = new CommonButton();  
	        btn23.setName("摇一摇");  
	        btn23.setType("click");  
	        btn23.setKey("23");
	        
	        CommonButton btn24 = new CommonButton();  
	        btn24.setName("点球大赛");  
	        btn24.setType("click");  
	        btn24.setKey("24");
	  
	        CommonButton btn31 = new CommonButton();  
	        btn31.setName("摄影大赛");  
	        btn31.setType("click");  
	        btn31.setKey("31");
	        
	        CommonButton btn32 = new CommonButton();  
	        btn32.setName("足球宝贝");  
	        btn32.setType("click");  
	        btn32.setKey("32");
	        
	        CommonButton btn33 = new CommonButton();  
	        btn33.setName("VC社区");  
	        btn33.setType("view");  
	        btn33.setUrl("http://wx.wsq.qq.com/233791702");
	        
	        CommonButton btn34 = new CommonButton();  
	        btn34.setName("体育报名");  
	        btn34.setType("view");  
	        btn34.setUrl("http://jrzb.touclick.com/mobile/shop/ListPdt.aspx");
	        
	        CommonButton btn35 = new CommonButton();  
	        btn35.setName("报名查询");  
	        btn35.setType("view");  
	        btn35.setUrl("http://jrzb.touclick.com/mobile/shop/BillSearch.aspx");
	        
	       
	    
	      
	        ComplexButton mainBtn1 = new ComplexButton();  
	        mainBtn1.setName("VC大本营");  
	        mainBtn1.setSub_button(new CommonButton[] { btn11,btn12}); 
	        
	        
	        ComplexButton mainBtn2 = new ComplexButton();  
	        mainBtn2.setName("VC游乐场");  
	        mainBtn2.setSub_button(new CommonButton[] { btn21,btn22,btn23,btn24}); 
	                                         
	        ComplexButton mainBtn3 = new ComplexButton();  
	        mainBtn3.setName("VC互动");  
	        mainBtn3.setSub_button(new CommonButton[] { btn31,btn32,btn33,btn34,btn35}); 
	    
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
