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
	        btn11.setName("��ɳ��Ѷ");  
	        btn11.setType("click");  
	        btn11.setKey("11");
	        
	        CommonButton btn12 = new CommonButton();  
	        btn12.setName("΢��־");  
	        btn12.setType("click");  
	        btn12.setKey("12");
	        
	        
	        CommonButton btn21 = new CommonButton();  
	        btn21.setName("��ɳ��");  
	        btn21.setType("click");  
	        btn21.setKey("21");
	        
	        CommonButton btn22 = new CommonButton();  
	        btn22.setName("��ʳ��");  
	        btn22.setType("click");  
	        btn22.setKey("22");
	           
	        CommonButton btn31 = new CommonButton();  
	        btn31.setName("���²�");  
	        btn31.setType("click");  
	        btn31.setKey("31");
	        
	        CommonButton btn32 = new CommonButton();  
	        btn32.setName("���Ҷ���ɳ");  
	        btn32.setType("click");  
	        btn32.setKey("32");
	        
	        CommonButton btn33 = new CommonButton();  
	        btn33.setName("���˴�ת��");  
	        btn33.setType("click");  
	        btn33.setKey("33");
	        
	        CommonButton btn34 = new CommonButton();  
	        btn34.setName("΢ͶƱ");  
	        btn34.setType("view");  
	        btn34.setUrl("http://xiasha.touclick.com/mobile/vote/ListVote.aspx");
	       
	        
	      
	        ComplexButton mainBtn1 = new ComplexButton();  
	        mainBtn1.setName("΢�Ķ�");  
	        mainBtn1.setSub_button(new CommonButton[] { btn11,btn12}); 
	        
	        
	        ComplexButton mainBtn2 = new ComplexButton();  
	        mainBtn2.setName("΢����");  
	        mainBtn2.setSub_button(new CommonButton[] { btn21,btn22}); 
	                                         
	        ComplexButton mainBtn3 = new ComplexButton();  
	        mainBtn3.setName("΢����");  
	        mainBtn3.setSub_button(new CommonButton[] { btn31,btn32,btn33,btn34}); 
	    
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
}
