package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import dbconnection.Dbconn;
import wxtry.Dbcon;
import model.Fans;
import model.Groups;
import model.UserType;
import model.WxUser;


public class WxService {
	Logger log = Logger.getLogger("logfile");
	public  static String token;
	public  static String slave_user;
	public  static String slave_sid;
	public  String sig;
    public static String[] fanId;

    public byte[] getVerifycode(String username, HttpServletRequest request) {
		try {
			String url = "https://mp.weixin.qq.com/cgi-bin/verifycode?username="
					+ username + "&r=" + System.currentTimeMillis();
			HttpClient client = new HttpClient();
			Protocol myhttps = new Protocol("https",
					new MySSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);
			GetMethod getMethod = new GetMethod(url);
			client.executeMethod(getMethod);
			Cookie[] cookies = client.getState().getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("sig")) {
					sig = cookies[i].getValue();
					request.getSession().setAttribute("sig", sig);
				}
			}
			return getMethod.getResponseBody();
		} catch (Exception e) {
			return null;
		}
	}
	public String md5(String pwd) throws Exception {
		ScriptEngineManager m = new ScriptEngineManager();
		ScriptEngine engine = m.getEngineByName("javascript");
		InputStreamReader in = new InputStreamReader(new WxService().getClass()
				.getResourceAsStream("/common/txmd5.js"));
		BufferedReader read = new BufferedReader(in);
		engine.eval(read);
		Invocable invocableEngine = (Invocable) engine;
		return invocableEngine.invokeFunction("md5", pwd).toString();
	}

	public boolean login(String username, String pwd) {
		try {
			String url = "https://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN&f=json&pwd=" + md5(pwd) + "&username=" + username;
			HttpClient client = new HttpClient();
			Protocol myhttps = new Protocol("https",
					new MySSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);
			log.info("444");
			PostMethod getMethod = new PostMethod(url);
		getMethod.setRequestHeader("Referer","https://mp.weixin.qq.com/");
			getMethod.setRequestHeader("Host","mp.weixin.qq.com");
			client.executeMethod(getMethod);
			Cookie[] cookies = client.getState().getCookies();
			String returnStr = getMethod.getResponseBodyAsString();
			token = returnStr.split("token=")[1].split("\"")[0];
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("slave_user")) {
					slave_user = cookies[i].getValue();
				} else if (cookies[i].getName().equals("slave_sid")) {
					slave_sid = cookies[i].getValue();
				}
			}
			
			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}
	public String[] getApiToken() throws HttpException, IOException{
		String url ="https://mp.weixin.qq.com/cgi-bin/advanced?action=dev&t=advanced/dev&token="+token+"&lang=zh_CN";
	HttpClient client = new HttpClient();
	Protocol myhttps = new Protocol("https",
			new MySSLProtocolSocketFactory(), 443);
	Protocol.registerProtocol("https", myhttps);
	GetMethod getMethod = new GetMethod(url);
	getMethod.setRequestHeader("Cookie", "slave_user=" + slave_user
			+ ";slave_sid=" + slave_sid);
	client.executeMethod(getMethod);
	String returnStr = getMethod.getResponseBodyAsString();
		String str="",str0;
		str=returnStr.split("URL</label>")[1].split("接口权限表")[0];
		str0=str.substring(str.indexOf("http"),str.indexOf("/div")-1);
		str0+="  "+str.substring(str.lastIndexOf("frm_input_box\">")+15,str.lastIndexOf("</div></div>"));
		return str0.split("  ");
	}
	public boolean setApiToken(String curl,String ctoken) throws HttpException, IOException{
		String url ="https://mp.weixin.qq.com/cgi-bin/callbackprofile?t=ajax-response&token="+token+"&lang=zh_CN";
	HttpClient client = new HttpClient();
	Protocol myhttps = new Protocol("https",
			new MySSLProtocolSocketFactory(), 443);
	Protocol.registerProtocol("https", myhttps);
	PostMethod postMethod = new PostMethod(url);
	postMethod.setParameter("callback_token", ctoken);
	postMethod.setParameter("url", curl);
		postMethod.setRequestHeader("Cookie", "slave_user=" + slave_user
				+ ";slave_sid=" + slave_sid);
	//	postMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
		postMethod.setRequestHeader(
						"Referer",
						"https://mp.weixin.qq.com/cgi-bin/advanced?action=interface&t=advanced/interface&token="+token+"&lang=zh_CN"		
		);
		client.executeMethod(postMethod);
		String str = postMethod.getResponseBodyAsString();
		log.info("str="+str);
		if((str.trim()).startsWith("{\"ret\":\"0\", \"msg\":\""))return true;
		else return false;
	}
	
	public ArrayList<UserType> getUserTypeList() {
		ArrayList<UserType> userTypeList = new ArrayList<UserType>();
		try {
			String url = //"https://mp.weixin.qq.com/cgi-bin/contactmanagepage?t=wxm-friend&token="+ token+ "&lang=zh_CN&pagesize=20&pageidx=0&type=0&groupid=0";
		              "https://mp.weixin.qq.com/cgi-bin/contactmanage?t=user/index&token="+token+"&lang=zh_CN&pagesize=20&pageidx=0&type=0&groupid=0";
			HttpClient client = new HttpClient();
			Protocol myhttps = new Protocol("https",
					new MySSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);
			GetMethod getMethod = new GetMethod(url);
			getMethod.setRequestHeader("Cookie", "slave_user=" + slave_user
					+ ";slave_sid=" + slave_sid);
			client.executeMethod(getMethod);
			String returnStr = getMethod.getResponseBodyAsString();
			String fzxx = "{\"message\":["
				+ returnStr.split("cgiData=")[1].split(".contacts,")[0]
				+ "}]}";
		fzxx = fzxx.replaceAll("[*]1", "").replace("(", "").replace(").groups", "").replace(")", "");
	//	log.info(fzxx);
		JSONObject jsonob = JSONObject.fromObject(fzxx);
		JSONObject jsons =jsonob.getJSONArray("message").getJSONObject(0);
		JSONArray tempJson;
		
			UserType userType = null;
			tempJson = JSONObject.fromObject(jsons.getString("friendsList")).getJSONArray("contacts");
			for (int i = 0; i < tempJson.size(); i++) {
				userType = new UserType();
				JSONObject ti= JSONObject.fromObject(tempJson.get(i).toString());
				userType.setId(ti.getString("id"));
				userType.setName(ti.getString("nick_name"));
				userType.setNum(ti.getInt("group_id"));
				userTypeList.add(userType);
			}
		} catch (Exception e) {
			log.error(e);
		}
//		request.setAttribute("userTypeList",userTypeList);
		return userTypeList;
	}
	public Groups getGroups() throws HttpException, IOException{
		String url = "https://mp.weixin.qq.com/cgi-bin/contactmanage?t=user/index&token="+token+"&lang=zh_CN&pagesize=10&pageidx=0&type=0&groupid=0";
		HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https",
				new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		GetMethod getMethod = new GetMethod(url);
		getMethod.setRequestHeader("Cookie", "slave_user=" + slave_user
				+ ";slave_sid=" + slave_sid);
		client.executeMethod(getMethod);
		String returnStr = getMethod.getResponseBodyAsString();
		// 获取分页等信息
		String fzxx = "{\"message\":["
			+ returnStr.split("cgiData=")[1].split(".contacts,")[0]+ "}]}";
		fzxx = fzxx.replaceAll("[*]1", "").replace("(", "").replace(").groups", "").replace(")", "");
		JSONObject jsonob = JSONObject.fromObject(fzxx);
		JSONObject jsons =jsonob.getJSONArray("message").getJSONObject(0);
		JSONArray jsona = JSONObject.fromObject(jsons.getString("groupsList")).getJSONArray("groups");
		String id="",name="",cnt="";
		for(int i=0;i<jsona.size();i++){
			id+=jsona.getJSONObject(i).getString("id")+" ";
			name+=jsona.getJSONObject(i).getString("name")+" ";
			cnt+=jsona.getJSONObject(i).getString("cnt")+" ";
		}
		log.info("gid="+id);
		Groups g=new Groups();
		g.setId(id.split(" "));
		g.setName(name.split(" "));
		g.setCnt(cnt.split(" "));
		return g;
		
	}
	
	public boolean getFanList(String database) {
		ArrayList<WxUser> userList = new ArrayList<WxUser>();
		try {
			Dbconn dbcon=new Dbconn();
			dbcon.setDatabase(database);
			dbcon.clearWxfans();
			Groups groups=getGroups();

			for(int pagei=0;pagei<groups.id.length;pagei++)
				for(int pagej=0;pagej<=Integer.parseInt(groups.cnt[pagei])/10;pagej++)
			{
//					log.info("i="+pagei+" j="+pagej);
			String url = //腾讯换地址了！"https://mp.weixin.qq.com/cgi-bin/contactmanagepage?t=wxm-friend&token="+ token+ "&lang=zh_CN&pagesize=10&pageidx="+(PageIdx-1)+"&type=0&groupid=0";
			           "https://mp.weixin.qq.com/cgi-bin/contactmanage?t=user/index&token="+token+"&lang=zh_CN&pagesize=10&pageidx="+pagej+"&type=0&groupid="+groups.id[pagei];
			HttpClient client = new HttpClient();
			Protocol myhttps = new Protocol("https",
					new MySSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);
			GetMethod getMethod = new GetMethod(url);
			getMethod.setRequestHeader("Cookie", "slave_user=" + slave_user
					+ ";slave_sid=" + slave_sid);
			client.executeMethod(getMethod);
			String returnStr = getMethod.getResponseBodyAsString();
			// 获取分页等信息
			String fzxx = "{\"message\":["
					+ returnStr.split("cgiData=")[1].split(".contacts,")[0]
					+ "}]}";
			fzxx = fzxx.replaceAll("[*]1", "").replace("(", "").replace(").groups", "").replace(")", "");
		//	log.info(fzxx);
			JSONObject jsonob = JSONObject.fromObject(fzxx);
			JSONObject jsons =jsonob.getJSONArray("message").getJSONObject(0);
			JSONArray tempJson;
			int PageCount = 0;  //总页数
			int PageSize = 0;   //每页数量

			tempJson = JSONObject.fromObject(jsons.getString("friendsList")).getJSONArray("contacts");
//			log.info("temp="+tempJson);
			fanId=new String[tempJson.size()];
			
				for (int i = 0; i < tempJson.size(); i++) {
					WxUser wxUser = new WxUser();
					JSONObject ti= JSONObject.fromObject(tempJson.get(i).toString());
					wxUser.setFakeId(ti.get("id").toString());
					
					String requestUrl="https://mp.weixin.qq.com/cgi-bin/getcontactinfo";
				//	HttpClient client = new HttpClient();
				//	Protocol myhttps = new Protocol("https",
				//			new MySSLProtocolSocketFactory(), 443);
				//	Protocol.registerProtocol("https", myhttps);
					PostMethod postMethod = new PostMethod(requestUrl);
				//	token=422167781&lang=zh_CN&t=ajax-getcontactinfo&fakeid=1007564643
					postMethod.setParameter("token", token);
					postMethod.setParameter("lang", "zh_CN");
					postMethod.setParameter("t", "ajax-getcontactinfo");
					postMethod.setParameter("fakeid",ti.get("id").toString());
					postMethod.setRequestHeader("Cookie", "slave_user=" + slave_user
							+ ";slave_sid=" + slave_sid);
				//	postMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
					postMethod.setRequestHeader(
									"Referer",
									"https://mp.weixin.qq.com/cgi-bin/contactmanage?t=user/index&token="+token+"&lang=zh_CN&pagesize=10&pageidx=0&type=0&groupid=0"		
					);
					client.executeMethod(postMethod);
					String Str = postMethod.getResponseBodyAsString();
					//log.info(ti.get("id").toString()+":"+Str);
					JSONObject jsonObject=JSONObject.fromObject(Str).getJSONObject("contact_info");
//					log.info("json="+jsonObject);
				//	JSONObject json=jsonObject.getJSONObject("contact_info");
					log.info("nickname="+jsonObject.getString("nick_name"));
					dbcon.freashen(groups.id[pagei],groups.name[pagei],jsonObject.getString("fake_id"),jsonObject.getString("nick_name"),jsonObject.getString("country"),jsonObject.getString("province"),jsonObject.getString("city"),jsonObject.getString("gender"));
					
					fanId[i]=ti.get("id").toString();
					wxUser.setNickName(ti.get("nick_name").toString());
					wxUser.setGroupid(ti.get("group_id").toString());
					userList.add(wxUser);
				}
				}
			
//			request.setAttribute("userList",userList);
//			request.setAttribute("page",page);
			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	//解析成json格式
	private static String getJson(String json) {
	    return Pattern.compile("defaultGroupName\\[\\d+]\\s+\\|*").matcher(json).replaceAll("").replaceAll("\\*1", "");
	}
	
	public WxUser sureFanList() {
		ArrayList<WxUser> userList = new ArrayList<WxUser>();
		try {
			Dbcon dbcon=new Dbcon();
			String url = "https://mp.weixin.qq.com/cgi-bin/contactmanage?t=user/index&token="+token+"&lang=zh_CN&pagesize=10&pageidx=0&type=0&groupid=0";
			HttpClient client = new HttpClient();
			Protocol myhttps = new Protocol("https",
					new MySSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);
			GetMethod getMethod = new GetMethod(url);
			getMethod.setRequestHeader("Cookie", "slave_user=" + slave_user
					+ ";slave_sid=" + slave_sid);
			client.executeMethod(getMethod);
			String returnStr = getMethod.getResponseBodyAsString();
			// 获取分页等信息
			String fzxx = "{\"message\":["
					+ returnStr.split("cgiData=")[1].split(".contacts,")[0]
					+ "}]}";
			fzxx = fzxx.replaceAll("[*]1", "").replace("(", "").replace(").groups", "").replace(")", "");
			JSONObject jsonob = JSONObject.fromObject(fzxx);
			JSONObject jsons =jsonob.getJSONArray("message").getJSONObject(0);
			JSONArray tempJson;
			tempJson = JSONObject.fromObject(jsons.getString("friendsList")).getJSONArray("contacts");
			//log.info("temp="+tempJson);
			
					WxUser wxUser = new WxUser();
					JSONObject ti= JSONObject.fromObject(tempJson.get(0).toString());
					wxUser.setFakeId(ti.get("id").toString());
					
					String requestUrl="https://mp.weixin.qq.com/cgi-bin/getcontactinfo";
					PostMethod postMethod = new PostMethod(requestUrl);
					postMethod.setParameter("token", token);
					postMethod.setParameter("lang", "zh_CN");
					postMethod.setParameter("t", "ajax-getcontactinfo");
					postMethod.setParameter("fakeid",ti.get("id").toString());
					postMethod.setRequestHeader("Cookie", "slave_user=" + slave_user
							+ ";slave_sid=" + slave_sid);
					postMethod.setRequestHeader(
									"Referer",
									"https://mp.weixin.qq.com/cgi-bin/contactmanage?t=user/index&token="+token+"&lang=zh_CN&pagesize=10&pageidx=0&type=0&groupid=0"		
					);
					client.executeMethod(postMethod);
					String Str = postMethod.getResponseBodyAsString();
					JSONObject jsonObject=JSONObject.fromObject(Str).getJSONObject("contact_info");
					JSONObject jsonObject0=JSONObject.fromObject(Str).getJSONObject("groups");
					String str0=jsonObject0.toString();
					String[] str00=str0.split("id\":");
					Map map = new HashMap();
					for(int i=1;i<str00.length;i++){	
						int p1=str00[i].indexOf("name\":\"");
						int p2=str00[i].indexOf("\",\"cnt");
						map.put(str00[i].substring(0,str00[i].indexOf(",")),str00[i].substring(p1+7,p2));	
					}
					
					WxUser wx=new WxUser();
					wx.setNickName(jsonObject.getString("nick_name"));
					wx.setCountry(jsonObject.getString("country"));
					wx.setProvince(jsonObject.getString("province"));
					wx.setCity(jsonObject.getString("city"));
					wx.setFakeId(jsonObject.getString("fake_id"));
					wx.setGroupid(jsonObject.getString("group_id"));
					wx.setGroupname((String)map.get(jsonObject.getString("group_id")));
					wx.setGender(jsonObject.getString("gender"));
					log.info("----------------");
					log.info(jsonObject.getString("nick_name"));
					log.info(jsonObject.getString("country"));
					log.info(jsonObject.getString("province"));
					log.info(jsonObject.getString("city"));
					log.info(jsonObject.getString("fake_id"));
					log.info(jsonObject.getString("group_id"));
					log.info(jsonObject.getString("gender"));
				//	if(dbcon.checkId(jsonObject.getString("fake_id")))
					//sendMessageById("[飞吻][菜单]\nb-时文 d-天气  f-英汉互译 g-周边查询",jsonObject.getString("fake_id"));
						return  wx;
		} catch (Exception e) {
			log.error(e);
			return new WxUser();
		}
	}
	
	public String sureFanByMsg(String str,String createtime) {
		ArrayList<WxUser> userList = new ArrayList<WxUser>();
		try {
			createtime=createtime.substring(0,createtime.length()-1);//去末位
			long lon=Long.parseLong(createtime);
			Dbcon dbcon=new Dbcon();
			String url = "https://mp.weixin.qq.com/cgi-bin/message?t=message/list&count=20&day=7&token="+token+"&lang=zh_CN";
			HttpClient client = new HttpClient();
			Protocol myhttps = new Protocol("https",
					new MySSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);
			GetMethod getMethod = new GetMethod(url);
			getMethod.setRequestHeader("Cookie", "slave_user=" + slave_user
					+ ";slave_sid=" + slave_sid);
			client.executeMethod(getMethod);
			String returnStr = getMethod.getResponseBodyAsString();
			//log.info("return="+returnStr);
			// 获取分页等信息
			String fzxx = returnStr.split("wx.cgiData")[1].split(lon+"")[0];
			if(fzxx.indexOf(".msg_item")>0)fzxx = returnStr.split("wx.cgiData")[1].split(lon+1+"")[0];//末尾数字99部分
			int a=fzxx.lastIndexOf("fakeid\":\"");
			String str0=fzxx.substring(a+9);
			String str00=str0.substring(0,str0.indexOf("\""));
			log.info("str00="+str00);
			return ""+Long.parseLong(str00);
		} catch (Exception e) {
			log.error(e);
			return "";
		}
	}

	public boolean sendMessageById(String str, String wId) {
		try {
			String url = "http://mp.weixin.qq.com/cgi-bin/singlesend?type=1&content="+URLEncoder.encode(str,"utf-8")+"&tofakeid="+wId+"&imgcode=&token="+token+"&lang=zh_CN&random=0.08037174646675638&f=json&ajax=1&t=ajax-response";
			HttpClient client = new HttpClient();
			PostMethod getMethod = new PostMethod(url);
			getMethod.setRequestHeader("Cookie", "slave_user=" + slave_user
					+ ";slave_sid=" + slave_sid);
			getMethod.setRequestHeader(
							"Referer",
							"https://mp.weixin.qq.com/cgi-bin/singlesendpage?t=message/send&action=index&tofakeid="+wId+"&token="+token+"&lang=zh_CN");
client.executeMethod(getMethod);
			String returnStr = getMethod.getResponseBodyAsString();
			log.info(returnStr);
			return true;
		} catch (IOException e) {
			log.error(e);
			return false;
		}
	}
	
//	public void pictest(){
//		 String url="https://mp.weixin.qq.com/cgi-bin/uploadmaterial?cgi=uploadmaterial&type=2&token="+token+"&t=iframe-uploadfile&lang=zh_CN&formId=1";
//	     String str="https://mp.weixin.qq.com/cgi-bin/getimgdata?token="+token+"&mode=large&source=file&fileId=10000012";
//	}
	public String getImageItem() {
		try {
			String url = "https://mp.weixin.qq.com/cgi-bin/appmsg?begin=0&count=0&t=media/appmsg_list&type=10&action=list&token="+token+"&lang=zh_CN";
			HttpClient client = new HttpClient();
			PostMethod getMethod = new PostMethod(url);
			getMethod.setRequestHeader("Cookie", "slave_user=" + slave_user
					+ ";slave_sid=" + slave_sid);
			getMethod.setRequestHeader(
							"Referer",
							"https://mp.weixin.qq.com/cgi-bin/contactmanage?t=user/index&pagesize=10&pageidx=0&type=0&groupid=0&token="+token+"&lang=zh_CN");
client.executeMethod(getMethod);
			String returnStr = getMethod.getResponseBodyAsString();
		//	log.info(returnStr);
			String str=returnStr.split("wx.cgiData =")[1].split("commondity_msg_cnt")[0];
			String str0="";
			int a,b;
			String str1[]=str.split("app_id\":");
			for(int i=1;i<str1.length;i++){
				a=str1[i].indexOf("title\":\"");
				b=str1[i].indexOf("\",\"digest");
				str0+=str1[i].substring(0,8)+","+str1[i].substring(a+8,b)+";";
				}
			//log.info("str0="+str0);
			return str0;
		} catch (IOException e) {
			log.error(e);
			return "";
		}
	}
	public boolean sendMessageAll(String str,ArrayList<Fans> fans) {
		boolean flag=false;
		try {
			log.info("fanid="+fanId);
			//log.info("wxId="+wxId);
			for(int i=0;i<fans.size();i++){
				String url = "http://mp.weixin.qq.com/cgi-bin/singlesend?t=ajax-response&lang=zh_CN&type=10" +
				"&app_id="+str+"&tofakeid="+fans.get(i).getFakeid()+"&appmsgid="+str+"&imgcode=&token="+token+"&lang=zh_CN&random=0.4564248079816847&t=ajax-response";
		HttpClient client = new HttpClient();
			PostMethod getMethod = new PostMethod(url);
			getMethod.setRequestHeader("Cookie", "slave_user=" + slave_user
					+ ";slave_sid=" + slave_sid);
			getMethod.setRequestHeader(
							"Referer",
							"https://mp.weixin.qq.com/cgi-bin/singlesendpage?t=message/send&action=index&tofakeid="+fans.get(i).getFakeid()+"&token="+token+"&lang=zh_CN");
			client.executeMethod(getMethod);
			String returnStr = getMethod.getResponseBodyAsString();
			if("{\"base_resp\":{\"ret\":0,\"err_msg\":\"ok\"}}".equals(returnStr))flag= true;
			}
			return flag;
		} catch (IOException e) {
			log.error(e);
			return false;
		}
	}
}
