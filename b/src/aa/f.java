package aa;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class f {
    public static void main(String[] args) {//quicksort
      int[] a={32,45,54,23,6,53,6,3,45,645,3,423,767,3};
       change(a,0,a.length-1);  
       for(int aa:a)
    	   System.out.print(aa+" ");
    }
    
    public static void change(int[] a,int beg,int end){
    		int i=beg,j=end;//eng beg об╠Й
    		int[] b=new int[j-i+1];
    	    int bb=0,cc=j-i;
    		while(i++<j){
    			if(a[i]<=a[beg]){
    			b[bb++]=a[i];
    		}else{
    			b[cc--]=a[i];
    		}
    		}
    		b[bb]=a[beg];
//    		String str="";
//    		 for(int aa:b)
//    			 str+="  "+aa;
//    	    	   System.out.println(str);
//    	    	   System.out.println("beg="+beg+" end="+end);
//    	    	   str="a=";
//    		 for(int aa:a)
//    			 str+="  "+aa;
//    	    	   System.out.println(str);
    			System.arraycopy(b, 0, a, beg, b.length);
    			if(bb>1)
    		change(a,beg,beg+bb-1);
    			if(end-bb-beg>1)
        	change(a,beg+bb+1,end);
    }
    
  
}


