<html> 
 <head><title>ģ�����ڶ���һЩ����</title></head> 
 <body> 
   <{*�������һ���൱����ģ���ڲ�����һ������UserName*}> 
   <{assign var="UserName" value="����"}> 
   ���ｫ��ʾģ���ڲ������һ������:UserName=<{$UserName}><br>
   <font color="Blue">�������һ�н���ʾ3��checkBox:</font><br>
   <{html_checkboxes name="id" options=$cust_checkboxes selected=$customer_id separator=" "}><br>
   <font color="red">��������һ�н���ʾ3��radio:</font><br>
   <{html_radios name="rid" options=$cust_radios selected=$radio_id separator="<br/>"}><br>
   ������ʾһ����,��, ��ѡ���:<br>
   <{html_select_date}> <br>
   <{*ҳ��β*}>
<{include file="footer.tpl"}>