package http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;

public class Wmain {//实现 应用无论本地与否  跟 服务器 无缝连接    需要java实现 本地访问服务器，ftp  cmd   db
	static String access_token="";
	static String oauth_consumer_key="801472785";
	static String openid="";
	public static void sendmessage(String content){
		try {
			content=URLEncoder.encode(content, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			
			e1.printStackTrace();
		}
		String url ="https://open.t.qq.com/api/t/add";
		HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https",
				new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		PostMethod postMethod = new PostMethod(url);
		postMethod.setParameter("content", content);
		postMethod.setParameter("format", "xml");
		postMethod.setParameter("access_token", access_token);
		postMethod.setParameter("oauth_consumer_key",oauth_consumer_key);
		postMethod.setParameter("openid", openid);
		postMethod.setParameter("oauth_version", "2.a");
		
			postMethod.setRequestHeader("Content-Type", "text\\/xml; charset=utf-8");
	
			try {
				client.executeMethod(postMethod);
			
			String str = postMethod.getResponseBodyAsString();
			System.out.println(str);
			} catch (HttpException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
	}
	
	//获取code openid
	
	//获取access_token

}
