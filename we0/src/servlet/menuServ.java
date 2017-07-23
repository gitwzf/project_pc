package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.log4j.Logger;



import security.MySSLProtocolSocketFactory;

import model.Button;
import model.Buttonclick;
import model.Buttonview;
import model.Manageruser;
import model.Menu;
import model.Ybutton;
import pubvari.Variable;

public class menuServ extends HttpServlet{
    private static String[] name=new String[3];
    Logger log=Logger.getLogger("logfile");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	//	getAccestoken();
	Variable.access_token=getAccessToken(Variable.appid,Variable.appsecret);
	log.info("menuServ  appid:"+Variable.appid+"  appsecret:"+Variable.appsecret+" access_token:"+Variable.access_token);
	//	获取的菜单放入sesson
	req.getSession().setAttribute("menu",menuToArray(getMenu(Variable.access_token)));
	req.getSession().setAttribute("name", name);
	//	跳转
		req.getRequestDispatcher("./menu.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String[] name=req.getParameterValues("name");
		String[] name0=req.getParameterValues("name0");
		String[] type=req.getParameterValues("type");
		String[] key=req.getParameterValues("key");
		String str = "";
		Manageruser u=(Manageruser) req.getSession().getAttribute("manager");
		if(u!=null)
		if(name!=null&&type!=null&&key!=null){
//			Buttonclick x1=new Buttonclick("V1001_TODAY_MUSIC","今日歌曲");
//			Buttonclick x2=new Buttonclick("V1001_TODAY_SINGER","歌手简介");
//			
//			Buttonclick x3=new Buttonclick("V1001_GOOD","赞一下我们");
//			Buttonview y1=new Buttonview("http://www.soso.com/","搜索");
//			Buttonview y2=new Buttonview("http://v.qq.com/","视频");
//			Menu m=new Menu();
//			m.setButton(new Button[]{x1,x2,new Ybutton("菜单",new Button[]{x3,y1,y2})});
		
			Button[] b=null,b0=new Button[3];
			for(int i=0;i<3;i++){
				b=new Button[5];
				int k=0;
				for(int j=5*i;j<5*(i+1);j++){
					if(!key[j].equals("")&&!name0[j].equals("")){
					if(type[j].equals("view"))
					b[k++]=new Buttonview(key[j],name0[j]);
					else if(type[j].equals("click"))
						b[k++]=new  Buttonclick(key[j],name0[j]);
				}
				}
				Button[] bb=new Button[k];
				System.arraycopy(b, 0, bb, 0, k);
				b0[i]=(b[1]==null?b[0]:new Ybutton(name[i],bb));
			}
			Menu m=new Menu();
			m.setButton(b0);
		//	System.out.println("保存菜单："+JSONObject.fromObject(m));
			 int result = sentMenu(JSONObject.fromObject(m).toString());
		        
	            // 判断菜单创建结果  
	            if (0 == result)  
	                log.info(str="菜单创建成功！");  
	            else  
	                log.info(str=("菜单创建失败，错误码：" + result));  
	            req.setAttribute("info",URLEncoder.encode(str,"utf-8"));
		}
		
		
//		获取的菜单放入sesson
		req.getSession().setAttribute("menu",menuToArray(getMenu(Variable.access_token)));
		req.getSession().setAttribute("name", name);
		//	跳转
			req.getRequestDispatcher("./menu.jsp").forward(req, resp);
		
		
	}
	
	public String getAccessToken(String appid,String appsecret){
		if(appid==null||appsecret==null)return null;
		String url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		url=url.replace("APPID", appid).replace("APPSECRET", appsecret);
    	HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https",
				new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		PostMethod postMethod = new PostMethod(url);
			try {
				client.executeMethod(postMethod);
			
			String str = postMethod.getResponseBodyAsString();
			JSONObject json=JSONObject.fromObject(str);
			log.info("menuServ get Token:"+json.get("access_token"));
			str=(String) json.get("access_token");
			return str;
			} catch (HttpException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
    	return null;
	}
	
	public String getMenu(String access_token){
		if(access_token==null)return null;
		String url ="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
		url=url.replace("ACCESS_TOKEN", access_token);
    	HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https",
				new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		GetMethod postMethod = new GetMethod(url);
		 postMethod.setRequestHeader("Content-Type", "text/html;charset=UTF-8");
			try {
				client.executeMethod(postMethod);
				String str= postMethod.getResponseBodyAsString();
				return new String(str.getBytes("iso8859-1"),"utf-8");
			} catch (HttpException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
    	return null;
		
	}
	public ArrayList<Button[]> menuToArray(String str){
		JSONObject josn=JSONObject.fromObject(str);
	//	System.out.println("josn="+josn);
		JSONArray jsona1=josn.getJSONObject("menu").getJSONArray("button");
		ArrayList<Button[]> array=new ArrayList<Button[]>();
		Button[] button=null;int j=0,k=0;
		for(int i=0;i<jsona1.size();i++){
			button=new Button[5];j=0;
			JSONObject json=JSONObject.fromObject(jsona1.get(i));
			 name[k++]=json.getString("name");
			if(json.containsKey("type")){
			  if(json.getString("type").equals("click")){
				  Buttonclick bc=(Buttonclick)JSONObject.toBean((JSONObject) jsona1.get(i), Buttonclick.class);
				  button[j++]=bc;
			  }
			  if(json.getString("type").equals("view")){
				  Buttonview bv=(Buttonview)JSONObject.toBean((JSONObject) jsona1.get(i), Buttonview.class);
				  button[j++]=bv;
			  }
			}else{
				JSONArray jsona2=JSONObject.fromObject(jsona1.get(i)).getJSONArray("sub_button");
				for(int m=0;m<jsona2.size();m++){
					JSONObject json0=JSONObject.fromObject(jsona2.get(m));	
					if(json0.containsKey("type")){
						  if(json0.getString("type").equals("click")){
							  Buttonclick bc=(Buttonclick)JSONObject.toBean((JSONObject) jsona2.get(m), Buttonclick.class);
							  button[j++]=bc;
						  }
						  if(json0.getString("type").equals("view")){
							  Buttonview bv=(Buttonview)JSONObject.toBean((JSONObject) jsona2.get(m), Buttonview.class);
							  button[j++]=bv;
						  }
						}
					
				}
			}
			  int jj=j;
				for(int n=0;n<(5-jj);n++){
					Buttonview bv=new Buttonview("", "");
				button[j++]=bv;
				
			}
				array.add(button);
		}
		return array;
	}
	public int sentMenu(String json){
		int in=0;
		String url ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		url=url.replace("ACCESS_TOKEN", Variable.access_token);
		JSONObject js=httpRequest(url, "POST", json);  
		  in=js.getInt("errcode");
    	return in;
	}
	/** 
     * 发起https请求并获取结果 
     *  
     * @param requestUrl 请求地址 
     * @param requestMethod 请求方式（GET、POST） 
     * @param outputStr 提交的数据 
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
     */  
    public JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {  
        JSONObject jsonObject = null;  
        StringBuffer buffer = new StringBuffer();  
        try {  
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化  
            TrustManager[] tm = { new x509() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
            sslContext.init(null, tm, new java.security.SecureRandom());  
            // 从上述SSLContext对象中得到SSLSocketFactory对象  
            SSLSocketFactory ssf = sslContext.getSocketFactory();  
  
            URL url = new URL(requestUrl);  
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
            httpUrlConn.setSSLSocketFactory(ssf);  
  
            httpUrlConn.setDoOutput(true);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
            // 设置请求方式（GET/POST）  
            httpUrlConn.setRequestMethod(requestMethod);  
  
            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
  
            // 当有数据需要提交时  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // 注意编码格式，防止中文乱码  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }  
  
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            jsonObject = JSONObject.fromObject(buffer.toString());  
        } catch (ConnectException ce) {  
           log.error("Weixin server connection timed out.");  
        } catch (Exception e) {  
            log.error("https request error:{}", e);  
        }  
        return jsonObject;  
    }class x509 implements X509TrustManager{
    	public void checkClientTrusted(X509Certificate[] chain, String authType)
    			throws CertificateException {
    		
    	}
    	public void checkServerTrusted(X509Certificate[] chain, String authType)
    			throws CertificateException {
    		
    	}
    	public X509Certificate[] getAcceptedIssuers() {
    		
    		return null;
    	}
    }
    
}
