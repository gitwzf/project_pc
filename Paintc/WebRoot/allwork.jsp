<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.net.URLDecoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>快乐展厅</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="css/nav.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<script type="text/javascript" src="js/spin.js"></script>
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
 $('#pagenum').text('${page.p}'+"/"+'${page.maxp}');
 
 $('#pageid').blur(function(){
        if($('#modelname').val()!=''){
           alert("只能选一种查询方式！");
           $('#modelname').attr("value","");
        }
   });
    $('#modelname').blur(function(){
        if($('#pageid').val()!=''){
           alert("只能选一种查询方式！");
           $('#pageid').attr("value","");
        }
   });
   
   
   $('.signup').click(function(){
    location.href="./register00.jsp";
   });
   
   $('.pagesub').click(function(){
      if($('#pageid').val()==''&&$('#modelname').val()==''){alert("请输入查询条件！");return;}
      if(isNaN($('#pageid').val())||$('#pageid').val().length>10){alert("页码非数字或数字太长！");return;}
       else if(Number($('#pageid').val())<0){alert("没有这个页码数！");return;}
      $('#pageform').submit();fo();
   });
   
   if('${page.p}'=='1'||'${page.p}'>'${page.maxp}'){
             $('#left0').attr("disabled",true);$('#left').attr("disabled",true);$('#left').attr("class","button btn3 left");
        }
        if('${page.p}'>='${page.maxp}'||'${page.maxp}'=='0'){
             $('#right0').attr("disabled",true);$('#right').attr("disabled",true);$('#right').attr("class","button btn3 right");
        }
        
         $('#left').click(function(){
                  location.href="Allwork?pageid="+(Number('${page.p}')-1)+"&modelname="+$('#modelname').val();fo();
         });
          $('#right').click(function(){
                  location.href="Allwork?pageid="+(Number('${page.p}')+1)+"&modelname="+$('#modelname').val();fo();
         });
 
 
 });
</script>
<style>
.wrap1{
	width:48.5%;
}
.work{
	background:#fff;
	padding:5px;
	overflow:hidden;
	margin-bottom:10px;
	border:1px solid #ccc;
	border-radius:3px;
}
.work img{
	width:100%;
	
}
</style>
</head>
<body>
	<div class="container">
	 <form action="Allwork" method="get" id="pageform" style='overflow:hidden'>
   	     <div class="input-box left" style="float:right;">
            	<label>页码:</label>
            	<input type="text" required name="pageid" id="pageid" placeholder="每页10个" style="width:50%;"/>
            </div>
             <div class="input-box left" style="">
            	<label>姓名:</label>
            	<input type="text" required name="modelname" id="modelname" value='<%=request.getAttribute("modelname")==null?"":URLDecoder.decode((String)request.getAttribute("modelname"),"utf-8") %>' placeholder="小画家姓名" style="width:50%;"/>
            </div>
        <div class="i-want pagesub" style="width:45%;float:right" >查询</div>
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
  <div class="i-want signup" style="padding:10px 40px;text-align:center;margin:15px auto;border:1px solid #ccc; background:#5fbea7;color:#fff;">我也要参加</div>
   <div style="overflow:hidden;margin-top:15px;"> 
   <div class="left wrap1">
   <c:if test="${empty painterlist}">没有展示的作品</c:if>
   
    <c:forEach items="${painterlist}" var="painter" varStatus="status">
      <c:if test="${status.index%2==0}">
      <a href="Detailwork?workid=${painter.workid}&type=allwork">
			<div class="work">
					<img src="${painter.picurl }"/>
				<span class="left">${painter.tname }</span><span class="right">${painter.age }岁</span>
			</div>
			</a>
		
		</c:if>
	</c:forEach>
	</div>
	<div class="right wrap1">
	<c:forEach items="${painterlist}" var="painter" varStatus="status">
	<c:if test="${status.index%2==1}">
     <a href="Detailwork?workid=${painter.workid}&type=allwork">
			<div class="work">
					<img src="${painter.picurl }"/>
				<span class="left">${painter.tname }</span><span class="right">${painter.age }岁</span>
			</div>
			</a>
			</c:if>
	</c:forEach>
	</div>		
			
	</div>	
		<div style='text-align:center;'>
      	<span id="pagenum" style='line-height:40px;'></span> 
            	<div class="button btn4 left" id="left" style='width:40%'> 
            		<input type="button" id="left0" name="" value="上一页" />
	            </div>
		         <div class="button btn4 right" id="right" style='width:40%'>
		         	<input type="button" id="right0" name="" value="下一页"/>
		         </div>
            </div>
            </div>
	</div>
	<div class="mask" ><div id="foo">请等候...</div></div>
</body>
</html>
