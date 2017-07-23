<?php
//全局变量
global $site_name;
global $site_addr;
global $message;
global $adminname;
global $badword;
//相关设置
$site_name = "在线音乐点播";                 			//站名
$site_addr = "http://localhost/phpbook/ch17/";		//最后的'/'不能少
$data_url = "http://localhost/phpbook/ch17/music/";	//最后的'/'不有少, 如果试听url不以'http://'开头则用此URL
$perpage = 36;                             			//每页显示份数
$adminname = array("admin");               			//管理名字
$adminemail = "weige@phpbook.com";    				//管理信箱
$needlogin = 0;					           			//听歌是否需要登录吗
$badword = array("访客","pp","p的","ppp","网管","guest","管理员","shit","fuck","damn","test","站长","all");				#过滤词汇
$default_cd_cover = 'images/nopix.gif';   #默认的cd封面图
$default_singer = 'images/nopix.gif';     #无头像的歌像图
$maxactive = 30;  # 最高在线人数支持
$smtp_mail = 1;   # 是否使用 smtp 发功能，如果服务器不支持 mail() 函数则用此项

/*
if($smtp_mail && defined("USE_MAIL")) {
	require_once("inc/smtp.class.php");
	$params = array();
	$params['host'] = "smtp.ddd.org";
	$params['port'] = 25;					//smtp邮件服务器的端口
	$params['helo'] = $params['host'];		//设置发送helo命令的时候的主机地址
	$params['auth'] = FALSE;				//是否需要认证
	$params['user'] = 'manager@ddd.org';			//用户名称
	$params['pass'] = 'manager';			//用户密码	
}
*/
//公用信息
$message = array();
$message[0] = "<a href=javascript:window.close()>【关闭窗口】</a>";
$message[1] = "<a href=javascript:window.history.back()>【返回上页】</a>";
$message[2] = "#D6E3FF";   #表格上较深的颜色
$message[3] = "#FFFFFF";   #表格上较浅的颜色
$message[4] = "<a href=# onclick=\"window.open('user_register.php','register','width=330,height=400,top=20,left=20'); return false;\">需要注册</a>?";   #需要注册
$message[5] = "<a href=# onclick=\"window.open('user_lostpw.php','lostpw','width=330,height=160,top=20,left=20');return false;\">忘记密码</a>?";   #忘掉密码
$message[6] = "<a href=help.php target=_blank>获取更多信息，点这里看帮助! </a>"; #可以修改歌词所需要的经验值
$message[7] = 450; #可以修改歌词所需要的经验值
$message[8] = 60; #最爱专辑/最爱歌曲的上限，不分页，少一些有助于提高效率。
$message[9] = "http://172.16.2.121:7654";       #聊天室url
$message[10] = "/home/freepage/music/tmp";	#临时位置，无聊天室时此项无效 for chat
$message[11] = 5;     # movite-tv(影视歌曲) 的cate_id
$message[12] = 10*60; # 十分钟之内重登无效
$message[13] = 60;    # 十分钟之内重登无效
$message[14] = "#1251E4";
$message[15] = ".ram"; # 播放列表的后缀  为 .ram 或 .m3u
?>