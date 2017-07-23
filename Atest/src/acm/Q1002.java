package acm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q1002 {
	/*Q��Zû��ӳ�䵽�κ����֣����ַ�����Ҫ���ţ�����������Ӻ�ɾ���� TUT-GLOP�ı�׼��ʽ��888-4567��310-GINO�ı�׼��ʽ��310-4466��3-10-10-10�ı�׼��ʽ��310-1010�� 
���������������ͬ�ı�׼��ʽ����ô���Ǿ��ǵ�ͬ�ģ���ͬ�Ĳ��ţ� 
��Ĺ�˾����Ϊ���صĹ�˾��дһ���绰���뱡����Ϊ�������Ƶ�һ���֣�����Ҫ����Ƿ��������Ͷ����˾ӵ����ͬ�ĵ绰���롣 */
	static Map<String, Integer> map_match=new HashMap<String, Integer>()
	,map_str=new HashMap<String, Integer>();
	static{
		map_match.put("A", 2);map_match.put("B", 2);map_match.put("C", 2);
		map_match.put("D", 3);map_match.put("E", 3);map_match.put("F", 3);
		map_match.put("H", 4);map_match.put("I", 4);map_match.put("G", 4);
		map_match.put("J", 5);map_match.put("K", 5);map_match.put("L", 5);
		map_match.put("M", 6);map_match.put("N", 6);map_match.put("O", 6);
		map_match.put("P", 7);map_match.put("S", 7);map_match.put("R", 7);
		map_match.put("U", 8);map_match.put("T", 8);map_match.put("V", 8);
		map_match.put("W", 9);map_match.put("X", 9);map_match.put("Y", 9);
	}
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		String[] str=new String[num];
		for(int i=0;i<num;i++)
		{
			str[i]=scan.next();
		}
		for(String str0:str)
	//	  if((str0=str0.replaceAll("\\W", "").replaceAll("[_a-z]", "")).length()==7)//�Ϸ�����
			if((str0=str0.replace("-", "")).length()==7)
		{
			str0=changeForm(str0);
			if(map_str.containsKey(str0))
				map_str.put(str0, map_str.get(str0)+1);
			else
				map_str.put(str0,1);
			
		}
		boolean flag=false;
		ArrayList<String> array=new ArrayList<String>();
		for(String key:map_str.keySet())
		{   int value=map_str.get(key);
		if(value!=1){flag=true;
			array.add(key);
		}
		}
		Collections.sort(array);
		for(String key:array)
			System.out.println(key+" "+map_str.get(key));
		
		if(!flag)
			System.out.println("No duplicates.");
		
	}
	
	public static String changeForm(String str){
		StringBuffer str0=new StringBuffer();
		for(int i=0;i<str.length();i++){
			String s=str.charAt(i)+"";
			if(map_match.containsKey(s))
				s=String.valueOf(map_match.get(s));
			
			str0.append(s);
			if(i==2)str0.append("-");
		}
		return str0.toString();
	}

}
