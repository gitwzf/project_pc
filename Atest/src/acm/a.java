package acm;

import java.util.Scanner;

public class a {
	public static void main(String[] args) {
		while(true){
			Scanner scan=new Scanner(System.in);
			String str=scan.next();
			String r=str.split(" ")[0];
			int n=scan.nextInt();
			outLog(r+" "+n);
			System.out.println(power(r,n,"1"));
			
		}
		
	}
	public static String power(String r,int n,String rr){
		if(n==0)return rr;
		else return power(r,--n,diyMul(rr,r));
		
		
	}
	public static String diyMul(String rr,String r){//str*str   貌似逻辑错了，不能操作同一个字符串str，高位不能被低位影响
		outLog("mul rr:"+rr+"  r="+r);
		String[] rrvalue=new String[r.length()];
		for(int i=r.length()-1;i>=0;i--){
			rrvalue[i]=diyMul0(rr,r.charAt(i),r.length()-1-i);
			System.out.println("rrvalue"+i+":"+rrvalue[i]);
		}
		//叠加rrvalue
		for(int i=1;i<rrvalue.length;i++){
			for(int j=0;j<rrvalue[i].length();j++){
				rrvalue[0]=diyAdd(rrvalue[0],rrvalue[i].charAt(j)-48,rrvalue[i].length()-1-j);
			}
	   System.out.println("rrvalue0="+rrvalue[0]);
		}
//		outLog("mul rr:"+rr);
		return rrvalue[0];
	}
	public static String diyMul0(String rr,char c,int pow){//str*char
		outLog("mul0  rr:"+rr+" c:"+c+"  pow:"+pow);
		for(int i=rr.length()-1;i>=0;i--){
			int p=rr.length()-1-i+pow;//乘积的分位
			
			int j=(rr.charAt(i)-48)*(c-48);
			int k=j%10;
			//outLog("mulo rr="+rr+"  off="+off+" i="+i);
			rr=rr.substring(0,i)+k+rr.substring(i+1);
			if(j/10>0)
				rr=diyAdd(rr,j/10,p-1);
		}
//		outLog("mul0 rr:"+rr);
		if(pow>0){//补0
			while(pow>0){
				rr=rr+"0";
				pow--;
			}
		}
		return rr;
	}
	public static String diyAdd(String rr,int k,int pow){
		int posval=0;
		outLog("add  rr:"+rr+" k:"+k+" pow:"+pow);
		int off=rr.length()-1-pow;//index
		if(off<0){//补0
			while(off<0){
				rr="0"+rr;
				off++;
			}
			
		}
	//	outLog(off+" "+rr.length());
		if(off<rr.length())
			posval=rr.charAt(off)-48;
		
		int add=k+posval;
		
		int newval;
		if(off>0){
			newval=rr.charAt(off-1)-48;
		if(add>=10)newval++;
			rr=rr.substring(0,off-1)+newval+add%10+rr.substring(off+1);
		}
		else{
			newval=0;
			if(add>=10)newval=1;
				rr=""+newval+add%10+rr.substring(1);
			
		}
//		outLog("add rr:"+rr);
		return rr;
	}
	
	public static void outLog(String str){
		System.out.println(str);
	}
}
