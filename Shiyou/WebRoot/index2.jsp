<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="Shortcut Icon" href="/ROOT/favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微信管理后台</title>
<meta name="keywords" content="微信,微信公众平台" />
<meta name="description" content="微信公众平台  微信公众平台管理系统。" />
<link type="text/css" rel="stylesheet" href="./resource/style/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/font-awesome.css" />
<link type="text/css" rel="stylesheet" href="./resource/style/common.css" />
<script type="text/javascript" src="./resource/script/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./resource/script/bootstrap.js"></script>
<script type="text/javascript" src="./resource/script/common.js"></script>
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
<div id="header">
	<div class=" pull-left"><a href="#"></a></div>
	<!-- 导航 -->
	<div class="hnav clearfix">
		<div class="row-fluid">
			<ul class="hnav-main text-center unstyled pull-left">
                <li class="hnav-parent"><a href="" target="main"><i class="icon-home icon-2x"></i>管理首页</a></li>
				<li class="hnav-parent">
					<a href=""><i class="icon-star icon-2x"></i>常用</a>
					<ul class="hnav-child unstyled text-left">
						<li><a target="main" href="">测试规则</a></li>
						<li><a target="main" href="">更新缓存</a></li>
					</ul>
				</li>
				<li class="hnav-parent"><a href="" target="_blank"><i class="icon-comment icon-2x"></i>论坛</a></li>
				<li class="hnav-parent"><a href="https://mp.weixin.qq.com/" target="_blank"><i class="icon-comments icon-2x"></i>公众平台</a></li>
				<li class="hnav-parent"><a href="" target="_blank"><i class="icon-question-sign icon-2x"></i>帮助</a></li>
			</ul>
			<!-- 右侧管理菜单 -->
			<ul class="hnav-manage text-center unstyled pull-right">
				<li class="hnav-parent" id="wechatpanel">
					<a href=""><i class="icon-chevron-down icon-large"></i><span id="current-account" onclick="s()">${pub}</span></a>
					<ul class="hnav-child unstyled text-left">
					                          <c:forEach items="${pubidlist}" var="pubid">
													<li><a href="Login?cname=${pubid.name}"">${pubid.name}</a></li>
											</c:forEach>
											</ul>
				</li>
				<li class="hnav-parent"><a href=""><i class="icon-user icon-large"></i>${manager.user}</a></li>
				<li class="hnav-parent"><a href="../we/login.jsp"><i class="icon-signout icon-large"></i>退出</a></li>
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
				<td valign="top" height="100%" class="content-left"  >
					<div class="sidebar-nav" style="overflow:auto;height:565px;">
					
													
																				<ul class="snav unstyled">
														<li class="snav-header-list"><a href="http://dkbbs.touclick.com/admin/User/MngUser.aspx
" target="main">用户管理<i class="arrow"></i></a></li>
																				</ul>
																				<ul class="snav unstyled">
														<li class="snav-header-list"><a href="http://dkbbs.touclick.com/admin/otherFun/LinkMaker.aspx
" target="main">制作链接<i class="arrow"></i></a></li>
																				</ul>
																				<ul class="snav unstyled">
														<li class="snav-header"><a href="">DATABASE-W<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=w" target="main">聊天记录<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=w0" target="main">聊天ROBOT<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=w1" target="main">管理员账号<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=w2" target="main">id-openid对应关系<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=w3" target="main">图片音频回复<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=w4" target="main">微信公众账号<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=w5" target="main">规则<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=w6" target="main">粉丝<i class="arrow"></i></a></li>
																				</ul>
												<ul class="snav unstyled">
														<li class="snav-header"><a href="">DATABASE-CSDK<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dk0" target="main">rkwrdmatchreply<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dk1" target="main">rlooknews<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dk2" target="main">rregactiv<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dk3" target="main">rregist<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dk4" target="main">rusersendmsg<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dk5" target="main">tactivity<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dk6" target="main">tdbpicwrd<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dk7" target="main">tkwrd<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dk8" target="main">tnews<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dk9" target="main">toperator<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dka" target="main">tpubacct<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dkb" target="main">tsglpag<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dkc" target="main">tsysreply<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dkd" target="main">tuser<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dke" target="main">tuseracct<i class="arrow"></i></a></li>
														<li class="snav-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=cs_dked" target="main">用户管理<i class="arrow"></i></a></li>
																				</ul>
																				<ul class="snav unstyled">
														<li class="snav-header-list"><a href="../WabacusBlank/ShowReport.wx?PAGEID=dk" target="main">DZTABASE-DK<i class="arrow"></i></a></li>
																				</ul>
												<ul class="snav unstyled">
												<ul class="snav unstyled">
											</div>
					<!-- 右侧管理菜单上下控制按钮 
					<div class="scroll-button">
						<span class="scroll-button-up"><i class="icon-caret-up"></i></span>
						<span class="scroll-button-down"><i class="icon-caret-down"></i></span>
					</div>
					 end -->
				</td>
				<td valign="top" height="100%" style="" ><iframe width="100%" scrolling="yes" height="100%" frameborder="0" style="min-width:800px; overflow:visible; height:100%;" name="main" id="main" src="http://dkbbs.touclick.com/admin/otherFun/LinkMaker.aspx"></iframe></td>
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
		$(".sidebar-nav").css({'position' : 'absolute', 'top' : top});
	});
	$(".scroll-button .scroll-button-down").click(function() {
		postion = $(".sidebar-nav").position().top;
		if(postion < 0 || postion==0) {
			top = postion-$(".content-main").height()+50;
			if(top< -($(".sidebar-nav").height()-$(".content-main").height()+50)) top = -($(".sidebar-nav").height()-$(".content-main").height()+50);
		} else {
			top =0;
		}
		$(".sidebar-nav").css({'position' : 'absolute', 'top' : top});
	});
	$.getScript('http%3A%2F%2Fs13.cnzz.com%2Fstat.php%3Fid%3D1998411%26web_id%3D1998411');
	$.get('index.php?act=announcement', function(s){
		$('body').append(s);
		if(cookie.get("we7_tips") == "0") {
			$("#we7_tips").hide();
		}
	});
});
$(window).resize(function(){
	//调整框架宽高 兼容ie8
	$(".content-main, .content-main table td").height($(window).height()-65);
	$("#main").width($(window).width()-200);
});
</script>
