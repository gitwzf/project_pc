<?php
//�����ļ�
//ȫ�ֱ���
global $site_name;
global $site_addr;
global $message;
global $adminname;
global $badword;

//һЩ����
$site_addr = "http://localhost/phpbook/ch16/";		#����'/'������
$data_url = "http://localhost/phpbook/ch16/music/";	#����'/'������, ������� url���� http:// ��ͷ���ô�URL���ϡ�
$perpage = 36;                             #ÿҳ��ʾ����
$adminname = array("admin");               #��������
$adminemail = "chengwei@net912.org";       #��������
$needlogin = 0;					           # ������Ҫ��¼��

$badword = array("�ÿ�","������","���","�����","����","guest","����Ա","shit","fuck","damn","test","վ��","all");   

$default_cd_cover = 'images/nopix.gif';   #Ĭ�ϵ�cd����ͼ
$default_singer = 'images/nopix.gif';     #��ͷ��ĸ���ͼ

$maxactive = 30;  # �����������֧��

$smtp_mail = 1;   # �Ƿ�ʹ�� smtp �����ܣ������������֧�� mail() �������ô���
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

//ͨ����Ϣ
$message = array();
$message[0] = "<a href=javascript:window.close()>���رմ��ڡ�</a>";
$message[1] = "<a href=javascript:window.history.back()>��������ҳ��</a>";
$message[2] = "#D6E3FF";   #����Ͻ������ɫ
$message[3] = "#FFFFFF";   #����Ͻ�ǳ����ɫ
$message[4] = "<a href=# onclick=\"window.open('user_register.php','register','width=330,height=400,top=20,left=20'); return false;\">��Ҫע��</a>?";   #��Ҫע��
$message[5] = "<a href=# onclick=\"window.open('user_lostpw.php','lostpw','width=330,height=160,top=20,left=20');return false;\">��������</a>?";   #��������
$message[6] = "<a href=help.php target=_blank>��ȡ������Ϣ�������￴����! </a>"; #�����޸ĸ������Ҫ�ľ���ֵ
$message[7] = 450; #�����޸ĸ������Ҫ�ľ���ֵ
$message[8] = 60; #�ר��/����������ޣ�����ҳ����һЩ���������Ч�ʡ�
$message[9] = "http://10.11.11.181:7603";       #������url
$message[10] = "/home/freepage/music/tmp";	#��ʱλ�ã���������ʱ������Ч for chat
$message[11] = 5;     # movite-tv(Ӱ�Ӹ���) ��cate_id
$message[12] = 10*60; # ʮ����֮���ص���Ч
$message[13] = 60;    # ʮ����֮���ص���Ч
$message[14] = "#1251E4";
$message[15] = ".ram"; # �����б�ĺ�׺  Ϊ .ram �� .m3u [real player]
$message[16] = ".asx"; # �����б�ĺ�׺  Ϊ .asx [windows media player]
?>
