<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="model.Pubid"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="Shortcut Icon" href="http://xiaowangzi.touclick.com/favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微信党刊后台管理</title>
<meta name="keywords" content="微信,微信公众平台" />
<meta name="description" content="微信公众平台  微信公众平台管理系统。" />
<link type="text/css" rel="stylesheet" href="./resource/style/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/font-awesome.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/common.css?v=1382939943" />
<script type="text/javascript" src="./resource/script/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./resource/script/bootstrap.js"></script>
<script type="text/javascript" src="./resource/script/common.js?v=1382939943"></script>
<script type="text/javascript" src="./resource/script/emotions.js"></script>

<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript" src="resource/js/windows.js"></script>
<script type="text/javascript">
cookie.prefix = 'f1c8_';

   $(function)(){
   $("#current-account").html("非");
   // alert("yes");
   }
   function s(){
  //   var a=document.getElementById("current-account");
    
   
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
<body style="height:100%; overflow:hidden;" scroll="no">
<%session.setMaxInactiveInterval(-1); %>
<div id="header">
	<div class=" pull-left"><a href="#"></a></div>
	<!-- 导航 -->
	<div class="hnav clearfix">
		<div class="row-fluid">
			<ul class="hnav-main text-center unstyled pull-left">
              <li class="hnav-parent"><a href="https://mp.weixin.qq.com/" target="_blank"><i class="icon-comments icon-2x"></i>公众平台</a></li>
			</ul>
			<!-- 右侧管理菜单 -->
			<ul class="hnav-manage text-center unstyled pull-right">
				<li class="hnav-parent" id="wechatpanel">
					<a href=""><i class="icon-chevron-down icon-large"></i><span id="current-account" onclick="s()">非公党刊</span></a>
					<ul class="hnav-child unstyled text-left">
											</ul>
				</li>
				<li class="hnav-parent"><a href=""><i class="icon-user icon-large"></i>${manager.user}</a></li>
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
												<ul class="snav unstyled">
														<li class="snav-header"><a href="">当前公众号<i class="arrow"></i></a></li>
																												<li class="snav-list"><a href="le11Serv" target="main">添加规则<i class="arrow"></i></a></li>
																			<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=dkdked&pub=${manager.user}" target="main">用户管理<i class="arrow"></i></a></li>
											<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=dk_cj&pub=${manager.user}" target="main">抽奖<i class="arrow"></i></a></li>
											<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=w_1&pub=${manager.user}
" target="main">推荐活动报名<i class="arrow"></i></a></li>	
<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=dk_book&pub=${manager.user}
" target="main">问卷管理<i class="arrow"></i></a></li>	
<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=dk_tag&pub=${manager.user}
" target="main">标签管理<i class="arrow"></i></a></li>	
<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=dkdk&pub=${manager.user}
" target="main">电商管理<i class="arrow"></i></a></li>
<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=plistenmz_dk&pub=${manager.user}
" target="main">党建好声音<i class="arrow"></i></a></li>
									</ul>
												
												<ul class="snav unstyled">
														<li class="snav-header-list"><a href="le3Serv" target="main">公众号管理<i class="arrow"></i></a></li>
																				</ul>
																				<ul class="snav unstyled">
														<li class="snav-header"><a href="">新闻管理<i class="arrow"></i></a></li>
																												<li class="snav-list"><a href="http://dkbbs.touclick.com/admin/News/MngNews.aspx" target="main">新闻<i class="arrow"></i></a></li>
																			<li class="snav-list"><a href="crudServ" target="main">类别<i class="arrow"></i></a></li>
											                                      </ul>
												<ul class="snav unstyled">
												<ul class="snav unstyled">
														<li class="snav-header-list"><a href="http://dkbbs.touclick.com/admin/Activity/MngActiv.aspx" target="main">活动管理<i class="arrow"></i></a></li>
																				</ul>
																				<ul class="snav unstyled">
														<li class="snav-header-list"><a href="http://dkbbs.touclick.com/admin/SglPage/MngSglPage.aspx
" target="main">单页管理<i class="arrow"></i></a></li>
																				</ul>
																				<ul class="snav unstyled">
														<li class="snav-header-list"><a href="http://dkbbs.touclick.com/admin/otherFun/LinkMaker.aspx
" target="main">制作链接<i class="arrow"></i></a></li>
																				</ul>
														
														<ul class="snav unstyled">
														<li class="snav-header"><a href="">微网站设置<i class="arrow"></i></a></li>
																												<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=dk_lt_a&pub=${manager.user}" target="main">页面设置<i class="arrow"></i></a></li>
																													<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=dk_lt_b&pub=${manager.user}" target="main">菜单设置<i class="arrow"></i></a></li>
																												<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=dk_lt_aa&pub=${manager.user}" target="main">功能设置<i class="arrow"></i></a></li>
																											</ul>
																												<ul class="snav unstyled">
														<li class="snav-header"><a href="">论坛管理<i class="arrow"></i></a></li>
																												<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=dk_lt_f&pub=${manager.user}" target="main">页面管理<i class="arrow"></i></a></li>
																												<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=dk_lt_c&pub=${manager.user}" target="main">栏目管理<i class="arrow"></i></a></li>
																												<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=dk_lt_d&pub=${manager.user}" target="main">帖子管理<i class="arrow"></i></a></li>
																												<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=dk_lt_e&pub=${manager.user}" target="main">评论管理<i class="arrow"></i></a></li>
																												</ul>
																												
																												</ul>					
												
											</div>
					<!-- 右侧管理菜单上下控制按钮 -->
					<div class="scroll-button">
						<span class="scroll-button-up"><i class="icon-caret-up"></i></span>
						<span class="scroll-button-down"><i class="icon-caret-down"></i></span>
					</div>
					<!-- end -->
				</td>
				<td valign="top" height="100%" style=""><iframe width="100%" scrolling="yes" height="100%" frameborder="0" style="min-width:800px; overflow:visible; height:100%;" name="main" id="main" src="le11.jsp"></iframe></td>
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
});
$(window).resize(function(){
	//调整框架宽高 兼容ie8
	$(".content-main, .content-main table td").height($(window).height()-65);
	$("#main").width($(window).width()-200);
});
</script>
