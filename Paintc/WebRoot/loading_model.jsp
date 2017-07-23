<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>上传照片</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="css/nav.css"/>
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/spin.js?g=7"></script>
<script type="text/javascript">
    $(function(){
  //   if('${toobig}'=='1')alert("上传图片需小于1MB！");

    if('${picurl}'=='')$('.uploading0').css("display","none");
     else $('.uploading').css("display","none");
    
     $('.picup').click(function(){
    if($('#imgFile').val()!="")
  { $('#picform').submit();
$('.mask').css('display','block');
  var height=$('body').height();
$('.mask').height((height+500)+'px');
   var target = document.getElementById('foo');
var spinner = new Spinner().spin();
target.appendChild(spinner.el);
  }
   else alert("还未选作品！");
   });
   
   $('.sub.btn1').click(function(){
       if($('.uploading0').css("display")==false){alert("请先上传图片！");return;}
        if($('#phone').val()==""||isNaN($('#phone').val())||$('#phone').val().length!=11){alert("请输入正确的电话！");return;}
         if($('#parentname').val()==""){alert("请输入家长姓名！");return;}
          if($('#studentname').val()==""){alert("请输入学生姓名！");return;}
            if($('#relationship').val()==""){alert("请选择亲子关系！");return;};
            if($('#age').val()==""||isNaN($('#age').val())||$('#age').val().length>2){alert("年龄请输入正确的数字！");return;};
            if($('#height').val()==""||isNaN($('#height').val())||$('#height').val().length>3){alert("身高请输入正确的数字！");return;}
            if($('#weight').val()==""||isNaN($('#weight').val())){alert("请输入正确的体重！");return;}
   
          var boygirl=$("#gender").val();
          if(boygirl=='1')boygirl='男';
          else if(boygirl=='0')boygirl='女';
          var relation_arr = ['父女','母女','父子','母子'];
          var relation = relation_arr[parseInt($('#relationship').val())];
     //     if($("#relationship option[value=1]").attr("selected")=="selected")relation="母女";
        //  if($("#relationship option[value=2]").attr("selected")=="selected")relation="父子";
       //   if($("#relationship option[value=3]").attr("selected")=="selected")relation="母子";
          if(confirm("请再次核对以下信息：\n家长电话："+$('#phone').val()+"\n家长姓名："+$('#parentname').val()+"\n学生姓名："+$('#studentname').val()+" 性别："+boygirl+"\n"+$('#age').val()+"岁   "+$('#height').val()+"cm   "+$('#weight').val()+"kg"
          +"\n亲子关系："+relation+"\nQQ:"+$('#qq').val()+"\n微信："+$('#weixin').val()+"\n邮箱："+$('#email').val()))
        $('.allsub').submit();
   
   });
   
   $('#phone').blur(function(){
      $.get("Models",{phone:$('#phone').val()},function(data){
      if(data.split(";").length>2) {
                 $('#parentname').attr("value",data.split(";")[0]);
                 $('#studentname').attr("value",data.split(";")[1]);
                $(":radio[value="+data.split(";")[2]+"]").attr("checked",true);
                $('#age').attr("value",data.split(";")[3]);
                $('#height').attr("value",data.split(";")[4]);
                $('#weight').attr("value",data.split(";")[5]);
                $("#relationship option[value="+data.split(";")[6]+"]").attr("selected",true);
                 $('#qq').attr("value",data.split(";")[7]);
                  $('#weixin').attr("value",data.split(";")[8]);
                   $('#email').attr("value",data.split(";")[9]);
                
                  alert("请核对信息！");
                }else
                 if(data=="11"){}
                 else {
                 alert(data);
                 $('.sub.btn1').css("display","none");
                 }
               
      });
   
   });
   
   });
 </script>
</head>

<body>
<div class="container">
    
		<p>hi支持JPG、GIF、PNG等格式，每项比赛限一张参赛照片，重复上传照片投票数会被清零</p>
        <img src="${picurl}" style="width:100%" class="uploading0"/>
        <div class="uploading" style="margin-left:0px;">+</div>
        <form action="Uploadmodelpic" enctype="multipart/form-data" method="post" id="picform" class="button btn5 left form1">  
        	<span>1选择照片
				<input type="file" tabindex="-1" name="imgFile" class="transparent_class" id="imgFile" accept="image/*;capture=camera" > 
			</span>   
		</form>
        <div class="button btn4 right">
        	<input type="button" class="pho right picup" value="2上传照片"/>
        </div>
        
        
         <form action="Models" method="post" class="allsub" style="padding-top:60px;">
        <div class="input-box"><label>家长电话:</label>
                <input type="number" required id="phone" name="phone"  placeholder="联系电话（重要）" />
        </div>
        <div class="input-box"><label>家长姓名:</label>
            <input type="text" required id="parentname" name="parentname" placeholder="家长姓名"/>
        </div>
        <div class="input-box"><label>学生姓名:</label>
            	<input type="text" required id="studentname" name="studentname"  placeholder="学生姓名" />
        </div>
        <div style="margin-left:5px;"><label>学生性别：</label>
        	<div style='display:inline-block;width:200px;padding:5px'>
        		<div class='gender gender-name-m' data-value='1'></div>
       			<span class='gender-name gender-name-m'>男</span>
        		<span class='gender-name gender-name-w'>女</span>
        		<div class='gender gender-name-w' data-value='0'></div>
       		</div>
        		
        		<input id='gender' name='gender' value='1' type='hidden'/>
        		<style>
.mask{
 position:absolute;
 top:0;
 left:0;
 width:100%;
 height:100%;
 display:none;
 background:rgba(0,0,0,0.5);
 }
 .mask #foo{
 position:absolute;
 bottom:700px;
 left:50%;
 width:100px;
 height:10000px;
 font-size:16px;
 }

        			.gender{
        				box-sizing:border-box;
        				width:20px;
        				height:20px;
        				background:#fff;
        		border-radius:20px;
        			}
        			.gender-active{
        				border:5px solid #f00;
        				
        			}
        			.gender-name{
        				font-size:16px;
        				color:#444;padding-left:5px;
        			}
        			.gender-name-m{
        				float:left;
        			}
        			.gender-name-w{
        				float:right;
        			}
        		</style>
        		<script>
        			$(document).ready(function(){
        				var gender = $('#gender');
        				var gender_val = gender.val();
        				$('.gender[data-value='+gender_val+']').addClass('gender-active');
        				$('.gender').on('click',function(){
        					var that = $(this);
        					var val = that.data('value');
        					gender.val(val);
        					$('.gender').removeClass('gender-active');
        					that.addClass('gender-active');
        					
        				});	
        			});
        		</script>
	            <!-- <input id='sex1' type="radio" name="gender" class="gender" value="1"  data-sex="男"/>
	            <label for='sex1'>男</label> 
	            <input id='sex0' type="radio" name="gender" class="gender" value="0"  checked="checked" data-sex="女"/>
	            <label for='sex0'>女</label> --> 
        </div>
        <div style="margin-bottom:10px;padding-bottom:20px ;border-bottom:1px dashed #393;">
             	 <input type="number" required id="age" name="age" placeholder="年龄" class="date" style="margin-right:3%;width:18%;padding-left:3%;"/>岁
         		 <input type="number" id="height" name="height" placeholder="身高" class="date" style="margin-right:3%;width:18%;padding-left:3%;"/>cm
            	 <input type="number" id="weight" name="weight" placeholder="体重"class="date"  style="margin-right:3%;width:18%;padding-left:3%;"/>kg
         </div>   
        
        <div class="input-box"><label>亲子关系:</label>
            <select name="relationship" id="relationship" style="color:#ccc;">
                <option selected="selected"  value="">选择亲子关系</option>
                <option value="0">父女</option>
                <option value="1">母女</option>
                <option value="2">父子</option>
                <option value="3">母子</option>
        	</select>
        </div>
        <div class="input-box"><label>QQ号:</label>
        <input type="number" id="qq" name="qq" placeholder="家长QQ号(选填）"/></div>
        <div class="input-box"><label>微信号:</label>
        <input type="text" id="weixin" name="weixin" placeholder="家长微信号(选填）"/></div>
        <div class="input-box"><label>邮箱:</label>
        <input type="text" id="email" name="email" placeholder="家长邮箱(选填）"/>
        </div>
         </form>
    
     <div class="button btn1 sub"><input type="button" id="username" name="" value="提交" /> </div>
    
</div>
<div class="mask" ><div id="foo">请等候...</div></div>
</body>
</html>
