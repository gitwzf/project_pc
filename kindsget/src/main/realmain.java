package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;



import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
public class realmain {
	private static final String String = null;
	static String[] keyarray=new String[]{"AuthCookie","LoginEmail","GCEmail","GCUserID","Token","userBaihe_channel"},
	  keyvalue=new String[]{"","","","","",""};
	
	public static void main(String args[]) throws IOException, JDOMException{
	//	  Music("明月几时有", "");      //音乐查询
//		News("李克强");                        // 新闻
//		Weather("101050101");                  //天气
//		Translate("apple");                     //英汉互译
//	TrainLook("SOH","NJH","2-16");                        //火车班次
//		 try {
//			Stanearby("39.916979519873,116.41004950566","美食");
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}                                 //邻近公交站点    汽车站  火车站    方向及距离
//		nearby("公交站","39.915,116.400","20000");
//		login("zhongweng.hao@gmail.com","Qq674380785");
		getJDLPrice();
	  } 
		
	/**
	 *获取多点佳得乐价格 
	 */
	public static void getJDLPrice() throws IOException, JDOMException{
		//原网页
//		String create_url = "http://wx.dmall.com/#details:id=2-403-100133585";  	
//		String cookie="wxLoc=120.187427%2C30.235013;wxLocName=%u676D%u5DDE%u6C7D%u8F66%u5357%u7AD9;wxShops=2-0-403%2C47-0-10384;";
	    String url="http://gw.wx.dmall.com/ware/get?param=%7B%22skuId%22%3A%22100133585%22%2C%22venderId%22%3A%222%22%2C%22erpStoreId%22%3A%22403%22%7D&token=&source=2&tempid=C70B15E3653000026D7532C712751D3E1461508411987&_=1461509055642&callback=jsonp1";
		StringBuffer strb = httpRequest1(url, "GET",""); 
		String str=strb.toString();
		if(str!=null&&str.indexOf("佳得乐")>0)  //起：佳得乐
			str=str.split("佳得乐")[1].split("sell")[0];//止：sell
	   if(str.matches("[\\s\\S]+380[\\s\\S]+380[\\s\\S]+")){}//正则	
	   else{
		System.out.println(str);
	   }
	}
	
	/**
	 * baihe  积分
	 * @throws JDOMException 
	 * @throws IOException 
	 * */
   public static void score(){
	   String url = "http://passport.baihe.com/login.jsp?ReturnUrl=dispatch.jsp&txtLoginEMail=wonderfeng12%40163.com&txtLoginPwd=987654321a";  
	   String JSESSIONID=null,AuthCookie = null;
	   try {
			HttpClient client = new HttpClient();
			PostMethod postMethod = new PostMethod(url);
			client.executeMethod(postMethod);
			Cookie[] cookies = client.getState().getCookies();
			String returnStr = postMethod.getResponseBodyAsString();
			
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("JSESSIONID")) {
					JSESSIONID = cookies[i].getValue();
				} else if (cookies[i].getName().equals("AuthCookie")) {//AuthCookie LoginEmail GCEmail GCUserID Token userBaihe_channel
					AuthCookie = cookies[i].getValue();
				}
			}
			System.out.println(JSESSIONID+","+AuthCookie);
			FileReader fr=new FileReader("d://log/kindsget/Web.log");
			  BufferedReader br=new BufferedReader(fr);
			  String ss=null;
			  
			  String time0=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()-1000*10);
			  String year_month=new SimpleDateFormat("yyyy-MM").format(new Date());
			  while((ss=br.readLine())!=null){
					try {                                                 //Unparseable date  2014-10-24 20:24:13？？？
					if(ss.startsWith(year_month)&&new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ss.split(",")[0])
							.after(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time0)))
						for(int i=0;i<keyarray.length;i++){
							String key=keyarray[i];
							if(ss.indexOf(key)>0)
								keyvalue[i]=ss.substring(ss.indexOf(key)+key.length()+1, ss.indexOf("; $Path="));
							}
					
						} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			System.out.println(returnStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
   }

   /**
    * 获取绍兴e网女会员信息
    **/
   public static  void getInfo() throws IOException, JDOMException {  
	   FileOutputStream fos=new FileOutputStream("C:\\Users\\WZF\\Desktop\\id.txt");
	   int j=0;
	   for(int i=9175;i<9999;i++){
	    String create_url = "http://love.e0575.com/info.php?id="+i;  		         
	    StringBuffer stringObject = httpRequest1(create_url, "GET","");  
	    String str=stringObject.toString();
	    //判断是否符合规则
	   System.out.println(str);
	   if(str.indexOf("信息还未审核通过，请耐心等待！")<=0){
	    if(str.indexOf("此信息不存在！")<=0){
	    String str_web=str.split("#相亲资料#")[1].split("如何相亲？")[0];
	    if(str_web.matches("[\\s\\S]+性别：女[\\s\\S]+年龄：2[3-8][\\s\\S]+身高：1[5-6][0-9][\\s\\S]+身高要求[\\s\\S]+16[0-5] 厘米左右[\\s\\S]+")
	    		||(str_web.matches("[\\s\\S]+性别：女[\\s\\S]+年龄：2[3-8][\\s\\S]+") && str_web.indexOf("身高要求")<=0)){
	        System.out.println(i);
	        j++;
	        fos.write((i+"\r\n").getBytes());
	    }
	    else 
	    	System.out.println(i+"不符合");
	    }else
	    	System.out.println(i+"信息不存在");
	   }else
		   System.out.println(i+"信息还未审核通过，请耐心等待！");
	    
	   }
	   fos.close();
       System.out.println("有效ID共有:"+j);
	}
   
	 public static  void get() {  
		    try {         
				String url = "https://open.t.qq.com/cgi-bin/oauth2/authorize?client_id=APP_KEY&response_type=code&redirect_uri=REDIRECT_URL";
				url=url.replace("APP_KEY", "801472785").replace("REDIRECT_URL", "http://xiaowangzi.touclick.com/weibo0");
				HttpClient client = new HttpClient();
				Protocol myhttps = new Protocol("https",
						new MySSLProtocolSocketFactory(), 443);
				Protocol.registerProtocol("https", myhttps);
				GetMethod getMethod = new GetMethod(url);
				client.executeMethod(getMethod);
				String returnStr = getMethod.getResponseBodyAsString();
				System.out.println("return="+returnStr);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	
	
	
	 public static  void getYzm(String phone) throws IOException, JDOMException {  
		    String create_url = "http://115.236.173.85/sendMsg/?type=1&mp={0}&bn=cgx&nr={1}&fix={2}";  		         
		    String newsurl = create_url.replace("{0}", phone).replace("{1}", "12543").replace("{2}",URLEncoder.encode("书画社","utf-8"));  
		    StringBuffer stringObject = httpRequest2(newsurl, "GET","");  
		  System.out.println(newsurl);

		}
	 
	 public static  void getPic() throws IOException, JDOMException {  
//		    String create_url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";  
//		    StringBuffer stringObject = httpRequest2(create_url, "GET","");  
		  System.out.println("111");
		    URL url = new URL("http://xiaowangzi.touclick.com/upload/20131222/a.zip");
			File outFile = new File("d://b.zip");
			OutputStream os = new FileOutputStream(outFile);
			InputStream is = url.openStream();
			byte[] buff = new byte[1024];
			while(true) {//1mb 1mb的读
				int readed = is.read(buff);//字节数吧
				if(readed == -1) {
					break;
				}
				byte[] temp = new byte[readed];
				System.arraycopy(buff, 0, temp, 0, readed);
				os.write(temp);
			}
			is.close(); 
             os.close();
		  
		    }
		  

		
	public static void refresh(){
//		try {
//			//content=URLEncoder.encode(content, "utf-8");
//		} catch (UnsupportedEncodingException e1) {
//			
//			e1.printStackTrace();
//		}   https://open.t.qq.com/cgi-bin/oauth2/access_token?client_id=APP_KEY&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
		String url ="https://open.t.qq.com/cgi-bin/oauth2/access_token";
		HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https",
				new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		PostMethod postMethod = new PostMethod(url);
		postMethod.setParameter("client_id", "801472784");
		postMethod.setParameter("grant_type", "refresh_token");
		postMethod.setParameter("refresh_token", "7f011d384aa1bd84a971239b9327c40d");
		
			//postMethod.setRequestHeader("Content-Type", "text\\/xml; charset=utf-8");//设置中文
	
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
	public static boolean login(String username, String pwd) {
		try {           //https://mp.weixin.qq.com/cgi-bin/home?t=home/index&lang=zh_CN&token=1588928530
			String url = "https://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN&f=json&pwd=" + md5(pwd) + "&username=" + username;
			HttpClient client = new HttpClient();
			Protocol myhttps = new Protocol("https",
					new MySSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);
			PostMethod getMethod = new PostMethod(url);
		getMethod.setRequestHeader("Referer","https://mp.weixin.qq.com/");
			getMethod.setRequestHeader("Host","mp.weixin.qq.com");
			client.executeMethod(getMethod);
			Cookie[] cookies = client.getState().getCookies();
			String returnStr = getMethod.getResponseBodyAsString();
			System.out.println(returnStr);
			System.out.println("token="+returnStr.split("token=")[1].split("\"")[0]);
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("slave_user")) {
					System.out.println("slave_user="+cookies[i].getValue());
				} else if (cookies[i].getName().equals("slave_sid")) {
			System.out.println("slave_sid="+cookies[i].getValue());
				}
			}
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	 public static  void Music(String title, String name) throws IOException, JDOMException {  
		    String menu_create_url = "http://box.zhangmen.baidu.com/x?op=12&count=1&title=song$$name$$$$ ";  
			 
		    String musicurl = menu_create_url.replace("song", title).replace("name", name);  
	
		    StringBuffer buffer = httpRequest(musicurl, "POST","");  
		  //  System.out.println(buffer);
		    String xmlStr = buffer.toString();  
            StringReader sr = new StringReader(xmlStr);  
            InputSource is = new InputSource(sr);  
            Document documentObject= (new SAXBuilder()).build(is); 		  
		    if (null != documentObject) {  
		     	Element root=documentObject.getRootElement();
		    	List<Element>list=root.getChildren();
		    	for(Element t:list)
		    		System.out.println(t.getChildText("decode"));
		    		if(!"0".equals(root.getChildText("count"))){  //有这歌
		    	//	System.out.println("url="+root.getChild("url"));
		    		Element url=root.getChild("url");
		    		String str=url.getChildText("encode");
		    		System.out.println("音乐路径："+str.substring(0, str.lastIndexOf("/")+1)+url.getChildText("decode"));
		    		System.out.println("小路径："+url.getChildText("decode"));
		    	//	System.out.println("durl="+root.getChildText("durl"));
		    		}
		    	}
		    else System.out.println("None");
		} 
	 
	 public static  void News(String title) throws IOException, JDOMException {  
		    String create_url = "http://news.baidu.com/ns?word=title&tn=newsfcu&from=news&cl=2&rn=3&ct=0";  		         
		    String newsurl = create_url.replace("title", title);  
		    StringBuffer stringObject = httpRequest2(newsurl, "GET","");  
		  
		    String firstspilt[]=stringObject.toString().split("<div");
//		    System.out.println(firstspilt[1]);
		    String secondsplit[]=firstspilt[2].split("<a href=\"");
		    for(int i=1;i<4;i++){
		    	int firTar=secondsplit[i].indexOf("\" target=\"_blank\">");
		    	int lasTar=secondsplit[i].lastIndexOf("\" target=\"_blank\">");
		    	int firA=secondsplit[i].indexOf("</a>");
		    	System.out.println("第"+i+"个新闻");
		    	System.out.println(secondsplit[i].substring(0, firTar));
		    	System.out.println(secondsplit[i].substring(lasTar+18, firA)+"\n");
		    }
		  

		}
	 public static  void Weather(String citycode) throws IOException, JDOMException {  
		 JSONObject jsonObject = null; 
		 StringBuffer buffer = new StringBuffer(); 
		    String create_url = "http://m.weather.com.cn/data/citycode.html";  
			 
		    String weaurl = create_url.replace("citycode", citycode);  
	
		    buffer = httpRequest1(weaurl, "POST","");  
		       jsonObject=JSONObject.fromObject(buffer.toString());
		       JSONObject jsonObject0=jsonObject.getJSONObject("weatherinfo");
		       System.out.println(jsonObject0.getString("date_y")+" "+jsonObject0.getString("week"));
		       System.out.println("今日："+jsonObject0.getString("temp1")+" "+jsonObject0.getString("weather1")+" "+jsonObject0.getString("wind1"));
		       System.out.println("温馨提示："+jsonObject0.getString("index_d"));
		       System.out.println("明日："+jsonObject0.getString("temp1")+" "+jsonObject0.getString("weather2")+" "+jsonObject0.getString("wind2"));
		       System.out.println("后天："+jsonObject0.getString("temp1")+" "+jsonObject0.getString("weather3")+" "+jsonObject0.getString("wind3"));
		   
		} 
	 public static  void Translate(String word) throws IOException, JDOMException {  
		 JSONObject jsonObject = null; 
		 boolean flag;
		 StringBuffer buffer = new StringBuffer();  
		    String create_url = "http://fanyi.youdao.com/openapi.do?keyfrom=weixin-app&key=767258678&type=data&doctype=json&version=1.1&q=";  
		  //  String weaurl = create_url.replace("word", word);  
	if(word.matches("[\\u4E00-\\u9FA5]+"))
		{buffer = httpRequest1(create_url+URLEncoder.encode(word,"utf-8"), "GET",""); flag=true;}
	else     {buffer = httpRequest1(create_url+word, "GET",""); flag=false;}
		     //  System.out.println("json:"+JSONObject.fromObject(buffer.toString()));
	              jsonObject=JSONObject.fromObject(buffer.toString());
	              if(!"0".equals(jsonObject.getString("errorCode")))
	            	  System.out.println("找不到这个词！");
	              else{
	              System.out.println("翻译"+jsonObject.getString("translation"));
	              JSONObject jsonObject0=jsonObject.getJSONObject("basic");
	              if(jsonObject0.containsKey("phonetic"))
	                   if(flag)System.out.println("读音"+new String(jsonObject0.getString("phonetic").getBytes(),"gbk"));
	                   else System.out.println("音标"+new String(jsonObject0.getString("phonetic").getBytes(),"utf-8"));
	              if(jsonObject0.containsKey("explains"))
	               System.out.println("译作"+jsonObject0.getString("explains"));
	               System.out.println("网络释义");
//	               System.out.println(jsonObject1.getString("value")+" "+jsonObject1.getString("key"));
	               for(int i=0;i<jsonObject.getJSONArray("web").size();i++){
	                 String str=jsonObject.getJSONArray("web").getString(i);
	                 JSONObject jsonObject1=JSONObject.fromObject(str);
	               System.out.println(jsonObject1.getString("value")+" "+jsonObject1.getString("key"));
	               }
	               }
	                    
	              }
	             
	 public static  void TrainLook(String citycode1,String citycode2,String looktime) throws IOException, JDOMException {  
//		    String create_url = "http://dynamic.12306.cn/otsquery/query/queryRemanentTicketAction.do?method=queryststrainall";  
//			 String str0="date=2014-looktime&fromstation=VNP&tostation=NVH&starttime=00:00--24:00";
//		    String weaurl = str0.replace("looktime", looktime).replace("citycode1", citycode1).replace("citycode2", citycode2);  
//	
//		    StringBuffer buffer = httpRequest(create_url, "POST",weaurl);  
//		    JSONObject jsonObject = JSONObject.fromObject("{a:"+buffer.toString()+"}"); 
//		     for(int i=0;i<jsonObject.getJSONArray("a").size();i++){
//		    	 String str=jsonObject.getJSONArray("a").getString(i);
//                 JSONObject jsonObject0=JSONObject.fromObject(str);
//		    	 System.out.println("班次："+jsonObject0.getString("id")+"出发站("+jsonObject0.getString("start_time")+")："+jsonObject0.getString("start_station_name")+"  目的站("+jsonObject0.getString("end_time")+")："+jsonObject0.getString("end_station_name")
//		    			 +"  火车编号："+jsonObject0.getString("value"));
		    	 
		    	 
//		     }
		    try {        
				String url = "http://dynamic.12306.cn/otsquery/query/queryRemanentTicketAction.do?method=queryststrainall";
				HttpClient client = new HttpClient();
				Protocol myhttps = new Protocol("https",
						new MySSLProtocolSocketFactory(), 443);
				Protocol.registerProtocol("https", myhttps);
				PostMethod getMethod = new PostMethod(url);
		//	getMethod.setRequestHeader("Referer","https://mp.weixin.qq.com/");
		//		getMethod.setRequestHeader("Host","mp.weixin.qq.com");
//				String str0="date=2014-looktime&fromstation=VNP&tostation=NVH&starttime=00:00--24:00";
//			    String weaurl = str0.replace("looktime", looktime).replace("citycode1", citycode1).replace("citycode2", citycode2);  
			    getMethod.setParameter("date", "2014-"+looktime);
			    getMethod.setParameter("fromstation", citycode1);
			    getMethod.setParameter("tostation", citycode2);
			    getMethod.setParameter("starttime", "00:00--24:00");
			    java.lang.String buffer  = getMethod.getResponseBodyAsString();
				
				client.executeMethod(getMethod);
				
				 System.out.println(buffer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		  
		} 
	 public static String md5(String pwd) throws Exception {
			ScriptEngineManager m = new ScriptEngineManager();
			ScriptEngine engine = m.getEngineByName("javascript");
			InputStreamReader in = new InputStreamReader(new realmain().getClass()
					.getResourceAsStream("/main/txmd5.js"));
			BufferedReader read = new BufferedReader(in);
			engine.eval(read);
			Invocable invocableEngine = (Invocable) engine;
			return invocableEngine.invokeFunction("md5", pwd).toString();
		}
	 public static String caculateSN(String uri, String sk) throws UnsupportedEncodingException{   
		    String tmpStr = new MySecurity().encode(URLEncoder.encode((uri+sk),"utf-8"),
					MySecurity.MD5);
		    return tmpStr;
		 }  
	 public static  void Stanearby(String location,String tags) throws Exception {  
		    caculateSN(String,String);
		    String create_url = "http://api.map.baidu.com/geosearch/v2/nearby?q=&location=Location&radius=1000&geotable_id=30828&ak=061c85af9c87f9d76fc9178d18179c9b&tags=Tags";  
		    String ss=md5(URLEncoder.encode("/geosearch/v2/nearby"+"C32b416082d2164493fd27446e572242","iso8859-1"));
//		   ss = new MySecurity().encode(URLEncoder.encode(("/geosearch/v2/nearby"+"C32b416082d2164493fd27446e572242"),"utf-8"),
//					MySecurity.MD5);
		    System.out.println("ss="+ss);
		    String newsurl = create_url.replace("Location", location).replace("Tags", tags).replace("SN",ss);  
		    StringBuffer buffer = httpRequest1(newsurl, "GET","");  
		    JSONObject jsonObject=JSONObject.fromObject(buffer.toString());
                   System.out.println(jsonObject);
		    }
	 
	 public static void nearby(String query,String location,String r) throws IOException, JDOMException{
		 if("".equals(r))r="1000";
		 String url="http://api.map.baidu.com/place/v2/search?&query=QUERY&location=LOCATION&radius=RADIUS&output=json&ak=AC39610b83a798c57469d1fc0a50cdb5";
		url=url.replace("QUERY", query).replace("LOCATION", location).replace("RADIUS", r);
		 StringBuffer buffer = httpRequest1(url, "GET","");  
		    JSONObject jsonObject=JSONObject.fromObject(buffer.toString());
               
                JSONArray json0=jsonObject.getJSONArray("results");
                //JSONArray json1=json0.getJSONArray(key)
		 for(int i=0;i<json0.size();i++){
			 String str;
			 str="名称："+json0.getJSONObject(i).getString("name")+"\n";
			 str+=json0.getJSONObject(i).getString("address")+"\n";
			 if(json0.getJSONObject(i).containsKey("telephone")) str+=json0.getJSONObject(i).getString("telephone")+"\n";
			 String str0=json0.getJSONObject(i).getString("uid");
			  str+="详细信息：\n"+ nearbyInf(str0)+"\n";
               System.out.println(str);}
	 }
	   public static String nearbyInf(String str) throws IOException, JDOMException{
		   String url="http://api.map.baidu.com/place/v2/detail?uid="+str+"&ak=AC39610b83a798c57469d1fc0a50cdb5&output=json&scope=2";
		   StringBuffer buffer = httpRequest1(url, "GET","");  
		    JSONObject jsonObject=JSONObject.fromObject(buffer.toString());
		    JSONObject json0=jsonObject.getJSONObject("result");
		    JSONObject json=json0.getJSONObject("detail_info");
		  //  System.out.println("json="+json);
		    String str0="";
		   if(json.containsKey("distance")) str0+="距离："+json.getString("distance")+"\n";
		   if(json.containsKey("type")) str0+="类别："+json.getString("type")+"\n";
		   if(json.containsKey("tag")) str0+="标签："+json.getString("tag")+"\n";
		   if(json.containsKey("price")) str0+="价格："+json.getString("price")+"\n";
		   if(json.containsKey("shop_hours"))str0+="营业时间："+json.getString("shop_hours")+"\n";
		   if(json.containsKey("overall_rating")) str0+="总评："+json.getString("overall_rating")+"\n";
		   if(json.containsKey("taste_rating")) str0+="口味："+json.getString("taste_rating")+"\n";
		   if(json.containsKey("service_rating")) str0+="服务："+json.getString("service_rating")+"\n";
		   if(json.containsKey("environment_rating")) str0+="环境："+json.getString("environment_rating")+"\n";
		   if(json.containsKey("facility_rating")) str0+="星级："+json.getString("facility_rating")+"\n";
		   if(json.containsKey("hygiene_rating")) str0+="卫生："+json.getString("hygiene_rating")+"\n";
		   if(json.containsKey("technology_rating")) str0+="技术："+json.getString("technology_rating")+"\n";
		   if(json.containsKey("image_num")) str0+="图片数："+json.getString("image_num")+"\n";
		   if(json.containsKey("groupon_num")) str0+="团购数："+json.getString("groupon_num")+"\n";
		   if(json.containsKey("discount_num")) str0+="优惠数："+json.getString("discount_num")+"\n";
		   if(json.containsKey("comment_num")) str0+="评论数："+json.getString("comment_num")+"\n";
		   if(json.containsKey("favorite_num")) str0+="收藏数："+json.getString("favorite_num")+"\n";
		   if(json.containsKey("checkin_num")) str0+="签到数："+json.getString("checkin_num")+"\n";
		   return str0;
	   }
	 
	//xml   post?
	 public static  StringBuffer httpRequest(String requestUrl, String requestMethod, String outputStr) throws IOException, JDOMException {  
	        StringBuffer buffer = new StringBuffer();  
	          URL geturl = new URL(requestUrl);  
	            HttpURLConnection httpUrlConn = (HttpURLConnection) geturl.openConnection();  
	            httpUrlConn.setDoOutput(true);              //可有可无
	            httpUrlConn.setDoInput(true);  
	            httpUrlConn.setUseCaches(false);  
	            // 设置请求方式（GET/POST）  
	            httpUrlConn.setRequestMethod(requestMethod);  //错误405
	            if ("GET".equalsIgnoreCase(requestMethod))  
	                httpUrlConn.connect();  
	            // 当有数据需要提交时  
	            if (null != outputStr) {                     
	                OutputStream outputStream = httpUrlConn.getOutputStream();  
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
	
	        return buffer;  
	    } 
	 
	 /**
	  * 字符流（带cookie）万能 get
	  */
	 public static  StringBuffer httpRequest0(String requestUrl, String requestMethod, String outputStr,String cookie) throws IOException, JDOMException {  
	        StringBuffer buffer = new StringBuffer();  
	//          URL url = new URL(requestUrl);    
      URL getUrl = new URL(requestUrl);
      // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
      // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
      HttpURLConnection connection = (HttpURLConnection) getUrl
              .openConnection();
      // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
      // 服务器
      if(cookie!=null && cookie.indexOf("=")>0)
      connection.setRequestProperty("Cookie", cookie);
      
      connection.connect();
      // 取得输入流，并使用Reader读取
      BufferedReader reader = new BufferedReader(new InputStreamReader(
              connection.getInputStream(),"utf-8"));
      String lines;
      while ((lines = reader.readLine()) != null) {
          buffer.append(lines);
      }
      reader.close();
      // 断开连接
      connection.disconnect();
	return buffer;
	    } 

	 /**
	  * 字符流（不带cookie） get
	  */
	 public static  StringBuffer httpRequest1(String requestUrl, String requestMethod, String outputStr) throws IOException, JDOMException {  
	        StringBuffer buffer = new StringBuffer();  
	//          URL url = new URL(requestUrl);    
      URL getUrl = new URL(requestUrl);
      // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
      // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
      HttpURLConnection connection = (HttpURLConnection) getUrl
              .openConnection();
      // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
      // 服务器
      connection.connect();
      // 取得输入流，并使用Reader读取
      BufferedReader reader = new BufferedReader(new InputStreamReader(
              connection.getInputStream(),"utf-8"));
      String lines;
      while ((lines = reader.readLine()) != null) {
          buffer.append(lines);
      }
      reader.close();
      // 断开连接
      connection.disconnect();
	return buffer;
	    } 
	 public static  StringBuffer httpRequest2(String requestUrl, String requestMethod, String outputStr) throws IOException, JDOMException {  
	        StringBuffer buffer = new StringBuffer();  
 URL getUrl = new URL(requestUrl);
   HttpURLConnection connection = (HttpURLConnection) getUrl
           .openConnection();
  connection.connect();
   BufferedReader reader = new BufferedReader(new InputStreamReader(
           connection.getInputStream(),"gb2312"));
   String lines;
   while ((lines = reader.readLine()) != null) {
       buffer.append(lines);
   }
   reader.close();
   connection.disconnect();
	return buffer;
	    } 
	 
//	 public static  StringBuffer httpRequest3(String requestUrl, String requestMethod, String outputStr) throws IOException, JDOMException {  
//	        StringBuffer buffer = new StringBuffer();  
//URL getUrl = new URL(requestUrl);
//HttpURLConnection connection = (HttpURLConnection) getUrl
//        .openConnection();
//connection.connect();
//
//BufferedReader reader = new BufferedReader(new InputStreamReader(
//        connection.getInputStream(),"gb2312"));
//
//FileInputStream fin = new FileInputStream(new File("d://2.jpg"));
//File f=new File("d://3.jpg");
//FileWriter fw=new FileWriter(f);
//
//InputStreamReader ir=new InputStreamReader(connection.getInputStream());
//FileInputStream fi=new FileInputStream(ir.getEncoding());
//fw.w.write(fi);
//byte[] bytes  = ir.
//fin.read(bytes);
//fin.close();
//	    } 
	
}