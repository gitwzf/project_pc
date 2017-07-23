<?php
//user_login.php  用户登录
require('config.php');
require('public.php');
require('inc/db.class.php');

session_start();
if($user_tmpid != '' && $user_tmppass != '')
{
	    setcookie("m_user_tmpid1","");
	    setcookie("m_user_tmpid1",$user_tmpid,360000);

		$tmp = $db->query_first("SELECT * FROM user WHERE user_name = '$user_tmpid'");
		if($tmp[user_name] == ""){
	        error_quit("帐号不存在! $message[4]");
	    }

		if($tmp[passwd] != $user_tmppass){
	        error_quit("密码错误! $message[5]");
	    }
		//update login
		/* 防止频繁登录 */
		$reallastlogin = eregi_replace('-| |:','',$tmp[lastlogin]);
		//$reallastlogin = intval($reallastlogin);
		$thislogin = date("YmdHis");
		//$thislogin = intval($thislogin);
		unset($flag); //安全起见
		if(($thislogin - $reallastlogin) > $message[12]) $flag = 1;
		else $flag = 0;

		/* end */

	    $lastfrom = getenv("REMOTE_ADDR");
		$lastlogin = date("Y-m-d H:i:s");
		if($flag == 1)
		{
			$db->query("UPDATE user SET numlogin = numlogin+1,lastlogin = '$lastlogin', lastfrom = '$lastfrom' WHERE 	user_id = '$tmp[user_id]'");
		}

		//session处理
		$m_online_tag = session_id();
		$m_user = $tmp;
	    session_register("m_user");
	    session_register("m_online_tag");
		
		if($__keep === 'on')
		{
			//setcookie('m_online_tag', $m_online_tag, time()+3156000); // 储存SessionID到Cookie中	
			setcookie('m_user_id', $m_user[user_id], time()+3156000); // 储存SessionID到Cookie中	
		}
		else
			unset($m_user_id);

		//更新session
		$m_user[lastfrom] = $lastfrom;
		if($flag == 1)
		{
		  $m_user[numlogin] += 1;
		}
}

//重定向到登录页
print "
<script language=javascript>
<!--
	//window.open('http://10.14.61.248/music/chat.php','chat','width=780,height=560,left=0,top=0');
	window.history.go(-1);
//-->
</script>
";

$db->close();
exit;
?>