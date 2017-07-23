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
function time(a){
var b=document.getElementById("btn7");
var c=document.getElementById("btn77");
   if(a==0){
      b.disabled = false;
      c.disabled = false;
    c.value = "获取短信";
      b.style.background="rgb(216, 120, 62)";
   }
   else{
      a--;
   c.value = a+"秒";
   setTimeout(function() {
                time(a)
            },
            1000)
   }
}

$(function(){
 var yzm;
      $('.btn7.right').click(function(){
          if($('#phone').val()==""||$('#phone').val().length!=11){alert("请输入正确的手机号码！");return;}
           else{
             $.get("Register",{phone:$('#phone').val(),forgetp:"forget"},function(data){
                           if(data!="11"){yzm=data;
                            $('.btn7').attr("disabled",true);
                            $('#btn77').attr("disabled",true);
                              $('.btn7').css("background-color","rgb(153, 153, 153)");
                              $('#phone').attr("readonly",true);
                             var a = 60;
							time(a);
                               alert("短信已发送！");
                                }
                             else alert("该手机号未注册或验证过于频繁！");
              });
          }
       });
        $('.btn1').click(function(){
            if($('#phone').val()==""){alert("请输入手机号！");return;}
            if($('#yzm').val()==""){alert("请输入验证码！");return;}
                else if($('#yzm').val()!=yzm){alert("请输入正确的验证码！");return;}
                $('#loginForm').submit();
        });
       
       
});
</script>
</head>

<body>
    <div class="container">
        <form id="loginForm" class="login-form" action="Forgetpass" method="get">
            <div class="input-box"><label>手机号码</label>:
            <input type="number" required id="phone" name="phone" placeholder="注册的手机号"/></div>
            <div class="input-box left" style="width:46%;"><label>验证码:</label>
            	<input type="text" required name="yzm" id="yzm" placeholder="验证码" style="width:40%;"/>
            </div>
            <div class="button btn7 right" style="width:46%;" id="btn7">
            <input type="button" name="" id="btn77" value="获取短信" /> </div>
            <div class="button btn1"><input type="button" name="" value="下一步" /></div>
        </form>
    </div>
</body>
</html>

