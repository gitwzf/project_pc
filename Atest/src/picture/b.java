package picture;

import java.io.File;

public class b {
	public static void main(String[] args) {
		String fdir="D:/�㴥ͼƬ/overall_441_600";
		File f=new File(fdir);
		File[] ff=f.listFiles();
		for(File f0:ff){
			f0.renameTo(new File(fdir+"/"+f0.getName().substring(0,3)));
			
		}
		
	}

}
