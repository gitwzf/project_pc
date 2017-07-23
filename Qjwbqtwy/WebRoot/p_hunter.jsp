<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:forEach items="${p_hunterlist}" var="p_hunter">
 <a href="./HunterServ?id=${p_hunter.id }&type=detail">
        <section class="list1">
            <img class="list-img-left" src="${p_hunter.head_url }"/>
            <div class="list-wapper">
                <h3>${p_hunter.name }</h3>
                <p class="list-content">${p_hunter.info }</p>
            </div>
        </section>
        </a>
        </c:forEach>