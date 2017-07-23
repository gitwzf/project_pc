    <%@ page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"%>
    <%@ page import="ueditor.Uploader_syw" %>
    <%@ page import="com.wzf.pubvari.Variable" %>

    <%
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
        Uploader_syw up = new Uploader_syw(request);
        up.setSavePath("../../../"+Variable.path_pic_upload);
        String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
        up.setAllowFiles(fileType);
        up.setMaxSize(10000); //单位KB
        up.upload();
        response.getWriter().print("{'original':'"+up.getOriginalName()+"','url':'"+up.getUrl()+"','title':'"+up.getTitle()+"','state':'"+up.getState()+"'}");
    %>
