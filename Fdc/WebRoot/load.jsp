<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.net.URLDecoder"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html><head>
<meta charset="utf-8">
<title>助力取房</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" type="text/css" href="css/base.css" media="all">
<link rel="stylesheet" type="text/css" href="css/login.css" media="all">
<script type="text/javascript" src="js/jquery1.8.2.min.js"></script>
<script type="text/javascript">
function  reloadcode(){
	$('#code').attr("src","Yzm?t=1");
	} 
	   function isyzm(obj){
	   var objv=obj.value.toLowerCase();
	   if(objv!='')
	   $.post("Yzm",function(data){
	      if(data!=objv){alert('验证码不正确！');obj.value='';}
	    //  else alert('验证码正确！');
	   })
	   }
	   
	   function isok(){
	   if($('#tel').val()==''||isNaN($('#tel').val())||$('#tel').val().length!=11){alert('请输入正确的手机号');return false;}
	   if($('#name').val()==''){alert('请输入姓名');return false;}
	   return true;
	   }

    $(function(){
     $('.istel').blur(function(){
     var tel=$('.istel').val();
     if(tel!='')
        $.post("./login.do",{tel:,type:'istelunique'},function(data){
        data=data.trim();
            if(data=='00'){alert('该手机号已注册，若是登录，请继续');}
        },'text');
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
                "img_url": $('.startpic').val(),
                "img_width": "200",
                "img_height": "200",
                "link": window.location.href+"&openid="+${user.id}+"&sname="+${user.name},
                "desc": '',
                "title": '助力登山'
            }, function(res) {
                //_report('send_msg', res.err_msg);
            })
        }
        function shareTimeline() {
            WeixinJSBridge.invoke('shareTimeline',{
                "img_url": $('.startpic').val(),
                "img_width": "200",
                "img_height": "200",
                "link": window.location.href+"&openid="+${user.id}+"&sname="+${user.name},
                "desc":'',
                "title": '助力登山'
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
	<div class="container bg" style="min-height:480px;">
		<div>
			<img src="images/text.png" style="width:100%">
		</div>
		<div class="wrapper v-align">
			
			<form action="./login.do?type=registertoo" method="post">
				<div class="login-form">
					<div class="item item-account">
						<span class="account">账号：</span>
						<input type="text" name="name" id="name" placeholder="请输入您的姓名">
					</div>
					<div class="item item-passwd">
						<span class="pwd">号码：</span>
						<input type="text" name="tel" id="tel" class="istel" placeholder="请输入您的手机号码">
					</div>
					<div class="btn item-login">
					<c:if test="${empty sgame}">
						<input type="submit" value="报&nbsp;&nbsp;&nbsp;&nbsp;名" class="login" onclick="return isok()">
				</c:if>
				<c:if test="${!empty sgame}">
						<input type="submit" value="报名并助力${sgame.name }" class="login" onclick="return isok()">
				</c:if>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>