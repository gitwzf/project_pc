package aa;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public class c {
	public static void main(String[] args) {
		String picdir="D:/�㴥ͼƬ/";
		String movepic="/b";
		
		String filename="/443�Զ�������";
		int rate=19;
		System.out.println("�ļ��У�"+filename);
		//����Ĭ�� �ļ�������ƶ�����ͼƬ
		rebackPic(movepic,picdir+filename);
		//�ƶ����Ӷ��ڵ�ͼƬ
		movePic(rate,movepic,picdir+filename);
		
	}
	/**
	 *�����ƶ�����ͼƬ 
	 **/
	public static void rebackPic(String movedir,String fdir){
		File f=new File(fdir+movedir);
		if(f.exists()){
			String[] dir=f.list();
			int count=0;
			for(int i=0;i<dir.length;i++){
			count++;
					f=new File(fdir+movedir+"/"+dir[i]);
					f.renameTo(new File(fdir+f.getName()));
					System.out.println("�Ƴ�Ŀ¼��"+f.getAbsolutePath()+"  "+(fdir+f.getName()));
				}
				System.out.println("ִ�в�������"+count+"��ͼƬ �Ƴ��ļ���"+movedir);
			
		}
		
	}
	
	/**
	 * �������ء����롯����ͼƬ
	 * 
	 * */
	public static void movePic(int fg,String movedir,String fdir){
		File f0=new File(fdir+movedir);
		f0.mkdir();
		File f=new File(fdir);
		String[] dir=f.list();
		double[] d=judgePic(fdir,dir);
		int count=0;
		for(int i=0;i<dir.length;i++){
			if(d[i]>fg){count++;
				 f=new File(fdir+"/"+dir[i]);
				f.renameTo(new File(fdir+movedir+"/"+f.getName()));
			}
			}
		System.out.println("ִ�в�������"+dir.length+"��ͼƬ,��"+count+"�Ŵ��ڷָ�ֵ"+fg+"��ͼƬ   �ƶ����ļ���"+movedir);
	}
	/**  int rgb=img.getRGB(i, j);     
	 *  int R = (rgb & 0xff0000) >> 16;     
	 *   int G = (rgb & 0xff00) >> 8;          
	 *   int B = (rgb & 0xff);          
	 *   rgb=((R*256)+G)*256+B;         
     *   ��RGBֵ���ý����Ӧ������     img.setRGB(i, j, rgb);
	 * */
	public static double[] judgePic(String fdir,String[] dir){
		BufferedImage img = null;
		double[] d=new double[dir.length];
		for(int len=0;len<dir.length&&!dir[len].equals("b");len++){
		try {
			img = ImageIO.read(new File(fdir+"/"+dir[len]));
		} catch (IOException e) {
			
			e.printStackTrace();System.out.println(fdir+"/"+dir[len]);
		}
		int width=img.getWidth();
		int height=img.getHeight();
		int rgb=-1,rgb0=-1;     
		int R = 0,R0=0,R1=0;      
		int G = 0,G0=0,G1=0;         
		int B = 0,B0=0,B1=0;          
		//rgb=((R*256)+G)*256+B;  //��RGBֵ���ý����Ӧ������     img.setRGB(i, j, rgb);
		Double sum=0d;
		for(int i=0;i<width-1;i++)
			for(int j=0;j<height-1;j++){
				 rgb=img.getRGB(i, j);     
					 R = (rgb & 0xff0000) >> 16;      
					 G = (rgb & 0xff00) >> 8;         
					 B = (rgb & 0xff);
					//��	 
					 rgb0=img.getRGB(i+1, j);     
					 R0 = (rgb & 0xff0000) >> 16;      
					 G0 = (rgb & 0xff00) >> 8;         
					 B0 = (rgb & 0xff);
				sum+=Math.sqrt(Math.pow(R-R0,2)+Math.pow(G-G0,2)+Math.pow(B-B0,2));
				//��
				 rgb=img.getRGB(i, j+1);     
				 R1 = (rgb & 0xff0000) >> 16;      
				 G1 = (rgb & 0xff00) >> 8;         
				 B1 = (rgb & 0xff);
				sum+=Math.sqrt(Math.pow(R-R1,2)+Math.pow(G-G1,2)+Math.pow(B-B1,2));
			}
		d[len]= sum/(width*height);
	//	System.out.println("ͼ����"+width+"  ��"+height+" ���룺"+d[len]);
		}
		return d;
	}

}
