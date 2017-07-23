<?php
echo '
<!--表单的显示-->
<table>
	<form name="first" action= "'.$PHP_SELF.'" method="POST">
	<tr>
		<td>用户名称:</td><td><input type=text name="user"></td>
	</tr>
	<tr>
		<td>设置密码:</td><td><input type=password name="pwd"></td>
	</tr>
	<tr>
		<td>确认密码:</td><td><input type=password name="repwd"></td>
	</tr>
	<tr>
		<td>邮件地址:</td><td><input type=text name="email"></td>
	</tr>
	<tr>
		<td><input type="hidden" name="op" value="reg"></td>
		<td><input type="submit" name="submit" value="注册"></td>
	</tr>
	</form>
</table>';

    //表单变量处理
	$op = $_POST['op'];
	if($op == 'reg'){
		$user = $_POST['user'];
		$pwd = $_POST['pwd'];
		$repwd = $_POST['repwd'];
		$email = $_POST['email'];
		if($pwd == $repwd){
			echo "您填写的参数如下：<br>"
				."用户名=".$user
				."用户密码=".$pwd
				."用户邮箱=".$email;
		}else{
			echo "您的密码填写不正确";
		}
	}
?>