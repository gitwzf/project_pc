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
<link type="text/css" rel="stylesheet" href="./resource/style/common.css?v=1382944595" />
<script type="text/javascript" src="./resource/script/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./resource/script/bootstrap.js"></script>
<script type="text/javascript" src="./resource/script/common.js?v=1382944595"></script>
<script type="text/javascript" src="./resource/script/emotions.js"></script>
<script type="text/javascript">
cookie.prefix = 'f1c8_';
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
<link type="text/css" rel="stylesheet" href="./resource/style/daterangepicker.css" />
<script type="text/javascript" src="./resource/script/daterangepicker.js"></script>
<script type="text/javascript">
$(function() {
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
function range(days) {
    var start = moment().add('days', 0 - days).format('YYYY-MM-DD');
    var end = moment().format('YYYY-MM-DD');
    $('#date-range .date-title').html(start + ' 至 ' + end);
    $(':hidden[name=start]').val(start);
    $(':hidden[name=end]').val(end);
    $('form[method=get]')[0].submit();
}
</script>
    <div class="main">
		<div class="stat">
			<div class="stat-div">
				<div class="navbar navbar-static-top">
					<div class="navbar-inner">
						<span class="brand">关键指标详解</span>
						<div class="pull-left">
							<ul class="nav">
								<li class="active"><a href="stat.php?act=history&do=display&searchtype=rule">已有规则回复</a></li>
								<li ><a href="stat.php?act=history&do=display&searchtype=default">默认规则回复</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="sub-item">
					<form action="le21Serv" method="get">
					<div class="pull-right">
						<input class="btn btn-primary" type="submit" value="搜索">
					</div>
					<div class="pull-left">
                        <input name="act" type="hidden" value="history" />
                        <input name="do" type="hidden" value="" />
                        <input name="searchtype" type="hidden" value="" />
						<input type="text" class="span2 kw" name="keyword" value="" placeholder="请输入关键字">
                        <input name="start" type="hidden" value="2013-10-28" />
                        <input name="end" type="hidden" value="2013-10-28" />
						<button class="btn" id="date-range" class="date" type="button"><span class="date-title">2013-10-28 至 2013-10-28</span> <i class="icon-caret-down"></i></button>
						<span class="date-section"><a href="javascript:;" onclick="range(7);">7天</a><a href="javascript:;" onclick="range(14);">14天</a><a href="javascript:;" onclick="range(30);">30天</a></span>
					</div>
					</form>
				</div>
				<div class="sub-item" id="table-list">
					<h4 class="sub-title">详细数据</h4>
					<div class="sub-content">
						<table class="table table-hover">
							<thead class="navbar-inner">
								<tr>
									<th style="width:80px;">用户<i></i></th>
									<th class="row-hover">内容<i></i></th>
									<th style="width:110px;">规则<i></i></th>
									<th style="width:80px;">模块<i></i></th>
									<th style="width:100px;">时间<i></i></th>
									<th style="width:110px;">操作</th>
								</tr>
							</thead>
							<tbody>
							 <c:forEach items="${chatrecord}" var="record">					 
							   <tr>
							      <td>${record.userid}</td>
							      <td>${record.usersay }</td>
							      <td>${record.wreply }</td>
							      <td>${record.wreply_type }</td>
							      <td>${record.time }</td>
							   </tr>
								</c:forEach>							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
    </div>

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
});
</script>
	
	<div class="emotions" style="display:none;"></div>
</body>
</html>