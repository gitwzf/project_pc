<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" />
   <script src="js/jquery-1.9.1.min.js"></script>
    <link href="css/css.css" rel="stylesheet" />
     <script src="js/jslib.js"></script>
    <script src="js/js.js"></script>
    <script type="text/javascript">
      $(function(){
        $('.showpage').click(function(){
         $.post("PageServ",{type:'p_hunter'},function(text){
         if(text.trim()==''){
         $('#hasMore').remove();
         TouClick.mask(1).float('已全部加载');}
          else
              $('#apage').append(text);  
        
        });
        });
      
      });
    </script>
</head>
<body>
    <div class="contain">
      <a href="${ad.url }" class="clicktimes" adid="${ad.id}"><img class="head_img" src="${ad.pic_url }" /></a>
        <a class="title" href="javascript:void(0)">金牌猎头</a>
      <div id="apage">
<c:forEach items="${hunterlist}" var="hunter">
   <a href="./HunterServ?id=${hunter.id }&type=detail">
        <section class="list1">
            <img class="list-img-left" src="${hunter.head_url }"/>
            <div class="list-wapper">
                <h3>${hunter.name }</h3>
                <div class="list-content">${hunter.info }</div>
            </div>
        </section>
        </a>
     </c:forEach>  
     </div>
       <c:if test="${empty hunterlist}">敬请期待</c:if>
        <c:if test="${!empty hunterlist}"><br/>
        <c:if test="${hpage.maxp!=hpage.p}">
     <center><a href="javascript:;" class="showpage" id="hasMore" typeid="<%=request.getAttribute("typeid") %>"><font size="4px" color="gray">点击加载更多</font></a></center> 
      </c:if>
      </c:if>
    </div>
    <mask style="display:none"></mask>
    <float style="display:none">
        <p>请完善您的简历</p>
        <a class="but">确定</a>
    </float>
<jsp:include page="_footer.jsp"/>
</body>
</html>
