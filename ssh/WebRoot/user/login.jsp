<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="../css/site/reset.css" media="all" />
    <link rel="stylesheet" type="text/css" href="../css/site/header.css" media="all" />
    <link rel="stylesheet" type="text/css" href="../css/site/site.css" media="all" />
    <link rel="stylesheet" type="text/css" href="../css/user/register.css" media="all" />
</head>
<body>
        <div class="wrapper" style="width:450px;margin:auto;position:fixed;top:200px;margin-left:-275px;left:50%">
            <div>
                <h2>登录</h2>
            </div>
            <div>
                <form action="../register.do?type=login" method="post" >
                    <div class="f-item">
                        <span style="width:100px;">邮箱:</span>
                        <input type="text" name="email" class="" placeholder="请输入您的注册邮箱"/>
                    </div>
                    <div class="f-item">
                        <span style="width:100px;">密码:</span>
                        <input type="text" name="pass" class="" placeholder="请输入您的密码"/>
                    </div>
                    <div class="register-btn" style="margin-left:110px;">
                        <input type="submit" value="登录" style="padding-left:30px;padding-right:30px;"/>
                    </div>
                </form>
            </div>
        </div>

</body>
</html>
