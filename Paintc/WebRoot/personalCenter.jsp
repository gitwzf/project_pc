<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>个人中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<link rel="stylesheet" href="css/nav.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript">
  $(function(){
     $('.year').click(function(){
          if($('.year').val()!=""&&$('.month').val()!=""){
       $('.week').css("display","none");
       $("tr[class='"+$('.year').val()+" "+$('.month').val()+" week']").removeAttr("style");
     }
     }).blur(function(){
          if($('.year').val()!=""&&$('.month').val()!=""){
       $('.week').css("display","none");
       $("tr[class='"+$('.year').val()+" "+$('.month').val()+" week']").removeAttr("style");
     }
     });
     $('.month').click(function(){
          if($('.year').val()!=""&&$('.month').val()!=""){
       $('.week').css("display","none");
       $("tr[class='"+$('.year').val()+" "+$('.month').val()+" week']").removeAttr("style");
     }
     }).blur(function(){
          if($('.year').val()!=""&&$('.month').val()!=""){
       $('.week').css("display","none");
       $("tr[class='"+$('.year').val()+" "+$('.month').val()+" week']").removeAttr("style");
     }
     });
   
  
  });

</script>
</head>

<body>
<div class="container">
   <div class="content">
        <section class="per-info">
        	<a href="./edithead.jsp"><img src="${manager.headimg }" class="left photo"/></a>
        	<a style="position:absolute;bottom:20px;left:26px">修改头像</a>
            <ul class="left">
            	<li><dd class="left">姓名：</dd><dt>${manager.tname }（<c:if test="${manager.isyearvip==1}">年会员</c:if><c:if test="${manager.isyearvip!=1}">注册用户</c:if>）</dt></li>
             <c:if test="${!empty manager.teachername}">
                <li><dd class="left">老师：</dd><dt>${manager.teachername}老师</dt></li>
                </c:if>
                <li><dd class="left">
                <c:if test="${manager.isTrain==1}">学校：</dd><dt class="left">${manager.stname }</dt></li></c:if>
                <c:if test="${manager.isTrain==0}">机构：</dd><dt class="left">${manager.stname }</dt></li></c:if>
                <c:if test="${manager.isTrain!=0&&manager.isTrain!=1}">学校/机构：</dd><dt class="left">${manager.ex1 }</dt></li></c:if>
           		<li><dt><a href="./loading.jsp">还有${manager.uptimes }次上传机会</a></dt></li>
            </ul>
        </section>
        <section class="orderitem">
            <h3>上传明细</h3>
            <div style="overflow:hidden;margin-bottom:20px;">
             <p style="margin-bottom:10px;">请选择你要查询的年份和月份。</p>
             <div class="input-box1 left" style="width:123px;margin-right:10px;margin-bottom:0px;">
                <select class="year" style="width:98%;">
                    <option selected="selected" value="">-请选择年份</option>
                    <c:forEach items="${years}" var="ye">
                    <option value="${ye }">${ye }年</option>
                    </c:forEach>
                </select>
             </div>
             <div class="input-box1 left" style="width:123px;">
                <select class="month" style="width:98%;">
                    <option selected="selected"  value="">-请选择月份</option>
                    <c:forEach items="${months}" var="mo">
                    <option value="${mo }">${mo }月份</option>
                    </c:forEach>
                </select>
             </div>
             </div>
            <div class="table">
                <table>
                    <thead>
	                    <tr>
		                    <td class="first">周次</td>
		                    <td class="second">上传次数</td><td class="third">操作</td>
	                    </tr>
                    </thead>
                    <tbody>
                         <c:forEach items="${weeklist}" var="week">
                            <tr class="${week.year} ${week.month} week">
                            <td class="first">${week.weeknumber }
                            <span style="font-size:0.8em;color:#ccc;">(${week.sbegdata }号始)</span>
                            </td>
                            <td class="second">${week.upworks}</td>
                            <td class="third">
                            <a href="Worklist?beg=${week.begdata }&end=${week.enddata}">查看</a>
                            </td></tr>
                         </c:forEach>
                     </tbody>
                </table>
            </div>
          </section>  
    <jsp:include page="menu.jsp" flush="true"/>  
</body>
</html>
