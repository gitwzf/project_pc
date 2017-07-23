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
<title>上传作品</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<link rel="stylesheet" href="css/nav.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>

<script type="text/javascript" src="js/windows.js"></script>
<script type="text/javascript">
    $(function(){
   
     if('${picurl}'=='')$('.uploading0').css("display","none");
     else $('.uploading').css("display","none");
   
   $('#imgFile').blur(function(){
   var purl=$('#imgFile').val();
   $('#imgFile').focus(function(){//alert('123');
         if($('#imgFile').val()!=''&&$('#imgFile').val()!=purl)$('#picform').submit();
   });
   });
   $('.picup').click(function(){
    if($('#imgFile').val()!="")
   $('#picform').submit();
   else alert("请选择作品！");
   });
   
   $('.picup0').click(function(){
      alert("上传次数不够了，请充值");
   });
        
    
       $('.btn4.sub').click(function(){
       var a=document.getElementById("picup0");
          if(a!=null){alert('上传次数不够了，请充值');return;}
           if($('.uploading0').css("display")==false){alert("请上传作品！");return;}
       //    if($('#introduction').val()==""){alert("请输入作品介绍！");return;}
    		if($('#introduction').val().length>200){alert("当前字数"+$('#introduction').val().length+"，已超200字数限制！");return;}
    		
          $('.allsub').submit();
       });
    
       //html5拍照
    //   $('.btn4.pho').click(function(){
   //       alert("拍照");
   //       location.href="photo.jsp";
   //    });
       
       $('.btn3').click(function(){
         $('.uploading').text("+");
         $('#title').attr("value","");
          $('#introduction').attr("value","");
           $('#remark').attr("value","");
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
	   <%=Variable.vobj_competepaint==null?"":("<div class=tips>"+Variable.vobj_competepaint.getName()+
	   ":<br>&nbsp;&nbsp;&nbsp;&nbsp;"+Variable.vobj_competepaint.getBegtim()+" &nbsp;&nbsp;&nbsp;&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;"+
	    Variable.vobj_competepaint.getEndtim()+" <br/>  </div>")%>
	<div class="content">
    	<p>支持JPG、GIF、PNG等格式（你还有${manager.uptimes }次上传机会）</p>
    	<img src="${picurl}" style="width:100%" class="uploading0"/>
        <div class="uploading" style="margin-left:0px;">+</div>
        <form action="Uploadpic" enctype="multipart/form-data" method="post" id="picform" class="button btn5 left form1">  
        	<span>1选择作品
				<input type="file" tabindex="-1" name="imgFile" class="transparent_class" id="imgFile" accept="image/*;capture=camera" > 
			</span>   
		</form>
      <c:if test="${manager.uptimes==0}"> <div class="button btn4 right">
        	<input type="button" class="pho right picup0" id="picup0" value="2上传作品"/>
        </div></c:if> 
         <c:if test="${manager.uptimes!=0}"> <div class="button btn4 right">
        	<input type="button" class="pho right picup" value="2上传作品"/>
        </div></c:if> 
        <!--<input type="button" class="btn4 pho right" value="拍照上传" style="display:none"/>-->
        <form action="Works" method="post" class="allsub">
<input type="text" required name="title" id="title" class="input-box f" placeholder="标题(若不填写系统将按时间自动命名)"/>
        <textarea rows="10" class="input-box f" name="introduction" id="introduction" placeholder="作品介绍（0-200字）" style="height:150px;"></textarea>
        <textarea rows="2" class="input-box f" name="remark" id="remark" placeholder="备注（如有需要给评委留言/建议/提问）" style="height:50px;"></textarea>
        </form>
        <div class="button btn3 left"><input type="button" class="" value="取消"/></div>
        <div class="button btn4 sub right"><input type="button" class="sub" value="提交"/></div>
        </div>
    <jsp:include page="menu.jsp" flush="true"/> 
    </div> 
	<script type="text/javascript" src="js/jr.js?y=7"></script> 
</body>
</html>
