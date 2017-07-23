<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String i0=(String)request.getAttribute("i");
int i=0;
  if(i0!=null)i=Integer.parseInt(i0);
 %>
   <c:forEach items="${modelss}" var="model">
      <div class="ranking i<%=i%7%>" style="border-left:5px solid;">
      <a href="Model_detail?phone=${model.phone}&competeid=${model.competeid}">
      <h1 class="left" style="display:block;font-size:1.7em;">
      <%=++i %></h1>
            <img src="${model.picurl}" class="left model-photo"/>
            <ul class="left model-info" >
                <li>${model.studentname}</li>
                <li style="font-size:0.8em;"><dd class="left" >性别：</dd><dt><c:if test="${model.gender==1}">男</c:if><c:if test="${model.gender==0}">女</c:if></dt></li>
                <li><dd class="left" style="font-size:0.8em;">已获得${model.vote }票</dt></li>
            </ul>
             </a>
            <c:if test="${voteuser.votenumbers!=0}"><div class="right vote" purl="phone=${model.phone}&competeid=${model.competeid}&action=model">投票</div></c:if>
             <c:if test="${voteuser.votenumbers==0}"><div class="right" purl="">已投票</div></c:if>
            <div style="clear:both;"></div>
        </div>	
        </c:forEach>