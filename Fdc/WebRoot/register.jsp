<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<link rel="stylesheet" type="text/css" href="css/base.css" media="all">
<link rel="stylesheet" type="text/css" href="css/enroll.css" media="all">
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
	   if($('#yzm').val()==''){alert('请输入验证码');return false;}
	   return true;
	   }

    $(function(){
      $('.istel').blur(function(){
        $.post("./login.do",{tel:$('.istel').val(),type:'istelunique'},function(data){
        data=data.trim();
            if(data=='00'){alert('该手机号已注册');$('.istel').attr("value","");}
            //可用else if(data=='11'){}
        
        },'text');
      
      });
    
    
    });
    </script>
  </head>
    
  <body>
  <div class="container">
		<div class="wrapper">
    <form action="./login.do?type=register" method="post"> 
    <div class="enroll-form">
     <div class="item"><label>姓名：</label><input type="text" name="name" id="name"/></div>
     <div class="item"><label>手机：</label><input type="text" name="tel" id="tel" class="istel"/></div>
  <!--  <label>性别：</label><select name="gender">
    <option value="">请选择性别</option>
       <option value="1">男</option>
    <option value="0">女</option>
    </select>
    <label>购房意愿：</label><select name="iswish">
    <option value="">请选择</option>
       <option value="1">有</option>
    <option value="0">无</option>
    </select>    -->
     <input type="text" name="yzm"  id="yzm" class="box"  value=""  placeholder="验证码" style="width:40%;" onblur="isyzm(this)"/>
      <img src="Yzm?t=1" id="code" onclick="reloadcode()" alt="看不清楚,换一张" style="cursor: pointer;float: left;width: 115px;margin-left: 10px;">
   <div class="btn"> <input type="submit" value="注册" onclick="return isok()"/></div>
   </div>
    </form>
    </div>
    </div>
  </body>
</html>
