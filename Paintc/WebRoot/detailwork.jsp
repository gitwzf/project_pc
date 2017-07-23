<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${detailwork.title }</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<link rel="stylesheet" href="css/nav.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/windows.js"></script>
<script type="text/javascript">
     $(function(){
      var ua = navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i)!="micromessenger") 
        $('.right.vote').remove();
      
      var isvote='<%=request.getAttribute("isvote")%>';
    //  alert(isvote);
         if(isvote!=''&&isvote!='null'){
            if(isvote=='${detailwork.workid}')alert("成功赞了一个！");
            else if(isvote=='00')alert("没赞成功，再试下");
         }
     
        if(${detailwork.begid==detailwork.workid}){
             $('#left0').attr("disabled",true);$('#left').attr("disabled",true);$('#left').attr("class","button btn3 left");
        }
        if(${detailwork.endid==detailwork.workid}){
             $('#right0').attr("disabled",true);$('#right').attr("disabled",true);$('#right').attr("class","button btn3 right");
        }
        
         $('#left').click(function(){
                  location.href="Detailwork?workid="+'${detailwork.befid}';
         });
          $('#right').click(function(){
                  location.href="Detailwork?workid="+'${detailwork.afrid}';
         });
         
         
        $('.right.vote').click(function(){
  if($(this).attr("purl")!=""){
 if(!window.WeixinJSBridge){alert("需要在微信点赞！"); return;}
  location.href="Vote?"+$(this).attr("purl");}
 });
     
       
     });

</script>
</head>

<body>
	<div class="container">
	<div class="content">
	  <center> <%=(request.getAttribute("competename")==null||request.getAttribute("competename")=="")?"":(request.getAttribute("competename")+" 排名："+request.getAttribute("place")) %></center>
    	<section class="detail">
            <h3>《${detailwork.title }》</h3>
            <img src="${detailwork.picurl }" style="width:100%;"/>
            <div class="info">
	            <span class="left">
	            <dd class="left">${detailwork.tname }</dd><dt class="left" >&nbsp;（<c:if test="${detailwork.gender==1 }">男</c:if><c:if test="${detailwork.gender==0 }">女</c:if>）</dt>
	          <br/>  	<dd class="left">上传时间：</dd><dt class="left" >${detailwork.uptime }</dt>
	            </span>
	            <c:if test="${empty type}">
	            <span class="right" style="color:#d8783e;font-size:1.3em;"> <dd class="left">得分：</dd><dt class="left">${detailwork.score }</dt>
	            </span>
	            </c:if>
            </div>
            <div class="angle"></div>
            <c:if test="${empty type}">
            <div class="comment">
                <h5>评语：</h5>
                <p>${detailwork.comment }</p>
            </div>
            <h5>指导老师评语</h5>
            <div class="angle1"></div>
             <div class="note">
                <p>${detailwork.teachersay}</p>
            </div>
            </c:if>
           <div class="introduce">
                <h5>作品简介</h5>
                <p>${detailwork.introduction }</p>
            </div>  
        </section>
        <c:if test="${!empty manager}">
         <p>点击右上角，分享到朋友圈让更多的朋友欣赏孩子的作品吧（要点赞哦！）。</p>
         <div class="button btn4 left" id="left"> <input type="button" id="left0" name="" value="上一个" /></div>
         <div class="button btn4 right" id="right"> <input type="button" id="right0" name="" value="下一个"/></div>
         </c:if>
   </div>
   <c:if test="${!empty manager}">
   <br/><br/>
   <jsp:include page="menu.jsp" flush="true"/>  
   </c:if>
   <c:if test="${empty manager}">
   <span style="font-size:14px;">搜索微信号 klxy0571 关注并加入快乐童学书画社</span>
     <c:if test="${isvote!=detailwork.workid&&isvote!='0'}"><div class="right vote zan" purl="phone=${detailwork.phone}&action=detail&workid=${detailwork.workid}">赞一个</div></c:if>
             <c:if test="${isvote==detailwork.workid||isvote=='0'}"><div class="right zan" purl="">已赞</div></c:if>
    
   </c:if>
</body>
</html>
