package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class a {
	/*输出任意数值大于m值的各种长度和的最小值
	 * */
	public static void main(String[] args) {
		ArrayList<Integer> a=new ArrayList<Integer>();
		a.add(3);
		a.add(6);
		a.add(1);
		a.add(4);
		int m=9;
		for(int i=0;i<a.size();i++)
			System.out.println(i+":"+getLen(a,1+i,m));
	}
	public static int getLen(ArrayList<Integer> aa,int len,int m){
		int max=0;
		ArrayList<Integer> a=new ArrayList<Integer>();
		a.addAll(aa);
	if(len==0)return 0;
	else
		if(a.size()==1)return a.get(0);
		else 
			for(int i=0;i<a.size();i++){
				ArrayList<Integer> c=new ArrayList<Integer>();
				int k=a.get(i);
				c.addAll(a);
				c.remove(i);
				int max0=k+getLen(c,len-1,m);//智能 用不定函数值表示 结果
				if(i==0)
				max=max0;
				if(max0>=m)
				  if(max0<max)
					  max=max0;
			}
		return max;
	}
}
