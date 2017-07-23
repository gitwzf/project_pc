<html> 
 <head><title>模板中内定的一些函数</title></head> 
 <body> 
   <{*下面的这一段相当于在模板内部定义一个变量UserName*}> 
   <{assign var="UserName" value="张三"}> 
   这里将显示模板内部定义的一个变量:UserName=<{$UserName}><br>
   <font color="Blue">下面的这一行将显示3个checkBox:</font><br>
   <{html_checkboxes name="id" options=$cust_checkboxes selected=$customer_id separator=" "}><br>
   <font color="red">下面在这一行将显示3个radio:</font><br>
   <{html_radios name="rid" options=$cust_radios selected=$radio_id separator="<br/>"}><br>
   下面显示一个月,日, 年选择框:<br>
   <{html_select_date}> <br>
   <{*页面尾*}>
<{include file="footer.tpl"}>