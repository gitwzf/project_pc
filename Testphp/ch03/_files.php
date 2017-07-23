<?php
//测试$_FILES使用效果
if ($_FILES != null) {
	foreach ($_FILES['userfile'] as $key => $value) {
		echo "Key: <font color='blue'>$key</font>; Value: <font color='red'>$value</font><br>\n";
	}
}
?>
<form enctype="multipart/form-data" action="_files.php" method="POST">
<input type="hidden" name="MAX_FILE_SIZE" value="30000">
选择要上传的文件: <input name="userfile" type="file">
<input type="submit" value="上传文件">
</form>