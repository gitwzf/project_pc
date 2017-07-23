<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>上传文件示例</TITLE>
<META NAME="Author" CONTENT="chengwei">
<META NAME="Keywords" CONTENT="完善的上传页面">
<META NAME="Description" CONTENT="完善的上传页面，同时限制上传文件的大小">
</HEAD>

<BODY>
	<table>
		<tr>
			<td>
				<form action="upload.php" method="post" enctype="multipart/form-data">
				<input type="hidden" name="MAX_FILE_SIZE" value="102400">
				选择提交的文件：<input type="file" name="userfile"><br>
				<input type="submit" name="上传" value="上传"><br>
				</form>
			</td>
		</tr>
	</table>
</BODY>
</HTML>