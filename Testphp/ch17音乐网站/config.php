<?php
//ȫ�ֱ���
global $site_name;
global $site_addr;
global $message;
global $adminname;
global $badword;
//�������
$site_name = "�������ֵ㲥";                 			//վ��
$site_addr = "http://localhost/phpbook/ch17/";		//����'/'������
$data_url = "http://localhost/phpbook/ch17/music/";	//����'/'������, �������url����'http://'��ͷ���ô�URL
$perpage = 36;                             			//ÿҳ��ʾ����
$adminname = array("admin");               			//��������
$adminemail = "weige@phpbook.com";    				//��������
$needlogin = 0;					           			//�����Ƿ���Ҫ��¼��
$badword = array("�ÿ�","pp","p��","ppp","����","guest","����Ա","shit","fuck","damn","test","վ��","all");				#���˴ʻ�
$default_cd_cover = 'images/nopix.gif';   #Ĭ�ϵ�cd����ͼ
$default_singer = 'images/nopix.gif';     #��ͷ��ĸ���ͼ
$maxactive = 30;  # �����������֧��
$smtp_mail = 1;   # �Ƿ�ʹ�� smtp �����ܣ������������֧�� mail() �������ô���

/*
if($smtp_mail && defined("USE_MAIL")) {
	require_once("inc/smtp.class.php");
	$params = array();
	$params['host'] = "smtp.ddd.org";
	$params['port'] = 25;					//smtp�ʼ��������Ķ˿�
	$params['helo'] = $params['host'];		//���÷���helo�����ʱ���������ַ
	$params['auth'] = FALSE;				//�Ƿ���Ҫ��֤
	$params['user'] = 'manager@ddd.org';			//�û�����
	$params['pass'] = 'manager';			//�û�����	
}
*/
//������Ϣ
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
$message[9] = "http://172.16.2.121:7654";       #������url
$message[10] = "/home/freepage/music/tmp";	#��ʱλ�ã���������ʱ������Ч for chat
$message[11] = 5;     # movite-tv(Ӱ�Ӹ���) ��cate_id
$message[12] = 10*60; # ʮ����֮���ص���Ч
$message[13] = 60;    # ʮ����֮���ص���Ч
$message[14] = "#1251E4";
$message[15] = ".ram"; # �����б�ĺ�׺  Ϊ .ram �� .m3u
?>