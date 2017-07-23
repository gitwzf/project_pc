package kindsmenu;

import creamenu.CommonButton;
import creamenu.ComplexButton;
import creamenu.Menu;
import creamenu.button;

public class dk {
	   public static String appid="wxf7a54c6af1dc90ec";
	   public static String appsecret="b657f2b3572032e3eae9a82864c28516";
	

	public static  Menu getMenu() {   //view  click
	        CommonButton btn11 = new CommonButton();  
	        btn11.setName("分享汇");  
	        btn11.setType("view");  
	        btn11.setUrl("http://dkbbs.touclick.com/mobile/bbs/bbsListNews.aspx?act=4");
	        
	        CommonButton btn12 = new CommonButton();  
	        btn12.setName("人物");  
	        btn12.setType("view");  
	        btn12.setUrl("http://dkbbs.touclick.com/mobile/bbs/bbsListNews.aspx?act=3");
	        
	        CommonButton btn13 = new CommonButton();  
	        btn13.setName("书记想什么");  
	        btn13.setType("view");  
	        btn13.setUrl("http://dkbbs.touclick.com/mobile/bbs/bbsListNews.aspx?act=2");
	        
	        CommonButton btn14 = new CommonButton();  
	        btn14.setName("动态");  
	        btn14.setType("view");  
	        btn14.setUrl("http://dkbbs.touclick.com/mobile/bbs/bbsListNews.aspx?act=1");
	        
	        CommonButton btn15 = new CommonButton();
	        btn15.setName("文摘");  
	        btn15.setType("view");  
	        btn15.setUrl("http://dkbbs.touclick.com/mobile/bbs/bbsListNews.aspx?act=6");
	        
	        
	        
	        CommonButton btn21 = new CommonButton();  
	        btn21.setName("活动");  
	        btn21.setType("view");  
	        btn21.setUrl("http://dkbbs.touclick.com/mobile/Active2/ListVoteAll.aspx");
	        
	        CommonButton btn22 = new CommonButton();  
	        btn22.setName("论坛");  
	        btn22.setType("view");  
	        btn22.setUrl("http://dkbbs.touclick.com/mobile/bbs/friendGroup.aspx?act=17");
	        
	        
	        CommonButton btn31 = new CommonButton();  
	        btn31.setName("个人中心"); 
	        btn31.setType("view");  
	        btn31.setUrl("http://dkbbs.touclick.com/mobile/member/MineCenter.aspx");
	        
	        CommonButton btn32 = new CommonButton();  
	        btn32.setName("联系我们");  
	        btn32.setType("view");  
	        btn32.setUrl("http://dkbbs.touclick.com/mobile/DetailSigPage.aspx?id=3");
	        
	        CommonButton btn33 = new CommonButton();  
	        btn33.setName("我要投稿");  
	        btn33.setType("view");  
	        btn33.setUrl("http://dkbbs.touclick.com/mobile/DetailSigPage.aspx?id=2");
	        
	        CommonButton btn34 = new CommonButton();  
	        btn34.setName("杂志订阅");  
	        btn34.setType("view");  
	        btn34.setUrl("http://dkbbs.touclick.com/shop2/magazine-list-selection.html");
	        
	        CommonButton btn35 = new CommonButton();  
	        btn35.setName("微杂志");  
	        btn35.setType("view");  
	        btn35.setUrl("http://dkbbs.touclick.com/mobile/DetailSigPage.aspx?id=1");
	       
	        
	      
	        ComplexButton mainBtn1 = new ComplexButton();  
	        mainBtn1.setName("微新闻");  
	        mainBtn1.setSub_button(new CommonButton[] { btn11,btn12,btn13,btn14}); 
	        
	        
	        ComplexButton mainBtn2 = new ComplexButton();  
	        mainBtn2.setName("微互动");  
	        mainBtn2.setSub_button(new CommonButton[] { btn21,btn22}); 
	                                         
	        ComplexButton mainBtn3 = new ComplexButton();  
	        mainBtn3.setName("微服务");  
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
