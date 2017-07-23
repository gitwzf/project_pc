<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.fdc.pubvari.Variable" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%Integer i=(request.getAttribute("pagesgame")==null?(request.getParameter("pagesgame")==null?1:Integer.parseInt((String)request.getParameter("pagesgame"))):
Integer.parseInt((String)request.getAttribute("pagesgame"))); %>
<%
    for(int j=Variable.number_pagesgame*(i-1);(j<Variable.number_pagesgame*i&&j<Variable.list_sgameuser.size());j++){
      out.print("<li><dd>第"+(j+1)+"名:</dd><dt>"+Variable.list_sgameuser.get(j).getName()
      +"</dt><dd>助力数:</dd><dt>"+Variable.list_sgameuser.get(j).getNumbersvote()+"</dt></li>");
    }

%>