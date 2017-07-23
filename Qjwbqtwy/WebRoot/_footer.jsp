<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!--底部代码-->
    <div class="footer_menu" data-role='none'>
        <ul>
            <li class="dropup">
                <span>微应聘</span>
                <dl class="submenu">
                    <dd><a href="./HunterServ">高端人才</a></dd>
                    <dd><a href="./ModeltypeServ">社会招聘</a></dd>
                </dl>
            </li>
            <li class="dropup">
                <span>微公告</span>
                <dl class="submenu">

                    <dd><a href="./CollegeServ">高校招聘</a></dd>
                    <dd><a href="./SpecialServ">专场招聘</a></dd>
                </dl>
            </li>
            <li class="dropup">
                <span>微服务</span>
                <dl class="submenu" >
                    <dd><a href="./Resume">我的简历</a></dd>
                    <dd><a href="./Workexperi">工作经历</a></dd>
                    <dd><a href="./Sendposition">我的应聘</a></dd>
              <!--     <dd><a href="./GameServ">戳我有奖</a></dd>    --> 
                </dl>
            </li>

        </ul>
    </div>
<script src="js/jquery-1.9.1.min.js"></script>
    <script>
        $(document).ready(function () {
		$('body').click(function(){
			$('dl.submenu').hide();
		})
            $("li.dropup").click(function () {
				event.stopPropagation();
                $(this).children('dl').slideToggle('fast');
                $(this).siblings().children('dl').slideUp('fast');
            });
        });
    </script>
    
 <script>
$(function(){
$("select").attr('data-role','none'); 
	$('.tr').on('click',function(){
		$('.arw').css('opacity','1');
		$('.arw',this).css('opacity','0');
	});
	$('.salary').change(function(){
		if(($('.salary').val()=='')){
			$(this).parent().siblings('.p-holder').css('display','block');
			$('.salary').css('opacity','0');
		}else{
			$(this).parent().siblings('.p-holder').css('display','none');
			$('.salary').css('opacity','1');
		}
	})
});

</script>

<script type="text/javascript">
        $(function () {
         var opt = {
			preset: 'date', //日期
			display: 'modal', //显示方式 
			dateFormat: 'yy-mm-dd', // 日期格式
			setText: '确定', //确认按钮名称
			cancelText: '取消',//取消按钮名籍我
			dateOrder: 'yymmdd', //面板中日期排列格式
			dayText: '日', monthText: '月', yearText: '年', //面板中年月日文字
			endYear:2020 //结束年份
		};
    
		$('input:jqmData(role="datebox")').mobiscroll(opt);
        });
    </script>

    