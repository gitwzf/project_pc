<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微信公众平台自助引擎 -  Powered by WE7.CC</title>
<meta name="keywords" content="微信,微信公众平台" />
<meta name="description" content="微信公众平台管理系统。" />
<link type="text/css" rel="stylesheet" href="./resource/style/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/font-awesome.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/common.css?v=1382944465" />
<script type="text/javascript" src="./resource/script/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./resource/script/bootstrap.js"></script>
<script type="text/javascript" src="./resource/script/common.js?v=1382944465"></script>
<script type="text/javascript" src="./resource/script/emotions.js"></script>

<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript" src="resource/js/windows.js"></script>
<script type="text/javascript">
cookie.prefix = 'f1c8_';
  
   function choose(obj){
   alert(obj.value);
     var state=document.getElementsByName(obj.value)[0];
     if(state.style.display=='none')state.style.display='block';
     else state.style.display='none';
   }
   
   function show1(obj){
   //alert(obj.value);
   var country= $('#country').val();
			document.getElementById(obj.value).options.length=0;
			var country= $('#country').val();
					jQuery.ajax({
			           type: "GET", //第一次抓文件建立本地缓存返回200,再次访问时如果本地缓存有效时则返回304
			           url : "le15Serv",
			           data:({"showid":obj.value}),
			           dataType: "text",
			           cache : true,
			           contentType:"application/x-www-form-urlencoded;charset=utf-8", //重要是这个定义字符编码
			           async : false,
			           success: function(result) {
			                if (result=="error") {
			                	alert("error");
			                } else {
			                 if(document.getElementById(obj.value).options.length==0)
			                   for(var i=0;i<result.split(";").length;i++){
					         var newopt=document.createElement("option");
                                  newopt.text=result.split(";")[i];
                                 newopt.value=result.split(";")[i];
                                 document.getElementById(obj.value).options.add(newopt,0);}
			                }
			            }
			        });
			}	
			 function show2(obj){
		//	 alert(obj.value);
   var country= $('#country').val();
			document.getElementsByName(obj.value)[0].options.length=0;
			document.getElementsByName(obj.value)[1].options.length=0;
			document.getElementsByName(obj.value)[2].options.length=0;
					jQuery.ajax({
			           type: "GET", //第一次抓文件建立本地缓存返回200,再次访问时如果本地缓存有效时则返回304
			           url : "le15Serv",
			           data:({"showid":obj.value}),
			           dataType: "text",
			           cache : true,
			           contentType:"application/x-www-form-urlencoded;charset=utf-8", //重要是这个定义字符编码
			           async : false,
			           success: function(result) {
			                if (result=="error") {
			                	alert("error");
			                } else {
			                var a=result.split(";");
			                var b=document.getElementById("pone");
			                   for(var i=0;i<result.split(";").length-1;i++){  
					         var newopt=document.createElement("option");
                                  newopt.text=a[i];
                                 newopt.value=a[i];
                                 b.options.add(newopt,0);
                 }
			                }
			            }
			        });
			}	
			function show3(obj){
		// alert($('#pone').val());
  			document.getElementById("ptwo").options.length=0;
					jQuery.ajax({
			           type: "GET", //第一次抓文件建立本地缓存返回200,再次访问时如果本地缓存有效时则返回304
			           url : "le15Serv",
			           data:({"showid":obj.name+"2","place1":$('#pone').val()}),
			           dataType: "text",
			           cache : true,
			           contentType:"application/x-www-form-urlencoded;charset=utf-8", //重要是这个定义字符编码
			           async : false,
			           success: function(result) {
			                if (result=="error") {
			                	alert("error");
			                } else {
			                var a=result.split(";");
			                var b=document.getElementById("ptwo");
			                   for(var i=0;i<result.split(";").length-1;i++){  
					         var newopt=document.createElement("option");
                                  newopt.text=a[i];
                                 newopt.value=a[i];
                                 b.options.add(newopt,0);
                 }
			                }
			            }
			        });
			}	
			function show4(obj){
		//	 alert(obj.value);
			document.getElementById("pthree").options.length=0;
					jQuery.ajax({
			           type: "GET", //第一次抓文件建立本地缓存返回200,再次访问时如果本地缓存有效时则返回304
			           url : "le15Serv",
			           data:({"showid":obj.name+"3","place2":$('#ptwo').val()}),
			           dataType: "text",
			           cache : true,
			           contentType:"application/x-www-form-urlencoded;charset=utf-8", //重要是这个定义字符编码
			           async : false,
			           success: function(result) {
			                if (result=="error") {
			                	alert("error");
			                } else {
			                var a=result.split(";");
			                var b=document.getElementById("pthree");
			                   for(var i=0;i<result.split(";").length-1;i++){  
					         var newopt=document.createElement("option");
                                  newopt.text=a[i];
                                 newopt.value=a[i];
                                 b.options.add(newopt,0);
                 }
			                }
			            }
			        });
			}	
			
			function openDiv(fackId){ 
			 $('#fackId').val(fackId);
		   jQuery.ajax({
			           type: "GET", //第一次抓文件建立本地缓存返回200,再次访问时如果本地缓存有效时则返回304
			           url : "Information",
			           data:({"showid":"imageitem"}),
			           dataType: "text",
			           cache : false,
			           contentType:"application/x-www-form-urlencoded;charset=utf-8", //重要是这个定义字符编码
			           async : false,
			           success: function(result) {
			                if (result==0) {
			                	alert("获取图文列表失败");
			                } else {
			                document.getElementById("content").options.length=0;
			                var a=result.split(";");
			                var b=document.getElementById("content");
			                   for(var i=0;i<result.split(";").length-1;i++){
					         var newopt=document.createElement("option");
                                  newopt.text=a[i].split(",")[1];
                                 newopt.value=a[i].split(",")[0];
                                 b.options.add(newopt,0);
                                 }
			                }
			            }
			        });
			        DivWindowOpen(380,125,'container');
		}
		function sub(){
				var fackId = $('#fackId').val();
				var content = $('#content').val();
				if(fackId!="" && content!=""){
					jQuery.ajax({
			           type: "POST", 
			           url : "SendMessageServlet",
			           data:({"fackId":fackId,"content":content}),
			           dataType: "text",
			           cache : true,
			           contentType:"application/x-www-form-urlencoded;charset=gb2312", //重要是这个定义字符编码
			           async : false,
			           success: function(result) {
			              $('#content').val("");
					      if(result==1){
					         alert('发送成功!');
					      }else{
					         alert('发送失败，请重新登陆!');
					      }
			           }
			        });
				}else{
					alert('请填写内容');
				}
			 }	
			 function suball(){
				var fackId = $('#fackId').val();
				var content = $('#content').val();
				if(fackId!="" && content!=""){
					jQuery.ajax({
			           type: "GET", //第一次抓文件建立本地缓存返回200,再次访问时如果本地缓存有效时则返回304
			           url : "SendMessageServlet",
			           data:({"fackId":fackId,"content":content}),
			           dataType: "text",
			           cache : true,
			           contentType:"application/x-www-form-urlencoded;charset=gb2312", //重要是这个定义字符编码
			           async : false,
			           success: function(result) {
			              $('#content').val("");
					      if(result==1){
					         alert('发送成功!');
					      }else{
					         alert('发送失败!请重新登陆');
					      }
			           }
			        });
				}else{
					alert('请填写内容');
				}
			 }	
			     function freshfans(){
			             jQuery.ajax({
			                  type:"POST",
			                  url:"Information",
			                  data:({"action":"freshfans"}),
			                  dataType:"text",
			                  cache:true,
			                  async:false,
			                  contentType:"application/x-www-form-urlencoded;charset=gb2312",
			                  success:function(result){
			                     if(result==0)
			                       alert('error');
			                     else alert('刷新成功！');
			                  
			                  }
			             });
			         
			     
			     
			     }
</script>
<!--[if IE 7]>
<link rel="stylesheet" href="./resource/style/font-awesome-ie7.min.css">
<![endif]-->
<!--[if lte IE 6]>
<link rel="stylesheet" type="text/css" href="./resource/style/bootstrap-ie6.min.css">
<link rel="stylesheet" type="text/css" href="./resource/style/ie.css">
<![endif]-->
</head>
<body >
<div class="main">
	<div class="stat">
		<div class="stat-div">
			<div class="navbar navbar-static-top">
				<div class="navbar-inner">
					<span class="brand">关键指标详解</span>
				</div>
				<div class="sub-item">
					<h4 class="sub-title">搜索</h4>
					<form action="le15Serv" method="post">
					<input type="hidden" name="act" value="display" />
					<table class="table sub-search">
					<tbody>
						<tr>
							<th>选择类别</th>
							<td>
																<label class="checkbox inline"><input type="checkbox" name="select[0]" value="groupid"  onclick="show1(this)"/> 组别</label> <select name="groupid" id="groupid" ><option>全部</option></select>
																<label class="checkbox inline"><input type="checkbox" name="select[1]" value="gender"  /> 性别</label>  <select name="gender" id="gender" ><option value=""0>全部</option><option value="1">男</option><option value="2">女</option></select>
																<br/><label class="checkbox inline"><input type="checkbox" name="select[2]" value="place"  onclick="show2(this)"/> 地区</label><select  name="place" id="pone" onchange="show3(this)"><option>全部</option></select>
																<select name="place" id="ptwo" onchange="show4(this)"><option>全部</option></select><select name="place" id="pthree" ><option>全部</option></select>
																
															</td>
						</tr>
						<tr>
							<th>关注时间</th>
							<td>
								<button style="margin:0;" class="btn span5" id="date-range" type="button"><span class="date-title" >2013-10-1 至 2013-10-1</span> <i class="icon-caret-down"></i></button>
								<input name="start" type="hidden" value="2013-10-1" />
								<input name="end" type="hidden" value="2013-10-1" />
							</td>
						</tr>
						<tr>
							<th></th>
							<td><input type="submit" name="" value="搜索" class="btn btn-primary"></td>
						</tr>
					</tbody>
					</table>
					</form>
				</div>
			</div>
			<div class="sub-item" id="table-list">
				<h4 class="sub-title">详细数据</h4>
				<!--  <input type="button" value="刷新粉丝" onclick="freshfans()"/>   --> 
				<form action="" method="post" onsubmit="">
				<div class="sub-content">
					<table class="table table-hover">
						<thead class="navbar-inner">
							<tr>
								<th style="width:10%;" class="row-first">组别</th>
								<th class="row-hover" style="width:15%;">ID<i></i></th>
								<th class="row-hover" style="width:15%;">昵称<i></i></th>
								<th class="row-hover" style="width:15%;">城市<i></i></th>
								<th class="row-hover" style="width:15%;">性别<i></i></th>
								<th class="row-hover" style="width:15%;">关注时间<i></i></th>
								<th style="width:*;">操作<i></i></th>
								<th></th>
							</tr>
							<c:forEach items="${sessionScope.fansarray}" var="fans">
							     <tr>
							       <th>${fans.groupname}</th>
							       <th>${fans.fakeid}</th>
							       <th>${fans.nickname}</th>
							       <th>${fans.city}</th>
							       <th>${fans.sex}</th>
							       <th>${fans.sub_time}</th>
							       <th><a onclick="openDiv('${fans.fakeid}');" href="#"><img src="resource/image/edit2.png" width="16" height="16" /></a></th>
							     </tr>
							</c:forEach>
						</thead>
						<tbody>
													</tbody>
					</table>
					<table class="table">
						<tr>
							<td class="row-first"></td>
							<td>
								<input type="hidden" name="token" value="da083f61" />
							</td>
						</tr>
					</table>
				</div>
				</form>
							</div>
		</div>
	</div>
</div>
<link type="text/css" rel="stylesheet" href="./resource/style/daterangepicker.css" />
<script type="text/javascript" src="./resource/script/daterangepicker.js"></script>
<script>
$(function() {
	//详细数据相关操作
	var tdIndex;
	$("#table-list thead").delegate("th", "mouseover", function(){
		if($(this).find("i").hasClass("")) {
			$("#table-list thead th").each(function() {
				if($(this).find("i").hasClass("icon-sort")) $(this).find("i").attr("class", "");
			});
			$("#table-list thead th").eq($(this).index()).find("i").addClass("icon-sort");
		}
	});
	$("#table-list thead th").click(function() {
		if($(this).find("i").length>0) {
			var a = $(this).find("i");
			if(a.hasClass("icon-sort") || a.hasClass("icon-caret-up")) { //递减排序
				/*
					数据处理代码位置
				*/
				$("#table-list thead th i").attr("class", "");
				a.addClass("icon-caret-down");
			} else if(a.hasClass("icon-caret-down")) { //递增排序
				/*
					数据处理代码位置
				*/
				$("#table-list thead th i").attr("class", "");
				a.addClass("icon-caret-up");
			}
			$("#table-list thead th,#table-list tbody:eq(0) td").removeClass("row-hover");
			$(this).addClass("row-hover");
			tdIndex = $(this).index();
			$("#table-list tbody:eq(0) tr").each(function() {
				$(this).find("td").eq(tdIndex).addClass("row-hover");
			});
		}
	});
	$('#date-range').daterangepicker({
        format: 'YYYY-MM-DD',
        startDate: $(':hidden[name=start]').val(),
        endDate: $(':hidden[name=end]').val(),
        locale: {
            applyLabel: '确定',
            cancelLabel: '取消',
            fromLabel: '从',
            toLabel: '至',
            weekLabel: '周',
            customRangeLabel: '日期范围',
            daysOfWeek: moment()._lang._weekdaysMin.slice(),
            monthNames: moment()._lang._monthsShort.slice(),
            firstDay: 0
        }
    }, function(start, end){
        $('#date-range .date-title').html(start.format('YYYY-MM-DD') + ' 至 ' + end.format('YYYY-MM-DD'));
        $(':hidden[name=start]').val(start.format('YYYY-MM-DD'));
        $(':hidden[name=end]').val(end.format('YYYY-MM-DD'));
    });
});
</script>
	<DIV style="DISPLAY: none" id="container" class="xbox">
	 
	
		<pre><h4>选择图文单发或群发：           <a id="box_close" class="xbox-close"><b>&nbsp;</b>关闭</a></h4></pre>
	
	<br/>
	<div class="tishi">
	<!-- <textarea rows="8" cols="10" id="content" >100000</textarea> -->	
		标题：<select id="content"><option>请选择</option></select>
		<input type="hidden" id="fackId"/>
		<input type="button" class="input_tj" value="单发" onclick="sub()" />
		<input type="button" class="input_tj" value="群发" onclick="suball()" />
		
	</div>
	<ul class="detail">
	</ul>
</div>
	<div class="emotions" style="display:none;"></div>
</body>
</html>