<html>
<head>
<title>�ϴ����</title>
<style type="text/css">
<!--
body {
	font-size: 9pt;
	color: #FF0000;
}
a:link {
	font-size: 9pt;
	text-decoration: underline;
	color: #FF0000;
}
a:visited {
	font-size: 9pt;
	text-decoration: underline;
	color: #FF0000;
}
a:hover {
	font-size: 9pt;
	color: #FF0000;
	text-decoration: none;
}
-->
</style>
</head>
<body>
<?php
include('global.php');
include('../inc/config.inc.php');
include('../inc/db.class.php');
//��ȡ�ļ���׺
$sufix=strtolower(getsufix($_FILES['file']['name']));
//��ʼ�ж��ϴ����ļ�����
if(!in_array($sufix,$type)){
	$text=implode(",",$type);
	echo "ֻ�����ϴ����������ļ�: ".$text."<br>";
}else{ //����Ŀ���ļ����ļ���
	$filename=explode(".",$_FILES['file']['name']);
	do{
		$filename[0] = random(10); //���������
		$name=implode(".",$filename);
		$uploadfile=$uploaddir.$name;
	}while(file_exists($uploadfile));
	//�ƶ��ļ�
	if (move_uploaded_file($_FILES['file']['tmp_name'],$uploadfile)){
		//�������ݿ����Ӷ���
		$db=new db;
		//�������Ӳ���
		$db->mysql($host,$user,$password,$database);
		//��������
		$db->createcon();
		$time = date("Y-m-d H:m:s");
		$url = $visit_url.$name;
		$sql="insert into uploadfile values (0,'$name','$url','$uploadfile','$time',0)";
		if($db->query($sql)){
			//���ͼƬԤ��
			echo "<center>�����ļ��Ѿ��ϴ����&nbsp;�ϴ�ͼƬԤ��: </center><br>";
			echo "<center><img src='".$uploadfile."'></center>";
			echo "<br><center><a href='javascript:history.go(-1)'>�����ϴ�</a>&nbsp;";
			echo "<a href=../admin/upfileman.php>����</a></center>";
		}else{
			echo "�ϴ�ʧ�ܣ�";
		}
	}
}
//�ر����ݿ�����
$db->close();
?>
</body>
</html>