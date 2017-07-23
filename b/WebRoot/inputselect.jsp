<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'inputselect.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script type="text/javascript">
    function change(obj){
       var h=obj.innerHTML;
    $('#shen').attr("value",h);
      $('#u').empty();
    }
    
   $(function(){
      $('#shen').blur(function(){
      $('#u').empty();
        var shen=$(this).val();
         if(shen!=''){
             var ops=$('#shi option').each(function(i,v){
                  var that=$(v);
                  var re=new RegExp("\\S*"+shen+"\\S*");
                  if(re.test(that.text())){
                        $('#u').append("<li onclick=change(this)>"+that.text()+"</li>");
                  }
             
             });
         
         }
      });
   });
    </script>
  </head>
  
  <body>
    <input type="text" id="shen">
    <ul id="u" style="list-style:none">
    </ul>
    <select id="shi">
    <option value="sx">浙江绍兴</option>
    <option value="sx">浙江杭州</option>
    <option value="sx">重庆</option>
    <option value="sx">北京</option>
    </select>
  </body>
</html>
