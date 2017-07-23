package com.wzf.servlet;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzf.pubvari.Variable;

import com.oreilly.servlet.MultipartRequest;


public class Upload extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Variable vari=new Variable();
		MultipartRequest mr=new MultipartRequest(req, vari.str_local_dir+vari.pic_path,1048576);
		File str =mr.getFile("imgFile");
		String str0="";
		Upload.scale(str.getAbsolutePath(), str0=vari.str_local_dir+vari.pic_path+"/"+new Date().getTime()+".jpg");
		//File str0;
		//str.renameTo(str0=new File(vari.up_pic_path+"/"+new Date().getTime()+".jpg"));
//		int ap;
//		req.getSession().setAttribute("ap",ap=(int)(Math.random()*10000));
//		System.out.println("ap="+ap);
		Writer out = resp.getWriter();   //img 长 宽  100%
		//System.out.println("str0="+str0.getAbsolutePath());
       out.write("<html><body  marginheight='0px' marginwidth='0px'><img height='50px' width='90px' src='"+str0.replace("\\", "/").replace((vari.str_local_dir+vari.pic_path).split("/file")[0], vari.URL)+"'/></body></html>");
       out.flush();
		out.close();
	}
	
	public final static void scale(String srcImageFile, String result) {
        try {
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
            int width = src.getWidth(); // 得到源图宽
            int height = src.getHeight(); // 得到源图长
           // 缩小
                width = 360;
                height = 200;
           
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
