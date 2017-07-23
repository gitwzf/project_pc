<html>
<head>
<title>上传结果</title>
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
//获取文件后缀
$sufix=strtolower(getsufix($_FILES['file']['name']));
//开始判断上传的文件类型
if(!in_array($sufix,$type)){
	$text=implode(",",$type);
	echo "只允许上传以下类型文件: ".$text."<br>";
}else{ //生成目标文件的文件名
	$filename=explode(".",$_FILES['file']['name']);
	do{
		$filename[0] = random(10); //设置随机数
		$name=implode(".",$filename);
		$uploadfile=$uploaddir.$name;
	}while(file_exists($uploadfile));
	//移动文件
	if (move_uploaded_file($_FILES['file']['tmp_name'],$uploadfile)){
		//创建数据库连接对象
		$db=new db;
		//调用连接参数
		$db->mysql($host,$user,$password,$database);
		//创建连接
		$db->createcon();
		$time = date("Y-m-d H:m:s");
		$url = $visit_url.$name;
		$sql="insert into uploadfile values (0,'$name','$url','$uploadfile','$time',0)";
		if($db->query($sql)){
			//输出图片预览
			echo "<center>您的文件已经上传完毕&nbsp;上传图片预览: </center><br>";
			echo "<center><img src='".$uploadfile."'></center>";
			echo "<br><center><a href='javascript:history.go(-1)'>继续上传</a>&nbsp;";
			echo "<a href=../admin/upfileman.php>管理</a></center>";
		}else{
			echo "上传失败！";
		}
	}
}
//关闭数据库连接
$db->close();
?>
</body>
</html>