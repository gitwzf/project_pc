package com.unit.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.unit.io.InputStreamUtil;
import com.unit.model.ReMethodObj;
import com.unit.pubvari.Variable;

public class HttpConnection {
	
	/**
	 * @return  ReMethodEnd
	 */
	public ReMethodObj connUrl(URL url,String method,byte[] inBytes){
		if(url==null){
			return new ReMethodObj(-1, "访问网址无法连接");
		}
		InputStream input;
			try {
				URLConnection urlConn=url.openConnection();
				urlConn.setDoInput(true);
				if(Variable.METHOD_POST.equals(method)){
					urlConn.setDoOutput(true);
					if(inBytes!=null){
					OutputStream outStream=urlConn.getOutputStream();
					outStream.write(inBytes);
					outStream.close();
					}
				}
				input=urlConn.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
				return new ReMethodObj(-1, "IO Exception:"+e.getMessage());
			}
		return new ReMethodObj(1, input);
	}
	
	public static void main(String[] args) {
		ReMethodObj re = null;
		try {
			re = new HttpConnection().connUrl(new URL("www.baidu.com"), Variable.METHOD_GET, null);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		InputStream inStream=(InputStream) re.getReAnswer();
		ReMethodObj re1=InputStreamUtil.getToStr(inStream, Variable.UTF8);
		System.out.println(re1.getReAnswer());
	}

}
