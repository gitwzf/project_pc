package test;

public class QuickSort {
	public static void main(String[] args) {
		int[] aa=new int[]{5,2,3,6,4,7,9,1,0,8};
		for(int a :aa)
			System.out.print(a+" ");
		quicksort(aa,0,9);
		System.out.println();
		for(int a :aa)
			System.out.print(a+" ");
		
		
	}
	//基准数 位置一直在变 最后分成基准数的左右大小两堆
	public static void quicksort(int[] arr,int low,int high){
		int l=low;
		int h=high;
		int x=arr[l];
		while(l<h){
			while(l<h&&arr[h]>=x)
				h--;
			if(l<h){
			  int temp=arr[h];
			  arr[h]=arr[l];
			  arr[l]=temp;
			  l++;
			}
			
			while(l<h&&arr[l]<=x)
				l++;
			if(l<h){
				int temp=arr[h];
				arr[h]=arr[l];
				arr[l]=temp;
				h--;
			}
		}
		if(l>low)
			quicksort(arr,low,l-1);
		if(h<high)
			quicksort(arr,h+1,high);
		
	}

}
