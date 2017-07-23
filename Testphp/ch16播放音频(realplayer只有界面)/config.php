<?php
//配置文件
//全局变量
global $site_name;
global $site_addr;
global $message;
global $adminname;
global $badword;

//一些设置
$site_addr = "http://localhost/phpbook/ch16/";		#最后的'/'不能少
$data_url = "http://localhost/phpbook/ch16/music/";	#最后的'/'不有少, 如果试听 url不以 http:// 开头则用此URL补上。
$perpage = 36;                             #每页显示份数
$adminname = array("admin");               #管理名字
$adminemail = "chengwei@net912.org";       #管理信箱
$needlogin = 0;					           # 听歌需要登录吗？

$badword = array("访客","操你妈","妈的","你妈的","网管","guest","管理员","shit","fuck","damn","test","站长","all");   

$default_cd_cover = 'images/nopix.gif';   #默认的cd封面图
$default_singer = 'images/nopix.gif';     #无头像的歌像图

$maxactive = 30;  # 最高在线人数支持

$smtp_mail = 1;   # 是否使用 smtp 发功能，如果服务器不支持 mail() 函数则用此项
if($smtp_mail && defined("USE_MAIL")) {
	require("inc/smtp.class.php");
	$params = array();
	$params['host'] = "smtp.126.com";
	$params['port'] = 25;					// The smtp server port
	$params['helo'] = $params['host'];		// What to use when sending the helo command. domain/hostname
	$params['auth'] = FALSE;				// Whether to use basic authentication or not
	$params['user'] = 'phpbook18';			// Username for authentication
	$params['pass'] = '12345qwert';			// Password for authentication	
}

//通用信息
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
$message[9] = "http://10.11.11.181:7603";       #聊天室url
$message[10] = "/home/freepage/music/tmp";	#临时位置，无聊天室时此项无效 for chat
$message[11] = 5;     # movite-tv(影视歌曲) 的cate_id
$message[12] = 10*60; # 十分钟之内重登无效
$message[13] = 60;    # 十分钟之内重登无效
$message[14] = "#1251E4";
$message[15] = ".ram"; # 播放列表的后缀  为 .ram 或 .m3u [real player]
$message[16] = ".asx"; # 播放列表的后缀  为 .asx [windows media player]
?>
