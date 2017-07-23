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
function fo(){
$('.mask').css('display','block');
var height=$('body').height();
$('.mask').height((height+500)+'px');
 var target = document.getElementById('foo');
var spinner = new Spinner().spin();
target.appendChild(spinner.el);
}

   $(function(){
  var ua = navigator.userAgent.toLowerCase();
 if(ua.match(/MicroMessenger/i)!="micromessenger") 
   $('.right').remove();
   
      $('#togo').click(function(){
      location.href="./Models";
      });
   
   $('.right.vote').click(function(){
   if($(this).attr("purl")!=""){
   var phone=$(this).attr("purl").split("&")[0].split("=")[1];
   var competeid=$(this).attr("purl").split("&")[1].split("=")[1];
  fo();
  $.post("Vote",{phone:phone,competeid:competeid,action:'model',username:'${username}',userkey:'${userkey}'},function(data){
  var isvote=data;
 if(isvote=='00')alert("投票失败！");
    else if(isvote!=''){
    var a="0";
   if('${voteuser.votenumbers}'!='')a='${voteuser.votenumbers}';
   if(a!=0)alert("投票成功！还有"+(a-1)+"张票可投");
   else alert("投票成功！");
   location.href="./Models";
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
            <img src="${model.picurl }" class="img1" style="width:100%;"/>
            <ul class="left" style="width:38%;">
                <li style="height:30px;width:320px;">${model.studentname }（${model.vote }）</li>
                <li style="height:30px;"><dd class="left">性别：</dd><dt><c:if test="${model.gender==1}">男</c:if><c:if test="${model.gender==0}">女</c:if></dt></li>
                <li style="height:30px;"><dd class="left">身高：</dd><dt class="left">${model.height}cm</dt></li>
            </ul>
            <ul class="left" style="width:33%;margin-top:30px;">
                <li style="height:30px;"><dd class="left">年龄：</dd><dt>${model.age}岁</dt></li>
                <li style="height:30px;"><dd class="left">体重：</dd><dt class="left">${model.weight}kg</dt></li>
                
            </ul>
            <c:if test="${model.competestatus==1}">
            <c:if test="${voteuser.votenumbers!=0}"><div class="right vote" style="margin-top:30px;" purl="phone=${model.phone}&competeid=${model.competeid}&action=model">投票</div></c:if>
             <c:if test="${voteuser.votenumbers==0}"><div class="right" style="margin-top:30px;" purl="">已投票</div></c:if>
           </c:if>
           <c:if test="${model.competestatus==0}"><div class="right" purl="">未开始</div></c:if>
             <c:if test="${model.competestatus==2}"><div class="right" purl="">已结束</div></c:if>
             <c:if test="${model.competestatus==3}"><div class="right" purl="">已排名</div></c:if>
           </section>
            <p class="tips">点击右上角，分享到朋友圈让更多的朋友欣赏孩子的照片吧。</p>
            <div class="button btn1" ><input type="button" id="togo" name="" value="返回榜单" style="letter-spacing:10px;" /> </div>
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
