<?php
//复制文件到c:\upload
if(copy($userfile, "c:\\upload\\")){
	  echo "<b>上传文件成功</b>";
}else{
	  echo "<b>上传失败！</b>";
}
//复制完成后删除
unlink($userfile);
?>