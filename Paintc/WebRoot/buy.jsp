<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>会员中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css" href="base.css" media="all">
<link rel="stylesheet" href="css/nav.css"/>
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript">
   $(function(){
      
        $('.yearv').click(function(){
            if('${manager}'==''){alert("请重新登陆！");return;}
            if('${manager.isyearvip}'=='1'){alert("你已经是年会员，无须购买！");return;}
        
           $("#buyform"+$(this).attr("vid")).submit();
        });
        
        $('.addv').click(function(){
             if('${manager}'==''){alert("请重新登陆！");return;}
            if('${manager.isyearvip}'!='1'){alert("请先成为年会员！");return;}
      //     $('#numbers').attr("value",prompt("购买份数："));
       //    alert($('#numbers').val());return
          $("#buyform"+$(this).attr("vid")).submit();
           
           });
        
        
        
   
   });
   </script>
</head>

<body>
<!-- 
<ul class="nav">
    <li class="active">成为会员</li>
</ul>  -->

<div class="container">
<!-- 
<c:forEach items="${vip}" var="v">
           <c:if test="${v.name=='年会员'}">
   <section class="product">
        <img src="images/good01.png" class="left product-img"/>
        <ul class="left">
            <li class="item">${v.name}</li>
            <li><dd class="left" >价格：</dd><dt>${v.charge}元/年</dt></li>
            <li><dd class="left">${v.numbers}次上传机会</dd></li>
        </ul>
        <form id="buyform${v.id}" method="post" action="Buy">
        <div class="button btn6 right yearv" vid="${v.id }"><input type="hidden" value="${v.id}" name="vid"></input>
        <input type="button" id="username"name="" value="购买" style="height:30px;line-height:30px;"/> </div>
        <input type="hidden" id="numbers">
        </form>
        <div style="clear:both;"></div>
    </section>	
     </c:if>
        </c:forEach>	
</div>
<ul class="nav">
    <li class="active">加油包</li>
    <li>&nbsp;</li>
</ul>
<div class="container">
    <div class="content">
    <c:forEach items="${vip}" var="v">
           <c:if test="${v.name!='年会员'}">
      <section class="product">
            <img src="images/good02.png" class="left product-img"/>
            <ul class="left">
                <li class="item">${v.name }</li>
                <li><dd class="left">价格：</dd><dt>${v.charge }元</dt></li>
                <li><dt class="left">加${v.numbers }次上传</dt></li>
            </ul>
            <form id="buyform${v.id}" method="post" action="Buy">
            <div class="button btn6 right addv" vid="${v.id}"><input type="hidden" value="${v.id}" name="vid"></input>
            <input type="button" id="username"name="" value="购买" style="height:30px;line-height:30px;"/></div>
            </form>
            <div style="clear:both;"></div>
        </section>	
       </c:if>
       </c:forEach>
       </div>
       -->
       <p>
    <span style="font-family: 宋体; font-size: 16px;">会员卡购买方式：在快乐童学书画社官方淘宝店中购买社员年卡，根据说明在爱童学微信公众账号上完成注册。</span>
</p>
<p style="margin-bottom: 0;margin-top: 0">
    <span style=";color:rgb(255,0,0);font-weight:bold;font-size:16px;font-family:&#39;宋体&#39;">快乐童学书画社官方淘宝店地址</span><span style=";color:rgb(255,0,0);font-size:16px;font-family:&#39;宋体&#39;">：</span><a href="http://shop111540519.taobao.com/?spm=2013.1.1000126.2.EVdWiY"><span style="color: rgb(0, 0, 255);font-size: 16px;font-family: 宋体">http://shop111540519.taobao.com/?spm=2013.1.1000126.2.EVdWiY</span></a>
</p>
<p>
    <span style=";font-size:16px;font-family:&#39;宋体&#39;">或可打开淘宝网，搜索宝贝“</span><span style=";color:rgb(255,0,0);font-size:16px;font-family:&#39;宋体&#39;">快乐童学书画社</span><span style=";font-size:16px;font-family:&#39;宋体&#39;">”找到</span>
</p>
<p>
    <br/>
</p>
       <jsp:include page="menu.jsp" flush="true"/>  
       </div>
</body>
</html>
