package aa;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
public class g extends HttpServlet{ 
     public static void main(String[] args) throws Exception { 
    	 System.out.println("���������");
    	Scanner scan=new Scanner(System.in);
    	int N=scan.nextInt();
    	System.out.println("����ÿ���������պŷָ���");
    	scan=new Scanner(System.in);
    	int[] Ns = new int[N];
//    	Set<Integer> set=new HashSet<Integer>();
    	 //	int k=0,j;
    	int l=(Ns[0]=scan.nextInt());
    	for(int i=1;i<N;i++)
    	{ 	Ns[i]=scan.nextInt();
    	    l^=Ns[i];
//    		if(set.contains(j=scan.nextInt()))
//    			set.remove(j);
//    		else set.add(j);
    	}
    	if(l==0)
    		System.out.println("Lost");//����ģ����ն�Ӧ�ã�
    	else
    		System.out.println("Win"+l);
   
//    	k=set.size();
//    	int m=0;
//    	m=k%2+k/2;
//    	if(m%2!=0)System.out.println("Win");//1 2 3�Ǳ������ �ص㣿 �ݼ�1����С1 �����ò���
//    	else System.out.println("Lost");//�Ǳ���   ����һ�ѣ��ö��ִ��ڷǱ�ʤ�ľ���   2��������ô�죿һ��һ�š�����ʵ����һ�������õĸ���һ��
    
    	
     }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		File file0=new File("aaa.txt");
		file0.mkdirs();
		System.out.println(file0.getAbsolutePath());
		System.out.println("�����ļ���"+file0.createNewFile());
		
		File file=new File("classes/aa.properties");
		System.out.println("�ļ����ڣ�"+file.exists());
		System.out.println(g.class.getResource("/").getPath());
		File file1=new File(g.class.getResource("/").getPath()+"/aa.properties");
		System.out.println("�ļ�����1��"+file1.exists());
		File file2=new File("../../aa.properties");
		System.out.println("�ļ�����2��"+file2.exists());
		
	}
   

     
     
     
} 

	