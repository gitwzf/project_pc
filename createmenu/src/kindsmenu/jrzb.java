package kindsmenu;

import creamenu.CommonButton;
import creamenu.ComplexButton;
import creamenu.Menu;
import creamenu.button;

public class jrzb {
	 public static  Menu getMenu() {   //view  click
	        CommonButton btn11 = new CommonButton();  
	        btn11.setName("�������籭");  
	        btn11.setType("click");  
	        btn11.setKey("11");
	        
	        CommonButton btn12 = new CommonButton();  
	        btn12.setName("�������籭");  
	        btn12.setType("click");  
	        btn12.setKey("12");
	        
	        CommonButton btn21 = new CommonButton();  
	        btn21.setName("��ת��");  
	        btn21.setType("click");  
	        btn21.setKey("21");
	        
	        CommonButton btn22 = new CommonButton();  
	        btn22.setName("�ι���");  
	        btn22.setType("click");  
	        btn22.setKey("22");
	        
	        CommonButton btn23 = new CommonButton();  
	        btn23.setName("ҡһҡ");  
	        btn23.setType("click");  
	        btn23.setKey("23");
	        
	        CommonButton btn24 = new CommonButton();  
	        btn24.setName("�������");  
	        btn24.setType("click");  
	        btn24.setKey("24");
	  
	        CommonButton btn31 = new CommonButton();  
	        btn31.setName("��Ӱ����");  
	        btn31.setType("click");  
	        btn31.setKey("31");
	        
	        CommonButton btn32 = new CommonButton();  
	        btn32.setName("���򱦱�");  
	        btn32.setType("click");  
	        btn32.setKey("32");
	        
	        CommonButton btn33 = new CommonButton();  
	        btn33.setName("VC����");  
	        btn33.setType("view");  
	        btn33.setUrl("http://wx.wsq.qq.com/233791702");
	        
	        CommonButton btn34 = new CommonButton();  
	        btn34.setName("��������");  
	        btn34.setType("view");  
	        btn34.setUrl("http://jrzb.touclick.com/mobile/shop/ListPdt.aspx");
	        
	        CommonButton btn35 = new CommonButton();  
	        btn35.setName("������ѯ");  
	        btn35.setType("view");  
	        btn35.setUrl("http://jrzb.touclick.com/mobile/shop/BillSearch.aspx");
	        
	       
	    
	      
	        ComplexButton mainBtn1 = new ComplexButton();  
	        mainBtn1.setName("VC��Ӫ");  
	        mainBtn1.setSub_button(new CommonButton[] { btn11,btn12}); 
	        
	        
	        ComplexButton mainBtn2 = new ComplexButton();  
	        mainBtn2.setName("VC���ֳ�");  
	        mainBtn2.setSub_button(new CommonButton[] { btn21,btn22,btn23,btn24}); 
	                                         
	        ComplexButton mainBtn3 = new ComplexButton();  
	        mainBtn3.setName("VC����");  
	        mainBtn3.setSub_button(new CommonButton[] { btn31,btn32,btn33,btn34,btn35}); 
	    
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
