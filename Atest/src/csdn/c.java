package csdn;

import java.util.Scanner;

public class c {
	/*
	 * ������������[A,B]�������ж��ٸ���ȫƽ������ �����ʽ�� �������ݣ���������������A,B 1<=A<=B<=2000000000�� �����ʽ��
	 * ÿ���������һ�а���һ����������ʾ������[A,B]�а�������ȫƽ�����ĸ����� ��ս���� �������� 1 1 1 2 3 10 3 3 ���������
	 * 1 1 2 0
	 */
	public static void main(String[] args) {
		while(true){
			Scanner scan = new Scanner(System.in);
			String[] str = scan.nextLine().split(" ");
			int a=Integer.parseInt(str[0]),b=Integer.parseInt(str[1]);
			int sum=0;
			int m=(int)Math.sqrt(2000000000)+1;
			for(int i=1;i<=m;i++){
				int x=i*i;
				if(x>=a&&x<=b)
					sum++;
				if(x>b)break;
			}
			
			System.out.println(sum);
		}
		
		
	}

}
