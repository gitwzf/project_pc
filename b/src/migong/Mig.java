package migong;

public class Mig {
	
	public static void main(String[] args){
		int a[][]={
				{1,0,1,1,1,1,1,1},
				{1,1,1,0,1,1,0,1},
				{0,1,0,0,1,1,1,1},
				{1,1,1,1,1,0,1,0},
				{0,1,0,1,0,1,1,1},
				{0,1,0,1,0,1,0,1},
				{1,1,1,1,1,1,1,0},
				{1,0,0,1,0,0,1,1}
				};
		int[] b=new int[64];
		int c=0,x=0,y=0;
		b[0]=11;
		while(c<63&&b[c]!=88){
			if(b[c+1]==0){
				if(x<7&&a[x+1][y]==1)b[++c]=10*(++x+1)+y+1;
				else if(y<7&&a[x][y+1]==1)b[++c]=10*(x+1)+(++y)+1;
				else if(x>0&&a[x-1][y]==1)b[++c]=10*(--x+1)+y+1;
				else if(y>0&&a[x][y-1]==1)b[++c]=10*(x+1)+(--y+1);
				else c--;
				//System.out.println(b[c]);
			}
			else {
				if(b[c+1]-10*x-y==10)
					if(y<7&&a[x][y+1]==1)b[++c]=10*(x+1)+(++y)+1;
				      else if(x>0&&a[x-1][y]==1)b[++c]=10*(--x+1)+y+1;
				        else if(y>0&&a[x][y-1]==1)b[++c]=10*(x+1)+(--y+1);
				           else c--;
			    else if(b[c+1]-10*x-y==1)
			    	if(x>0&&a[x-1][y]==1)b[++c]=10*(--x+1)+y+1;
				        else if(y>0&&a[x][y-1]==1)b[++c]=10*(x+1)+(--y+1);
				           else c--;
				else if(b[c+1]-10*x-y==-10)
					if(y>0&&a[x][y-1]==1)b[++c]=10*(x+1)+(--y+1);
				           else c--;
			}
			}
		int k=0;
		for(int d:b)
			System.out.println("µÚ"+(++k)+"²½£º"+d);
				
		}
		
	}




