<?php
//user_login.php  �û���¼
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
	        error_quit("�ʺŲ�����! $message[4]");
	    }

		if($tmp[passwd] != $user_tmppass){
	        error_quit("�������! $message[5]");
	    }
		//update login
		/* ��ֹƵ����¼ */
		$reallastlogin = eregi_replace('-| |:','',$tmp[lastlogin]);
		//$reallastlogin = intval($reallastlogin);
		$thislogin = date("YmdHis");
		//$thislogin = intval($thislogin);
		unset($flag); //��ȫ���
		if(($thislogin - $reallastlogin) > $message[12]) $flag = 1;
		else $flag = 0;

		/* end */

	    $lastfrom = getenv("REMOTE_ADDR");
		$lastlogin = date("Y-m-d H:i:s");
		if($flag == 1)
		{
			$db->query("UPDATE user SET numlogin = numlogin+1,lastlogin = '$lastlogin', lastfrom = '$lastfrom' WHERE 	user_id = '$tmp[user_id]'");
		}

		//session����
		$m_online_tag = session_id();
		$m_user = $tmp;
	    session_register("m_user");
	    session_register("m_online_tag");
		
		if($__keep === 'on')
		{
			//setcookie('m_online_tag', $m_online_tag, time()+3156000); // ����SessionID��Cookie��	
			setcookie('m_user_id', $m_user[user_id], time()+3156000); // ����SessionID��Cookie��	
		}
		else
			unset($m_user_id);

		//����session
		$m_user[lastfrom] = $lastfrom;
		if($flag == 1)
		{
		  $m_user[numlogin] += 1;
		}
}

//�ض��򵽵�¼ҳ
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