<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>�û���¼</title>
		<script language="javascript">
		<!--
		function IsDigit(cCheck) {
			return (('0'<=cCheck) && (cCheck<='9'));
		}
		
		function IsAlpha(cCheck) { 
			return ((('a'<=cCheck) && (cCheck<='z')) || (('A'<=cCheck) && (cCheck<='Z'))) 
		}
			
		function IsValid(){
			var struserName = login.UserName.value;
			for (nIndex=0; nIndex<struserName.length; nIndex++){
				cCheck = struserName.charAt(nIndex);		
				if (!(IsDigit(cCheck) || IsAlpha(cCheck))){
					return false;
				}
			}	
			return true;
		}
		
		function docheck(){
			if(login.UserName.value==""){
				alert("����д�û���");
				return false;
			}else if(!IsValid()){
			   alert("�û���ֻ��ʹ����ĸ������");
			   return false;					
			}else if(login.UserPassword.value==""){
				alert("����д����");
				return false;
			}else{
				return true;
			}
		}
		//-->
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
		<h1 align="center">�û���¼</h1>
		<div align="center">
		<form name="login" action="user_login_check.php" method="post" target="_self"  onSubmit="return docheck()">
		<table width="90%">
		<tr>
		<td width="50%" align="right" height="25">�û�����</td>
		<td width="50%" align="left" height="25"><input type="text" name="UserName"></td>
		</tr>
		<tr>
		<td width="50%" align="right" height="25">���룺</td>
		<td width="50%" align="left" height="25"><input type="password" name="UserPassword"></td>
		</tr>		
		</table>
		<p>
		<input type="submit" name="sub" value="��¼">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" name="res" value="����">
		</p>
		</form>
		</div>
	</body>
</html>