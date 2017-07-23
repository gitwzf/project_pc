package aa;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.wzf.method.RandomString;
import com.wzf.pubvari.Variable;

public class Yzm extends HttpServlet{
    Variable vari=new Variable();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("122");
		System.out.println(vari.getClass());
		resp.setContentType("image/jpeg");
        ServletOutputStream out = resp.getOutputStream(); 
        getCertPic(100,30,out);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		out.flush();
		out.close();
	}
	 public String getCertPic(int width, int height, OutputStream os) {
		   if(width<=0)width=60;
		   if(height<=0)height=30; 
		   BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			  
		   // ��ȡͼ�������� 
		   Graphics g = image.getGraphics(); 
		   // �趨����ɫ 
		   g.setColor(new Color(0xDCDCDC)); 
		   g.fillRect(0, 0, width, height); 
		   //���߿� 
//		   g.setColor(Color.black); 
//		   g.drawRect(0,0,width-1,height-1); 
		   // ȡ�����������֤��
		   String strEnsure =new RandomString().getRandomString(6, true, true, true, false, "0Ol1");
		   // ��������֤����ʾ��ͼ����,���Ҫ���ɸ���λ����֤��,����drawString���
		   g.setColor(Color.BLUE); 
		   g.setFont(new Font("Atlantic Inline",Font.BOLD,24)); 
		   String str ="";
		   for(int i=0;i<strEnsure.length();i++){
		   str=strEnsure.substring(i,i+1); 
		   
		   g.drawString(str,8+(width-8)/strEnsure.length()*i,22); 
		   }
		   // �������10�����ŵ�
		   Random rand = new Random();
		   for (int i=0;i<10;i++) { 
		    int x = rand.nextInt(width); 
		    int y = rand.nextInt(height); 
		    g.drawOval(x,y,1,1); 
		   } 
		   // �ͷ�ͼ��������
		   g.dispose();   
		   try {
		    // ���ͼ��ҳ�� 
			 image=twistImage(width,height,image);
		    ImageIO.write(image, "JPEG", os);
		   } catch (IOException e) {
		    return "";
		   }  
		   return strEnsure;
		   }
	 private BufferedImage twistImage(int width, int height,BufferedImage image) {  
		 double dMultValue =10;// ���εķ��ȱ�����Խ��Ť���ĳ���Խ�ߣ�һ��Ϊ3  
	        double dPhase =2;// ���ε���ʼ��λ��ȡֵ���䣨0-2��PI��  
	  
	        BufferedImage destBi = new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
	  
	        for (int i = 0; i < destBi.getWidth(); i++) 
	            for (int j = 0; j < destBi.getHeight(); j++) 
	            	destBi.setRGB(i, j,image.getRGB(1, 1));
	        
	        for (int i = 0; i < destBi.getWidth(); i++) {  
	            for (int j = 0; j < destBi.getHeight(); j++) { 
	                int nOldX = getXPosition4Twist(dPhase, dMultValue,  
	                        destBi.getHeight(), i, j);  
	                int nOldY = j;  
	                if (nOldX >= 0 && nOldX < destBi.getWidth() && nOldY >= 0  
	                        && nOldY < destBi.getHeight()) {  
	                    destBi.setRGB(nOldX, nOldY,image.getRGB(i, j));  
	                }
	            }  
	        }  
	        return destBi;  
	    }
	 private int getXPosition4Twist(double dPhase, double dMultValue,  
	            int height, int xPosition, int yPosition) {  
	        double PI = 3.1415926535897932384626433832799; // ��ֵԽ��Ť���̶�Խ��  
	        double dx = (double) (PI * yPosition) / height + dPhase;  
	        double dy = Math.sin(dx);  
	        return xPosition + (int) (dy * dMultValue);  
	    } 
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
	

}
