<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.net.URLDecoder"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" />
    <script src="js/jquery-1.9.1.min.js"></script>
    <link href="css/css.css" rel="stylesheet" />

    <script src="js/jslib.js"></script>
    <script src="js/js.js"></script>
    <script type="text/javascript">
      $(function(){
        $('.showpage').click(function(){
        var typeid=$(this).attr("typeid");
         $.post("PageServ",{type:'p_company',typeid:typeid},function(text){
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
        <a class="title" href="javascript:void(0)">企业列表</a>
        <a class="but" href="./CompanyServ?mtype=0&typeid=<%=request.getAttribute("typeid") %>" style="float:left;margin-top:5px;"><%=request.getAttribute("all")==null?"":URLDecoder.decode((String)request.getAttribute("all"),"utf-8") %></a>
        <div style="clear:left"></div>
        <div id="apage">
        <c:forEach items="${companylist}" var="company">
        <a href="./CompanyServ?type=detail&companyid=${company.id }">
            <section class="list1 list2 list3 list4">
                <div class="list-wapper">
                   <h3><strong>${company.name }</strong></h3>
                    <p class="list-item left">地点：${company.address }</p>
                    <p class="list-item right">时间：${company.position_tim }</p>
                </div>
            </section>
        </a>
        </c:forEach>
        </div>
        <c:if test="${!empty companylist}"><br/>
        <c:if test="${compage.maxp!=compage.p}">
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
