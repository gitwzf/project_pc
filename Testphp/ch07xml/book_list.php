<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> 显示图书详细信息 </TITLE>
<META NAME="Author" CONTENT="cheng">
<META NAME="Keywords" CONTENT="显示图书详细信息">
<style>
body{ font-size:12px};
td{ font-size:12px};
</style>
</HEAD>
<BODY>
	图书列表如下：
	<hr>
	<table border='1'>
			<tr>
				<td> 标题 </td>
				<td> 作者 </td>
				<td> 书籍编号 </td>
				<td> 发行时间 </td>
			</tr>
		<tbody>
			<?php 
				include_once("common.php"); 
				$book = parserBookInfo();
				for($i = 0; $i < count($book); $i ++){
					getBookDetail($book[$i]["title"],$book[$i]["authors"],
								$book[$i]["isbn"],$book[$i]["date"],
								$book[$i]["zone"]);
				}
			?>
		</tbody>
	</table>
</BODY>
</HTML>
