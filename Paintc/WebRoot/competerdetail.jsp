<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wzf.pubvari.Variable"  %>
<%@ page import="com.wzf.model.Manageruser" %>
<%@ page import="com.wzf.model.Workss" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>绿色天堂漫画人气榜</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="css/nav.css"/>
<link rel="stylesheet" type="text/css" href="css/index.css" media="all">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/spin.js"></script>
</head>
<script type="text/javascript">
//style    2 #f7c37c    3 #5fbea7 #b695c4
function modify(){

$('.ranking.i0').css("border-left-color","#f7c37c");
$('.ranking.i1').css("border-left-color","#5fbea7");
$('.ranking.i2').css("border-left-color","#b695c4");
$('.ranking.i3').css("border-left-color","#A52A2A");
$('.ranking.i4').css("border-left-color","#DAA520");
$('.ranking.i5').css("border-left-color","##BC8F8F");
$('.ranking.i6').css("border-left-color","#808080");
var ua = navigator.userAgent.toLowerCase();
 if(ua.match(/MicroMessenger/i)!="micromessenger") 
  $('.right.vote').remove();
}
function fo(){
$('.mask').css('display','block');
var height=$('body').height();
$('.mask').height((height+500)+'px');
 var target = document.getElementById('foo');
var spinner = new Spinner().spin();
target.appendChild(spinner.el);
}

$(function(){
 modify();

window._vote = function(workid){
 	    $.get("Vote",
	    		{competeid:workid,action:'paint',username:'${username}',userkey:'${userkey}'},
	    function(data){
		   	if(data=='00'){
					alert("投票失败！");
				}
			    else {
				   		alert("投票成功！还有"+data+"张票可投");
				   		if(data=='0'){
				   		$('.right.vote').text('已投票');
				   		$('.right.vote').attr("disabled","disabled");
				   		$('.right.vote').removeClass('vote');
				   		}
			     }
	 	 });
};

   
   
 });
</script>
<body>

<div class="container">
<%int i=0; %>
 <div class="content">
   
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
 height:100px;
 font-size:16px;
 }
 #pageform div,.i-want{
 	width:45%;
 	box-sizing:border-box;	
 }
 	.i-want{
 		padding:10px 40px;text-align:center;
 		border:1px solid #ccc; background:#5fbea7;color:#fff;
 		border-radius:4px;margin:0;
 		width:100%;
 	
 	}
 </style>
 <%Manageruser m=Variable.map_compete_user.get(request.getParameter("studentid")); 
  if(m!=null){
 %>
 
        <section class="per-info">
        	<img src="<%=m.getHeadimg() %>" class="left photo"/>
            <ul class="left">
            	<li><dd class="left">姓名：</dd><dt><%=m.getStname() %></dt></li>
             	 <li style="font-size:0.8em;"><dd class="left" >年龄：</dd><dt><%=m.getAge()%></dt></li>
                <li><dd class="left" style="font-size:0.8em;">已累计获得<%=m.getVote()%>票<dt><br></dt></li>
            </ul>
        </section>
       <%
       String ids=m.getCompetepaintids();
       ArrayList<Workss> array=new ArrayList<Workss>();
       if(ids.indexOf(';')>0)
       for(String id:ids.split(";"))
    	   array.add(Variable.array_competepaint.get(Integer.parseInt(id)));
       for(Workss w:array){
       %> 
      <div class="ranking i<%=i%7%>" style="border-left:5px solid;">
      <a href="Paintdetail?workid=<%=w.getWorkid() %>&i0=<%=++i %>">
      <h1 class="left" style="display:block;font-size:1.7em;">
      <%=i %></h1>
            <img src="<%=w.getPicurl() %>" class="left model-photo"/>
            <ul class="left model-info" >
                <li><%=w.getTitle() %></li>
                <li><dd class="left" style="font-size:0.8em;">已获得<%=w.getScore() %>票<dt><br></dt></li>
            </ul>
             </a>
              <c:if test="${voteuser.votepaintnumbers!=0}">
           <a href='javascript:window._vote(<%=w.getWorkid() %>)'> <div class="right vote">投票</div></a></c:if>
             <c:if test="${voteuser.votepaintnumbers==0}"><div class="right" purl="">已投票</div></c:if>
             <div style="clear:both;"></div>
        </div>	
      <%}
      
       } %>  
         </div>   
          
   </div>  
 <div class="mask" ><div id="foo">请等候...</div></div>
</body>
</html>
