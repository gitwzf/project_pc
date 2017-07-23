package acm;

import java.util.Scanner;

public class Q1088 {
	/*一个人可以从某个点滑向上下左右相邻四个点之一，当且仅当高度减小。在上面的例子中，
	 * 一条可滑行的滑坡为24-17-16-1。当然25-24-23-...-3-2-1更长。
1 2 3 4 5 3
16 17 18 19 6 2
15 24 25 20 7 5 
14 23 22 21 8 7
13 12 11 10 9 8
4 22 32 3 2 11
	 * */
	public static int[][] val;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a = scan.nextInt(), b = scan.nextInt();
		int[][] arr = new int[a][b];
		val=new int[a][b];
		for (int i = 0; i < a; i++) {
		for (int j = 0; j < b; j++)
				arr[i][j] = scan.nextInt();
		}
	int len = 0;
		// 遍历 记录最长
		for (int i = 0; i < a; i++)
			for (int j = 0; j < b; j++) {
				// 从上到下走，四方向
				int len0 = getLen(arr, i, j);
				val[i][j]=len0;
				if (len < len0)
					len = len0;

			}
		System.out.println(len+1);

	}

	public static int getLen(int[][] arr, int a, int b) {
		if(val[a][b]!=0)return val[a][b];//不重复计算
		int a0, b0,lenx,leny,size1=0,size2=0,size3=0,size4=0,max;
		lenx=arr.length;
		leny=arr[0].length;
		a0=a;
		b0=b-1;
		if ( b0>= 0&&b0<leny)
			if(arr[a0][b0]<arr[a][b]){//System.out.println("("+arr[a][b]+")-->("+arr[a0][b0]+")");
			size1=1+getLen(arr,a0,b0);
			}	
			
		a0=a;
		b0=b+1;
		if (b0>= 0&&b0<leny)
			if(arr[a0][b0]<arr[a][b]){//System.out.println("("+arr[a][b]+")-->("+arr[a0][b0]+")");
			size2= 1+getLen(arr,a0,b0);
			}
		a0=a+1;
		b0=b;
		if (a0>= 0 &&a0<lenx)
			if(arr[a0][b0]<arr[a][b]){//System.out.println("("+arr[a][b]+")-->("+arr[a0][b0]+")");
			size3= 1+getLen(arr,a0,b0);
			}
		a0=a-1;
		b0=b;
		if (a0>= 0 &&a0<lenx)
			if(arr[a0][b0]<arr[a][b]){//System.out.println("("+arr[a][b]+")-->("+arr[a0][b0]+")");
			size4= 1+getLen(arr,a0,b0);
			}
		//System.out.println("-->("+arr[a][b]+")");
		max=size1;
		if(size2>max)max=size2;
		if(size3>max)max=size3;
		if(size4>max)max=size4;
		
		
		
		return max;
	}

}
