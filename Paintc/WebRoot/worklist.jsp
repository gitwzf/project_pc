<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>作品列表</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<link rel="stylesheet" href="css/nav.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/windows.js"></script>
<script type="text/javascript">
   $(function(){
       var type='<%=request.getAttribute("type")%>';
       if(type=='null'||(type!='right'&&type!='left'))type="right";
        if(type=='right'){
              $('.isa').css("display","block"); 
              $('.isb').css("display","none"); 
              $('.right').addClass("active");
              $('.left').removeClass("active");
   }else if(type=='left'){
              $('.isb').css("display","block"); 
              $('.isa').css("display","none"); 
             $('.left').addClass("active");
              $('.right').removeClass("active");
   }
   
      $('.left').click(function(){
             $('.isb').css("display","block"); 
              $('.isa').css("display","none"); 
              $(this).addClass("active");
              $('.right').removeClass("active");
      });
      $('.right').click(function(){
             $('.isa').css("display","block"); 
              $('.isb').css("display","none"); 
              $(this).addClass("active");
              $('.left').removeClass("active");
      });
      
   
   
   // setTimeout(function(){
     //   var imgs=document.getElementsByTagName("img");
     //   for(var i=0;i<imgs.length;i++)
      //      imgs[i].src=imgs[i].getAttribute("lazy_src");
     //   },5000);
   });

</script>
</head>

<body>
<ul class="listnav">
     <li class="active left">未评价</li>
     <li class="right">已评价</li>
</ul>   
<div class="container">
    <div class="content">
       <c:forEach items="${apworks}" var="works">
          <c:if test="${works.isA==1}">
          <section class="worklist isa">
          <a href="Detailwork?workid=${works.workid}">
                <img src="${works.picurl}" class="left photo"/>
                <ul class="left">
                    <li>《&nbsp;${works.title}&nbsp;》</li>
                    <li><dd class="left">得分：</dd><dt>${works.score }</dt></li>
                    <li class="ccc"><dd class="left">上传时间：</dd><dt class="left">&nbsp;${works.uptime}</dt></li>
                    <li><dd class="left">&nbsp;</dd><dt class="left">
                    </dt></li>
              	</ul>
                <div style="clear:both;"></div>
            </a>
            </section>
            </c:if>
            
          <c:if test="${works.isA!=1}">
          <section class="worklist isb">
      <!--     <a href="Detailwork?workid=${works.workid}">  --> 
                <img src="${works.picurl}" class="left photo"/>
                <ul class="left">
                    <li>《&nbsp;${works.title}&nbsp;》</li>
                    <li class="ccc"><dd class="left">上传时间：</dd><dt class="left">${works.uptime}</dt></li>
                </ul>
                <div style="clear:both;"></div>
       <!--      </a>    --> 
            </section>		
            </c:if>
      </c:forEach> 
    </div>
<jsp:include page="menu.jsp" flush="true"/>  
</body>
</html>
