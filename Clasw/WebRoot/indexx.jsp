<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.wzf.pubvari.Variable" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
	<meta property="qc:admins" content="550327600471445346316110063757" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>首页</title>
		<link href="css/base.css" rel="stylesheet" type="text/css" />
		<link href="css/index.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.7.2.min.js" ></script>
	<script type="text/javascript" src="js/windows.js"></script>
		<script type="text/javascript">
          $(function(){
            if(${!empty lookfor}){  //查询选项
            $('#group option[value='+${lookfor.group}+']').attr("selected",true);
         $('#period option[value='+${lookfor.period}+']').attr("selected",true);
           $('#time option[value='+${lookfor.time}+']').attr("selected",true);
          $('#class_addr option[value='+'${lookfor.class_addr}'+']').attr("selected",true);
             
            }
          
          
          });
</script>
	</head>
	<body>
	<%Variable vari=new Variable(); %>
		<div id="container">
			<div id="banner">
			<!-- 	<a href="javascript:" class="collect" onClick="jscript:window.external.AddFavorite(document.location.href,document.title);"></a>
			-->
			</div>
			<div id="nav">
				<ul>
					<li><a href="./index.jsp">首页</a></li>
					<li><a href="./course0.jsp">我的课程</a></li>
					<li><a href="./information1.jsp">课程介绍</a></li>
					<li><a href="./information2.jsp">报名须知</a></li>
					<li><a href="./message0.jsp">留言板</a></li>
					<li><a href="./message0.jsp"> </a></li>
					<li><a href="./message0.jsp"> </a></li>
					<li><a href=""></a></li>
					<li><a href="https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=<%=vari.appid %>&redirect_uri=<%=vari.redirect_uri %>"><img src="./image/qq1.png"/></a></li>
				</ul>
			</div>
			<div id="sidebar">
				<div class="login">
				<div class="topbar"><span>账号</span></div>
						<div class="logincontent">
					<c:if test="${empty user}"><form action="Login" method="post">
							<label>账号:</label>
							<input type="text" name="username" class="box" value="${clientuser}"/><br/>
							<label>密码:</label>
							<input type="password" name="password"  class="box"/><br/>
							<input type="checkbox" name="rember" value="1" id="remember">记住账号
							<input type="checkbox" name="saveTime" value="1" id="saveTime"> 自动登陆
							<a href="./register.jsp" class="register">注册</a>
							<input type="submit" value="登录" name="submit" class="submitlogin" />
							<div class="clear"></div>
						</form>	
						<!-- <p><a href="" class="find">找回密码</a></p> --></c:if>
						<c:if test="${!empty user}">
						<div>  hi，${user.username}</div>
						${user.sex}
						<img src="${user.picurl }">
					<div>	<a href="./register.jsp" class="sub">编辑资料</a></div>
					<p><a href="./quit.jsp" class="find">退出登陆</a></p>
						</c:if>
					</div>  
				</div> 
				<div class="notice">
					<div class="topbar"><span>公告</span></div>
					<div class="noticecontent">
						<ul>
						<c:forEach items="${annlist}" var="ann">
							<li><a href="./information0.jsp?id=${ann.id}"><span>·</span>${ann.title}</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<a href="javascript:void(0)" onclick="postToWb();" class="tmblog">腾讯微博</a> 
				
				<a target="_self" onclick="(function(){var url = 'link=http://news.163.com/&amp;source='+ encodeURIComponent('网易新闻')+ '&amp;info='+ encodeURIComponent(document.title) + ' ' + encodeURIComponent(document.location.href);window.open('http://t.163.com/article/user/checkLogin.do?'+url+'&amp;'+new Date().getTime(),'newwindow','height=330,width=550,top='+(screen.height-280)/2+',left='+(screen.width-550)/2+', toolbar=no, menubar=no, scrollbars=no,resizable=yes,location=no, status=no');})()" href="javascript:void(0);"><img height="19px" border="0" align="absMiddle" alt="分享到网易微博" title="分享到网易微博" />网易微博</a>  
				
				<a  href="javascript:void((function(s,d,e,r,l,p,t,z,c){var f='http://t.sohu.com/third/post.jsp?',u=z||d.location,p=['&url=',e(u),'&title=',e(t||d.title),'&content=',c||'gb2312','&pic=',e(p||'')].join('');function%20a(){if(!window.open([f,p].join(''),'mb',['toolbar=0,status=0,resizable=1,width=660,height=470,left=',(s.width-660)/2,',top=',(s.height-470)/2].join('')))u.href=[f,p].join('');};if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a();})(screen,document,encodeURIComponent,'','','','','','utf-8'));" title="分享到搜狐微博" class="s3">分享到搜狐微博 </a> 
			
			<a  href="javascript:void(function(){var%20d=document,e=encodeURIComponent,s1=window.getSelection,s2=d.getSelection,s3=d.selection,s=s1?s1():s2?s2():s3?s3.createRange().text:' ',r='http://www.douban.com/recommend/?url='+e(d.location.href)+'&title='+e(d.title)+'&sel='+e(s)+'&v=1',x=function(){if(!window.open(r,'douban','toolbar=0,resizable=1,scrollbars=yes,status=1,width=450,height=330'))location.href=r+'&r=1'};if(/firefox/.test(navigator.userAgent)){setTimeout(x,0)}else{x()}})()" title="分享到豆瓣网" class="s6">分享到豆瓣网 </a> 
			</div>
			<div id="main">
				<div class="process"></div>
				<div class="select">
					<form action="Login" method="get"> 
						<select name="group" id="group"> 
						<option value="0">所有组</option> 
						<option value="1">少年组</option> 
						<option value="2">初中组</option> 
						<option value="3">成人组</option> 
						<option value="4">港北组</option> 
						</select> 
					
						<select name="period" id="period"> 
						<option value="0">所有期次</option> 
						<option value="1">第一期</option> 
						<option value="2">第二期</option> 
						<option value="3">第三期</option> 
						</select> 
					
						<select name="time" id="time"> 
						<option value="0">所有时间段</option> 
						<option value="1">上午</option> 
						<option value="2">下午</option> 
						<option value="3">晚上</option>
						</select> 
						<select name="class_addr" id="class_addr"> 
						<option value="0">所有地点</option> 
						<option value=玉环游泳池>玉环游泳池</option> 
						<option value=拱北游泳池>拱北游泳池</option> 
						<option value=玉环中学>玉环中学</option> 
						</select> 
						<input type="submit" value="查询" style="width:80px">
					</form> 
					<div class="clear"></div>
				</div>
				<div class="list">
					<ul>
						<li class="thead">
							<dd class="rowone">期数</dd>
							<dd class="rowtow">组别</dd>
							<dd class="rowthree">上课时间</dd>
							<dd class="rowfour">上课地点</dd>
							<dd class="rowfive">剩余名额</dd>
							<dd class="rowsix">报名</dd>
						</li>
						<c:forEach items="${classlist}" var="class">
						<li class="tbody">
							<dd class="rowone"><c:if test="${class.period==1}">第一期</c:if><c:if test="${class.period==2}">第二期</c:if><c:if test="${class.period==3}">第三期</c:if></dd>
							<dd class="rowtow"><c:if test="${class.group==1}">少年组（8-14周岁）</c:if><c:if test="${class.group==2}">初中组（14-16周岁）</c:if><c:if test="${class.group==3}">成人组</c:if><c:if test="${class.group==4}">港北组</c:if></dd>
							<dd class="rowthree">${class.time_beg}-${class.time_end}</dd>
							<dd class="rowfour">&nbsp;${class.class_addr}&nbsp;</dd>
							<dd class="rowfive">${class.maxpeople-class.residue}</dd>
							<dd class="rowsix"><c:if test="${(class.maxpeople-class.residue)>0}"><c:if test="${class.status==0}"><a href="./enroll.jsp?classid=${class.id}" class="apply">报名</c:if><c:if test="${class.status==1}"><a href="" class="full">已报名</c:if></c:if><c:if test="${(class.maxpeople-class.residue)==0}"><a href="" class="full">已满</c:if></a></dd>
						</li>
						</c:forEach>
					
					</ul>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">  
function postToWb(){  
var _t = encodeURI(document.title); 
var _url = encodeURI(document.location);  
var _appkey = encodeURI("appkey");//你从腾讯获得的appkey  
var _pic = encodeURI('');//（列如：var _pic='图片url1|图片url2|图片url3....）  
var _site = '';//你的网站地址  
var _u = 'http://v.t.qq.com/share/share.php?title='+_t+'&url='+_url+'&appkey='+_appkey+'&site='+_site+'&pic='+_pic;  
window.open( _u,'转播到腾讯微博', 'width=700, height=680, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no' );  
}  
</script>  
</html>