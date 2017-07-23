<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>戳我有奖</title>
    <script src="js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/index.js?f=4"></script>
     <link href="css/css.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="css/index.css?y=8">
    
   
</head>
<body>
   <c:if test="${empty user.cjstate}">
    <div class="wrap first clearfix">
		<img src="images/title.png" class="title"/>
        <div class="enroll-form">  
            <div class="form-wapper form-content">  
                <input id="mobile" name="mobile" type="text" tabindex="1" class="ipt-phone"/>
                  <input id="name" name="name" type="text" tabindex="0" class="ipt-name"/>
                <a href="javascript:void(0)" class="form-submit" id="form-submit"></a>
            </div>
        </div>
         <p class="ac-time">${gamerule.title}</p>
		<a href="javascript:void(0)" class="rule">游戏规则</a>
    </div>
    <div class="wrap second" style="display:none;">
    <img src="images/title2.png" class="title"/>
        <div class="expri">
            <img id="expri-img" src="images/win-0.gif" border=0 />
            <div class="tips">奖品就在前方，只需努力戳！戳！戳！</div>
            
        </div>
        <a href="javascript:void(0)" class="rule rule2">游戏规则</a>
		<div class="per-info">
			<p>电话：<span id="in-phone"></span></p>
			<p>手机：<span id="in-name"></span></p>
		</div>
    </div>
    
    </c:if>
   <c:if test="${!empty user.cjstate}">
   <div class="wrap first clearfix" style="display:none;">
		<img src="images/title.png" class="title"/>
        <div class="enroll-form">  
            <div class="form-wapper form-content">  
                <input id="mobile" name="mobile" type="text" tabindex="1" class="ipt-phone"/>
                  <input id="name" name="name" type="text" tabindex="0" class="ipt-name"/>
                <a href="javascript:void(0)" class="form-submit" id="form-submit"></a>
            </div>
        </div>
        <p class="ac-time">${gamerule.title}</p>
		<a href="javascript:void(0)" class="rule">游戏规则</a>
    </div>
    <div class="wrap second" style="display:block;">
    <img src="images/title2.png" class="title"/>
        <div class="expri">
        <c:if test="${empty user.cjstate}">
            <img id="expri-img" src="images/win-0.gif" border=0 />
            <div class="tips">还没签到哦，快来签到吧！</div>
            </c:if>
             <c:if test="${!empty user.cjstate}">
            <img id="expri-img" src="images/win-${user.cjstate+1}.gif" border=0 />
                <c:if test="${user.cjstate==0}"><div class="tips">恭喜你中奖了！耶~主人，快带我回家！</div></c:if>
                <c:if test="${user.cjstate==1}"><div class="tips">恭喜你中奖了！等你好久，终于点到了我！</div></c:if>
                <c:if test="${user.cjstate==2}"><div class="tips">恭喜你中了无敌幸运奖，趁着运气不错，赶快去看看你想要的职位！</div></c:if>
                <c:if test="${user.cjstate==3}"><div class="tips">可惜了，到手的奖飞走了！</div></c:if>
                <c:if test="${user.cjstate==4}"><div class="tips">你领过勒！该去找工作啦！</div></c:if>
                <c:if test="${user.cjstate==-1}"><div class="tips">还有机会哦！</div></c:if>
            </c:if>
            
        </div>
        <a href="javascript:void(0)" class="rule rule2">游戏规则</a>
		<div class="per-info">
			<p>电话：<span id="in-phone"><span style="color:transparent">.</span>${user.cjphone }</span></p>
			<p>姓名：<span id="in-name"><span style="color:transparent">.</span>${user.cjname }</span></p>
		</div>
    </div>
  
   </c:if>
<div class="wrap third" style="display:none">
        <div class="introduce">
			<h2>戳图有奖游戏规则</h2>
			<div>
				${gamerule.content}
			</div>
            
		 <a href="javascript:void(0)" class="ret-btn"></a> 	
        </div>
      
    </div>
	<div class="wrap fourth" style="display:none;">
		<img src="images/title.png" class="title"/>
		<div class="error-tips">
			<p>该微信号已经注册</p>
			<a href="javascript:void(0)" class="que-btn"></a>
		</div>
	</div>
	<jsp:include page="_footer.jsp"/>
	
	
</body>
</html>
