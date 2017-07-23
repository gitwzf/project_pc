<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'active.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body><div align="left"> 
                     <p>                                                                                                              <center>       <strong> 夏季游泳培训班电脑摇号招生通告</strong> <br/> </center>
 
&nbsp;&nbsp;&nbsp;&nbsp;为满足全县广大群众的实际需求，提高自身身体素质、增强免疫力，并在活动中学到一技之长，我中心现决定组织开办夏季游泳培训班。 
为体现公平、公开、公正的原则，消除往年排队报名的安全隐患，今年培训报名采取电脑摇号方式。报名分三个阶段：报名登记阶段（5月20日8:30&mdash;6月15日17:30）；电脑摇号公示阶段（6月16日）；报名阶段（6月17日&mdash;6月19日）。请携带本人身份证或户口簿于体育中心办公室预报名，或发送姓名、联系电话、身份证号码至yhtyzx@sina.cn网上报名。网上报名截至2013年6月12日。一证一号，学员出生年月截至2005年6月30日前。注：港北片学员请于港北游泳池报名处登记。身高1.5米以上请报晚班。初中组、成人组学习时间均安排在晚上。 
 <br/>
学习班次： 玉环游泳池少年组（8:00&mdash;9:10）；（9:30&mdash;10:40）； 
                               （13:00&mdash;14:10）；（14:30&mdash;15:40）；  <br/>
初中组（18:30&mdash;19:40）       <br/>
成人（初中组）（20:00&mdash;21:00）  <br/>
港北游泳池组（8:30&mdash;9:40）（9:50&mdash;11:00） <br/>
 
学习期次及人次：<br/> 少年组第一期（400人）少年组第二期（400人） 
少年组第三期（400人） <br/>
      初中组第一期（80人）初中组第二期（80人） 
初中组第三期（80人） <br/>
成人组（300人含初中） <br/>
港北组第一期（200人）港北组第二期（200人） 
港北组第三期（200人） <br/>
   
预报名地点： 县体育馆内办公室 
             港北游泳池  <br/>
开课地点：  玉环游泳池（玉城组） 
            港北游泳池（港北组）玉环中学（提高班） 
 <br/>
咨询电话：87211692  87212692 联系人：陈老师 
</p>
  
<div class="main">
	<form action="le4Serv" class="form-horizontal form" onsubmit="return check();" method="post">
		<h4>报名信息</h4>
		<table class="tb">
			<tr>
				<th><label for="">姓名</label></th>
				<td>
					<div><input type="text" id="user" name="user" class="span6" value="" onchange="check1()"><span class="help-block">请输入真实姓名</span></div>
					
				</td>
			</tr>
			<tr>
				<th><label for="">电话</label></th>
				<td>
					<div><input type="text" id="telephone" name="telephone" class="span6" value="" onchange="check2()"><span class="help-block">请输入联系方式，手机或电话均可</span></div>
					
				</td>
			</tr>
			<tr>
				<th><label for="">身份证号</label></th>
				<td>
					<div><input type="text" id="identity" name="identity" class="span6" value="" onchange="check3()"><span class="help-block">请输入 15 或18个数字，替人报名注意一证一号</span></div>
					
				</td>
			</tr>
			<tr>
				<th><label for="">组别</label></th>
				<td>
					<div><input type="text" id="group" name="group" class="span6" value=""></div>
					
				</td>
			</tr>
			<tr>
				<th><label for="">期次</label></th>
				<td>
					<div><select id="term" name="term"><option value="1">第一期</option><option value="2">第二期</option><option value="3">第三期</option></select><span class="help-block">请选择参加的学期</span></div>
					
				</td>
			</tr>
			<tr>
				<th><label for="">班次</label></th>
				<td>
					<div><select id="class" name="class"><option value="1">一班</option><option value="2">二班</option><option value="3">三班</option><option value="4">四班</option></select><span class="help-block">请选择参加的班级</span></div>
					
				</td>
			</tr>
			<tr>
				<th><label for="">培训地点</label></th>
				<td>
					<div><select id="place" name="place"><option value="玉环游泳池">玉环游泳池</option><option value="港北游泳池">港北游泳池</option><option value="玉环中学">玉环中学</option></select><span class="help-block">请选择培训地点</span></div>
					
				</td>
			</tr>
			<tr>
				<th><label for="">备注</label></th>
				<td>
					<textarea id="" name="instruction" style="height:80px;" class="span6"></textarea>
					<span class="help-block">方便注明此学员的特殊情况</span>
				</td>
			</tr>
			<tr>
				<th></th>
				<td>
					<input type="submit" class="btn btn-primary span2" name="submit" value="提交" />
				</td>
			</tr>
		</table>
	</form>
</div>  
  </body>
</html>
