<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>注册</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
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
	   function isunique(obj){
	   var ph=obj.value;
	    $.get("Register",{phone:ph},function(data){
	        if(data=='11'){
	        alert('该手机已注册');$('#phone0').attr("value","");}
	    });
	   }

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
    //  $('.btn7').click(function(){
       //   if($('#phone0').val()==""||$('#phone0').val().length!=11){alert("请输入正确的手机号码！");return;}
       //    else{
       //      $.get("Register",{phone:$('#phone0').val()},function(data){
       //                    if(data!="11"){yzm=data;
       //                     $('.btn7').attr("disabled",true);
        //                    $('#btn77').attr("disabled",true);
        //                      $('.btn7').css("background-color","rgb(153, 153, 153)");
         //                   $('#phone').attr("value",$('#phone0').val());
       //                      var a = 60;
		//					time(a);
         //                      alert("短信已发送！");
         //                       }
         //                    else alert("该手机号已注册或非正常注册！");
         //     });
        //  }
     //  });
        
        $('.btn1').click(function(){
             if($('#parentname').val()==""){alert("请输入家长姓名！");return;}
        	if($('#studentname').val()==""){alert("请输入学生姓名！");return;}
        	if($('#biryear').val()==""||isNaN($('#biryear').val())||$('#biryear').val().length!=4){alert("请输入正确的出生年！");return;}
        	if($('#birmonth').val()==""||isNaN($('#birmonth').val())||$('#birmonth').val()>12){alert("请输入正确的出生月！");return;}
        	if($('#birday').val()==""||isNaN($('#birday').val())||$('#birday').val()>31){alert("请输入正确的出生日！");return;}
        	
        	if($('#biryear').val()/4!=0&&$('#birmonth').val()==2&&$('#birday').val()>28){alert("没有这个日期！");return;}
        	if($('#biryear').val()/4==0&&$('#birmonth').val()==2&&$('#birday').val()>29){alert("没有这个日期！");return;}
        	if($('#birmonth').val()==4||$('#birmonth').val()==6||$('#birmonth').val()==9||$('#birmonth').val()==11)
        	   if($('#birday').val()>30){alert("该月没有这个日期！");return;}
        	if($('#password1').val()!=$('#password2').val()){$('#password2').attr("value","");alert("两次密码不一样！");return;}
            if($('#phone0').val()==""||$('#phone0').val().length!=11||isNaN($('#phone0').val())){alert("请输入正确的手机号！");return;}
            if($('#yzm').val()==""){alert("请输入验证码！");return;}
             //   else if($('#yzm').val()!=yzm){alert("请输入正确的验证码！");return;}
                $('#loginForm').submit();
        });
   
   
   });

</script>

</head>

<body>
	 <div class="container">
        <form id="loginForm" action="Register" method="post" class="form">
            <div class="input-box"><label>家长姓名:</label>
            <input type="text" required id="parentname" name="parentname" placeholder="家长姓名" /></div>
            <div class="input-box"><label>学生姓名:</label>
            	<input type="text" required id="studentname" name="studentname"  placeholder="学生姓名" />
            </div>
            <div style="margin-left:5px;margin-bottom:10px;">
            	<label style="height:35px;line-height:35px;display:inline-block;">学生生日：</label>
             	 <br/><input type="number" required id="biryear" name="biryear" placeholder="年" class="date" style="margin-left:6%;margin-right:3%;"/>
         		 <input type="number" id="birmonth" name="birmonth" placeholder="月" class="date" style="margin-right:3%;"/>
            	 <input type="number" id="birday" name="birday" placeholder="日" class="date" style=""/>
            </div>
            <div style="margin-left:5px;"><label>学生性别：</label>
	            <input type="radio" name="gender" value="1" checked="checked" />男 
	            <input type="radio" name="gender" value="0" />女 
            </div>
            <div class="input-box">
            	<label style="letter-spacing:15px";>密码:</label>
            	<input type="password" required id="password1"name="password"  placeholder="密码" />
            </div>
            <div class="input-box">
            	<label>确认密码:</label>
            	<input type="password" required id="password2" name="password0"  placeholder="确认密码" />
            </div>
            <div class="input-box">
            	<label>手机号码:</label>
            	<input type="number" required id="phone0" name="phone" class="input-box" placeholder="手机" onblur="isunique(this)" />
           <!--   	<input type="hidden" required id="phone" name="phone" class="input-box" placeholder="手机" />-->
            </div>
            <div class="input-box left" style="width:46%;">
            	<label>验证码:</label>
       <!--     	<input type="text" required name="yzm" id="yzm" placeholder="验证码" style="width:40%;"/> --> 
                    <input type="text" name="yzm"  id="yzm" class="box"  value=""  placeholder="验证码" style="width:40%;" onblur="isyzm(this)"/>
          </div>  
            
            <!--  <div class="button btn7 right" style="width:46%;" id="btn7">	<input type="button" name="" id="btn77" value="获取短信" /> -->
            <img src="Yzm?t=1" id="code" onclick="reloadcode()" alt="看不清楚,换一张" style="cursor: pointer;float: left;width: 115px;margin-left: 10px;">
           <!--   </div>  -->
            <div class="button btn1"><input type="button" name="" value="完成" /></div> 
        </form>
    </div>
</body>
</html>
