<?php
include_once "up.class.php";
set_time_limit(0);
if($_SERVER['REQUEST_METHOD'] == 'POST')
{
	 $f  = new upfile(".","txt");
	 if(isset($_FILES)){
		foreach($_FILES as $key=>$val)
//�ļ����			if($f->upload($key,"")){
				if(file_exists($f->savename)){
					echo "�ļ��ϴ��ɹ�";
				}else{
					echo "�ϴ�����";
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
���ϴ����ļ���Ϣ���£�<br><br>
�ļ����ƣ�<?php echo $name; ?><br>
�ļ���С��<?php echo $size; ?><br>
�ļ����ͣ�<?php echo $type; ?><br>