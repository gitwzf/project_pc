<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>�û�ע��</title>
		<script language="javascript">
		function IsDigit(cCheck){
			return (('0'<=cCheck) && (cCheck<='9'));
		}
		
		function IsAlpha(cCheck) { 
			return ((('a'<=cCheck) && (cCheck<='z')) || (('A'<=cCheck) && (cCheck<='Z'))) 
		}
			
		function IsValid(){
			var struserName = reg.UserName.value;
			for (nIndex=0; nIndex<struserName.length; nIndex++)
			{
				cCheck = struserName.charAt(nIndex);		
				if (!(IsDigit(cCheck) || IsAlpha(cCheck)))
				{
					return false;
				}
			}	
			return true;
		}
		function chkEmail(str){
			return str.search(/[\w\-]{1,}@[\w\-]{1,}\.[\w\-]{1,}/)==0?true:false
		}
		
		function docheck(){
			if(reg.UserName.value==""){
				alert("����д�û���");
				return false;
			}else if(!IsValid()){
			   alert("�û���ֻ��ʹ����ĸ������");
			   return false;					
			}else if(reg.UserPassword.value==""){
				alert("����д����");
				return false;
			}else if(reg.UserPassword.value != reg.CUserPassword.value){
				alert("�������벻һ��");
				return false;
			}else if(reg.NickName.value ==""){
				alert("����д�ǳ�");
				return false;
			}else if(reg.Email.value ==""){
				alert("����д����");
				return false;
			}else if(!chkEmail(reg.Email.value)){
				alert("����д��Ч��Email��ַ");
				return false;
			}else{
				return true;
			}
		}
		</script>
		<STYLE type=text/css>
		td, th {
		font-family: Arial, Helvetica, sans-serif;
		font-size: 14px;
		line-height: 24px;
		color: #333333;
		}
		</STYLE>
	</head>
	<body>
		<h1 align="center">�û�ע��</h1>
		<div align="center">
			<form name="reg" action="user_add.php" method="post" target="_self" onSubmit="return docheck()">
			<table width="90%" border="0">		
				<tr>
				<td width="50%" align="right"  height="25"><font face="Arial, Helvetica, sans-serif">������Ҫע����û�����</font></td>
				<td width="50%" align="left"  height="25">
				&nbsp;<input type="text" name="UserName">
				<br>
				<font color="red">&nbsp;�û���ֻ������ĸ���������</font>
				</td>
				</tr>
				<tr>
				<td width="50%" align="right"  height="25">���������룺</td>
				<td width="50%" align="left"  height="25">&nbsp;<input type="password" name="UserPassword"></td>
				</tr>
				<tr>
				<td width="50%" align="right"  height="25">������ȷ�����룺</td>
				<td width="50%" align="left"  height="25">&nbsp;<input type="password" name="CUserPassword"></td>
				</tr>
				<tr>
				<td width="50%" align="right"  height="25">�������ǳƣ�</td>
				<td width="50%" align="left"  height="25">&nbsp;<input type="text" name="NickName"></td>
				</tr>
				<tr>
				<td width="50%" align="right"  height="25">��ѡ���Ա�</td>
				<td width="50%" align="left"  height="25">&nbsp;<input type="radio" name="Sex" value="0" checked>��&nbsp;<input type="radio" name="Sex" value="1">Ů</td>
				</tr>
				<tr>
				<td width="50%" align="right"  height="25">������Email��ַ��</td>
				<td width="50%" align="left"  height="25">&nbsp;<input type="text" name="Email"></td>
				</tr>
			</table>
			<p>
			<input type="submit" name="sub" value="ע��">&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" name="res" value="����">
			</p>
			</form>	
		</div>
	</body>
</html>