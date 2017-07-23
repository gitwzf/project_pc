<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><html><head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="测试">
<meta name="format-detection" content="telephone=no">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="white">
<link href="askpaper/css/index.css" rel="stylesheet" type="text/css">
<script src="askpaper/js/jquery-1.9.1.min.js" type="text/javascript" ></script>
<script>
	
	$(function(){
			var last_update = 0;
	     	var number=0;//题号   i不行  识别不到？
			var score=0;//用于记录总分
			var html='';//用来拼接ul li列表
			var current=0;//记录当前题号
			 var quesAnsw ={ 
			    paper  :{},  //该问卷
				question : [], //问题
				answer: [], //答案 现在与问题合并
				grade: [] //得分等级
			} //题库数组
			
			//从json中取出相应数据
			function initQuesAnsw(){ 
				var data="askpaper/js/data.json"; 
				$.getJSON("./Askpaper",{pageid:'3'}, function(data){ 
					quesAnsw.paper=data.askpaper;
					quesAnsw.question=data.askpaper.askquestion; 
					quesAnsw.answer=data.askpaper.askquestion.askitems; 
					quesAnsw.grade=data.askresult.askresultitems; 
					
					$('.numbers').html(quesAnsw.paper.clicktimes);//怎么只能放这里有效   $里面有效？没加载完？
					$('.title').html(quesAnsw.paper.title);
					$('.info').html(quesAnsw.paper.info);
					$('.startpic').attr("value",quesAnsw.paper.url);
				//	alert(quesAnsw.grade[0].content);
				}); 
				
		//		alert(quesAnsw.paper.clicktimes);
				//alert(quesAnsw.question[0].id);
        	}
        	
		initQuesAnsw();
		//开始答题
		$(".start").on('click',function(){
			$('.xu-title').text((++number)+'：'+quesAnsw.question[current].question);
			var queurl=quesAnsw.question[current].queurl;
			if(queurl.length>0)
			$('.xu-pic').attr("src",queurl);
			else{
			  $('.xu-pic').hide();}
			for(var i=0;i<quesAnsw.question[current].askitems.length;i++){
				html+="<li data-score=\""+quesAnsw.question[current].askitems[i].core+"\">"+quesAnsw.question[current].askitems[i].content+"</li>";
			}
			current++;
			//alert(html);
			$('.ul').append(html);
			html='';
			$(this).parent().hide().next('.xu-block').show();
			
			
			
		})
		
		//选完答案
		$('.ul').on('click','li',function(){
			var curTime = new Date().getTime();
			if ((curTime - last_update) > 500){
			    last_update = curTime;
				$('.xu-pic').show();
				$(this).addClass('on');
				var that=this;
				setTimeout(function(){if(quesAnsw.question[current]){
								
					//alert($(this).data('score'))
					$('.xu-title').text((++number)+'：'+quesAnsw.question[current].question);
					var queurl=quesAnsw.question[current].queurl;
					if(queurl.length>0)
						$('.xu-pic').attr("src",queurl);
					else{
					  $('.xu-pic').hide();
					}
					for(var i=0;i<quesAnsw.question[current].askitems.length;i++){
						html+="<li data-score=\""+quesAnsw.question[current].askitems[i].core+"\">"+quesAnsw.question[current].askitems[i].content+"</li>";
					}
					$('.ul').empty().append(html);
					html='';
					//$(this).css('background','');
					score+=$(that).data('score');
					current++;
				}else{
					score+=$(that).data('score');
					$(that).parent().parent().parent().remove();
					$('.result').show();
					//score判断
					//	alert(quesAnsw.grade.length);
					var flag=false;
					for(var i=0;i<quesAnsw.grade.length;i++){
						if(score>=quesAnsw.grade[i].downgoal&&score<=quesAnsw.grade[i].upgoal){
						flag=true;
						var etitle=quesAnsw.grade[i].title;
						 if(etitle.length>0){//有称号
						$('.endtitle').attr('value','称号  '+etitle);
						$('.result .title').html('恭喜你，得了'+score+'分，获得'+etitle+'称号');
						 }else{
						 $('.endtitle').attr('value','了'+score+'分!');
						 $('.result .title').html('恭喜你，得了'+score+'分!');
						 }
						$('.result .intro').html(quesAnsw.grade[i].content);
						}}
						if(!flag){
						$('.endtitle').attr('value','了'+score+'分!');
						$('.result .title').html('恭喜你，得了'+score+'分!');}
				}
			},400);		
		  }
		})
		$('.share').on('click',function(){
			$('.share_intro').show();
			event.stopPropagation();
		})
		$('body').on('click',function(){
			$('.share_intro').hide();
		})
		
	})
        var imgUrl = "图片地址";
        var lineLink = "网址";
        var descContent = '爱在五月，\n\n妈咪爱1+1亲子健康之旅开启全国行首站----重庆站妈咪爱活性益生菌';
        var shareTitle = '标题';
        var appid = '';
         
        function shareFriend() {
            WeixinJSBridge.invoke('sendAppMessage',{
                "appid": appid,
                "img_url": $('.startpic').val(),
                "img_width": "200",
                "img_height": "200",
                "link": window.location.href,
                "desc": $('.result .title').html(),
                "title": '我在  '+$('.title').html()+$('.endtitle').val()+'！你也来试试？'
            }, function(res) {
                //_report('send_msg', res.err_msg);
            })
        }
        function shareTimeline() {
            WeixinJSBridge.invoke('shareTimeline',{
                "img_url": $('.startpic').val(),
                "img_width": "200",
                "img_height": "200",
                "link": window.location.href,
                "desc":$('.result .title').html(),
                "title": '我在  '+$('.title').html()+$('.endtitle').val()+'！你也来试试？'
            }, function(res) {
                   //_report('timeline', res.err_msg);
            });
        }
        function shareWeibo() {
            WeixinJSBridge.invoke('shareWeibo',{
                "content": descContent,
                "url": lineLink,
            }, function(res) {
                //_report('weibo', res.err_msg);
            });
        }
        // 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
        document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
            // 发送给好友
            WeixinJSBridge.on('menu:share:appmessage', function(argv){
                shareFriend();
            });
            // 分享到朋友圈
            WeixinJSBridge.on('menu:share:timeline', function(argv){
                shareTimeline();
            });
            // 分享到微博
            WeixinJSBridge.on('menu:share:weibo', function(argv){
                shareWeibo();
            });
        }, false);
</script>
</head>
<body>
<!--标题-->
	<div class="main-t">
		<h2 class="title"><span class="title"></span></h2>
		<p>已有<span class="numbers"></span>人参与测试</p>
	</div>
<!--标题-->

	<section class="fm-block">
		<!--第一页说明-->
		<div class="intro">
			<p><span class="info"></span></p>
			<button class="start btn">开始测试</button>
		</div>
		<!--第一页说明-->
		<!--问题与选项-->
		<div class="xu-block">
			<h3 class="xu-title"></h3>
			<img class="xu-pic" style="width:100%"/>
			<div class="item-bg">
				<ul class="ul">
					
				</ul>
			</div>
		</div>
		<!--问题与选项-->
		<!--结果页--><input type="hidden" class="endtitle"/><input type="hidden" class="startpic"/>
		<div class="result"  style="display:none">
			<div class="title"></div>
			<div class="intro-title">详细分析</div>
			<div class="intro"></div>
			<button class="btn share">分享给朋友（圈）</button>
		</div>
		<!--结果页-->
	</section>
	<div class="share_intro"></div>
</body>
</html>