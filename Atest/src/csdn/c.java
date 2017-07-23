package csdn;

import java.util.Scanner;

public class c {
	/*
	 * 给定整数区间[A,B]问其中有多少个完全平方数。 输入格式： 多组数据，包含两个正整数A,B 1<=A<=B<=2000000000。 输出格式：
	 * 每组数据输出一行包含一个整数，表示闭区间[A,B]中包含的完全平方数的个数。 挑战规则： 输入样例 1 1 1 2 3 10 3 3 输出样例：
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
