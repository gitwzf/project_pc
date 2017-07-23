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
<title>师友网后台管理</title>
<meta name="description" content="平台管理系统。" />
<link type="text/css" rel="stylesheet" href="./resource/style/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/font-awesome.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/common.css?v=1382939943" />
<script type="text/javascript" src="./resource/script/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./resource/script/bootstrap.js"></script>
<script type="text/javascript" src="./resource/script/common.js?v=1382939943"></script>
<script type="text/javascript" src="./resource/script/emotions.js"></script>

<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript" src="resource/js/windows.js"></script>
<script type="text/javascript" src="resource/js/jquery.form.js"></script>
<style type="text/css">
  button{
     height:30px;
     width:100%;
	background:#36393a;
	color:#fff;
	font-family:"微软雅黑";
	border:none;
	border-bottom:1px solid #999;
  }
  .mark1{
    margin-left:-10px;
  }
  .mark2{
    margin-left:-36px;
  }
</style>
<script type="text/javascript">
function  updatesql(sql){
              $.post("UpdatesqlServ",{sql:sql},function(data){
                     if(data=='00')alert("请重新登陆");
                     else if(data=='11')alert("执行成功");
              });
         } 
</script>
</head>
<body style="height:100%; overflow:hidden;" scroll="no">
<%session.setMaxInactiveInterval(-1); %>
<div id="header">
	<div class=" pull-left"><a href="#"></a></div>
	<!-- 导航 -->
	<div class="hnav clearfix">
		<div class="row-fluid">
			<ul class="hnav-main text-center unstyled pull-left">
         	</ul>
			<!-- 右侧管理菜单 -->
			<ul class="hnav-manage text-center unstyled pull-right">
				<li class="hnav-parent" id="wechatpanel">
					<a href=""><i class="icon-chevron-down icon-large"></i><span id="current-account" onclick="s()">${user.username}</span></a>
					<ul class="hnav-child unstyled text-left">
											</ul>
				</li>
				<li class="hnav-parent"><a href=""><i class="icon-user icon-large"></i>管理员</a></li>
				<li class="hnav-parent"><a href="./login.jsp"><i class="icon-signout icon-large"></i>退出</a></li>
			</ul>
			<!-- end -->
		</div>
	</div>
	<!-- end -->
</div>
<!-- 头部 end -->
<div class="content-main">
	<table width="1054" height="257" cellspacing="0" cellpadding="0" id="frametable">
		<tbody>
			<tr>
				<td valign="top" height="100%" class="content-left" style="overflow:hidden;">
					<div class="sidebar-nav" style="">
												<!-- 
														<li class="snav-header"><a href="">aaa<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="">sss</li>
												 -->
												           <ul class="snav unstyled">
															<li class="snav-header"><a href="">用户管理<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_user&pub=${user.username }">用户列表</li>
														<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_school&pub=${user.username }">学校管理</li>
														<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_student&pub=${user.username }">学生管理</li>
														<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_teacher&pub=${user.username }">导师管理</li>
																		</ul>
																		<ul class="snav unstyled">
															<li class="snav-header"><a href="./ShowReport.wx?PAGEID=shiyouwang_school&pub=${user.username }">演讲管理<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_speech&pub=${user.username }">演讲管理</li>
														<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_invite&pub=${user.username }">邀约</li>
															<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_preinvite&pub=${user.username }">讲座预告</li>
																	</ul>
																		<c:if test="${user.privlg==0}">					
														<ul class="snav unstyled">
															<li class="snav-header"><a href="">系统管理<i class="arrow"></i></a></li>
															<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_hpage&pub=${user.username }">首页管理</li>
														<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_person_info&pub=${user.username }">个人信息</li>
														<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_admin&pub=${user.username }">管理员管理</li>
													<!-- 	<li class="snav-list"><a href="">栏目管理</li>  -->
														<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_type&pub=${user.username }">分类管理</li>
																		</ul>
																		</c:if>
																				<ul class="snav unstyled">
														<li class="snav-header-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_news&pub=${user.username }" target="main">资讯管理<i class="arrow"></i></a></li>
																				</ul>
																				<ul class="snav unstyled">
														<li class="snav-header-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_share&pub=${user.username }" target="main">分享管理<i class="arrow"></i></a></li>
																				</ul>
																					<ul class="snav unstyled">
															<li class="snav-header"><a href="">活动管理<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_active&pub=${user.username }">活动</li>
															<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_activesignup&pub=${user.username }">活动报名记录</li>
															</ul>	
															<c:if test="${user.privlg==0}">	
															<ul class="snav unstyled">
															<li class="snav-header"><a href="">结对管理<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_pair&pub=${user.username }">结对关系</li>
														<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_pairapply&pub=${user.username }">结对申请</li>
														<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_pairpre&pub=${user.username }">结对预选</li>
														<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_pairend&pub=${user.username }">结对结果</li>
																<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_pairtime&pub=${user.username }">结对时间</li>
																<li class="snav-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_pairauto&pub=${user.username }">是否自动分配</li>
																</ul>	
																</c:if>
																		<ul class="snav unstyled">
														<li class="snav-header-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_comment&pub=${user.username }" target="main">评论管理<i class="arrow"></i></a></li>
																				</ul>	
																	<ul class="snav unstyled">
														<li class="snav-header-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_reply&pub=${user.username }" target="main">回复管理<i class="arrow"></i></a></li>
																				</ul>
																				
																		<ul class="snav unstyled">
														<li class="snav-header-list"><a href="./ShowReport.wx?PAGEID=shiyouwang_singlepage&pub=${user.username }" target="main">单页管理<i class="arrow"></i></a></li>
																				</ul>	
																		
											</div>
					<!-- 右侧管理菜单上下控制按钮 -->
					<div class="scroll-button">
						<span class="scroll-button-up"><i class="icon-caret-up"></i></span>
						<span class="scroll-button-down"><i class="icon-caret-down"></i></span>
					</div>
					<!-- end -->
				</td>
				<td valign="top" height="100%" style=""><iframe width="100%" scrolling="yes" height="100%" frameborder="0" style="min-width:800px; overflow:visible; height:100%;" name="main" id="main" src="./ShowReport.wx?PAGEID=shiyouwang_person_info&pub=${user.username }"></iframe></td>
			</tr>
		</tbody>
	</table>
</div>
<script type="text/javascript">
function max(a) {
	var b = a[0];
	for(var i=1;i<a.length;i++){ if(b<a[i])b=a[i]; }
	return b;
}
function currentMenuItem(a) {
	window.frames['main'].location.href= a;
}
function scrollButton() {
	if($(".sidebar-nav").height() > $(".content-main").height()) {
		$(".scroll-button").show();
	} else {
		if($(".sidebar-nav").position().top == 0) $(".scroll-button").hide();
	}
}
function switchHandler(s) {
	window.frames['main'].location.reload();
	$('#current-account').html(s);
}

	//顶部子导航
	$(".hnav").delegate(".hnav-parent", "mouseover", function(){
			var $this = this;
			var tmp = new Array();
			$($this).find(".hnav-child").show();
			$($this).find(".hnav-child li").each(function(i) {
				tmp[i] = $($this).find("a").width();
			});
			$($this).find(".hnav-child li a").css("width", max(tmp));
			$($this).find(".hnav-child").css("left", $($this).offset().left);
		
		return false;
	});
	$(".hnav").delegate(".hnav-parent", "mouseout", function(){
		$(".hnav-child").hide();
	});
	//左侧导航
	$(".sidebar-nav").delegate(".snav-header", "click", function(){
		$(this).toggleClass("open");
		$(this).parent().find(".snav-list").each(function(i) {
			$(this).toggle();
		});
		scrollButton();
		return false;
	});
	$(".sidebar-nav .snav").each(function() {
		if($(this).find(".snav-header").hasClass("open")) {
			$(this).find(".snav-list").each(function() {
				$(this).find(".snav-header").toggle();
			});
		}
		$(this).find(".snav-list").each(function() {
			if($(this).hasClass("current")) {
				$(this).parent().find(".snav-header").toggleClass("open");
				$(this).parent().find(".snav-list").each(function() {
					$(this).toggle();
				});
			}
		});
		$(this).find(".snav-list a,.snav-header-list a").click(function() {
			$(".snav-list,.snav-header-list").removeClass("current");
			$(this).parent().addClass("current");
			currentMenuItem($(this).attr("href"));
			return false;
		});
	});

$(function() {
	//调整框架宽高 兼容ie8
	$(".content-main, .content-main table td").height($(window).height()-65);
	$("#main").width($(window).width()-200);
	//右侧菜单上下控制按钮
	var postion = 0,top = 0;
	$(".scroll-button .scroll-button-up").click(function() {
		postion = $(".sidebar-nav").position().top;
		if(postion > 0 || postion==0) {
			top = 0;
		} else {
			top = postion+$(".content-main").height()-50;
			if(top > 0) top =0;
		}
		$(".sidebar-nav").css({'position' : 'relative', 'top' : top});
	});
	$(".scroll-button .scroll-button-down").click(function() {
		postion = $(".sidebar-nav").position().top;
		if(postion < 0 || postion==0) {
			top = postion-$(".content-main").height()+50;
			if(top< -($(".sidebar-nav").height()-$(".content-main").height()+50)) top = -($(".sidebar-nav").height()-$(".content-main").height()+50);
		} else {
			top =0;
		}
		$(".sidebar-nav").css({'position' : 'relative', 'top' : top});
	});
//	$.getScript('http%3A%2F%2Fs13.cnzz.com%2Fstat.php%3Fid%3D1998411%26web_id%3D1998411');
	//$.get('index.php?act=announcement', function(s){
	//	$('body').append(s);
	//	if(cookie.get("we7_tips") == "0") {
	//		$("#we7_tips").hide();
	//	}
//	});
});
$(window).resize(function(){
	//调整框架宽高 兼容ie8
	$(".content-main, .content-main table td").height($(window).height()-65);
	$("#main").width($(window).width()-200);
});
</script>