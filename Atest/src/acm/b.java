package acm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class b {
	// ��������һ��ȳ���ľ��������������ؿ��ϣ�ʹ��ÿһ��ľ���ĳ��ȶ�������50�����ȵ�λ��Ȼ�����������Щľ���ָ���Ϊ�ý�ǰ��״̬��
	//�������˳�ʼʱ�ж���ľ���Լ�ľ���ĳ�ʼ���ȡ��������һ�����򣬰������μ���ľ���Ŀ�����С���ȡ�ÿһ��ľ���ĳ��ȶ��ô������������ʾ��
	//9 ľ��5 2 1 5 2 1 5 2 1   ��� �������ӣ���С������� ���ѣ�>1��--��ֵ��ͬ
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
			inner: for (Integer i : getAllFactor(length)) {// �ֳ�i��
				ArrayList<Integer> alllen0 = new ArrayList<Integer>();
				alllen0.addAll(alllen);
				slength=length/i;
				outLog("�ֳ�"+i+"������������"+slength);
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
	public static boolean dealArr(ArrayList<Integer> arr, int len,int size) {//false������
		// �������� ȡlen��ľ�� û����ݹ�Сľ��
		if(size==0)return true;
		else
			for (int i = 0; i < arr.size(); i++) {
				int len0 = arr.get(arr.size() - 1 - i);
				if(len0==len){
					arr.remove((Integer) len);
					outLog("��"+size+"�����        len:"+len+"  ʣ��arr:"+arr.toString());//��i��
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
					if(!dealArr(arr, len - len0,size))//��size������ֵ�޷���ȡ
					{
						arr.add(len0);
						Collections.sort(arr);
					}else return true;
				}
			}
		//outLog("��"+size+"������ "+len+"�޷�ƥ�䡣��");
		return false;// ����len�� �޷�ƥ��

	}
	public static void outLog(String str){
		System.out.println(str);
	}
	

	public static ArrayList<Integer> getAllFactor(int num) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		int n = num;
		for (int i = n; i > 0; i--) {
			if (num % i == 0) {
				//System.out.println("factor����:"+i);
				array.add(i);
				// num=num/i;
			}
		}
		return array;

	}

}
