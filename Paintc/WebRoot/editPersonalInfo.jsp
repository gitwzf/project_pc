<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>修改个人信息</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<link rel="stylesheet" href="css/nav.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<c:if test="${!empty manager.isTrain}">
<script type="text/javascript">
$(function(){
    if('${manager.isTrain}'=='1')
      {  $("#school option[value='"+${manager.schoolid}+"']").attr("selected",true);
       $('.strain').hide();
       $(".source[value='1']").attr("checked","checked");
      }
    else  if('${manager.isTrain}'=='0')
       { $("#train option[value='"+${manager.schoolid}+"']").attr("selected",true);
        $('.sschool').hide();
        $(".source[value='0']").attr("checked","checked");
        }
    $('.gender[value='+${manager.gender}+']').attr("checked",true);
    });

</script>
</c:if>
<script type="text/javascript">
$(function(){
    $('#birthday3').blur(function(){
        if($('#birthday1').val()==""||$('#birthday2').val()=="")
            alert("出生年月日需全填，可不填！");
     });
        
    $('.btn4').click(function(){
        if(typeof($('#teacherphone').val())!='undefined'&&(isNaN($('#teacherphone').val())||$('#teacherphone').val().length!=11)){alert("请输入正确的手机号");return;}
        else 
           if(typeof($('#school').val())!='undefined'&&$('#school').val()!="")
              $.get("Editpersonal",{school:$('#school').val(),phone:$('#teacherphone').val()},function(data){
                  if(data!="11"){
                    alert("该学校无该老师！");
                  }else{alert("修改成功！"); $('#loginForm').submit();}
        
           });
          else if(typeof($('#train').val())!='undefined'&&$('#train').val()!="")
           $.get("Editpersonal",{train:$('#train').val(),phone:$('#teacherphone').val()},function(data){
               if(data!="11"){
                 alert("该机构无该老师！");
               }else {alert("修改成功！");$('#loginForm').submit();}
			   });
			   else $('#loginForm').submit();
      	});
        
    $('.btn3').click(function(){
        location.href="./Personalcenter";
     });
     
     
    $('[name="source"]').click(function(){
	    if($('[name="source"]:checked').val()==1){
	    	$('.sschool').show();
	    	$('.strain').hide();
	    }else{
	    	$('.sschool').hide();
	    	$('.strain').show();
	    }
	    });
        
});

</script>

</head>

<body>
	 <div class="container">
        <form id="loginForm" action="Editpersonal?type=info" method="post" class="form">
        <div class="input-box">
        	<label>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</label>
            <input type="number" required id="phone" name="phone" placeholder="账号" value="${manager.phone }" readonly="readonly"/>
        </div>
        <c:if test="${manager.status==1}">
            <div style="margin-left:5px;"><label>来自：</label>
	            <input type="radio" class="source" name="source" value="1" />学校
	            <input type="radio" class="source" name="source" value="0"/>机构
            </div>
            <div class="input-box sschool">
            	<label>就读学校:</label>
            	<select name="school" id="school">
	            	<option selected="selected" value="">--	请选择学校--</option>
	                <c:forEach items="${schoollist}" var="school">
	                <option value="${school.id}">${school.name }</option>
	                </c:forEach>   
            	</select>
            </div>
            
            <div class="input-box strain"><label>培训机构:</label>
                <select name="train" id="train">
	            	<option selected="selected"  value="">--	请选择机构--</option>
	                <c:forEach items="${trainlist}" var="train">
	                <option value="${train.id}">${train.name }</option>
	                </c:forEach>
            	</select>
            </div>
            <div class="input-box">
            	<label>老师电话:</label>
            	<input type="number" required id="teacherphone" name="teacherphone" placeholder="老师电话" value="${manager.teacherphone }"/>
            </div>
            </c:if>
            <c:if test="${manager.status==2}">
            <div class="input-box">
            	<label>学校/机构:</label>
            	<input type="text" required id="stnam" name="stnam" placeholder="学校/机构名称" value="${manager.ex1 }"/>
            </div>
            </c:if>
            <div class="input-box">
            	<label>家长姓名:</label>
            	<input type="text" required id="parentname" name="parentname" placeholder="家长姓名" value="${manager.parentsname }" />
            </div>
            <div class="input-box">
            	<label>学生姓名:</label>
            	<input type="text" required id="studentname" name="studentname"  placeholder="学生姓名"  value="${manager.tname }"/>
            </div>
            <div style="margin-left:5px;margin-bottom:10px;">
            	<label style="height:35px;line-height:35px;display:inline-block;">学生生日：</label>
             	 <br/><input type="number" required id="birthday1" name="biryear" placeholder="年" class="date" style="margin-left:6%;margin-right:3%;" value="${manager.biryear }"/>
         		 <input type="number" id="birthday2" name="birmonth" placeholder="月" class="date" style="margin-right:3%;" value="${manager.birmonth }"/>
            	 <input type="number" id="birthday3" name="birday" placeholder="日" class="date" style="" value="${manager.birday }"/>
            </div>
            <div style="margin-left:5px;"><label>学生性别：</label>
	            <input type="radio" class="gender" id="gender" name="gender" value="1" checked="checked" />男 
	            <input type="radio" class="gender" id="gender" name="gender" value="0" />女 
            </div>
            <div class="button btn3 left">
            	<input type="button" required name="" value="取消" /> 
            </div>
            <div class="button btn4 right">
            	<input type="button" required name="" value="修改" />
            </div>
        </form>
    <jsp:include page="menu.jsp" flush="true"/> 
</body>
</html>
