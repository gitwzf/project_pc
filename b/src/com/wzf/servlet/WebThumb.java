package com.wzf.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.commsen.jwebthumb.WebThumbException;
import com.commsen.jwebthumb.WebThumbFetchRequest;
import com.commsen.jwebthumb.WebThumbJob;
import com.commsen.jwebthumb.WebThumbRequest;
import com.commsen.jwebthumb.WebThumbService;
import com.commsen.jwebthumb.WebThumbFetchRequest.Size;
import com.commsen.jwebthumb.WebThumbRequest.OutputType;

public class WebThumb extends TimerTask{
	static String id;
	static Timer timer=new Timer();
	public static void main(String[] args) throws Exception {
	 	getFetch();
	}
	
	public static void getPicById(){//commons.io.2.2.jar
		System.out.println("11..");
		WebThumbService webThumbService = new WebThumbService("6f6b6c1f0b8e5de839bb4522c22211d6");
	  WebThumbFetchRequest fetchRequest = new WebThumbFetchRequest(id, Size.large);
	        try {
				webThumbService.fetch(fetchRequest, new FileOutputStream(new File("d://gg2.jpg")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WebThumbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void getFetch(){//simple-xml-2.0.2.jar
		
			WebThumbService webThumbService = new WebThumbService("6f6b6c1f0b8e5de839bb4522c22211d6");
			
			WebThumbRequest request = new WebThumbRequest("http://www.baidu.com/", OutputType.jpg);
	        WebThumbJob job = null;
	        try {
	                job = webThumbService.sendRequest(request);
	        } catch (WebThumbException e) {
	                // handle error appropriately 
	        }
			
	        StringBuilder sb = new StringBuilder();
	        sb.append("The server confirms a request for '").append(job.getUrl())
            .append("' was received on '").append(job.getTime())
            .append("'. Thumbnail will be ready in about ").append(job.getEstimate())//TODO WAIT TIME
            .append(" seconds! This request costs ").append(job.getCost())
            .append(" credit(s). Please use '").append(job.getId())//
            .append("' job id, to fetch the thumbnail!");
    System.out.println(sb);
    id=job.getId();
    
       
       Date do_time=new Date(System.currentTimeMillis()+30000);
       timer.schedule(new WebThumb(), do_time);
       
	}

	@Override
	public void run() {
		getPicById();
		timer.cancel();
	       System.gc();
	}
	

}
