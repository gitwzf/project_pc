package wxtry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.marker.weixin.DefaultSession;
import org.marker.weixin.HandleMessageAdapter;
import org.marker.weixin.MySecurity;
import org.marker.weixin.msg.Data4Item;
import org.marker.weixin.msg.Msg;
import org.marker.weixin.msg.Msg4Event;
import org.marker.weixin.msg.Msg4ImageText;
import org.marker.weixin.msg.Msg4Link;
import org.marker.weixin.msg.Msg4Music;
import org.marker.weixin.msg.Msg4Text;

import sun.nio.cs.ext.ISCII91;

public class WxDo extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public WxDo() {  }
	
	
	//TOKEN 是你在微信平台开发模式中设置的哦
	public static final String TOKEN = "D8yuc";

	/**
	 * 处理微信服务器验证
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		String echostr = request.getParameter("echostr");// 随机字符串
		System.out.println("222");
		// 重写totring方法，得到三个参数的拼接字符串
		List<String> list = new ArrayList<String>(3) {
			private static final long serialVersionUID = 2621444383666420433L;
			public String toString() {
				return this.get(0) + this.get(1) + this.get(2);
			}
		};
		list.add(TOKEN);
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);// 排序
		String tmpStr = new MySecurity().encode(list.toString(),
				MySecurity.SHA_1);// SHA-1加密
		Writer out = response.getWriter();
		if (signature.equals(tmpStr)) {
			out.write(echostr);// 请求验证成功，返回随机码
		} else {
			out.write("");
		}
		out.flush();
		out.close();
	}

	
	/**
	 * 处理微信服务器发过来的各种消息，包括：文本、图片、地理位置、音乐等等
	 * 
	 * 
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InputStream is = request.getInputStream();
		OutputStream os = response.getOutputStream();
		
		final Dbcon db=new Dbcon();
		final DefaultSession session = DefaultSession.newInstance(); 
		
		session.addOnHandleMessageListener(new HandleMessageAdapter(){
			
			@Override  //重写
			public void onEventMsg(Msg4Event msg) {
				String reback="";
				 if("subscribe".equals(msg.getEvent())){
					reback="感谢您的关注，我们会做得更好！";
				}
				 else if("unsubscribe".equals(msg.getEvent()));
				 else if("11".equals(msg.getEventKey())){reback="美女点击";}
				 else if("12".equals(msg.getEventKey())){reback="帅哥点击";}
				 else if("13".equals(msg.getEventKey())){reback="随便点击";}
				 else if("21".equals(msg.getEventKey())){reback="娱乐点击";}
				 else if("22".equals(msg.getEventKey())){reback="国家点击";}
				 else if("23".equals(msg.getEventKey())){reback="社会点击";}
				 else if("31".equals(msg.getEventKey())){reback="动感点击";}
				 else if("32".equals(msg.getEventKey())){reback="轻音乐点击";}
				 else if("33".equals(msg.getEventKey())){reback="网络歌曲点击";}
				 else if("41".equals(msg.getEventKey())){reback="简介点击";}
				 else if("42".equals(msg.getEventKey())){reback="联系我们点击";}
				Msg4Text rmsg =	new Msg4Text();
				rmsg.setFromUserName(msg.getToUserName());
				rmsg.setToUserName(msg.getFromUserName());
				rmsg.setFuncFlag("0");
				rmsg.setContent(reback);
				session.callback(rmsg);	
			}

			@Override
			public void onTextMsg(Msg4Text msg) {
				System.out.println("收到微信消息："+msg.getContent());
				String str=msg.getContent();
				String user=msg.getFromUserName();
				String mode="";
				
				try {
					if(db.isVis(user))
					     db.getTimes(user);
					else {
							db.addUser(user);
					}
					
					if("a".equals(str)||"b".equals(str)||"c".equals(str)||"d".equals(str)||"菜单".equals(str))
						 db.updateStr(user,str);
		         
					  mode=db.getMode(user);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
					if("a".equals(mode)){
						Msg4Text rmsg =	new Msg4Text();
						rmsg.setFromUserName(msg.getToUserName());
						rmsg.setToUserName(msg.getFromUserName());
						rmsg.setFuncFlag("0");
						String reback="看看图听听歌放放松吧~";
						db.addInstruction(user,str);
						if(!"".equals(db.getReback(user)))reback=db.getReback(user);
						rmsg.setContent(reback);
						session.callback(rmsg);
						return;
					}
					if("b".equals(mode)){
						Msg4ImageText mit = new Msg4ImageText();
						mit.setFromUserName(msg.getToUserName());
						mit.setToUserName(msg.getFromUserName()); 
						mit.setCreateTime(msg.getCreateTime());
						ArrayList<ArrayList>array=new ArrayList<ArrayList>();
						if((array=db.getThirdArr(str)).isEmpty()){
						for(int i=1;i<4;i++){
						//	System.out.println("aaaa");
						Data4Item d1 = new Data4Item(db.getTitle(i), db.getMain(i), db.getPicurl(i), db.getLink(i)); 
						mit.addItem(d1);
						}
						}
						else 
							for(int i=0;i<array.size();i++){
								Data4Item d1 = new Data4Item(array.get(i).get(0).toString(), array.get(i).get(1).toString(), array.get(i).get(2).toString(), array.get(i).get(3).toString()); 
								mit.addItem(d1);
								System.out.println("bbbb");
							}
						mit.setFuncFlag("0");  
						session.callback(mit);
					}
					
					if("c".equals(mode)){
						Msg4Music mm=new Msg4Music();
						mm.setCreateTime(msg.getCreateTime());
						mm.setDescription("听轻松音乐，放松心情！");
						mm.setFromUserName(msg.getToUserName());
						mm.setToUserName(msg.getFromUserName());
						mm.setFuncFlag("0");
						mm.setHQMusicUrl(db.getCon(str));
					//	mm.setMusicUrl(db.getCon(str));
						mm.setTitle("Hao");
						session.callback(mm);
					}
					if("d".equals(mode)){
						String weatherInf="请输入你要查询的城市:";
						db.addInstruction(user,str);
						if(db.isChinese(user)){  //判断中文
							FileReader fr = null;
							try {
								fr = new FileReader("d:/Tomcat 6.0/webapps/weixin/weatherCode.txt");
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							String str0="";
							BufferedReader br=new BufferedReader(fr);
							try{
							do{
									str0=br.readLine();
									//System.out.println("str0="+str0);
							}while(!str0.endsWith(str)&&!str0.endsWith("新港"));
								fr.close();
								br.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String weaInfurl="http://flash.weather.com.cn/sk2/shikuang.swf?id="+str0.substring(0,9);
							Data4Item d2 = new Data4Item(str+"天气", "点击查看", "http://pic14.nipic.com/20110425/441846_171438516370_2.jpg", weaInfurl);      
							Msg4ImageText mit = new Msg4ImageText();
							mit.setFromUserName(msg.getToUserName());
							mit.setToUserName(msg.getFromUserName()); 
							mit.setCreateTime(msg.getCreateTime());
							mit.addItem(d2);
							mit.setFuncFlag("0");  
							session.callback(mit);
							
						}
						else weatherInf="请输入中文";
						
						Msg4Text rmsg =	new Msg4Text();
						rmsg.setFromUserName(msg.getToUserName());
						rmsg.setToUserName(msg.getFromUserName());
						rmsg.setFuncFlag("0");
						rmsg.setContent(weatherInf);
						session.callback(rmsg);
						
					}
                    if("菜单".equals(mode)){
						
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Msg4Text rmsg =	new Msg4Text();
				rmsg.setFromUserName(msg.getToUserName());
				rmsg.setToUserName(msg.getFromUserName());
				rmsg.setFuncFlag("0");
				rmsg.setContent("[菜单]\na.随便聊\nb.看时图\nc.听音乐\nd.查天气");
				session.callback(rmsg);
				
				
				
				
				
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
		});
		
		//必须调用这两个方法
                //如果不调用close方法，将会出现响应数据串到其它Servlet中。
		session.process(is, os);//处理微信消息 
		session.close();//关闭Session
	}

}


