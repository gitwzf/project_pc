<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> ��ʾͼ����ϸ��Ϣ </TITLE>
<META NAME="Author" CONTENT="cheng">
<META NAME="Keywords" CONTENT="��ʾͼ����ϸ��Ϣ">
<style>
body{ font-size:12px};
td{ font-size:12px};
</style>
</HEAD>
<BODY>
	ͼ���б����£�
	<hr>
	<table border='1'>
			<tr>
				<td> ���� </td>
				<td> ���� </td>
				<td> �鼮��� </td>
				<td> ����ʱ�� </td>
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
