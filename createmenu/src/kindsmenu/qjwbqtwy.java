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
	        btn34.setName("���ǰ�");  
	        btn34.setType("view");  
	        btn34.setUrl("http://wx.wsq.qq.com/234815794");
	        
	        CommonButton btn35 = new CommonButton();  
	        btn35.setName("ʵϰ����");  
	        btn35.setType("view");  
	        btn35.setUrl("http://115.236.173.81/mobileInPage!home.htm");
	     
	    
	      
	        ComplexButton mainBtn1 = new ComplexButton();  
	        mainBtn1.setName("΢ӦƸ");  
	        mainBtn1.setSub_button(new CommonButton[] { btn11,btn12,btn34}); 
	        
	        
	        ComplexButton mainBtn2 = new ComplexButton();  
	        mainBtn2.setName("΢����");  
	        mainBtn2.setSub_button(new CommonButton[] { btn22}); 
	                                         
	        ComplexButton mainBtn3 = new ComplexButton();  
	        mainBtn3.setName("΢����");  
	        mainBtn3.setSub_button(new CommonButton[] { btn31,btn32,btn33,btn35}); 
	    
	        /** 
	         * ���ǹ��ں�xiaoqrobotĿǰ�Ĳ˵��ṹ��ÿ��һ���˵����ж����˵���<br> 
	         *  
	         * ��ĳ��һ���˵���û�ж����˵��������menu����ζ����أ�<br> 
	         * ���磬������һ���˵���ǡ��������顱����ֱ���ǡ���ĬЦ��������ômenuӦ���������壺<br> 
	         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
	         */  
	        Menu menu = new Menu();  
	        menu.setButton(new button[] { mainBtn1,btn22,mainBtn3 });  
	  
	        return menu;  
	    }  
}
