<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="../css/site/reset.css" media="all" />
    <link rel="stylesheet" type="text/css" href="../css/site/header.css" media="all" />
    <link rel="stylesheet" type="text/css" href="../css/site/site.css" media="all" />
    <link rel="stylesheet" type="text/css" href="../css/user/register.css" media="all" />
</head>
<body>
    <div>
        <div class="wrapper">
            <div>
                <h2>注册</h2>
            </div>
            <div>
                <form action="../register.do?type=register">
                    <div class="f-item">
                        <span>邮箱:</span>
                        <input type="text" name="" class=""/>
                        <p class="error">请输入正确的邮箱地址</p>
                    </div>
                    <div class="f-item">
                        <span>密码:</span>
                        <input type="text" name="" class="" />
                        <p>6~16个字符，区分大小写</p>
                    </div>
                    <div class="f-item">
                        <span>确认密码:</span>
                        <input type="text" name="" class="" />
                        <p class="success">请再次输入密码</p>
                    </div>
                    <div class="f-item">
                        <span>联系人:</span>
                        <input type="text" name="" class="" />
                        <p>请输入联系人姓名</p>
                    </div>
                    <div class="f-item">
                        <span>手机号码:</span>
                        <input type="text" name="" class="" />
                        <p>请输入手机号码</p>
                    </div>
                    <div class="register-btn">
                        <input type="submit" value="同意微触条款并注册"/>
                    </div>
                </form>
            </div>
        </div>

    </div>
</body>
</html>
