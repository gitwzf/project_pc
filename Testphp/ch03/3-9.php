<?php
echo '
<!--������ʾ-->
<table>
	<form name="first" action= "'.$PHP_SELF.'" method="POST">
	<tr>
		<td>�û�����:</td><td><input type=text name="user"></td>
	</tr>
	<tr>
		<td>��������:</td><td><input type=password name="pwd"></td>
	</tr>
	<tr>
		<td>ȷ������:</td><td><input type=password name="repwd"></td>
	</tr>
	<tr>
		<td>�ʼ���ַ:</td><td><input type=text name="email"></td>
	</tr>
	<tr>
		<td><input type="hidden" name="op" value="reg"></td>
		<td><input type="submit" name="submit" value="ע��"></td>
	</tr>
	</form>
</table>';

    //����������
	$op = $_POST['op'];
	if($op == 'reg'){
		$user = $_POST['user'];
		$pwd = $_POST['pwd'];
		$repwd = $_POST['repwd'];
		$email = $_POST['email'];
		if($pwd == $repwd){
			echo "����д�Ĳ������£�<br>"
				."�û���=".$user
				."�û�����=".$pwd
				."�û�����=".$email;
		}else{
			echo "����������д����ȷ";
		}
	}
?>