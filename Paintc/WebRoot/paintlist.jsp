<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>绿色天堂漫画人气榜</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="css/nav.css"/>
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/spin.js"></script>
</head>
<script type="text/javascript">
//style    2 #f7c37c    3 #5fbea7 #b695c4
function modify(){

$('.ranking.i0').css("border-left-color","#f7c37c");
$('.ranking.i1').css("border-left-color","#5fbea7");
$('.ranking.i2').css("border-left-color","#b695c4");
$('.ranking.i3').css("border-left-color","#A52A2A");
$('.ranking.i4').css("border-left-color","#DAA520");
$('.ranking.i5').css("border-left-color","##BC8F8F");
$('.ranking.i6').css("border-left-color","#808080");
var ua = navigator.userAgent.toLowerCase();
 if(ua.match(/MicroMessenger/i)!="micromessenger") 
  $('.right.vote').remove();
}
function fo(){
$('.mask').css('display','block');
var height=$('body').height();
$('.mask').height((height+500)+'px');
 var target = document.getElementById('foo');
var spinner = new Spinner().spin();
target.appendChild(spinner.el);
}

$(function(){
 modify();
$('#pagenum').text('${page.p}'+"/"+'${page.maxp}');

window._vote = function(workid){
 	    $.get("Vote",
	    		{competeid:workid,action:'paint',username:'${username}',userkey:'${userkey}'},
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
};

   
   $('.showpage').click(function(){   
  var target = document.getElementById('foo');
var spinner = new Spinner().spin();
target.appendChild(spinner.el);
   $('.spinner').css("position","relative");
   var p=$(this).attr("p");
   var i=$(this).attr("i");
   $.get("Showpage",{p:p,i:i},function(text){
   $('.content').append(text);
   modify();
   target.removeChild(spinner.el);
   $('#left').remove();
   $('#right').remove();
if(Number($('.showpage').attr("p"))>=Number('${page.maxp}'))$('.showpage').css("display","none");
 $('#pagenum').text($('.showpage').attr("p")+"/"+'${page.maxp}');
   $('.showpage').attr("p",Number(p)+1);
   $('.showpage').attr("i",Number(i)+Number('${page.items}'));
   });
   });
   
   
    $('#pageid').blur(function(){
        if($('#modelname').val()!=''){
           alert("只能选一种查询方式！");
           $('#modelsid').attr("value","");
           $('#modelname').attr("value","");
        }
   });
    $('#modelname').blur(function(){
        if($('#pageid').val()!=''){
           alert("只能选一种查询方式！");
           $('#pageid').attr("value","");
           $('#modelsid').attr("value","");
        }
   });
   $('.pagesub').click(function(){
      if($('#modelsid').val()==''&&$('#pageid').val()==''&&$('#modelname').val()==''){alert("请输入查询条件！");return;}
      if(isNaN($('#pageid').val())||$('#pageid').val().length>10){alert("页码非数字或数字太长！");return;}
       else if(Number($('#pageid').val())>Number('${page.maxp}')||Number($('#pageid').val())<0){alert("没有这个页码数！");return;}
      $('#pageform').submit();fo();
   });
   
   
   
   
   if('${page.p}'=='1'){
             $('#left0').attr("disabled",true);$('#left').attr("disabled",true);$('#left').attr("class","button btn3 left");
        }
        if('${page.p}'=='${page.maxp}'){
             $('#right0').attr("disabled",true);$('#right').attr("disabled",true);$('#right').attr("class","button btn3 right");
        }
       
         
           $('#left').click(function(){
			if(Number('${page.p}')>1){
                  location.href="Paintcom?pageid="+(Number('${page.p}')-1);fo();
				  }
         });
          $('#right').click(function(){
			if(Number('${page.p}')<Number('${page.maxp}')){
                  location.href="Paintcom?pageid="+(Number('${page.p}')+1);fo();
			}
         });

         
   
   
   
   
 });
</script>
<body>

<div class="container">
<%Integer i0=(request.getAttribute("i0")==null?0:Integer.parseInt((String)request.getAttribute("i0")));
int i=i0; %>
 <div class="content">
    <form action="Paintcom" method="get" id="pageform" style='overflow:hidden;padding:10px;'>
   	     <div class="input-box left" style="float:right;">
            	<label>页码:</label>
            	<input type="text" required name="pageid" id="pageid" placeholder="每页20个" style="width:50%;"/>
            </div>
             <div class="input-box left" style="">
            	<label>姓名:</label>
            	<input type="text" required name="modelname" id="modelname" placeholder="小画家姓名" style="width:50%;"/>
            </div>
        <div class="i-want pagesub" style="width:45%;float:right;" >查询</div>
 </form>
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
 #pageform div,.i-want{
 	width:45%;
 	box-sizing:border-box;	
 }
 	.i-want{
 		padding:10px 40px;text-align:center;
 		border:1px solid #ccc; background:#5fbea7;color:#fff;
 		border-radius:4px;margin:0;
 		width:100%;
 	
 	}
 </style>
   <c:if test="${empty paintlist}">暂无比赛作品</c:if>
    <c:forEach items="${paintlist}" var="paint">
      <div class="ranking i<%=i%7%>" style="border-left:5px solid;">
      <a href="Paintdetail?workid=${paint.workid }&i0=<%=++i %>">
      <h1 class="left" style="display:block;font-size:1.7em;">
      <%=i %></h1>
            <img src="${paint.picurl}" class="left model-photo"/>
            <ul class="left model-info" >
                <li>${paint.tname}</li>
                <li style="font-size:0.8em;"><dd class="left" >年龄：</dd><dt>${paint.age }</dt></li>
                <li><dd class="left" style="font-size:0.8em;">已获得${paint.score }票<dt><br></dt></li>
            </ul>
             </a>
              <c:if test="${voteuser.votepaintnumbers!=0}">
           <a href='javascript:window._vote(${paint.workid})'> <div class="right vote">投票</div></a></c:if>
             <c:if test="${voteuser.votepaintnumbers==0}"><div class="right" purl="">已投票</div></c:if>
             <div style="clear:both;"></div>
        </div>	
        </c:forEach>
         </div>   
           <c:if test="${!empty page}">
           <div style='text-align:center;'>
      	<span id="pagenum" style='line-height:40px;'></span> 
            	<div class="button btn4 left" id="left" style='width:40%'> 
            		<input type="button" id="left0" name="" value="上一页" />
	            </div>
		         <div class="button btn4 right" id="right" style='width:40%'>
		         	<input type="button" id="right0" name="" value="下一页"/>
		         </div>
            </div>
            
         
          </c:if>
           <c:if test="${empty page}">
       <center><a href="./Models" >查看全部</a></center>
           </c:if>
   </div>  
 <div class="mask" ><div id="foo">请等候...</div></div>
</body>
</html>
