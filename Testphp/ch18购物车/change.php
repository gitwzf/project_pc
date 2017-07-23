<?php
//修改个人资料
session_start();
if (!isset($_SESSION["userid"])) {
	$_SESSION["userid"]= $_GET["uid"];
}
$userid = $_SESSION["userid"];
$act = $_POST["act"];
include_once("usershow.php");
//如果用户没有登录
if(!isset($userid)){
	echo "对不起,您还没有登录,请先登录";
	$message = usershow($userid);
	$i = 1;
	while (isset($message[$i])) {
		echo "<br>".$message[$i];
		$i ++ ;
	}
}
//如果不是修改,则输出相应的信息
elseif ($act != "change"){
	$conn = mysql_connect("localhost","root", "");
	mysql_select_db("phpbook_ch18") or die("连接到数据库失败!");
	$query = "select * from user where userid=".$userid;
	$result = mysql_query($query);
	$res = mysql_fetch_object($result);
	echo "<form method='post' action='change.php'>
			<table width='95%' border='1' cellspacing='0' cellpadding='0'>
				<tr bgcolor='#99ccff'>
					<td>请输入你的新密码
						<input type='password' name='newpass' size='12'>
					</td>
				</tr>
				<tr bgcolor='#99ccff'>
					<td>请确认你的新密码
						<input type='password' name='renewpass' size='12'>
					</td>
				</tr>
				<tr bgcolor='#99ccff'>
					<td>您的密码提示问题
						<input type='text' name='passwdq' size='30' value='".$res->userpassq."'>
					</td>
				</tr>
				<tr bgcolor='#99ccff'>
					<td>您的密码提示问题答案
						<input type='text' name='passwda' size='30' value='".$res->userpassa."'>
					</td>
				</tr>
				<tr bgcolor='#99ccff'>
					<td>您的Email
						<input type='text' name='email' size='30' value='".$res->email."'>
					</td>
				</tr>
				<tr bgcolor='#99ccff'>
					<td>您的电话
						<input type='text' name='tel' size='30' value='".$res->usertel."'>
					</td>
				</tr>
				<tr bgcolor='#99ccff'>
					<td>您的详细住址
						<input type='text' name='addr' size='30' value='".$res->useraddr."'>
						<input type='hidden' name='act' value='change'>
					</td>
				</tr>
				<tr bgcolor='#99ccff'>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type='hidden' name='userid' value='$userid'>
						<input type='submit' name='submit' value='修改'>
						<input type='reset' name='submit2' value='重来'>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</form>";
	mysql_close($conn);
}elseif ($act == "change"){
	//检查是否有输入错误
	$newpass = $_POST["newpass"];
	$renewpass = $_POST["renewpass"];
	$passq = $_POST["passwdq"];
	$passa = $_POST["passwda"];
	$email = $_POST["email"];
	$tel = $_POST["tel"];
	$addr = $_POST["addr"];
	$userid = $_POST["userid"];
	if ($newpass != $renewpass) {
		header("Location:change.php?act=fail");
		exit();
	}
	$conn = mysql_connect("localhost","root","");
	mysql_select_db("phpbook_ch18");
	if (isset($newpass) && isset($renewpass)) {
		$password = md5($newpass);
		$query = "update user set userpass='".$password."',userpassq='"
			.$passq	."',userpassa='".$passa."',email='".$email
			."',usertel='".$tel	."',useraddr='".$addr."' where userid=".$userid;
	}else{
		$query = "update user set userpassq='" . $passq	."',userpassa='"
			. $passa ."',email='".$email ."',usertel='". $tel ."',useraddr='".$addr
			. "' where userid=".$userid;
	}
	if(mysql_query($query))
		echo "用户【".$userid."】资料已经成功更新！";
}
?>