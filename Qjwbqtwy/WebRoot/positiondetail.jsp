<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.net.URLDecoder"%>
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
    <link href="css/css.css" rel="stylesheet" />

    <script src="js/jslib.js"></script>
    <script src="js/js.js"></script>
     <script type="text/javascript">
    $(function(){
     $('#sendresu').click(function(){
     if('${user}'==''){TouClick.mask(1).float('请重新访问该页面');return;}
     if('${user.state}'=='0'){TouClick.mask(1).float('请先完善你的简历');return;}
     var hunterid=$(this).attr("hunterid");
     if(hunterid=='')//公司
     $.post("HunterServ",{positionid:'${position.id}',positionname:'${position.position_name }',companyname:'${position.companyname }'},function(data){
             if(data=="11")TouClick.mask(1).float('投递成功');
             else TouClick.mask(1).float('一周内请勿重复投递');
     });
     else//猎头
     $.post("HunterServ",{hunterid:hunterid,positionname:'${position.position_name }',positionid:'${position.id}'},function(data){
             if(data=="11")TouClick.mask(1).float('投递成功');
             else TouClick.mask(1).float('一周内请勿重复投递');
     });
     
     });
     
    });
    </script>
</head>
<body>
    <div class="contain">
        <a class="title" href="javascript:void(0)">职位推荐</a>
         <a href="${ad.url }" class="clicktimes" adid="${ad.id}"><img class="head_img" src="${ad.pic_url }" /></a>
        <div class="form-wapper">
            
            <span class="form-item">
                <span class="item-key">招聘职位:</span>
                <span>${position.position_name }（截止投递日：${position.end_tim}）</span>
            </span>
            <span class="form-item">
                <span class="item-key">任职要求:</span>
                <div class="form-item-content">
                    <div>
                    <c:if test="${!empty position.degree }">
                        <p>学历：${position.degree }</p>
                        </c:if><c:if test="${!empty position.experience }">
                        <p>工作经验：${position.experience}</p>
                        </c:if>
						<c:if test="${!empty position.sex }">
                        <p>性别要求：<c:if test="${position.sex==0}">女</c:if><c:if test="${position.sex==1}">男</c:if><c:if test="${position.sex==2}">无</c:if></p>
                 		</c:if>
                    </div>
                    <div>
                   <c:if test="${!empty position.posi_type }">
                        <p>类别：${position.posi_type }</p>
                      </c:if><c:if test="${!empty position.peoples }">
                        <p>招聘人数：${position.peoples }</p>
                        </c:if>
						<c:if test="${!empty position.address }">
                 		 <p>工作地点：${position.address }</p>
                 		</c:if>
                    </div>
                </div>
            </span>
            <span class="form-item">
                <span class="item-key">岗位具体描述:</span>
                <c:if test="${!empty position.salary }">
                <p class="form-itme-p">年薪：${position.salary }</p>
                </c:if>
                <c:if test="${!empty position.info}">
                <p class="form-itme-p">${position.info }</p>
                </c:if>
               </span>
        </div>
        <a class="but" href="javascript:void(0)" id="sendresu" hunterid="<%=request.getAttribute("hunterid")==null?"":request.getAttribute("hunterid") %>"><%=request.getAttribute("title")==null?"":URLDecoder.decode((String)request.getAttribute("title"),"utf-8") %>简历</a>
    </div>

    <mask style="display:none"></mask>
    <float style="display:none">
        <p>请完善您的简历</p>
        <a class="but">确定</a>
    </float>
    <jsp:include page="_footer.jsp"/>
</body>
</html>
