<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.net.URLDecoder"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
     <%String info=request.getAttribute("info")==null?"":URLDecoder.decode((String)request.getAttribute("info"),"utf-8"); %> 
    <%=info %>