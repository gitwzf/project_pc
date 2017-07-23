package servlet;

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

import org.apache.log4j.Logger;

import pubvari.Variable;

import com.oreilly.servlet.MultipartRequest;


public class Upload extends HttpServlet{
	Logger log = Logger.getLogger("logfile");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Variable vari=new Variable();
		String npath=req.getSession().getServletContext().getRealPath("/")+vari.up_pic_path;
		npath=npath.replace("\\", "/").replace("//", "/");
		log.info("uppath:"+npath);
		MultipartRequest mr=new MultipartRequest(req,npath,1048576);
		File str =mr.getFile("imgFile");
		String str0="";
		Upload.scale(str.getAbsolutePath(), str0=npath+"/"+new Date().getTime()+".jpg");
		Writer out = resp.getWriter();   //img 长 宽  100%
		//System.out.println("str0="+str0.getAbsolutePath());
		log.info("picurl:"+str0.replace("//", "/").replace(npath.split("/file")[0], vari.URL0));
       out.write("<html><body  marginheight='0px' marginwidth='0px'><img height='50px' width='90px' src='"+str0.replace("//", "/").replace(npath.split("/file")[0], vari.URL0)+"'/></body></html>");
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
