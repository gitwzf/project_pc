<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.net.URLDecoder"%>
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
        var i=$(this).attr("i");
        $.post("PageServ",{type:'p_college',i:i},function(text){
        if(text.trim()==''){
        $('#hasMore').remove();
        TouClick.mask(1).float('已全部加载');
        
        }
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
        <a class="title" href="javascript:void(0)"><%=request.getAttribute("title")==null?"":URLDecoder.decode((String)request.getAttribute("title"),"utf-8") %></a>
        <div class="speart"><!--空白分割--></div>
        <div id="apage">
        <c:forEach items="${collegelist}" var="college">
        <a href="./Fair?fairid=${college.id }">
        <section class="list1 list2">
            <img class="list-img-left" src="${college.head_url }" />
            <div class="list-wapper">
                <h3>${college.name }</h3>
                <p class="list-content">${college.info }</p>
                <p class="list-item">时间：${college.beg_day} 至 ${college.end_day }</p>
                <p class="list-item">地点：${college.address }</p>
            </div>
        </section>
        </a>
        </c:forEach>
        </div>
        <c:if test="${empty collegelist}">敬请期待</c:if>
     <c:if test="${!empty collegelist}"><br/>
     <c:if test="${cpage.maxp!=cpage.p}">
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
