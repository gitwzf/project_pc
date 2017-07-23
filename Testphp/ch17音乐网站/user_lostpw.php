<?php
define("USE_MAIL", 1);

require('config.php');
require('inc/db.class.php');
require('public.php');

html_head('忘记密码');
?>

<script language=Javascript>
<!--
function dosub1() 
{
 if(document.res.name.value == '')
	{ 
	  alert("填写不完整!");
	  return false;
	}
  else
	  return true;
  }
 //-->
 </script>
<?php
function error_return($msg) { //错误时借助javascript返回
	print "<script language=javascript> alert('".$msg."'); window.history.go(-1);</script>";
	exit;
}

 if(!empty($name) && ($job == "lostpw")) { //读出旧资料

    $info = $db->query_first("SELECT user_name, passwd, email FROM user WHERE user_name = '$name'");

    if($name != $info[user_name]) error_return('用户不存在！');
    $message = "您在 $site_name 的帐号为: $name 其中密码是: $info[passwd] 请注意保管!\n";
	$subject = "[181] 密码使者";
	$headers = "From: ".$adminname[0]." <".$adminemail.">\r\n"
				."Reply-To: ".$adminname[0]." <".$adminemail.">\r\n"
				."Content-Type: text/plain\r\n"
				."X-Mailer: cheng.Mar\r\n";

	if($smtp_mail == 0)
		mail($info[email], $subject, $message, $headers);
	else {
		$send_params = array();
		$send_params['headers'] = "Subject: ".$subject."\r\n".$headers;
		unset($headers);
		$send_params['recipients'] = $info[email];
		$send_params['from'] = $adminname[0]." <".$adminemail.">";
		$send_params['body'] = $message;
		$smtp = smtp::connect($params);
		$smtp->send($send_params);
		unset($send_params);
		unset($params);
	}
		
	print "<center><font color=red size=+1>操作成功!</font><br>密码已经寄往您的信箱: $info[email]<br><br>【<a href=javascript:window.close()>关闭窗口</a>】</center></body></html>";
    exit;
 }
else {
?>
<center>
<table border="0" width="91%" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="26" colspan="3" bgcolor="#dfdfcf"><p align="center"><font
    size="4" color="#FF0000"><em> 忘记密码</em></font></td>
  </tr>
  <tr>
    <td width="2%" align="center" bgcolor="#dfdfcf" rowspan="2" valign="top"></td>
    <td  valign="top" width="96%">
	<table border=0>
      <tr><form method="post" name=res action= "<?echo $PHP_SELF;?>"  onsubmit="return dosub1();"><font size=2>
        <td valign=top>
          <input type="hidden" name="job" value="lostpw">
          <b><font color="#800000">您的帐号：</font></b> <input type="text" name="name"><br>
		 </td></tr>
    </table>       
　  </td>
    <td width="2%" align="center" bgcolor="#dfdfcf" rowspan="2" valign="top"></td>
  </tr>
  <tr>
    <td width="100%" align="center" height="26" colspan="3" bgcolor="#dfdfcf"><input type="submit" value="确定"> <input type="reset" value="重写">
	<input type="button" value="关闭" onclick="window.close()"></td>
	</form>
  </tr>
</table>
</center>
<?php
}
?>
</body>
</html>
<?php
 $db->close();
 exit; 
?>
