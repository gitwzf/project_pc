<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wzf.pubvari.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>修改头像</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<link rel="stylesheet" href="css/nav.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/windows.js"></script>
<script type="text/javascript">
    $(function(){
   
     if('${headurl}'=='')$('.uploading0').css("display","none");
     else $('.uploading').css("display","none");
   
   $('#imgFile').blur(function(){
   var purl=$('#imgFile').val();
   $('#imgFile').focus(function(){
         if($('#imgFile').val()!=''&&$('#imgFile').val()!=purl)$('#picform').submit();
   });
   });
   $('.picup').click(function(){
    if($('#imgFile').val()!="")
   $('#picform').submit();
   else alert("请选择头像！");
   });
   
    
       $('.btn4.save').click(function(){
         if($('.uploading0').css("display")==false){alert("请上传头像！");return;}
         $('#editheadForm').submit();
       });
    
       
       $('.btn3').click(function(){
         $('.uploading').text("+");
          $('#st').attr("value","");
       });
       
    
    });
</script>

 <style type="text/css">
   #picform{
     display: block;
   }
 
 </style>
</head>

<body>
	<div class="container">
		<div class="content">
    	<p>上传头像（支持JPG、GIF、PNG）</p>
    	<img src="${headurl}" style="width:100%" class="uploading0"/>
        <div class="uploading" style="margin-left:0px;">+</div>
        <form action="Uploadpic" enctype="multipart/form-data" method="post" id="picform" class="button btn5 left form1">  
        	<span>1选择
				<input type="file" tabindex="-1" name="imgFile00" class="transparent_class" id="imgFile" accept="image/*;capture=camera" > 
			</span>   
		</form>
        	<div class="button btn4 right">
        	<input type="button" class="pho right picup" value="2上传"/>
        </div>
        <form id="editheadForm" action="Editpersonal?type=head" method="post" class="form">
        </form>
        <div class="button btn4 save"><input type="button" name="" value="保存" /></div> 
        <jsp:include page="menu.jsp" flush="true"/>  
        </div>
         </div>
    </div> 
    
	<script type="text/javascript" src="js/jr.js?y=7"></script> 
</body>
</html>
