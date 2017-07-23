package wxtry;
import com.wzf.pubvari.Variable;
import interf.ChooseMsg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wzf.model.News;
import com.wzf.model.SimplePubid;
import com.wzf.model.WxUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.marker.weixin.DefaultSession;
import org.marker.weixin.MySecurity;
import org.marker.weixin.msg.Data4Item;
import org.marker.weixin.msg.Msg;
import org.marker.weixin.msg.Msg4Event;
import org.marker.weixin.msg.Msg4ImageText;
import org.marker.weixin.msg.Msg4Location;
import org.marker.weixin.msg.Msg4Music;
import org.marker.weixin.msg.Msg4Text;
import org.xml.sax.InputSource;

import common.WxService;

import com.wzf.security.CryptoTools;
/*�����Ż�:�û����� �˳� ����  ���--����a���黯
 *         
 * */
public class Allapi {
	public String str;
	public String user;
	public Variable vari=new Variable();
	public String url=vari.url;
	static Logger log = Logger.getLogger("logfile");

	public void Subscribe(Msg msg, DefaultSession session) throws Exception {
		Dbcon db=new Dbcon();
		String databae=db.getPubDatabase(msg.getToUserName());
		db.setDatabase(databae);
		log.info("databae="+databae);
		WxUser newfan=db.getWxfansByOpenid(msg.getFromUserName());
	       CryptoTools tools = new CryptoTools();
	       String str22=db.getIdByOpenid(msg.getFromUserName());
		Data4Item dm =new Data4Item();
		 dm = new Data4Item("Ǯ����",
				"",
				vari.URL+"/file/image/qjwb/012.jpg",
				vari.url+"?pid=12&openid="+str22+"&id="+db.getId(msg.getFromUserName()));
	
	rebackImage(dm, msg, session);
	}
	
	public void Near(Msg msg, DefaultSession session)
	throws SQLException, IOException, JDOMException {
    Dbcon db=new Dbcon();
   db.setDatabase(db.getPubDatabase(msg.getToUserName()));
   str = new ChooseMsg().onMsg(msg);
    user = msg.getFromUserName();
    if("".equals(db.getInstruction(user))||db.getInstruction(user)==null){rebackText("�뷢�͹ؼ���/:?", msg, session);return;
    }
    if ("".equals(db.getParamLoca(user))||db.getParamLoca(user)==null) {
	rebackText("/::)�뷢�����λ����Ϣ�����������Եġ�+������", msg, session);
	return;
}  
     Data4Item[] dm=Nearby(db.getInstruction(user),db.getParamLoca(user),"2000");
     rebackImage(dm, msg, session);
}
	public void Delins(Msg msg, DefaultSession session) throws SQLException{ //�򵥻ظ� ���˳���
		rebackText("[����][�˵�]\nb-ʱ�� d-����  f-Ӣ������ g-�ܱ߲�ѯ",msg,session);
	
	}

	public void Translate(Msg msg, DefaultSession session)
			throws SQLException, IOException, JDOMException {
		Dbcon db=new Dbcon();
		db.setDatabase(db.getPubDatabase(msg.getToUserName()));
		str = new ChooseMsg().onMsg(msg);
		log.info("f_str="+str);
		user = msg.getFromUserName();
		if ("".equals(db.getInstruction(user))) {
			rebackText("������/:ok��", msg, session);
			return;
		}
		rebackText(new Allapi().Translate(str), msg, session);
	}

	public void Songs(Msg msg, DefaultSession session) throws SQLException {
		Dbcon db=new Dbcon();
		db.setDatabase(db.getPubDatabase(msg.getToUserName()));
		str = new ChooseMsg().onMsg(msg)+"  $";
		user = msg.getFromUserName();
		if ("".equals(db.getInstruction(user))) {
			rebackText("����������͸��֣��磨���¼�ʱ�� ���ƣ�/:8-)", msg, session);
			return;
		}
		try {
			String str0=new Allapi().Music(str.split(" ")[0], str.split(" ")[1]);
			if(!str0.startsWith("http://")){rebackText("��Ǹ���Ҳ������׸裡", msg, session);return;}
			rebackMusic("���������֣��������飡",new ChooseMsg().onMsg(msg),str0,msg,session);
		} catch (Exception e) {
			log.error(e);
			rebackText("��Ǹ���Ҳ������׸裡", msg, session);
			// mm.setMusicUrl(db.getCon(str));
		}
	}

	public void News(Msg msg, DefaultSession session) throws SQLException,
			IOException, JDOMException {
		Dbcon db=new Dbcon();
		db.setDatabase(db.getPubDatabase(msg.getToUserName()));
		str = new ChooseMsg().onMsg(msg);
		user = msg.getFromUserName();
		if ("".equals(db.getInstruction(user))) {
			rebackText("/:?Ҫ����ʲô������", msg, session);
			return;
		}
		ArrayList<ArrayList> array1 = new Allapi().News(str);
		ArrayList<ArrayList> array2 = db.getThirdArr(str);
		Data4Item[] dm = new Data4Item[array1.size()];
		if (array1.size() > 0) {
			for (int i = 0; i < array1.size(); i++) {
				// log.info("aaaa");
				dm[i] = new Data4Item(array1.get(i).get(1).toString(), array1
						.get(i).get(1).toString(), "", array1.get(i).get(0)
						.toString());
			}
		} else {
			dm = new Data4Item[array2.size()];
			for (int i = 0; i < array2.size(); i++) {
				dm[i] = new Data4Item(array2.get(i).get(0).toString(), array2
						.get(i).get(1).toString(), array2.get(i).get(2)
						.toString(), array2.get(i).get(3).toString());
			}
		}
		rebackImage(dm, msg, session);
	}

	public void Robot(Msg msg, DefaultSession session) throws SQLException {
		Dbcon db=new Dbcon();
		db.setDatabase(db.getPubDatabase(msg.getToUserName()));
		str =new ChooseMsg().onMsg(msg);
		user = msg.getFromUserName();
		String reback = "�Ҹ�������,ף�����������/::),����������ͼ�������~";
		db.addInstruction(user, str);
		if (!"".equals(db.getReback(user)))
			reback = db.getReback(user);
		rebackText(reback, msg, session);
	}

	public void rebackImage(Data4Item[] dm, Msg msg, DefaultSession session) throws SQLException {
		// Data4Item d1 = new Data4Item("Play", "take a try",
		// "http://teacher.3xy.com.cn/sxyAdminData/images/resource/jpg/200805/20080517154256287.jpg",
		// "www.qq.com");
		Dbcon db=new Dbcon(); 
		db.setDatabase(db.getPubDatabase(msg.getToUserName()));
		String time= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		//msg.getToUserName(),msg.getClass().getName()
		boolean flag=msg instanceof Msg4Event;
		db.addChatrecorder(msg.getFromUserName(),msg.getToUserName(),new ChooseMsg().onReMsg(msg),flag? "0":"4","[ͼ��1]","1",time);
		Msg4ImageText mit = new Msg4ImageText();
		mit.setFromUserName(msg.getToUserName());
		mit.setToUserName(msg.getFromUserName());
		mit.setCreateTime(msg.getCreateTime());
		for (Data4Item d : dm)
			mit.addItem(d);
		mit.setFuncFlag("0");
		session.callback(mit);
	}

	public void rebackImage(Data4Item dm, Msg msg, DefaultSession session) throws SQLException {
		// Data4Item d1 = new Data4Item("Play", "take a try",
		// "http://teacher.3xy.com.cn/sxyAdminData/images/resource/jpg/200805/20080517154256287.jpg",
		// "www.qq.com");
		Dbcon db=new Dbcon();
		db.setDatabase(db.getPubDatabase(msg.getToUserName()));
		String time= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		boolean flag=msg instanceof Msg4Text;
		db.addChatrecorder(msg.getFromUserName(),msg.getToUserName(),flag?((Msg4Text)msg).getContent():"[�¼�]"+((Msg4Event)msg).getEvent(),flag? "0":"4","[ͼ��2]"+dm.getDescription(),"1",time);
		Msg4ImageText mit = new Msg4ImageText();
		mit.setFromUserName(msg.getToUserName());
		mit.setToUserName(msg.getFromUserName());
		mit.setCreateTime(msg.getCreateTime());
		mit.addItem(dm);
		mit.setFuncFlag("0");
		session.callback(mit);
	}

	public void rebackText(String reback, Msg msg, DefaultSession session) throws SQLException {//reback<1500�ַ�
		// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Dbcon db=new Dbcon();
		db.setDatabase(db.getPubDatabase(msg.getToUserName()));
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		boolean flag=msg instanceof Msg4Event;
		db.addChatrecorder(msg.getFromUserName(), msg.getToUserName(), new ChooseMsg().onReMsg(msg),flag? "4":"0" ,reback, "0", time);
		Msg4Text rmsg = new Msg4Text();
		rmsg.setFromUserName(msg.getToUserName());
		rmsg.setToUserName(msg.getFromUserName());
		rmsg.setFuncFlag("0");
		rmsg.setContent(reback);
		session.callback(rmsg);
	}

	public void rebackMusic(String title,String main,String url,Msg msg,DefaultSession session) throws SQLException{
		Dbcon db=new Dbcon();
		db.setDatabase(db.getPubDatabase(msg.getToUserName()));
		String time= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		boolean flag=msg instanceof Msg4Text;;
		db.addChatrecorder(msg.getFromUserName(),msg.getToUserName(),flag?((Msg4Text)msg).getContent():"[�¼�]"+((Msg4Event)msg).getEvent(),flag? "0":"4","[��Ƶ]"+title,"2",time);
		Msg4Music mm=new Msg4Music();
		mm.setCreateTime(msg.getCreateTime());
		mm.setDescription(main);
		mm.setFromUserName(msg.getToUserName());
		mm.setToUserName(msg.getFromUserName());
		mm.setFuncFlag("0");
	//	log.info(new allapi().Music(str.split("��")[0],str.split("��")[1]));
		mm.setHQMusicUrl(url);
		mm.setTitle(title);
		session.callback(mm);
	}
	public  Data4Item[] Nearby(String query,String location,String r) throws IOException, JDOMException{
		 if("".equals(r))r="2000";
		 String url="http://api.map.baidu.com/place/v2/search?&query=QUERY&location=LOCATION&radius=RADIUS&output=json&ak=AC39610b83a798c57469d1fc0a50cdb5";
		url=url.replace("QUERY", query).replace("LOCATION", location).replace("RADIUS", r);
		 StringBuffer buffer = httpRequest1(url, "utf-8","");  
		    JSONObject jsonObject=JSONObject.fromObject(buffer.toString());  
              JSONArray json0=jsonObject.getJSONArray("results");
              //JSONArray json1=json0.getJSONArray(key)
              String str="";
              String str1="",str2="";
              int j=1;
              String urlo=vari.URL+"/"+vari.project+"/indexx.jsp?length="+j+"&query="+URLEncoder.encode(query,"utf-8");
              WxService wx=new WxService();
              Data4Item dm[]=new Data4Item[(json0.size()>7?8:json0.size())+1];
            for(int i=0;i<json0.size()&&i<=7;i++){ j=i;
			 String name=json0.getJSONObject(i).getString("name");
			 String address=json0.getJSONObject(i).getString("address");
			 str+="���ƣ�"+name+"\n";
			 str+=address+"\n";
			 String json00=json0.getJSONObject(i).getJSONObject("location").getString("lat");
			 String json01=json0.getJSONObject(i).getJSONObject("location").getString("lng");
			 str1=json01+","+json00;str2+="m,B|";
			 Double x1=Double.parseDouble(json00)*Math.PI/180;
			 Double y1=Double.parseDouble(json01)*Math.PI/180;
			 Double x2=Double.parseDouble(location.split(",")[0])*Math.PI/180;
			 Double y2=Double.parseDouble(location.split(",")[1])*Math.PI/180;
			 Double d=6371004*Math.acos(Math.sin(x1)*Math.sin(x2)+Math.cos(x1)*Math.cos(x2)*Math.cos(y1-y2));
//			 d(x1,y1,x2,y2)=r*arccos(sin(x1)*sin(x2)+cos(x1)*cos(x2)*cos(y1-y2))
			 str+="����"+(y1>y2?"��":"��")+(x1>x2?"��":"��")+"\n";
			 String distance=new DecimalFormat("0").format(d)+"��";
			 String teltphone="";
			 if(json0.getJSONObject(i).containsKey("telephone"))teltphone=json0.getJSONObject(i).getString("telephone")+"\n";
			 String str0=json0.getJSONObject(i).getString("uid");
			 // str+="��ϸ��Ϣ��\n"+ nearbyInf(str0)+"\n";
			 String str00="&name="+URLEncoder.encode(name,"utf-8")+"&address="+URLEncoder.encode("��ϸ��Ϣ��"+ nearbyInf(str0),"utf-8")+"&query="+URLEncoder.encode(query,"utf-8")+"&location_x="+json00+"&location_y="+json01;
			  //str+="<a href='http://xiaowangzi.touclick.com/weixin/indexx.jsp?length=1"+str00+"'>�鿴��ͼ</a>\n";
			  urlo+=str00;
			  dm[i+1]=new Data4Item(name+"\n"+teltphone+"��ַ��"+address+"("+distance+")","","http://api.map.baidu.com/staticimage?center="+json01+","+json00+"&width=300&height=600&zoom=11&markers="+json01+","+json00+"&bbox=minX",vari.URL+"/"+vari.project+"/indexx.jsp?length=1"+str00);
            }
		 dm[0]=new Data4Item("�鿴����"+query,"","http://api.map.baidu.com/staticimage?center="+str1+"&width=300&height=200&zoom=11",urlo);
		log.info("urlo="+urlo);
		 return dm;
	 }
	   public static String nearbyInf(String str) throws IOException, JDOMException{
		   String url="http://api.map.baidu.com/place/v2/detail?uid="+str+"&ak=AC39610b83a798c57469d1fc0a50cdb5&output=json&scope=2";
		   StringBuffer buffer = httpRequest1(url, "utf-8","");  
		    JSONObject jsonObject=JSONObject.fromObject(buffer.toString());
		    JSONObject json0=jsonObject.getJSONObject("result");
		    JSONObject json=json0.getJSONObject("detail_info");
		  //  log.info("json="+json);
		    String str0="";
		   if(json.containsKey("distance")) str0+="��ͼ���룺"+json.getString("distance")+" ";
		   if(json.containsKey("type")) str0+="���"+json.getString("type")+" ";
		   if(json.containsKey("tag")) str0+="��ǩ��"+json.getString("tag")+" ";
		   if(json.containsKey("price")) str0+="�۸�"+json.getString("price")+" ";
		   if(json.containsKey("shop_hours"))str0+="Ӫҵʱ�䣺"+json.getString("shop_hours")+" ";
		   if(json.containsKey("overall_rating")) str0+="������"+json.getString("overall_rating")+" ";
		   if(json.containsKey("taste_rating")) str0+="��ζ��"+json.getString("taste_rating")+" ";
		   if(json.containsKey("service_rating")) str0+="����"+json.getString("service_rating")+" ";
		   if(json.containsKey("environment_rating")) str0+="������"+json.getString("environment_rating")+" ";
		   if(json.containsKey("facility_rating")) str0+="�Ǽ���"+json.getString("facility_rating")+" ";
		   if(json.containsKey("hygiene_rating")) str0+="������"+json.getString("hygiene_rating")+" ";
		   if(json.containsKey("technology_rating")) str0+="������"+json.getString("technology_rating")+" ";
		   if(json.containsKey("image_num")) str0+="ͼƬ����"+json.getString("image_num")+" ";
		   if(json.containsKey("groupon_num")) str0+="�Ź�����"+json.getString("groupon_num")+" ";
		   if(json.containsKey("discount_num")) str0+="�Ż�����"+json.getString("discount_num")+" ";
		   if(json.containsKey("comment_num")) str0+="��������"+json.getString("comment_num")+" ";
		   if(json.containsKey("favorite_num")) str0+="�ղ�����"+json.getString("favorite_num")+" ";
		   if(json.containsKey("checkin_num")) str0+="ǩ������"+json.getString("checkin_num")+" ";
		   return str0;
	   }
	
	public static String Music(String title, String name) throws IOException,
			JDOMException {
		String menu_create_url = "http://box.zhangmen.baidu.com/x?op=12&count=1&title=song$$name$$$$ ";
		String returnurl = "";
		String musicurl;
		if("".equals(name))
			musicurl= menu_create_url.replace("song", title).replace(
				"name$$$$", "");
		else
			musicurl= menu_create_url.replace("song", title).replace(
					"name", name);
		log.info("musicurl1="+musicurl);
		StringBuffer buffer = httpRequest(musicurl, "POST", "");
		String xmlStr = buffer.toString();
		StringReader sr = new StringReader(xmlStr);
		InputSource is = new InputSource(sr);
		Document documentObject = (new SAXBuilder()).build(is);
		if (null != documentObject) {
			Element root = documentObject.getRootElement();
			 List<Element>list=root.getChildren();
			int count=Integer.parseInt(root.getChildText("count"));
			if (count!=0) { // �����
				// log.info("url="+root.getChild("url"));
				for(Element t:list){
				//Element url = root.getChild("url");
				String str = t.getChildText("encode");
				if(str==null||!str.startsWith("http://"))continue;
				returnurl = str.substring(0, str.lastIndexOf("/") + 1)
						+ t.getChildText("decode");
				if(!returnurl.endsWith(".mp3")&&!returnurl.endsWith(".wma")){returnurl = "None";continue;}
				break;
				// log.info("С·����"+url.getChildText("decode"));
				// log.info("durl="+root.getChildText("durl"));
				}
			log.info("return="+returnurl);	
			}
		} else
			returnurl = "None";
		return returnurl;
	}

	public static ArrayList News(String title) throws IOException,
			JDOMException {
		ArrayList arr = new ArrayList();
		String create_url = "http://news.baidu.com/ns?word=title&tn=newsfcu&from=news&cl=2&rn=3&ct=0";
		String newsurl = create_url.replace("title", title);
		StringBuffer stringObject = httpRequest1(newsurl, "gb2312", "");
		String firstspilt[] = stringObject.toString().split("<div");
		// log.info(firstspilt[1]);
		String secondsplit[] = firstspilt[2].split("<a href=\"");
		for (int i = 1; i < 4; i++) {
			int firTar = secondsplit[i].indexOf("\" target=\"_blank\">");
			int lasTar = secondsplit[i].lastIndexOf("\" target=\"_blank\">");
			int firA = secondsplit[i].indexOf("</a>");
			ArrayList ar = new ArrayList();
			ar.add(secondsplit[i].substring(0, firTar));
			ar.add(secondsplit[i].substring(lasTar + 18, firA) + "\n");
			arr.add(ar);
		}
		return arr;
	}

	public static String Weather(String citycode) throws IOException,
			JDOMException {
		String rebacktext = "";
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		String create_url = "http://m.weather.com.cn/data/citycode.html";
		String weaurl = create_url.replace("citycode", citycode);
		buffer = httpRequest1(weaurl, "utf-8", "");
		jsonObject = JSONObject.fromObject(buffer.toString());
		JSONObject jsonObject0 = jsonObject.getJSONObject("weatherinfo");
		rebacktext = jsonObject0.getString("city") + "����\n"
				+ jsonObject0.getString("date_y") + " "
				+ jsonObject0.getString("week") + "\n" + "���գ�"
				+ jsonObject0.getString("temp1") + " "
				+ jsonObject0.getString("weather1") + " "
				+ jsonObject0.getString("wind1") + "\n" + "��ܰ��ʾ��"
				+ jsonObject0.getString("index_d") + "\n" + "���գ�"
				+ jsonObject0.getString("temp1") + " "
				+ jsonObject0.getString("weather2") + " "
				+ jsonObject0.getString("wind2") + "\n" + "���죺"
				+ jsonObject0.getString("temp1") + " "
				+ jsonObject0.getString("weather3") + " "
				+ jsonObject0.getString("wind3");
		return rebacktext;
	}

	public static String Translate(String word) throws IOException,
			JDOMException {
		JSONObject jsonObject = null;
		String rebacktext = "";
		boolean flag;
		StringBuffer buffer = new StringBuffer();
		String create_url = "http://fanyi.youdao.com/openapi.do?keyfrom=weixin-app&key=767258678&type=data&doctype=json&version=1.1&q=";
		// String weaurl = create_url.replace("word", word);
		if (word.matches("[\\u4E00-\\u9FA5]+")) {
			buffer = httpRequest1(
					create_url + URLEncoder.encode(word, "utf-8"), "utf-8", "");
			flag = true;
		} else {
			buffer = httpRequest1(create_url + word, "utf-8", "");
			flag = false;
		}
		// log.info("json:"+JSONObject.fromObject(buffer.toString()));
		jsonObject = JSONObject.fromObject(buffer.toString());
		log.info("jsonO="+jsonObject);
		if (!"0".equals(jsonObject.getString("errorCode")))
			return rebacktext = "�Ҳ�������ʣ�";
		else {
			rebacktext = "����" + jsonObject.getString("translation") + "\n";
			JSONObject jsonObject0 = jsonObject.getJSONObject("basic");
			if (jsonObject0.containsKey("phonetic"))
				if (flag)
					rebacktext += "����"
							+ new String(jsonObject0.getString("phonetic")
									.getBytes(), "gbk") + "\n";
				else
					rebacktext += "����"
							+ new String(jsonObject0.getString("phonetic")
									.getBytes(), "utf-8") + "\n";
			log.info("rebacktext="+rebacktext);
			if (jsonObject0.containsKey("explains"))
				rebacktext += "����" + jsonObject0.getString("explains") + "\n";
			rebacktext += "��������" + "\n";
			// log.info("json="+jsonObject);
			if(jsonObject.containsKey("web"))
			for (int i = 0; i < jsonObject.getJSONArray("web").size(); i++) {
				String str = jsonObject.getJSONArray("web").getString(i);
				JSONObject jsonObject1 = JSONObject.fromObject(str);
				rebacktext += jsonObject1.getString("value") + " "
						+ jsonObject1.getString("key") + "\n";
			}
		}
		return rebacktext;
	}

	public static String TrainLook(String citycode1, String citycode2,
			String looktime) throws IOException, JDOMException {
		String create_url = "http://dynamic.12306.cn/otsquery/query/queryRemanentTicketAction.do?method=queryststrainall";
		String postText = "date="+new SimpleDateFormat("yyyy").format(new Date())+"-looktime&fromstation=citycode1&tostation=citycode2&starttime=00:00--24:00";
		String newText = postText.replace("looktime", looktime).replace(
				"citycode1", citycode1).replace("citycode2", citycode2);
		log.info(newText);
		StringBuffer buffer = httpRequest(create_url, "POST", newText);
		JSONObject jsonObject = JSONObject.fromObject("{a:" + buffer.toString()
				+ "}");
		// log.info(jsonObject);
		String rebackText = "[��Ʊ�����������ʾ10��Σ�]\n";
		for (int i = 0; i < (jsonObject.getJSONArray("a").size() > 10 ? 10
				: jsonObject.getJSONArray("a").size()); i++) {
			String str = jsonObject.getJSONArray("a").getString(i);
			JSONObject jsonObject0 = JSONObject.fromObject(str);
			rebackText += "[" + jsonObject0.getString("id") + "]ʼ��վ("
					+ jsonObject0.getString("start_time") + ")��"
					+ jsonObject0.getString("start_station_name") + "  �յ�վ("
					+ jsonObject0.getString("end_time") + ")��"
					+ jsonObject0.getString("end_station_name") + "  �𳵱�ţ�"
					+ jsonObject0.getString("value") + "\n";

		}
		// log.info("rebackText="+rebackText);
		return rebackText;
	}

	// xml post?
	public static StringBuffer httpRequest(String requestUrl,
			String requestMethod, String outputStr) throws IOException,
			JDOMException {
		StringBuffer buffer = new StringBuffer();
		URL geturl = new URL(requestUrl);
		HttpURLConnection httpUrlConn = (HttpURLConnection) geturl
				.openConnection();
		httpUrlConn.setDoOutput(true); // ���п���
		httpUrlConn.setDoInput(true);
		httpUrlConn.setUseCaches(false);
		// ��������ʽ��GET/POST��
		httpUrlConn.setRequestMethod(requestMethod); // ����405
		if ("GET".equalsIgnoreCase(requestMethod))
			httpUrlConn.connect();
		// ����������Ҫ�ύʱ
		if (null != outputStr) {
			OutputStream outputStream = httpUrlConn.getOutputStream();
			outputStream.write(outputStr.getBytes("UTF-8"));
			outputStream.close();
		}
		// �����ص�������ת�����ַ���
		InputStream inputStream = httpUrlConn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		// �ͷ���Դ
		inputStream.close();
		inputStream = null;
		httpUrlConn.disconnect();

		return buffer;
	}

	// �ַ��������ܣ� get
	public static StringBuffer httpRequest1(String requestUrl, String encod,
			String outputStr) throws IOException, JDOMException {
		StringBuffer buffer = new StringBuffer();
		// URL url = new URL(requestUrl);
		URL getUrl = new URL(requestUrl);
		// ����ƴ�յ�URL�������ӣ�URL.openConnection���������URL�����ͣ�
		// ���ز�ͬ��URLConnection����Ķ�������URL��һ��http�����ʵ�ʷ��ص���HttpURLConnection
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		// �������ӣ�����ʵ����get requestҪ����һ���connection.getInputStream()�����вŻ���������
		// ������
		connection.connect();
		// ȡ������������ʹ��Reader��ȡ
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), encod));
		String lines;
		while ((lines = reader.readLine()) != null) {
			buffer.append(lines);
		}
		reader.close();
		// �Ͽ�����
		connection.disconnect();
		return buffer;
	}

}
