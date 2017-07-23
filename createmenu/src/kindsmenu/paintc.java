package kindsmenu;

import creamenu.CommonButton;
import creamenu.ComplexButton;
import creamenu.Menu;
import creamenu.button;

public class paintc {
	   public static String appid="wxbf26dc1597ac9bf9";
	   public static String appsecret="b6ac03444c2edc32b238895fc9a4d835";
	

	public static  Menu getMenu() {   //view  click
	        CommonButton btn11 = new CommonButton();  
	        btn11.setName("家长登陆");  
	        btn11.setType("view");  
	        btn11.setUrl("http://xiaowangzi.touclick.com/Paintc/login.jsp");
	        
	        CommonButton btn12 = new CommonButton();  
	        btn12.setName("比赛人气榜");  
	        btn12.setType("view");  
	        btn12.setUrl("http://xiaowangzi.touclick.com/Paintc/paintlist.jsp");
	        
	        CommonButton btn14 = new CommonButton();  
	        btn14.setName("快乐展厅");  
	        btn14.setType("view");  
	        btn14.setUrl("http://xiaowangzi.touclick.com/Paintc/Allwork");
	        
	        CommonButton btn13 = new CommonButton();  
	        btn13.setName("比赛说明");  
	        btn13.setType("view");  
	        btn13.setUrl("http://xiaowangzi.touclick.com/Paintc/aboutsignup.jsp");
	        
	        CommonButton btn15 = new CommonButton();  
	        btn15.setName("教师端登陆");  
	        btn15.setType("view");  
	        btn15.setUrl("http://xiaowangzi.touclick.com/Paints/login.jsp");
	        
	        CommonButton btn21 = new CommonButton();  
	        btn21.setName("模特报名");  
	        btn21.setType("view");  
	        btn21.setUrl("http://xiaowangzi.touclick.com/Paintc/loading_model.jsp");
	        
	        CommonButton btn22 = new CommonButton();  
	        btn22.setName("投票");  
	        btn22.setType("view");  
	        btn22.setUrl("http://xiaowangzi.touclick.com/Paintc/modellist.jsp");
	        
	        CommonButton btn23 = new CommonButton();  
	        btn23.setName("赛程说明");  
	        btn23.setType("view");  
	        btn23.setUrl("http://xiaowangzi.touclick.com/Paintc/aboutvote.jsp");
	        
	        CommonButton btn31 = new CommonButton();  
	        btn31.setName("测试小游戏");  
	        btn31.setType("view");  
	        btn31.setUrl("http://xiaowangzi.touclick.com/Paintc/ask.jsp");
	        
	        CommonButton btn32 = new CommonButton();  
	        btn32.setName("关于我们");  
	        btn32.setType("view");  
	        btn32.setUrl("http://xiaowangzi.touclick.com/Paints/about.jsp");
	        
	        CommonButton btn33 = new CommonButton();  
	        btn33.setName("萌宝抽奖");  
	        btn33.setType("view");  
	        btn33.setUrl("http://xiaowangzi.touclick.com/Paintc/game.jsp");
	        
	        CommonButton btn34 = new CommonButton();  
	        btn34.setName("快乐小泳士");  
	        btn34.setType("view");  
	        btn34.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MDMwMzMzNA==&mid=200549842&idx=2&sn=6630285780114295b5e00f3bd498cff5#rd");
	        
	       
	       
	        
	      
	        ComplexButton mainBtn1 = new ComplexButton();  
	        mainBtn1.setName("书画社");  
	        mainBtn1.setSub_button(new CommonButton[] { btn11,btn15,btn12,btn14,btn13}); 
	        
	        
	        ComplexButton mainBtn2 = new ComplexButton();  
	        mainBtn2.setName("爱童星");  
	        mainBtn2.setSub_button(new CommonButton[] { btn21,btn23}); 
	                                         
	        ComplexButton mainBtn3 = new ComplexButton();  
	        mainBtn3.setName("活动");  
	        mainBtn3.setSub_button(new CommonButton[] { btn33,btn34}); 
	    
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
