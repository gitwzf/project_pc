<?php
//����$_FILESʹ��Ч��
if ($_FILES != null) {
	foreach ($_FILES['userfile'] as $key => $value) {
		echo "Key: <font color='blue'>$key</font>; Value: <font color='red'>$value</font><br>\n";
	}
}
?>
<form enctype="multipart/form-data" action="_files.php" method="POST">
<input type="hidden" name="MAX_FILE_SIZE" value="30000">
ѡ��Ҫ�ϴ����ļ�: <input name="userfile" type="file">
<input type="submit" value="�ϴ��ļ�">
</form>