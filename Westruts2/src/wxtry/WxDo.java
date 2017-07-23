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
	
	
	//TOKEN ������΢��ƽ̨����ģʽ�����õ�Ŷ
	public static final String TOKEN = "D8yuc";

	
	
	/**
	 * ����΢�ŷ������������ĸ�����Ϣ���������ı���ͼƬ������λ�á����ֵȵ�
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
			
			@Override  //��д
			//�˵��¼�
			public void onEventMsg(Msg4Event msg) {
				log.info("�յ�΢����Ϣ��"+msg.getEvent());
				log.info("���ͷ�:"+msg.getFromUserName());
				log.info("���շ�:"+msg.getToUserName());
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
										log.error(e);rebackText("ϵͳ����Exception��",msg);
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
						 else {               //����Ĭ�ϻظ� -1
							 Class class2=Class.forName("wxtry.Allapi");
							 Method method = class2.getMethod("Test3",Msg.class,DefaultSession.class);
						        Object object=(Allapi)class2.newInstance();
						        method.invoke(object,msg,session);}
						
					}
					 else if("unsubscribe".equals(msg.getEvent())){
						 db.setOpenIdval(msg.getFromUserName(),"0");
					 }
					 else if("11".equals(msg.getEventKey())){          //�������
						 try {
							db.updateStr(user,20);
							reback="�Ҹ�������,ף�����������/::),����������ͼ�������~";
							rebackText(reback, msg);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							log.error(e);rebackText("ϵͳ����SQLException��",msg);
						}
					 }
					 else if("12".equals(msg.getEventKey())){           //��������
}
					 else if("21".equals(msg.getEventKey())){           //ʱͼ����
						 try {
							db.updateStr(user,21);
							rebackText("/:?Ҫ����ʲô������", msg);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							log.error(e);rebackText("ϵͳ����SQLException��",msg);
						}
					 }
					 
					 else if("31".equals(msg.getEventKey())){           //ˬ������
						 try {
							db.updateStr(user,23);
							rebackText("��������������ֿ�ѡ���պŸ��������磨���¼�ʱ�� ���ƣ�/:8-)", msg);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							log.error(e);rebackText("ϵͳ����SQLException��",msg);
						}
					 }
					 
					 else if("41".equals(msg.getEventKey())){           //����
						 try {
							db.updateStr(user,24);
							rebackText("�����������/:sun", msg);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							log.error(e);
						}
					 }
					 else if("42".equals(msg.getEventKey())){           //�𳵰��
						 try {
							db.updateStr(user,25);
							rebackText("�밴��ʽ�������վ,����վ�Լ����գ��籱���ϣ��Ϻ���3-4��/:8-)", msg);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							log.error(e);
						}
					 } 
					 else if("43".equals(msg.getEventKey())){           //Ӣ���ʵ�
						 try {
							db.updateStr(user,26);
							rebackText("������/:ok", msg);
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
					rebackText("ϵͳ����Exception��",msg);return;
					
				}
			}

			@Override
			public void onTextMsg(Msg4Text msg) {
				log.info("�յ�΢����Ϣ��"+msg.getContent());
				log.info("���ͷ�:"+msg.getFromUserName());
				log.info("���շ�:"+msg.getToUserName());
				log.info("����ʱ��:"+msg.getCreateTime());
				String createtime=msg.getCreateTime();
				 String str=msg.getContent();
				String user=msg.getFromUserName();
				String mode="";
			//	String retime=""+new Date().getTime();
				try {
					db.setDatabase(db.getPubDatabase(msg.getToUserName()));
					//û�еĻ����openid   //�жϡ�fakeid�� ��openid�Ƿ���ͬ
					if(!db.isaddId(user)){//��ͬfalse
						log.info("11");
						 //��ͬ���ȽϷ������ݸ�openidģ���½  ��ȡ��fakeid������  ��Ӧfakeid
						if(!db.addOpenid(user))
							 rebackText("�뷢���������ݻ����֤����", msg);
							 
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
		               reId=Integer.parseInt(db.getMode(user));//��ȡ�˵���
		               if(reId<=0)
		            	   reId=db.getDefault();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("ϵͳ����SQLException��",msg);return;
				}
//				if("��".equals(msg.getContent())){
//					Msg4Text rmsg =	new Msg4Text();
//					rmsg.setFromUserName(msg.getToUserName());
//					rmsg.setToUserName(msg.getFromUserName());
//					rmsg.setFuncFlag("0");
//					rmsg.setContent("����!");
//					session.callback(rmsg);
//					return;
//				} 
				
				try {
					int inf=db.getType0ById(reId);
					log.info("inf="+inf);
					log.info("reId="+reId);
					if(inf==-1){                                        //�ӿ�
						 Class class2=Class.forName("wxtry.Allapi");
					        Object object=(Allapi)class2.newInstance();
					    	Method method = class2.getMethod(db.getReContentById(reId),Msg.class,DefaultSession.class);
					        method.invoke(object,msg,session);
//					        rebackText((String)method.invoke(object,msg), msg);
					}
					
					if(inf==0){                                          //����
						rebackText(db.getReContent(reId),msg);
					}
					if(inf==1){											//ͼ��
						String[] dmid=db.getReContent(reId).split("\\|");
						Data4Item[] dm=new Data4Item[dmid.length];
						ArrayList<News>array=db.getPicMusById(dmid);
						for(int i=0;i<array.size();i++){
							if("��ҳ".equals(str)) //��ҳ  ����-1
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
								log.error(e);rebackText("ϵͳ����Exception��",msg);
							}
						dm[i] = new Data4Item(array.get(i).getTitle(), array.get(i).getMain(), (array.get(i).getUrlo().startsWith("http:")?"":vari.wa_pic_path)+array.get(i).getUrlo(), vari.url+"?pty="+array.get(i).getPty()+"&pid="+array.get(i).getPid()+"&rdm="+str2); 
						}
						rebackImage(dm,msg);
					}
					if(inf==2){											//��Ƶ
						String musid[]=new String[1];
						 musid[0]=db.getReContent(reId).split("\\|")[0];
						ArrayList<News>array=db.getPicMusById(musid);
						rebackMusic(array.get(0).getTitle(),array.get(0).getMain(),array.get(0).getUrl(),msg);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("ϵͳ����SQLException��",msg);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("ϵͳ����IllegalAccessException��",msg);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("ϵͳ����IllegalArgumentException��",msg);
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("ϵͳ����InvocationTargetException��",msg);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("ϵͳ����ClassNotFoundException��",msg);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("ϵͳ����InstantiationException��",msg);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("ϵͳ����NoSuchMethodException��",msg);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("ϵͳ����SecurityException��",msg);
				}

				//�ظ�һ����Ϣ
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
				db.addChatrecorder(msg.getFromUserName(),msg.getToUserName(),flag?((Msg4Text)msg).getContent():((Msg4Event)msg).getEvent(),flag? "0":"4","[ͼ��1]","1",time);
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
				db.addChatrecorder(msg.getFromUserName(),msg.getToUserName(),flag?((Msg4Text)msg).getContent():"[�¼�]"+((Msg4Event)msg).getEvent(),flag? "0":"4","[ͼ��2]"+dm.getDescription(),"1",time);
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
				db.addChatrecorder(msg.getFromUserName(), msg.getToUserName(), flag?((Msg4Text)msg).getContent():"[�¼�]"+((Msg4Event)msg).getEvent(),flag? "0":"4", reback, "0", time);
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
			
			@Override
			public void onLocationMsg(Msg4Location msg){
				log.info("�յ�΢����Ϣx��"+msg.getLocation_X());
				log.info("�յ�΢����Ϣy��"+msg.getLocation_Y());
				log.info("���ͷ�:"+msg.getFromUserName());
				log.info("���շ�:"+msg.getToUserName());
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
		        Object object = (Allapi)class2.newInstance();      //�ݶ�λ82
		    	Method method= class2.getMethod("Near",Msg.class,DefaultSession.class);
		    	method.invoke(object,msg,session);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);rebackText("ϵͳ����SQLException��",msg);
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
		
                //���������close���������������Ӧ���ݴ�������Servlet�С�
		session.process(is, os);//����΢����Ϣ�� ���������ж����ͣ�����handle�ж�Ӧ���������������Ϣ��
		session.close();
	}
	

}


