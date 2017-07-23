package com.wzf.action;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import pubvari.Variable;

import com.oreilly.servlet.MultipartRequest;
import com.wzf.method.ReqResp;

public class UploadAction {
	
	public String audio(){
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpServletResponse resp=ServletActionContext.getResponse();
		Variable vari=new Variable();
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, vari.up_audio_path,10485760);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File str = mr.getFile("attachFile");
		File str0;
		str.renameTo(str0=new File(vari.up_audio_path+"/"+new Date().getTime()+".mp3"));
		ReqResp.writer(resp, "<html><body bgcolor='green'><form name='news'><input type='text' name='_picture' id='apath' value='"+str0.getAbsolutePath().replace("\\", "/").replace(vari.up_pic_path.split("/file")[0], vari.URL)+"'/></form></body></html>");
		
		return null;
	}
	
	public String pic(){
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpServletResponse resp=ServletActionContext.getResponse();
		Variable vari=new Variable();
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, vari.up_pic_path,1048576);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File str =mr.getFile("imgFile");
		String str0="";
		scale(str.getAbsolutePath(), str0=vari.up_pic_path+"/"+new Date().getTime()+".jpg");
		ReqResp.writer(resp, "<html><body  marginheight='0px' marginwidth='0px'><img height='50px' width='90px' src='"+str0.replace("\\", "/").replace(vari.up_pic_path.split("/file")[0], vari.URL)+"'/></body></html>");
		
		return null;
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
