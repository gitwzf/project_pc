<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>作品详情</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="css/nav.css"/>
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/spin.js?g=7"></script>
<script type="text/javascript">


   $(function(){
  var ua = navigator.userAgent.toLowerCase();
 if(ua.match(/MicroMessenger/i)!="micromessenger") 
   $('.right').remove();
   
      $('#togo').click(function(){
      location.href="./Paintcom";
      });
   
   $('.right.vote').click(function(){
   if($(this).attr("purl")!=""){
   var competeid=$(this).attr("purl");
  $.get("Vote",
	    		{competeid:competeid,action:'paint',username:'${username}',userkey:'${userkey}'},
	    function(data){
		   	if(data=='00'){
					alert("投票失败！");
				}
			    else {
				   		alert("投票成功！还有"+data+"张票可投");
				   		if(data=='0'){
				   		$('.right.vote').text('已投票');
				   		$('.right.vote').attr("disabled","disabled");
				   		$('.right.vote').removeClass('vote');
				   		}
			   	
			     }
	 	 });
 }
 });
   
   
   });

</script>
</head>

<body>
<div class="container">
    <div class="content">
    	<section class="detail">
    	<h1 class="left" style="display:block;font-size:1.7em;">
      <%=request.getAttribute("i0")==null?"":request.getAttribute("i0") %></h1>
            <img src="${paint.picurl }" class="img1" style="width:100%;"/>
            <ul class="left" style="width:38%;">
                <li style="height:30px;width:320px;">${paint.tname }</li>
                 <li style="height:30px;"><dd class="left">年龄：</dd><dt class="left">${paint.age}</dt></li>
               <li style="height:30px;"><dd class="left">票数：</dd><dt>${paint.score }</dt></li>
        
            </ul>
            <c:if test="${voteuser.votepaintnumbers!=0}"><div class="right vote" style="margin-top:30px;" purl="${paint.workid}">投票</div></c:if>
             <c:if test="${voteuser.votepaintnumbers==0}"><div class="right" style="margin-top:30px;">已投票</div></c:if>
         
           </section>
            <p class="tips">点击右上角，分享到朋友圈让更多的朋友欣赏孩子的作品吧。</p>
            <div class="button btn1" ><input type="button" id="togo" name="" value="返回比赛列表" style="letter-spacing:10px;" /> </div>
            </div>
            </div>

<style>
.mask{
 position:absolute;
 top:0;
 left:0;
 width:100%;
 height:100%;
 display:none;
 background:rgba(0,0,0,0.5);
 }
 .mask #foo{
 position:absolute;
 bottom:700px;
 left:50%;
 width:100px;
 height:100px;
 font-size:16px;
 }
</style>
<div class="mask" ><div id="foo">请等候...</div></div>
</body>
</html>
