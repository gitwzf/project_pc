<?php
//���ݿ�����
$conn = mysql_connect("localhost","root","");
mysql_select_db("phpbook_ch18") or die("ѡ�����ݿ�ʧ��!");
$result = mysql_list_tables("phpbook_ch18");
$i = 0;
$flag = false ; //�趨��ʶΪfalse
//�ж��Ƿ����User�û���
while ($i <mysql_numrows($result)) {
	if(mysql_table_name($result, $i) =="user" || mysql_table_name($result, $i) == "User"){
		$flag = true;
		break;
	}
	$i ++ ;
}
//����û�������,���Դ���һ���µ�
if(!$flag){
	$query = "
	CREATE TABLE `user` (
	  `userid` int(10) unsigned NOT NULL auto_increment,
	  `username` varchar(20) default '0',
	  `userpass` varchar(30) default '0',
	  `email` varchar(40) NOT NULL default '0',
	  `usertel` varchar(6) default '0',
	  `useraddr` varchar(255) default '0',
	  `createtime` datetime default '0000-00-00 00:00:00',
	  `logintime` datetime default '0000-00-00 00:00:00',
	  `currentlogintime` datetime default '0000-00-00 00:00:00',
	  `logincount` int(10) NOT NULL default '0',
	  `usercredit` int(10) NOT NULL default '0',
	  `liking1` varchar(30) default NULL,
	  `liking2` varchar(30) default NULL,
	  `liking3` varchar(30) default NULL,
	  PRIMARY KEY  (`userid`)
	) TYPE=MyISAM;
	";
	//����ɹ���Ϣ
	if(mysql_query($query)){
		echo "�ɹ������û���";
	}else{
		//����,����Ѿ����ڵ������Ϣ
		echo "�û����Ѿ�����";
	}
	//�ر����ݿ�����
	mysql_close($conn);
}
?>