<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微信公众平台自助引擎 -  Powered by WE7.CC</title>
<meta name="keywords" content="微信,微信公众平台" />
<meta name="description" content="微信公众平台管理系统。" />
<link type="text/css" rel="stylesheet" href="./resource/style/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/font-awesome.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/common.css?v=1382944697" />
<script type="text/javascript" src="./resource/script/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./resource/script/bootstrap.js"></script>
<script type="text/javascript" src="./resource/script/common.js?v=1382944697"></script>
<script type="text/javascript" src="./resource/script/emotions.js"></script>
<script type="text/javascript">
cookie.prefix = 'f1c8_';
</script>
<!--[if IE 7]>
<link rel="stylesheet" href="./resource/style/font-awesome-ie7.min.css">
<![endif]-->
<!--[if lte IE 6]>
<link rel="stylesheet" type="text/css" href="./resource/style/bootstrap-ie6.min.css">
<link rel="stylesheet" type="text/css" href="./resource/style/ie.css">
<![endif]-->
</head>
<body >
<script type="text/javascript">
     function edit(obj){
      var a=obj.parentNode.parentNode.parentNode;
      var b=a.cells[0].innerHTML;
      var c=document.getElementById(b).selectedIndex;
      var d=document.getElementById(b).options[c].value;
       if(!confirm('确认要修改该权限为'+document.getElementById(b).options[c].text+'吗? ')) {
            return;
        }
        
      if(d!="-1")
      location.href="le4Serv?action=edit&user="+b+"&power="+d;
     }
     
     function deluser(obj){
     if(!confirm('确认要删除用户'+obj+'吗? ')) {
            return;
        }
      location.href="le4Serv?action=delete&user="+obj;
     }
   
    var u ={};
    u.deny = function(uid, status){
        var uid = parseInt(uid);
        if(isNaN(uid)) {
            return;
        }
       
        $.post('member.php?act=edit&', {'do': 'deny', uid: uid, status: status}, function(dat){
            if(dat == 'success') {
                location.href = location.href;
            } else {
                message('操作失败, 请稍后重试. ' + dat);
            }
        });
    };
    u.del = function(uid){
        var uid = parseInt(uid);
        if(isNaN(uid)) {
            return;
        }
        if(!confirm('确认要删除此用户吗? ')) {
            return;
        }
        $.post('.jsp?', {'do': 'delete', uid: uid}, function(dat){
            if(dat == 'success') {
                location.href = location.href;
            } else {
                message('操作失败, 请稍后重试. ' + dat);
            }
        });
    };
</script>
<ul class="nav nav-tabs">
	<li><a href="le44.jsp">添加用户</a></li>
    <li class="active"><a href="">用户列表</a></li>
</ul>
<div class="rule">
    <table class="table table-hover">
        <thead class="navbar-inner">
            <tr>
                <th style="max-width:150px;">用户名</th>
                <th style="width:60px;">身份</th>
                <th style="width:80px;">状态</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${managerarray}" var="mana">
                        <tr>
                <td>${mana.user}</td>
                <td ><select id="${mana.user}"><option selected="selected" value="-1"><c:if test="${mana.power==3}">总管理</c:if><c:if test="${mana.power==2}">中级管理</c:if><c:if test="${mana.power==1}">普通管理</c:if><c:if test="${mana.power==0}">无权限</c:if></option><option value="3">总管理</option><option value="2">中级管理</option><option value="1">普通管理</option><option value="0">无权限</option></select></td>
                <td><c:if test="${mana.value==1}">可用</c:if><c:if test="${mana.value==0}">禁用</c:if></td>
                <td>
                                        <div><a href="javascript:;" onclick="edit(this)">设置权限（不能超过自身权限）</a>&nbsp;&nbsp;<c:if test="${mana.value==0}"><a href="le4Serv?action=disable&user=${mana.user}&value=1" >启用用户</c:if></a><c:if test="${mana.value==1}"><a href="le4Serv?action=disable&user=${mana.user}&value=0" >禁止用户</a></c:if>&nbsp;&nbsp;<a href="javascript:;" onclick="deluser('${mana.user}')">删除用户</a></div>
                                    </td>
            </tr>
            </c:forEach>
                    </tbody>
    </table>
</div>
	
	<div class="emotions" style="display:none;"></div>
</body>
</html>