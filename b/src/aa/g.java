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
    	 System.out.println("输入堆数：");
    	Scanner scan=new Scanner(System.in);
    	int N=scan.nextInt();
    	System.out.println("输入每堆数量，空号分隔：");
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
    		System.out.println("Lost");//必输的（参照对应拿）
    	else
    		System.out.println("Win"+l);
   
//    	k=set.size();
//    	int m=0;
//    	m=k%2+k/2;
//    	if(m%2!=0)System.out.println("Win");//1 2 3是必输局面 特点？ 递加1，最小1 ，不得不输
//    	else System.out.println("Lost");//非必输   拿走一堆，让对手处在非必胜的局面   2人无赖怎么办？一颗一颗。。其实就是一堆中乙拿的跟甲一样
    
    	
     }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		File file0=new File("aaa.txt");
		file0.mkdirs();
		System.out.println(file0.getAbsolutePath());
		System.out.println("创建文件："+file0.createNewFile());
		
		File file=new File("classes/aa.properties");
		System.out.println("文件存在："+file.exists());
		System.out.println(g.class.getResource("/").getPath());
		File file1=new File(g.class.getResource("/").getPath()+"/aa.properties");
		System.out.println("文件存在1："+file1.exists());
		File file2=new File("../../aa.properties");
		System.out.println("文件存在2："+file2.exists());
		
	}
   

     
     
     
} 

	