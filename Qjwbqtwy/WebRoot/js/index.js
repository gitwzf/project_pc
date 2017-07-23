$(function(){
	var dis1="block",dis2="block";
var curIndex=parseInt(Math.random()*3);
var Index=3;
//时间间隔 单位毫秒
var timeInterval=10;
var flag=1;
var arr=new Array();
arr[0]="win-1.gif";
arr[1]="win-2.gif";
arr[2]="win-3.gif";
arr[3]="win-4.gif";
//setInterval(changeImg,timeInterval);
function changeExp()
{  
    var expri_img=document.getElementById('expri-img');
   
   expri_img.src='images/'+arr[Index];
}

function changeExpression()
{
    var expri_img=document.getElementById('expri-img');
    if (curIndex>=arr.length-1) {
        curIndex=0;
    }else{
        curIndex+=1;
    }
   expri_img.src='images/'+arr[curIndex];
}
function change(time){
	var time=time;
	for(var i=0;i<6;i++){
		if(time<600){
			time=time+100;
			setTimeout(changeExpression,time);
		}else {
			time=time+200;
			setTimeout(changeExpression,time);
		}
	}
	//alert(curIndex)
}
function tips() {
    if(Index==0){
	    $('.tips').text('恭喜你中奖了！耶~主人，快带我回家！');
	}else if(Index==1){
	    $('.tips').text('恭喜你中奖了！等你好久，终于点到了我！');
	}else if(Index==2){
	    $('.tips').text('恭喜你中了无敌幸运奖，趁着运气不错，赶快去看看你想要的职位！');
	}else{ 
	    var p=Math.random()<0.5?'可惜了，到手的奖飞走了！':'离奖品就差。。。1小步！'
	    $('.tips').text(p);
	}
   $('.tips').css('display', 'block');
}
$('#expri-img').on('click', function () {
	var phone=$('#in-phone').text().replace(".","");
    $.get("GameServ",{phone:phone},function(data){
    	if(data.split(";")[0]=='00'){
    		
    		Index=data.split(";")[1];
    		tips();
    		changeExp();
    		$('.second').css('display', 'block');
    	}
    	else if(!isNaN(data)){
    			Index=data;
    			$('.tips').css('display','none')
    			if(flag==1){
    		    change(timeInterval);
    		    setTimeout(changeExp,700);
    			setTimeout(tips,1000);
    			//flag=0;
    		}
    	}
    	else location.href="./error.jsp";
    });
});
$('.form-submit').on('click', function () {
	if($('.ipt-name').val()==''||$('.ipt-phone').val()==''||$('.ipt-phone').val().search(/^\d{11}$/)==-1){
		$('p','.error-tips').text('请将信息填写正确');
		$('.first').css('display', 'none');
		$('.fourth').css('display', 'block');
	}else{
		$.post("GameServ",{phone:$('.ipt-phone').val(),name:$('.ipt-name').val()},function(data){
			if(data=="00"){
			$('.first').css('display', 'none');
//			$('.fourth').css('display', 'block');
			$('.second').css('display', 'block');
			}else
				if(data.split("%3B").length>=2){
				data=decodeURI(data);
				$('#in-phone').text(data.split("%3B")[0]);
				$('#in-name').text(data.split("%3B")[1]);
				
				$('.first').css('display', 'none');
				$('.second').css('display', 'block');
				
			}else
				location.href="./error.jsp";
		});
	}
    
})
$('.que-btn').on('click',function(){
	$('.first').css('display', 'block');
	$('.fourth').css('display', 'none');
})
$('.rule').on('click', function () {
	dis1=$('.first').css('display');
	dis2=$('.second').css('display');
    $('.first').css('display', 'none');
    $('.second').css('display', 'none');
	$('.third').css('display', 'block');
})
$('.ret-btn').on('click', function () {
    $('.first').css('display',dis1);
    $('.second').css('display', dis2);
	$('.third').css('display', 'none');
})

	})