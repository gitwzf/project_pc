<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" />
    <script src="js/jquery-1.9.1.min.js"></script>
     <script src="js/js.js?f=2"></script>
      <script src="js/jslib.js"></script>
    <link href="css/css.css?y=5" rel="stylesheet" />
    <script type="text/javascript">
    $(function(){
     $('#sendresume').on('click',function() {
		if ('${user}' == '') {
			TouClick.mask(1).float('请重新访问该页面');
			return;
		}
		if ('${user.state}'=='0') {
			TouClick.mask(1).float("请先完善你的简历");
			return;
		}
		$.post("HunterServ", {
			hunterid : '${hunterdetail.id}'
		}, function(data) {
			if (data == "11")
				TouClick.mask(1).float("投递成功");
			else{
				TouClick.mask(1).float("一周内请勿重复投递");}
		});

	});
    
    });
    
    </script>
</head>
<body>
    <div class="contain">
        <a class="title" href="javascript:void(0)">金牌猎头</a>
        <div class="uc-top">
            <img src="${hunterdetail.head_url}" class="avatar" />
            <h3 class="uc-name">${hunterdetail.name}<c:if test="${!empty hunterdetail.ename }"><c:if test="${hunterdetail.ename!=''}">（${hunterdetail.ename}）</c:if></c:if></h3>
            <span class="renzheng">(实名认证)</span>
            <h4 class="dengji">
                <span>猎头等级：</span>
                 <c:if test="${hunterdetail.rank==1}"><a class="active"></a><a class=""></a><a class=""></a><a class=""></a><a class=""></a></c:if>
                 <c:if test="${hunterdetail.rank==2}"><a class="active"></a><a class="active"></a><a class=""></a><a class=""></a><a class=""></a></c:if>
                <c:if test="${hunterdetail.rank==3}"><a class="active"></a><a class="active"></a><a class="active"></a><a class=""></a><a class=""></a></c:if>
                <c:if test="${hunterdetail.rank==4}"><a class="active"></a><a class="active"></a><a class="active"></a><a class="active"></a><a class=""></a></c:if>
                <c:if test="${hunterdetail.rank==5}"><a class="active"></a><a class="active"></a><a class="active"></a><a class="active"></a><a class="active"></a></c:if>
               
            </h4>
            <span class="dengji">擅长职能：${hunterdetail.typeone }</span>
        </div>
        
         <div class="uc-top uc-content" >
          <c:if test="${!empty hunterdetail.position }">
            <h3 class="uc-name">职位：${hunterdetail.position }</h3>
            </c:if>
            <h3 class="uc-name">关注度：${hunterdetail.focuscnt }</h3>
            <h3 class="uc-name">从业年限：${hunterdetail.workyear }</h3>
        </div>
		<c:if test="${!empty hunterdetail.info }">
        <div class="uc-top uc-content" >
            <h3 class="uc-name">介绍</h3>
           <div>${hunterdetail.info }</div>
        </div>
        </c:if>
        <c:if test="${!empty hunterdetail.background }">
         <div class="uc-top uc-content" >
            <h3 class="uc-name">背景及典型成功案例</h3>
            <div>${hunterdetail.background }</div>
        </div>
        </c:if>
        <c:if test="${!empty hunterpositionlist}">
        <a class="title" href="javascript:void(0)">职位</a>
        <c:forEach items="${hunterpositionlist}" var="position">
        <a href="./CompanyServ?type=positiondetail&positionid=${position.id}&hunterid=${hunterdetail.id}">
            <section class="list1 list2 list3 list4" >
                <div class="list-wapper">
                    <h3>${position.position_name }</h3>
                    <c:if test="${!empty position.add_tim}">
                    <p class="list-item">时间：${position.add_tim }</p>
                    </c:if>
                    <c:if test="${!empty position.peoples }">
                    <p class="list-item left">招聘人数：${position.peoples }人</p>
					</c:if>
					<c:if test="${!empty position.salary }">
                    <p class="list-item right">年薪：<span class="color-red">${position.salary }</span></p>
              </c:if>
                </div>
            </section>
            </a>
       </c:forEach>
        </c:if>
        <c:if test="${empty hunterpositionlist}">
        <a class="but" href="javascript:void(0)" id="sendresume">委托简历</a>
        </c:if>
    </div>
    
     <mask style="display:none"></mask>
    <float style="display:none">
        <p>请完善您的简历</p>
        <a class="but">确定</a>
    </float>
    <jsp:include page="_footer.jsp"/>
</body>
</html>
