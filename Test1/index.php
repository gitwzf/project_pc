<html>
<head>
<title>�ļ������ۺ�Ӧ��</title>
</head>
<body>
<table border=0 width=100%>
<tr>
<td align=center bgcolor=yellow>
<font size=4 color=green>�ҵ�PHP�ļ������</font>
</td>
</tr>
</table>
<table border=0 width=100%>
<tr align=center bgcolor=pink>
<th>�ļ���</th><th>��С</th><th>����ʱ��</th><th>�޸�ʱ��</th>
</tr>
<?php
//ϵͳ����
unset($GLOBALS,$_ENV,$HTTP_ENV_VARS,$_REQUEST,$HTTP_POST_VARS,$HTTP_GET_VARS,$HTTP_POST_FILES,$HTTP_COOKIE_VARS);
if(!ini_get('register_globals')){
	@extract($_POST,EXTR_SKIP);
	@extract($_GET,EXTR_SKIP);
	@extract($_COOKIE,EXTR_SKIP);
	@extract($_FILES,EXTR_SKIP);
}
//���Ŀ¼û�����ã�����Ϊ��ǰĿ¼
if(!isset($currentdir))$currentdir="./";
chdir($currentdir);
$id = opendir(".");
//ѭ����ȡĿ¼��Ϣ
while($temp=readdir($id)){
	print("<tr bgcolor=lightblue><td width=30%>");
	//��$temp��һ��Ŀ¼ʱ
	if(is_dir($temp)){
		//���$temp�ǵ�ǰĿ¼"."
		if($temp==".")
			print("<A HREF=\"".$PHP_SELF."?currentdir=".$currentdir."\">".$temp."</A>");
		//���$temp����һ��Ŀ¼".."
		elseif($temp==".."){
			//�����һ��Ŀ¼����ͨ���ص�����Ŀ¼���õ�ʱ
			//����../����һ��Ŀ¼��phpbook/ʱ��ֻ��ͨ��../phpbook/�õ�
			if(strrpos($currentdir,".")==strlen($currentdir)-2){
				print("<A HREF=\"".$PHP_SELF."?currentdir=".$currentdir.$temp."/\">".$temp."</A>");
			}else{
				//�����һ��Ŀ¼����ͨ���ص�����Ŀ¼���õ�ʱ
				//����../phpbook/haha/test��/��һ��Ŀ¼�ɽص�test/�õ�
				$tempdir = substr($currentdir,0,strlen($currentdir)-1);
				$tempdir = substr($tempdir,0,strrpos($tempdir,'/')+1);
				print("<A HREF=\"".$PHP_SELF."?currentdir=".$tempdir."\">".$temp."</A>");
			}
		}else print("<A HREF=\"".$PHP_SELF."?currentdir=".$currentdir.$temp."\">".$temp."</A>");
	}else{
		//��$temp��һ���ļ�ʱ
		//��ȡ�ļ�����չ��
		$extname=substr($temp,strrpos($temp,"."),strlen($temp)-strrpos($temp,"."));
		$extname=strtoupper($extname);
		//����ļ�����Ϊtxt/htm/html�������ʾ
		if($extname==".TXT"||$extname==".HTM"||$extname==".HTML")
		print("<A HREF=\"managefile.php?currentdir=".$currentdir."&filename=".$temp."&type=".$extname."\">".$temp."</A>");
		else
		print($temp);
	}
	//��ʾ���ļ�/Ŀ¼���й���Ϣ��������С������ʱ����޸�ʱ��
	print("<td width=20% align=right>".(is_dir($temp)?"Ŀ¼":round(filesize($temp)/1024)."K")."</td>");
	print("<td width=25% align=right>".date("y-m-dh:i:sA",filectime($temp))."</td>");
	print("<td width=25% align=right>".date("y-m-dh:i:sA",filemtime($temp))."</td>");
	print("</td></tr>\n");
}
closedir($id);
?>
</table>
</body>
</html>