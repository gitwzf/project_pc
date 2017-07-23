<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:forEach items="${p_companylist}" var="company">
        <a href="./CompanyServ?type=detail&companyid=${company.id }">
            <section class="list1 list2 list3 list4">
                <div class="list-wapper">
                    <h3><strong>${company.name }</strong></h3>
                    <p class="list-item left">地点：${company.address }</p>
                    <p class="list-item right">时间：${company.position_tim }</p>
                </div>
            </section>
        </a>
        </c:forEach>