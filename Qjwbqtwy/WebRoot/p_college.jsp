<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:forEach items="${p_collegelist}" var="p_college">
 <a href="./Fair?fairid=${p_college.id }">
        <section class="list1 list2">
            <img class="list-img-left" src="${p_college.head_url }" />
            <div class="list-wapper">
                <h3>${p_college.name }</h3>
                <p class="list-content">${p_college.info }</p>
                <p class="list-item">时间：${p_college.beg_day } 至 ${p_college.end_day }</p>
                <p class="list-item">地点：${p_college.address }</p>
            </div>
        </section>
        </a>
        </c:forEach>