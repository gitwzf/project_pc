<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="ueditor.Uploader_syw" %>
<%@ page import="com.wzf.pubvari.Variable" %>

<%
	request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");

    Uploader_syw up = new Uploader_syw(request);
    up.setSavePath("../../../"+Variable.path_pic_upload); //保存路径
    String[] fileType = {".rar" , ".doc" , ".docx" , ".zip" , ".pdf" , ".txt" , ".swf", ".wmv"};  //允许的文件类型
    up.setAllowFiles(fileType);
    up.setMaxSize(10000);        //允许的文件最大尺寸，单位KB
    up.upload();
    response.getWriter().print("{'url':'"+up.getUrl()+"','fileType':'"+up.getType()+"','state':'"+up.getState()+"','original':'"+up.getOriginalName()+"'}");
%>