package picture;

import java.io.File;

public class a {
   public static void main(String[] args) {
	   delCopy();
}
   /**ɾ�����Ʋ��� ��ͼƬ
    * 
    * */
   public static void delCopy(){
  	 String dir="D:/�㴥ͼƬ/overall_601_806/648��ɰ��";
  	 File f=new File(dir);
			File[] ff=f.listFiles();
			int i=0;
			for(File file:ff)
				if(file.getName().contains("����"))
				{	
					file.delete();
					File fff=new File(dir+"/"+file.getName().split(" ")[0]+".jpeg");
					fff.delete();
					System.out.println(++i+" del "+fff.getName());
				}
  	 
  	 
   }
}
