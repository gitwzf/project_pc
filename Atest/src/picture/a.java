package picture;

import java.io.File;

public class a {
   public static void main(String[] args) {
	   delCopy();
}
   /**É¾³ýÎó¸´ÖÆ²Ù×÷ µÄÍ¼Æ¬
    * 
    * */
   public static void delCopy(){
  	 String dir="D:/µã´¥Í¼Æ¬/overall_601_806/648×ÏÉ°ºø";
  	 File f=new File(dir);
			File[] ff=f.listFiles();
			int i=0;
			for(File file:ff)
				if(file.getName().contains("¸±±¾"))
				{	
					file.delete();
					File fff=new File(dir+"/"+file.getName().split(" ")[0]+".jpeg");
					fff.delete();
					System.out.println(++i+" del "+fff.getName());
				}
  	 
  	 
   }
}
