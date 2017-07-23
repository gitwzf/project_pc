package aa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class a {
	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int a=scan.nextInt();
		int b;
		for(b=a;b>0;b--){
			line(b,a);
			System.out.println();
		}
		
		
	}
    public static void line(int b,int a){
    	String str="";
    	int c[] = new int[a/b+(a%b==0?0:1)];//该行第一个
    	int j=0,d=a,e=c.length;
    	for(;;){
    		if(d==0)break;
    		c[j++]=d>b?b:d;
    		d=d-c[j-1];
    		str+="+"+c[j-1];
    	//	System.out.println(d);
    	}
    	str=str.substring(1)+";";
    	System.out.println(str);
    	//改行其他项
    	String str0="";
    	for(int m=c.length-1;m>0;m--)
    	{   String str00="";
    	   int f=0;
    		for(;;){
    			if(f==1)break;
    			str00=(f=--c[m])+"1"+str00;
    		}
    		
    		
    		
    	}
    	
    	
    	
    	for(int k=e;k>0;k--){
    		if(c[1]==1||c[0]==a){print(c);break;}
    	}
    	
    }	
    public static void print(int[] c){
    	String str="";
    	for(int i=0;i<c.length;i++)
    		str=str+"+"+c[i];
    	System.out.print(str+" ");
    }

}