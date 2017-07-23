<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.net.URLDecoder"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><html><head>
<meta charset="utf-8">
<title>报名</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" type="text/css" href="css/style.css" media="all">
<script type="text/javascript" src="js/jquery1.8.2.min.js"></script>
<script>
	window.onload=function(){
		document.getElementById('share_btn').onclick=function(){
			document.getElementById('share_intro').style.display='block';
		}
		document.getElementById('share_intro').onclick=function(){
			this.style.display='none';
		}
		document.getElementById('rank').onclick=function(){
			document.getElementById('rank_box').style.display='block';
		}
		document.getElementById('close').onclick=function(){
			this.parentNode.style.display='none';
		}
		
	}
	$(function(){
	var info='<%=request.getAttribute("info")==null?"":URLDecoder.decode((String)request.getAttribute("info"),"utf-8")%>'
	if(info!='')alert(info);
	
	var pages=1;
	   $('.moresuser').click(function(){
	     var ul=$(this).parent().children()[0];
	       $.get("./p_suser.jsp",{pagesgame:pages+1},function(data){
	       pages++;
	       if(data.trim()==''){alert('已加载完毕');}
	         $(ul).append(data);
	       });
	   
	   });
	
	
	
	});
	
	
</script>
<c:if test="${status=='micro' }">
    <script type="text/javascript">
    var imgUrl = "图片地址";
        var lineLink = "网址";
        var descContent = '爱在五月，\n\n妈咪爱1+1亲子健康之旅开启全国行首站----重庆站妈咪爱活性益生菌';
        var shareTitle = '标题';
        var appid = '';
         
        function shareFriend() {
            WeixinJSBridge.invoke('sendAppMessage',{
                "appid": appid,
                "img_url": 'http://192.168.30.111:8080/Fdc/images/bg0.png',
                "img_width": "200",
                "img_height": "200",
                "link": window.location.href+"&openid=<%=request.getAttribute("userid")%>&sname=<%=request.getAttribute("sname")==null?"":request.getAttribute("sname")%>",
                "desc": '',
                "title": '助力登山，拿大奖'
            }, function(res) {
                //_report('send_msg', res.err_msg);
            })
        }
        function shareTimeline() {
            WeixinJSBridge.invoke('shareTimeline',{
                "img_url":'http://192.168.30.111:8080/Fdc/images/bg0.png',
                "img_width": "200",
                "img_height": "200",
                "link": window.location.href+"&openid=<%=request.getAttribute("userid")%>&sname=<%=request.getAttribute("sname")==null?"":URLDecoder.decode((String)request.getAttribute("sname"),"utf-8")%>",
                "desc":'',
                "title": '助力登山，拿大奖！'
            }, function(res) {
                   //_report('timeline', res.err_msg);
            });
        }
        function shareWeibo() {
            WeixinJSBridge.invoke('shareWeibo',{
                "content": descContent,
                "url": lineLink,
            }, function(res) {
                //_report('weibo', res.err_msg);
            });
        }
        // 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
        document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
            // 发送给好友
            WeixinJSBridge.on('menu:share:appmessage', function(argv){
                shareFriend();
            });
            // 分享到朋友圈
            WeixinJSBridge.on('menu:share:timeline', function(argv){
                shareTimeline();
            });
            // 分享到微博
            WeixinJSBridge.on('menu:share:weibo', function(argv){
                shareWeibo();
            });
        }, false);
    </script>
    </c:if>
</head>
<body>

	<div style="position:relative;padding-bottom:50px;">
		<img src="images/bg0.png" style="width:100%;"/>
		<a href="javascript:void(0)" id="share_btn" style="display:block;position:absolute;bottom:80px;left:50%;margin-left:-100px;width:120px;text-align:center;height:35px;border-radius:5px;background:#efb000;color:#fff;line-height:35px;">邀请小伙伴助力</a>
	
	</div>
	<div style="position:fixed;bottom:0;left:0;height:50px;background:#000;width:100%;">
		<ul style="color:#fff" class="nav">
			<li>&nbsp;&nbsp;首页&nbsp;&nbsp;</li>
			<li id="rank">&nbsp;排行榜&nbsp;</li>
			<li>中奖名单</li>
			<li>游戏说明</li>
		</ul>
	</div>
	<div class="share_intro" id="share_intro"></div>
	<div class="rank-box" id="rank_box" style="display:none;position:fixed;top:100px;left:10%;box-sizing:border-box;width:80%;background:rgba(255,255,255,0.5);border-radius:5px;padding:20px;">
		<span>姓名：${user.name }  当前助力：${user.numbersvote }</span>
		<ul>
			<jsp:include page="p_suser.jsp"/>
		
		</ul>
		<button class="moresuser">更多</button>
		<div id="close">
			<span></span>
			<span></span>
		</div>
	</div>
</body>
</html>