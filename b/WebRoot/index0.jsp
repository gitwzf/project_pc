<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
	<script type="text/javascript">

	
	function  reloadcode(){
	$('#code').attr("src","Yzm?t=1");
	
	}
	
	function showimg(e,obj){
	$('body').append("<div id='pic' style='position: fixed;'><img src='"+obj.href+"'/></div>");
 $('#pic').css("left",mousePos(e).x);
 $('#pic').css("top",mousePos(e).y); 
	}
	function hidimg(){//remove
	var node=document.getElementById("pic");
	document.body.removeChild(node);
	}
	function mousePos(e){
var x,y;
var e = e||window.event;
return {
x:e.clientX+document.body.scrollLeft+document.documentElement.scrollLeft,
y:e.clientY+document.body.scrollTop+document.documentElement.scrollTop
};
}
function savepic(){alert(document.getElementById('IframeImg'));
 document.getElementById('IframeImg').document.execCommand("SaveAs",false,'http://xiaowangzi.touclick.com/file/image/1385479913485.jpg');    
}
	</script>
	
  </head>
  
  <body>
  <img src="Yzm?t=1" id="code" onclick="reloadcode()" style="cursor: pointer;" alt="看不清楚,换一张">
   <a id="showimg" value="333" href="http://xiaowangzi.touclick.com/file/image/1385479913485.jpg" onmouseover="showimg(event,this)" onmouseout="hidimg()" style="course:pointer">点击查看图片</a>
  
  
  <iframe src="http://xiaowangzi.touclick.com/file/image/1385479913485.jpg" id="IframeImg" name="IframeImg"></iframe>
 <img src="http://xiaowangzi.touclick.com/file/image/1385479913485.jpg" id="DemoImg" border="0" onclick="savepic()"> 
  </body>
</html>
