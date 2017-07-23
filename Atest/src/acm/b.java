package acm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class b {
	// 乔治拿来一组等长的木棒，将它们随机地砍断，使得每一节木棍的长度都不超过50个长度单位。然后他又想把这些木棍恢复到为裁截前的状态，
	//但忘记了初始时有多少木棒以及木棒的初始长度。请你设计一个程序，帮助乔治计算木棒的可能最小长度。每一节木棍的长度都用大于零的整数表示。
	//9 木棒5 2 1 5 2 1 5 2 1   算出 各个因子，从小到大测试 数堆（>1）--》值相同
	//27  15 3 2 4 11 1 8 8 8 15 3 2 4 11 1 8 8 8 15 3 2 4 11 1 8 8 8
	public static int slength;
	public static int maxlen;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int lens = scan.nextInt();
		ArrayList<Integer> minlen = new ArrayList<Integer>();
		while (lens != 0) {
			ArrayList<Integer> alllen = new ArrayList<Integer>();
			int length = 0;
			for (int i = 0; i < lens; i++) {
				alllen.add(scan.nextInt());
				length += alllen.get(i);
				if(maxlen<alllen.get(i))maxlen=alllen.get(i);
			}
			Collections.sort(alllen);System.out.println(new Date());
			inner: for (Integer i : getAllFactor(length)) {// 分成i根
				ArrayList<Integer> alllen0 = new ArrayList<Integer>();
				alllen0.addAll(alllen);
				slength=length/i;
				outLog("分成"+i+"根。。。长："+slength);
				if(i==9)
				if(slength>=maxlen)
				if (dealArr(alllen0, length / i, i)) {
					minlen.add(slength);
					break inner;
				}
			}
			lens = scan.nextInt();
		}System.out.println(new Date());
		for (Integer i : minlen)
			System.out.println(i);

	}
	public static boolean dealArr(ArrayList<Integer> arr, int len,int size) {//false，回溯
		// 操作引用 取len长木棒 没有则递归小木棒
		if(size==0)return true;
		else
			for (int i = 0; i < arr.size(); i++) {
				int len0 = arr.get(arr.size() - 1 - i);
				if(len0==len){
					arr.remove((Integer) len);
					outLog("第"+size+"根完结        len:"+len+"  剩余arr:"+arr.toString());//第i根
				//	return dealArr(arr,slength,size-1);
					if(!dealArr(arr,slength,size-1))
					{
						arr.add((Integer)len);
						Collections.sort(arr);
					}else {
						return true;}
				}else
				if (len0 < len) { 
					arr.remove((Integer)len0);
					if(!dealArr(arr, len - len0,size))//第size次子数值无法获取
					{
						arr.add(len0);
						Collections.sort(arr);
					}else return true;
				}
			}
		//outLog("第"+size+"根长度 "+len+"无法匹配。。");
		return false;// 都比len大 无法匹配

	}
	public static void outLog(String str){
		System.out.println(str);
	}
	

	public static ArrayList<Integer> getAllFactor(int num) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		int n = num;
		for (int i = n; i > 0; i--) {
			if (num % i == 0) {
				//System.out.println("factor根数:"+i);
				array.add(i);
				// num=num/i;
			}
		}
		return array;

	}

}
