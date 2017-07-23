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
    <link href="css/mobiscroll.custom-2.5.0.min.css" rel="stylesheet" type="text/css" />
    <script src="js/jslib.js"></script>
    <script src="js/js.js?f=6"></script>
    <script type="text/javascript">
       function showsave(){
       $('#work_exper_save').removeAttr("style");
       $('#add_table_form').css('display','block');
       $('.but-revers').css('display','none');
       }
       function showsavebutton(){
       $('.but-revers').removeAttr("style");
       }
    
       $(function(){
         if('${worklist}'=='[]'||'${worklist}'==''){$('#work_exper_save').css("display","none");}
       
       });
    </script>
</head>
<body>
    <div class="contain">
        <div class="table-list-wapper">
           <%int i=0; %>
       <c:forEach items="${worklist}" var="work">
            <div class="table">
                <div class="thead">
                    <div class="tr">
                        <p>工作经验<%=++i %><a class="gzjl-close" href="./Workexperi?deleteid=${work.id }">删除</a></p>
                    </div>
                </div>
                <div class="tbody">
                
                    <div class="tr">
                        <span class="t-title">公司名称</span>
                        <span>${work.companyname }</span>
                    </div>
                    <div class="tr">
                        <span class="t-title">职位</span>
                        <span>${work.position }</span>
                    </div>
                    <div class="tr">
                        <span class="t-title">开始时间</span>
                        <span class="">${work.beg_tim }</span>
                    </div>
                    <div class="tr">
                        <span class="t-title">结束时间</span>
                        <span class="">${work.end_tim }</span>
                    </div>
                    <div class="tr">
                        <span class="t-title">说明</span>
                        <span>${work.info }</span>
                    </div>
                </div>
            </div>
            </c:forEach>
     
            <form action="Workexperi" method="post" id="add_table_form" style="display:none">
                <div class="table add_table" >
                    <input type="hidden" name="name-index" value="1"/>
                    <!--可采取同步提交的方式,点击提交按钮，触发表单提交-->
                 <div class="thead">
                        <div class="tr">
                            <p>添加工作经验<a class="gzjl-close-a" href="javascript:void(0)" onclick="showsavebutton()">关闭</a>  </p>

                        </div>
                    </div>    
                    <div class="tbody">
                        <div class="tr">
                            <span class="t-title">公司名称</span>
                            <input type="text" name="companyname" id="companyname" placeholder="请输入公司名称"/>
                            <span class="arw"><span>
                        </div>
                        <div class="tr">
                            <span class="t-title">部门</span>
                            <input type="text" name="department" id="department" placeholder="请输入部门"/>
                            <span class="arw"><span>
                        </div>
                        <div class="tr">
                            <span class="t-title">职位</span>
                            <input type="text" name="position" id="position" placeholder="请输入职位"/>
                            <span class="arw"><span>
                        </div>
                        <div class="tr">
                            <span class="t-title">薪资</span>
                            <input type="text" name="salary" id="salary"  placeholder="请输入薪资"/>
                            <span class="arw"><span>
                        </div>
                        <div class="tr">
                            <span class="t-title">说明</span>
                             <textarea name="info" id="info" placeholder="请输入说明"></textarea>
                             <span class="arw"><span>
                        </div>
                        <div class="tr">
                            <span class="t-title">开始时间</span>
                            <input type="text" data-role="datebox" name="beg_tim" id="start_time" placeholder="请输入开始时间" />
                            <span class="arw"><span>
                        </div>
                        <div class="tr">
                            <span class="t-title">结束时间</span>
                            <input type="text" data-role="datebox" name="end_tim" id="end_time" placeholder="请输入结束时间"/>
                            <span class="arw"><span>
                        </div>
                    </div>

                </div>
                 <a class="but" id="work_exper_save" href="javascript:void(0)" >保存</a>
            </form>
           
             <a class="but but-revers"  href="javascript:void(0)" onclick="showsave()" style="display:block">添加</a>
        </div>
    </div>

    <mask style="display:none"></mask>
    <float style="display:none">
        <p>请完善您的简历</p>
        <a class="but">确定</a>
    </float>
     <jsp:include page="_footer.jsp"/>
    <script src="js/jquery.mobile-1.4.2.min.js"></script><!--必须最后且在jquery1.9.2后加载？   而菜单页须在1.9.2下加载？  -->
	<script src="js/mobiscroll.custom-2.5.0.min.js" type="text/javascript"></script>
 
</body>
</html>
