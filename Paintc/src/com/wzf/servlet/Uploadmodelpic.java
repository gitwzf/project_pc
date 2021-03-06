package com.wzf.servlet;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.wzf.method.OSSObjectSample;
import com.wzf.pubvari.Variable;


public class Uploadmodelpic extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getSession().removeAttribute("picurl");
		req.getRequestDispatcher("/loading_model.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("phone_pic...");
		Variable vari=new Variable();
		MultipartRequest mr=new MultipartRequest(req, vari.up_pic_path,1048576*5);//5mb
		File str =mr.getFile("imgFile");
//		String str0;
		File str00;
		String exec="cmd /c C:\\\\\"Program Files\"\\ImageMagick-6.8.9-Q16\\convert "+vari.up_pic_path_cmd+"\\"+str.getName()+" -resize 300x300 "+vari.up_pic_path_cmd+"\\"+str.getName();
	//	System.out.println(exec);
		Process pro = Runtime.getRuntime().exec(exec);   
		BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));   
		String msg = null;     
		while ((msg = br.readLine()) != null) { 
			System.out.println(msg);     }
		str.renameTo(str00=new File(vari.up_pic_path+"/"+new Date().getTime()+".jpg"));
//		if(str.length()>1024*1024){
//			req.getSession().setAttribute("toobig", "1");
//			str.delete();
//		}
//		else{
//		if(str.length()>1024*80){//大于80k
//			double m=Math.sqrt(str.length()/(1024*80.000));
//			System.out.println(m);
//		Uploadmodelpic.scale(str.getAbsolutePath(), str0=vari.up_pic_path+"/"+new Date().getTime()+".png",m);
//		  str.delete();//删除原图
//		}
//		else
//		{	str.renameTo(str00=new File(vari.up_pic_path+"/"+new Date().getTime()+".jpg"));
//		   str0=str00.getAbsolutePath();
//		}
		
		OSSObjectSample oss=new OSSObjectSample();
		oss.uploadFile(str00.getName(), str00.getAbsolutePath());
		req.getSession().setAttribute("picurl",vari.aliyun_url+"/"+str00.getName());
	
//		req.getSession().setAttribute("picurl",str00.getAbsolutePath().replace("\\", "/").replace(vari.up_pic_path.split("/file")[0], vari.URL));
//		}
		req.getRequestDispatcher("/loading_model.jsp").forward(req, resp);
	}
	
	public final static void scale(String srcImageFile, String result,double multiple) {
        try {
        	ImageIO.setUseCache(false);
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
//        	Toolkit toolkit = Toolkit.getDefaultToolkit();
//            Image src = toolkit.getImage(srcImageFile);
            int width = src.getWidth(); // 得到源图宽
            int height = src.getHeight(); // 得到源图长
           // 缩小
              //  width = (int) (width/multiple);  有较大误差，没考虑压缩率等参数，png图比较靠普
              //  height = (int) (height/multiple);
           double m=(width*1.000)/height;            
           height=(int) Math.sqrt(1024*80/2/m);
           width=(int) Math.sqrt(1024*80/2*m);
           
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            ImageIO.write(tag, "png", new File(result));// 输出到文件流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
