<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>注册</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript">
   $(function(){
     $('#phone').blur(function(){
          if($('#phone').val()!=""&&!isNaN($('#phone').val()))
          $.get("Register0",{phone:$('#phone').val()},function(data){
                if(data!="11"){
                  $('#email').attr("value",data.split(";")[0]);
                $('#weixin').attr("value",data.split(";")[1]);
                $('#qq').attr("value",data.split(";")[2]);
                $('#teachername').attr("value",data.split(";")[3]);
                $('#email').attr("readonly",true);
                $('#weixin').attr("readonly",true);
                $('#qq').attr("readonly",true);
                $('#teachername').attr("readonly",true);
                }else{
                $('#email').attr("readonly",false);
                $('#weixin').attr("readonly",false);
                $('#qq').attr("readonly",false);
                $('#teachername').attr("readonly",false);               
                }
          });
     });
   
    $('.btn1').click(function(){
      //    if($('#school').val()==""&&$('#train').val()==""&&$('#othera').css("display","none")&&$('#otherb').css("display","none")){alert("请选择学校机构！");return;}
      if($('.othera').val()==''&&$('.otherb').val()==''){alert("请输入学校机构！");return;}
         if($('#phone').val()==""||$('#phone').val().length!=11){alert("请输入手机号！");return;}
           if($('#teachername').val()==""){alert("请输入教师姓名！");return;}
          $('#loginForm').submit();
    });
    
    
    
    
    $('#school').blur(function(){
       if($(this).attr("value")=="othera"){$('#othera').css("display","block");alert("请输入学校名！");}
       else $('#othera').css("display","none");
    });
    $('#train').blur(function(){
       if($(this).attr("value")=="otherb"){$('#otherb').css("display","block");alert("请输入机构名！");}
       else $('#otherb').css("display","none");
    });
    

	 $('.strain').hide();
	  $('.sschool').hide();
	  $('#othera').css("display","block");
    $('[name="source"]').click(function(){
	    if($('[name="source"]:checked').val()==1){
	    	//$('.sschool').show();
	    	//$('.strain').hide();
	    	
	    	$('#othera').css("display","block");
	    	$('.otherb').attr("value","");$('#otherb').css("display","none");
	    }else{
	    //	$('.sschool').hide();
	    //	$('.strain').show();
	    
	    $('#otherb').css("display","block");
	    $('.othera').attr("value","");$('#othera').css("display","none");
	    }
	    });
    
   });

</script>
</head>

<body>
	 <div class="container">
     <!--<p style="margin-bottom:10px;">打*的为必填项，其他项选填</p>-->
     <div style="margin-left:5px;"><label>来自：</label>
	            <input type="radio" name="source" value="1"  checked="checked"/>学校
	            <input type="radio" name="source" value="0"/>机构
            </div>
        <form id="loginForm" action="Register0" method="post" class="form">
            <div class="input-box sschool"><label>就读学校:</label>
            	<select name="school" id="school">
            	<option selected="selected" value="">--	请选择学校--</option>
                <c:forEach items="${schoollist}" var="school">
                <option value="${school.id}">${school.name }</option>
                </c:forEach>   
                <option value="othera">其他学校</option>
            </select>
            </div>
            <div class="input-box strain"><label>培训机构:</label>
                <select name="train" id="train">
            	<option selected="selected"  value="">--	请选择机构--</option>
                <c:forEach items="${trainlist}" var="train">
                <option value="${train.id}">${train.name }</option>
                </c:forEach>
                <option value="otherb">其他机构</option>
            </select>
            </div>
            <div class="input-box" id="othera" style="display:none"><label>学校全称:</label>
            <input type="text" required  name="othera" class="othera" placeholder="学校全称"/></div>
            <div class="input-box" id="otherb" style="display:none"><label>机构全称:</label>
            <input type="text" required  name="otherb" class="otherb" placeholder="机构全称"/></div>
            
            
            <div class="input-box"><label>老师电话:</label>
            <input type="number" required id="phone" name="phone" placeholder="老师电话"/></div>
            <div class="input-box"><label>老师姓名:</label>
            <input type="text" required id="teachername" name="teachername" placeholder="老师姓名"/></div>
            <div class="input-box"><label>老师QQ号:</label>
            <input type="number" id="qq" name="qq" placeholder="老师QQ号(选填）"/></div>
            <div class="input-box"><label>老师微信号:</label>
            <input type="text" id="weixin" name="weixin" placeholder="老师微信号(选填）"/></div>
            <div class="input-box"><label>老师邮箱:</label>
            <input type="text" id="email" name="email" placeholder="老师邮箱(选填）"/>
            </div>
            <div class="button btn1"><input type="button" name="" value="下一步" /></div> 
        </form>
    </div>
</body>
</html>
