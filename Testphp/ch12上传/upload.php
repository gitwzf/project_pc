<?php
include_once "up.class.php";
set_time_limit(0);
if($_SERVER['REQUEST_METHOD'] == 'POST')
{
	 $f  = new upfile(".","txt");
	 if(isset($_FILES)){
		foreach($_FILES as $key=>$val)
//文件句柄			if($f->upload($key,"")){
				if(file_exists($f->savename)){
					echo "文件上传成功";
				}else{
					echo "上传出错！";
					exit;
				}
			}
	    }
}
$size = $_FILES['userfile']['size'];
$name = $_FILES['userfile']['name'];
$type = $_FILES['userfile']['type'];
?>
<br>
您上传的文件信息如下：<br><br>
文件名称：<?php echo $name; ?><br>
文件大小：<?php echo $size; ?><br>
文件类型：<?php echo $type; ?><br>