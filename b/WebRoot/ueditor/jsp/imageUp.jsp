    <%@ page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"%>
    <%@ page import="ueditor.Uploader_syw" %>

    <%
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
        Uploader_syw up = new Uploader_syw(request);
        up.setSavePath("../../../upload_syw");
        String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
        up.setAllowFiles(fileType);
        up.setMaxSize(10000); //单位KB
        up.upload();
        response.getWriter().print("{'original':'"+up.getOriginalName()+"','url':'"+up.getUrl()+"','title':'"+up.getTitle()+"','state':'"+up.getState()+"'}");
    %>
