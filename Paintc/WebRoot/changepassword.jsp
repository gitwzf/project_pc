<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>修改密码</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<link rel="stylesheet" href="css/nav.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript">
   $(function(){
     $('.btn4').click(function(){
          if($('#phone').val()==""){alert("手机号不能为空！");return;}
          if($('#password1').val()==""){alert("旧密码不能为空！");return;}
          if($('#password2').val()==""){alert("新密码不能为空！");return;}
          if($('#password2').val()!=$('#password3').val()){alert("两次新密码不一样！");return;}
          
          $.post("Repassword",{phone:$('#username').val(),password1:$('#password1').val(),password2:$('#password2').val()},function(data){
              if(data=="00"){alert("修改成功，请重新登录");location.href="./Login?action=getout";}
              else alert("密码输入错误！");
          });
     
     });
     
     $('.btn3').click(function(){
         location.href="./Personalcenter";
      });
   });
</script>
</head>

<body>
    <div class="container">
    	<div  style="margin:20px auto 10px;width:150px;">
    		<img src="images/photo1.png" style="width:120px;margin:auto;"/>
    	</div>
        <form id="loginForm" class="login-form form" action="Repassword" method="post">
	        <div class="input-box">
	        	<label>手机号码</label>:
	            <input type="text" required id="username" name="username" placeholder="手机号" value="${manager.phone}" readonly/>
	        </div>
            <div class="input-box">
            	<label style="letter-spacing:5px";>旧密码</label>:
            	<input type="password" required id="password1" name="password1" placeholder="旧密码" />
            </div>
            <div class="input-box">
            	<label style="letter-spacing:5px";>新密码</label>:
            	<input type="password" required id="password2" name="password2" placeholder="新密码" />
            </div>
            <div class="input-box">
            	<label>确认密码</label>:
            	<input type="password" required id="password3" name="password3" placeholder="确认密码" />
            </div>
            <div class="button btn3 left">
            	<input type="button" required name="" value="取消" /> 
            </div>
            <div class="button btn4 right">
            	<input type="button" required name="" value="修改" />
            </div>
        </form>
        <jsp:include page="menu.jsp" flush="true"/> 
   
    
</body>
</html>
