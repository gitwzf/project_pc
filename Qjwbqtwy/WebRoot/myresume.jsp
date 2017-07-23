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
    <title>我的简历</title>
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" />
    <script src="js/jquery-1.9.1.min.js"></script>
    <link href="css/css.css" rel="stylesheet" />
	<link href="css/mobiscroll.custom-2.5.0.min.css" rel="stylesheet" type="text/css" />
    <script src="js/jslib.js"></script>
    <script src="js/js.js"></script>
    <script type="text/javascript">
     $(function(){
        if('${myresu}'!=''){
        $("#years option[value="+'${myresu[4]}'+"]").attr("selected",true);
         $("#salary option[value="+'${myresu[9]}'+"]").attr("selected",true);
          $("#degree option[value="+'${myresu[7]}'+"]").attr("selected",true);
        }
     
     
     });
    </script>
</head>
<body>
    <div class="contain">
        <div class="table-list-wapper">
            
            <div class="table" id="add_table" style="">
                <form action="Resume" method="post" id="my_resume_form">
                    <!--可采取同步提交的方式,点击提交按钮，触发表单提交-->
                    <div class="tbody">
                        <div class="tr">
                            <span class="t-title">姓名</span>
                            <input type="text" name="real_nam" id="real_nam" value="${myresu[0] }" placeholder="请输入姓名"/>
                        </div>
                        <c:if test="${!empty myresu[1]}"><div class="tr tr-sex">
                            <div class="td" data-value="1">男</div>
                            <div class="td" data-value="0">女</div>
                            <!--js会根据 value值 对页面显示进行初始化 对应关系男= 1  女 = 0-->
                            <input type="hidden" name="gender" id="tr-sex-input" value="${myresu[1] }" />
                        </div></c:if>
                        <c:if test="${empty myresu[1]}"><div class="tr tr-sex">
                            <div class="td active" data-value="1">男</div>
                            <div class="td" data-value="0">女</div>
                            <!--js会根据 value值 对页面显示进行初始化 对应关系男= 1  女 = 0-->
                            <input type="hidden" name="gender" id="tr-sex-input" value="" />
                        </div></c:if>
                    </div>
                    <div class="tbody">
                        <div class="tr">
                            <span class="t-title">出生日期</span>
                            <input type="text" data-role="datebox" name="birthday" value="${myresu[13] }"  id="birthday" placeholder="请选择出生日期"/>
							<span class="arw">&gt<span>
                        </div>
                        <div class="tr">
                            <span class="t-title">毕业院校</span>
                            <input type="text" name="school" id="school" value="${myresu[5] }" placeholder="请输入毕业院校"/>
                            <span class="arw">&gt<span>
                        </div>
                        <div class="tr">
                            <span class="t-title">毕业时间</span>
                            <input type="text" data-role="datebox" name="gradu_tim" id="gradu_tim" value="${myresu[6] }" placeholder="请输入毕业时间"/>
                            <span class="arw">&gt<span>
                        </div>
                        <div class="tr">
                            <span class="t-title">工作年限</span>
                           
                            <span style="180px;overflow:hidden;" class="my">
                            <select name="years" id="years" data-role='none' class="salary">
                               <option value="">请选择工作年限</option>
                               <c:forEach items="${YEARS}" var="year">
                                <option value="${year.key }">${year.value }</option>
                               </c:forEach>
                            </select>
                            </span>
                            <span class="arw">&gt<span>
                        </div>
                        <div class="tr">
                       
                            <span class="t-title">目前年薪</span>
                           
                            <span style="180px;overflow:hidden;" class="my">
                              <select name="salary" id="salary" data-role='none' class="salary">
                               <option value="">请选择目前年薪</option>
                               <c:forEach items="${SALARY}" var="sala">
                                <option value="${sala.key }">${sala.value }</option>
                               </c:forEach>
                            </select>
                            </span>
                            <span class="arw">&gt<span>
                        </div>
                        <div class="tr">
                            <span class="t-title">期望年薪</span>
                            <input type="text" name="expectsalary" id="expectsalary" value="${myresu[10] }" placeholder="请输入期望年薪"/>
                            <span class="arw">&gt<span>
                        </div>
                        <div class="tr">
                            <span class="t-title">学历</span>
                            <span style="180px;overflow:hidden;" class="my">
                             <select name="degree" id="degree" data-role='none' class="salary" >
                               <option value="">请选择学历</option>
                               <c:forEach items="${DEGREE}" var="deg">
                                <option value="${deg.key }">${deg.value }</option>
                               </c:forEach>
                            </select>
                            </span>
                            <span class="arw">&gt<span>
                        </div>
                        <div class="tr">
                            <span class="t-title">专业</span>
                            <input type="text" name="major" id="major" value="${myresu[8] }" placeholder="请输入专业"/>
                            <span class="arw">&gt<span>
                        </div>
                        <div class="tr">
                            <span class="t-title">居住地址</span>
                            <input type="text" name="address" id="address" value="${myresu[11] }" placeholder="请输入居住地址"/>
                             <span class="arw">&gt<span>
                        </div>
                        <div class="tr">
                            <span class="t-title">自我评价</span>
                            <textarea name="self_evaluate" id="self_evaluate" placeholder="请输入自我评价">${myresu[12] }</textarea>
                            <span class="arw">&gt<span>
                        </div>
                    </div>
                    <div class="thead">
                        <div class="tr">
                            <p>联系方式</p>
                        </div>
                    </div>
                    <div class="tbody">
                        <div class="tr">
                            <span class="t-title">电话</span>
                            <input type="text" name="telphone" id="telphone" value="${myresu[2] }" placeholder="请输入电话"/>
                             <span class="arw">&gt<span>
                        </div>
                        <div class="tr">
                            <span class="t-title">邮箱</span>
                            <input type="text" name="email" id="email" value="${myresu[3] }" placeholder="请输入邮箱"/>
                             <span class="arw">&gt<span>
                        </div>

                    </div>
                </form>
            </div>
            <a class="but" id="my_resume_save" href="javascript:void(0)">提交</a>
        </div>
    </div>

    <mask style="display:none"></mask>
    <float style="display:none">
        <p>请完善您的简历</p>
        <a class="but">确定</a>
    </float>
    <jsp:include page="_footer.jsp"/>
     <script src="js/jquery.mobile-1.4.2.min.js?h=8"></script>
	<script src="js/mobiscroll.custom-2.5.0.min.js?y=8" type="text/javascript"></script>
 
    

</body>
</html>
