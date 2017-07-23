package wxtry;
import interf.ChooseMsg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.News;
import model.SimplePubid;
import model.WxUser;

import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.marker.weixin.DefaultSession;
import org.marker.weixin.HandleMessageAdapter;
import org.marker.weixin.MySecurity;
import org.marker.weixin.msg.Data4Item;
import org.marker.weixin.msg.Msg;
import org.marker.weixin.msg.Msg4Event;
import org.marker.weixin.msg.Msg4Image;
import org.marker.weixin.msg.Msg4ImageText;
import org.marker.weixin.msg.Msg4Link;
import org.marker.weixin.msg.Msg4Location;
import org.marker.weixin.msg.Msg4Music;
import org.marker.weixin.msg.Msg4Text;

import pubvari.Variable;


import security.CryptoTools;
import sun.nio.cs.ext.ISCII91;

public class WxDo extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public WxDo() {  }
	
	
	//TOKEN 是你在微信平台开发模式中设置的哦
	public static final String TOKEN = "D8yuc";

	
	
	/**
	 * 处理微信服务器发过来的各种消息，包括：文本、图片、地理位置、音乐等等
	 * 
	 * 
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("responsesession:"+response.get);
		InputStream is = request.getInputStream();
		OutputStream os = response.getOutputStream();
		
		final Dbcon db=new Dbcon();
//		db.setDatabase("w");
		final Variable vari=new Variable();
		final Logger log = Logger.getLogger("logfile");
		final DefaultSession session = DefaultSession.newInstance(); 
		
		session.addOnHandleMessageListener(new HandleMessageAdapter(){
			
			@Override  //重写
			//菜单事件
			public void onEventMsg(Msg4Event msg) {
				log.info("收到微信消息："+msg.getEvent());
				log.info("发送方:"+msg.getFromUserName());
				log.info("接收方:"+msg.getToUserName());
				String user=msg.getFromUserName();//user name
				String reback="";//reback mesge
				try {
					db.setDatabase(db.getPubDatabase(msg.getToUserName()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}
				log.info("database:"+db.getDatabase());
				 try {
				       CryptoTools tools = new CryptoTools();
					if("subscribe".equals(msg.getEvent())){//ding yue msg
						int ev=db.getSubEventType0();
					       log.info("ev="+ev);
					       db.addOpenid(user);
						 String content=db.getSubContent();
						  if(ev==0){
							  rebackText(content,msg); 
						  }
						 else if(ev==1){
							 String[] dmid=content.split("\\|");
								Data4Item[] dm=new Data4Item[dmid.length];
								ArrayList<News>array=db.getPicMusById(dmid);
								for(int i=0;i<array.size();i++){
									String str2 = "";
								//	CryptoTools tools;
									try {
										tools = new CryptoTools();
									       String time= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
									       WxUser newfan0=db.getWxfansByOpenid(msg.getFromUserName());
										str2="1|0|"+time.split("-")[0]+"|"+time.split("-")[1]+"|"+time.split("-")[2]+"|0|"+msg.getToUserName()+"|"+db.getIdByOpenid(msg.getFromUserName()) +"|"+msg.getFromUserName()+"|"+newfan0.getNickName()+"|"+newfan0.getCountry()+"|"+newfan0.getProvince()+"|"+newfan0.getCity(); 
									int a=array.get(i).getPty().length()+array.get(i).getPid().length();
										str2=URLEncoder.encode(tools.encode(str2.length()+a+"|"+str2),"utf-8");
									} catch (Exception e) {
										// TODO Auto-generated catch block
										log.error(e);rebackText("系统错误Exception！",msg);
									}
								dm[i] = new Data4Item(array.get(i).getTitle(), array.get(i).getMain(), (array.get(i).getUrlo().startsWith("http:")?"":vari.wa_pic_path)+array.get(i).getUrlo(), vari.url+"?pty="+array.get(i).getPty()+"&pid="+array.get(i).getPid()+"&rdm="+str2); 
								}
								rebackImage(dm,msg);
						 }
						 else if(ev==2){
							 String musid[]=new String[1];
							 musid[0]=content.split("\\|")[0];
							ArrayList<News>array=db.getPicMusById(musid);
							rebackMusic(array.get(0).getTitle(),array.get(0).getMain(),array.get(0).getUrl(),msg);
						 }
						 else {               //订阅默认回复 -1
							 Class class2=Class.forName("wxtry.Allapi");
							 Method method = class2.getMethod("Test3",Msg.class,DefaultSession.class);
						        Object object=(Allapi)class2.newInstance();
						        method.invoke(object,msg,session);}
						
					}
					 else if("unsubscribe".equals(msg.getEvent())){
						 db.setOpenIdval(msg.getFromUserName(),"0");
					 }
					 else if("11".equals(msg.getEventKey())){          //随便聊聊
						 try {
							db.updateStr(user,20);
							reback="幸福想你了,祝你天天好心情/::),不如来看看图听听歌吧~";
							rebackText(reback, msg);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							log.error(e);rebackText("系统错误SQLException！",msg);
						}
					 }
					 else if("12".equals(msg.getEventKey())){           //关于我们
}
					 else if("21".equals(msg.getEventKey())){           //时图新闻
						 try {
							db.updateStr(user,21);
							rebackText("/:?要搜索什么内容呢", msg);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							log.error(e);rebackText("系统错误SQLException！",msg);
						}
					 }
					 
					 else if("31".equals(msg.getEventKey())){           //爽听音乐
						 try {
							db.updateStr(user,23);
							rebackText("请输入歌名（歌手可选，空号隔开），如（明月几时有 王菲）/:8-)", msg);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							log.error(e);rebackText("系统错误SQLException！",msg);
						}
					 }
					 
					 else if("41".equals(msg.getEventKey())){           //天气
						 try {
							db.updateStr(user,24);
							rebackText("请输入城市名/:sun", msg);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							log.error(e);
						}
					 }
					 else if("42".equals(msg.getEventKey())){           //火车班次
						 try {
							db.updateStr(user,25);
							rebackText("请按格式输入出发站,到达站以及月日（如北京南，上海，3-4）/:8-)", msg);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							log.error(e);
						}
					 } 
					 else if("43".equals(msg.getEventKey())){           //英汉词典
						 try {
							db.updateStr(user,26);
							rebackText("请输入/:ok", msg);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							log.error(e);
						}
					 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					log.error(e);
					rebackText("系统错误Exception！",msg);return;
					
				}
			}

			@Override
			public void onTextMsg(Msg4Text msg) {
				log.info("收到微信消息："+msg.getContent());
				log.info("发送方:"+msg.getFromUserName());
				log.info("接收方:"+msg.getToUserName());
				log.info("发送时间:"+msg.getCreateTime());
				String createtime=msg.getCreateTime();
				 String str=msg.getContent();
				String user=msg.getFromUserName();
				String mode="";
			//	String retime=""+new Date().getTime();
				try {
					db.setDatabase(db.getPubDatabase(msg.getToUserName()));
					//没有的话添加openid   //判断“fakeid” 跟openid是否相同
					if(!db.isaddId(user)){//相同false
						log.info("11");
						 //相同：比较发送内容根openid模拟登陆  获取“fakeid”内容  对应fakeid
						if(!db.addOpenid(user))
							 rebackText("请发送任意内容获得验证：）", msg);
							 
					}
					    
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}
//				log.info("url:"+request.getRequestURI());
				
				int reId = 0;
				reId = db.isReKeyWord(str);
//		log.info("f_reid="+reId);
				try {
					if(db.isVis(user))
						db.addInstruction(user,str);
					else 
							db.addUser(user);
					if(reId>0)
					 db.updateStr(user,reId);
					else{ 
						if(db.getMode(user)!=null)
		               reId=Integer.parseInt(db.getMode(user));//获取菜单号
		               if(reId<=0)
		            	   reId=db.getDefault();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("系统错误SQLException！",msg);return;
				}
//				if("我".equals(msg.getContent())){
//					Msg4Text rmsg =	new Msg4Text();
//					rmsg.setFromUserName(msg.getToUserName());
//					rmsg.setToUserName(msg.getFromUserName());
//					rmsg.setFuncFlag("0");
//					rmsg.setContent("哈哈!");
//					session.callback(rmsg);
//					return;
//				} 
				
				try {
					int inf=db.getType0ById(reId);
					log.info("inf="+inf);
					log.info("reId="+reId);
					if(inf==-1){                                        //接口
						 Class class2=Class.forName("wxtry.Allapi");
					        Object object=(Allapi)class2.newInstance();
					    	Method method = class2.getMethod(db.getReContentById(reId),Msg.class,DefaultSession.class);
					        method.invoke(object,msg,session);
//					        rebackText((String)method.invoke(object,msg), msg);
					}
					
					if(inf==0){                                          //文字
						rebackText(db.getReContent(reId),msg);
					}
					if(inf==1){											//图文
						String[] dmid=db.getReContent(reId).split("\\|");
						Data4Item[] dm=new Data4Item[dmid.length];
						ArrayList<News>array=db.getPicMusById(dmid);
						for(int i=0;i<array.size();i++){
							if("首页".equals(str)) //首页  设置-1
								array.get(i).setPty("-1");
							String str2 = "";
							CryptoTools tools;
							try {
								tools = new CryptoTools();
							       String time= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
								str2="1|1|"+time.split("-")[0]+"|"+time.split("-")[1]+"|"+time.split("-")[2]+"|0|"+msg.getToUserName()+"|"+db.getIdByOpenid(msg.getFromUserName()) +"|"+msg.getFromUserName();
							int a=array.get(i).getPty().length()+array.get(i).getPid().length();
								str2=URLEncoder.encode(tools.encode(str2.length()+a+"|"+str2),"utf-8");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								log.error(e);rebackText("系统错误Exception！",msg);
							}
						dm[i] = new Data4Item(array.get(i).getTitle(), array.get(i).getMain(), (array.get(i).getUrlo().startsWith("http:")?"":vari.wa_pic_path)+array.get(i).getUrlo(), vari.url+"?pty="+array.get(i).getPty()+"&pid="+array.get(i).getPid()+"&rdm="+str2); 
						}
						rebackImage(dm,msg);
					}
					if(inf==2){											//音频
						String musid[]=new String[1];
						 musid[0]=db.getReContent(reId).split("\\|")[0];
						ArrayList<News>array=db.getPicMusById(musid);
						rebackMusic(array.get(0).getTitle(),array.get(0).getMain(),array.get(0).getUrl(),msg);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("系统错误SQLException！",msg);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("系统错误IllegalAccessException！",msg);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("系统错误IllegalArgumentException！",msg);
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("系统错误InvocationTargetException！",msg);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("系统错误ClassNotFoundException！",msg);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("系统错误InstantiationException！",msg);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("系统错误NoSuchMethodException！",msg);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("系统错误SecurityException！",msg);
				}

				//回复一条消息
//				Data4Item d1 = new Data4Item("Play", "take a try", "http://teacher.3xy.com.cn/sxyAdminData/images/resource/jpg/200805/20080517154256287.jpg", "www.qq.com"); 
//				Data4Item d2 = new Data4Item("Again", "one more time", "http://t3.baidu.com/it/u=1442812906,3291929699&fm=21&gp=0.jpg", "www.qq.com");      
//				Msg4ImageText mit = new Msg4ImageText();
//				mit.setFromUserName(msg.getToUserName());
//				mit.setToUserName(msg.getFromUserName()); 
//				mit.setCreateTime(msg.getCreateTime());
//				mit.addItem(d1);
//				mit.addItem(d2);
//				mit.setFuncFlag("0");  
//				session.callback(mit);
				
			}
			public void rebackImage(Data4Item[] dm, Msg msg) {
				String time= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				//msg.getToUserName(),msg.getClass().getName()
				boolean flag=msg.getClass().getName().equals("org.marker.weixin.msg.Msg4Text");
				db.addChatrecorder(msg.getFromUserName(),msg.getToUserName(),flag?((Msg4Text)msg).getContent():((Msg4Event)msg).getEvent(),flag? "0":"4","[图文1]","1",time);
				Msg4ImageText mit = new Msg4ImageText();
				mit.setFromUserName(msg.getToUserName());
				mit.setToUserName(msg.getFromUserName());
				mit.setCreateTime(msg.getCreateTime());
				for (Data4Item d : dm)
					mit.addItem(d);
				mit.setFuncFlag("0");
			//	Msg4Image
			   session.callback(mit);
			}

			public void rebackImage(Data4Item dm, Msg msg) {
				String time= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				boolean flag=msg.getClass().getName().equals("org.marker.weixin.msg.Msg4Text");
				db.addChatrecorder(msg.getFromUserName(),msg.getToUserName(),flag?((Msg4Text)msg).getContent():"[事件]"+((Msg4Event)msg).getEvent(),flag? "0":"4","[图文2]"+dm.getDescription(),"1",time);
				Msg4ImageText mit = new Msg4ImageText();
				mit.setFromUserName(msg.getToUserName());
				mit.setToUserName(msg.getFromUserName());
				mit.setCreateTime(msg.getCreateTime());
				mit.addItem(dm);
				mit.setFuncFlag("0");
				session.callback(mit);
			}

			public void rebackText(String reback, Msg msg) {
				// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date());
				boolean flag=msg.getClass().getName().equals("org.marker.weixin.msg.Msg4Text");
				db.addChatrecorder(msg.getFromUserName(), msg.getToUserName(), flag?((Msg4Text)msg).getContent():"[事件]"+((Msg4Event)msg).getEvent(),flag? "0":"4", reback, "0", time);
				Msg4Text rmsg = new Msg4Text();
				rmsg.setFromUserName(msg.getToUserName());
				rmsg.setToUserName(msg.getFromUserName());
				rmsg.setFuncFlag("0");
				rmsg.setContent(reback);
				session.callback(rmsg);
			}
			public void rebackMusic(String title,String main,String url,Msg msg){
				String time= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				boolean flag=msg.getClass().getName().equals("org.marker.weixin.msg.Msg4Text");
				db.addChatrecorder(msg.getFromUserName(),msg.getToUserName(),flag?((Msg4Text)msg).getContent():"[事件]"+((Msg4Event)msg).getEvent(),flag? "0":"4","[音频]"+title,"2",time);
				Msg4Music mm=new Msg4Music();
				mm.setCreateTime(msg.getCreateTime());
				mm.setDescription(main);
				mm.setFromUserName(msg.getToUserName());
				mm.setToUserName(msg.getFromUserName());
				mm.setFuncFlag("0");
			//	log.info(new allapi().Music(str.split("，")[0],str.split("，")[1]));
				mm.setHQMusicUrl(url);
				mm.setTitle(title);
				session.callback(mm);
			}
			
			@Override
			public void onLocationMsg(Msg4Location msg){
				log.info("收到微信消息x："+msg.getLocation_X());
				log.info("收到微信消息y："+msg.getLocation_Y());
				log.info("发送方:"+msg.getFromUserName());
				log.info("接收方:"+msg.getToUserName());
				try {
					db.setDatabase(db.getPubDatabase(msg.getToUserName()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}
//				log.info("url:"+request.getRequestURI());
				String str=new ChooseMsg().onMsg(msg);
				String user=msg.getFromUserName();
				try {
					if(db.isVis(user))
						db.addParamLoca(user,str);
					else 
							db.addUser(user);
				Class class2= Class.forName("wxtry.Allapi");	
		        Object object = (Allapi)class2.newInstance();      //暂定位82
		    	Method method= class2.getMethod("Near",Msg.class,DefaultSession.class);
		    	method.invoke(object,msg,session);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("系统错误SQLException！",msg);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					log.error(e);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					log.error(e);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					log.error(e);
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					log.error(e);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					log.error(e);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}
				}
				
				
			
		});
		
                //如果不调用close方法，将会出现响应数据串到其它Servlet中。
		session.process(is, os);//处理微信消息： 输入流先判断类型，调用handle中对应方法（包含输出信息）
		session.close();
	}
	

}


