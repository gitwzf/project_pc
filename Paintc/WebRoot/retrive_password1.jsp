<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>找回密码</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript">
   $(function(){
      $('#username').attr("readonly",true);
   
     $('.btn1').click(function(){
          if($('#password1').val()==""){alert("新密码不能为空！");return;}
          if($('#password2').val()==""){alert("重复密码不能为空！");return;}
           if($('#password2').val()!=$('#password1').val()){alert("两次密码不一样！");return;}
           
          });
     
     });
     
</script>
</head>

<body>
    <div class="container">
        <form id="loginForm" class="login-form" action="Login" method="post">
            <div class="input-box"><label>手机号码</label>:
            <input type="number" required id="username" name="username" placeholder="手机号" value="${uss}" /></div>
            <div class="input-box"><label style="letter-spacing:15px";>密码</label>:
            <input type="password" required id="password1" name="password" placeholder="密码" /></div>
            <div class="input-box"><label>确认密码</label>:
            <input type="password" required id="password2" name="password2" placeholder="确认密码" /></div>
            <div class="button btn1"><input type="submit" name="" value="完成" /></div>
        </form>
    </div> 
</body>
</html>
