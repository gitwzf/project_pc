<?php
//�Ƽ�ר��
require_once('../config.php');
require_once('../public.php');
require_once('../inc/db.class.php');
if(!is_admin()) error_quit3("���������ǹ���Ա��û�е�¼��");
$rec_file = "recommend.no.php";
html_head("�޸��Ƽ�ר��");
if(isset($rec_id)){ //�����޸�
	$db->query("SELECT COUNT(*) FROM cd WHERE cd_id = $rec_id");
	$chk = $db->num_rows();
	$db->close();
	if($chk == 0) {
		echo "�����CD_ID: $rec_id <a href='$PHP_SELF'>����</a>\n";
	} else {
		if($fd = fopen($rec_file, "w")) {
			fputs($fd, "<? \$recom_id = ".$rec_id."; ?>\n");
			fclose($fd);
			echo "�޸ĳɹ��� <a href='$PHP_SELF'>����</a>\n";
		} else {
			echo "�޷��� ".$rec_file."!  <a href='$PHP_SELF'>����</a>\n";
		}
	}
}else { //ѡ�����
	include_once($rec_file);
	echo "<h3>�Ƽ�ר��(1) -- ����ID</h3>"
	."<form method=post action='".$_SERVER['PHP_SELF']."'>\n"
	."����ר��ID��<input type=text name=rec_id value='$recom_id' class=input>\n"
	."<input type=submit class=button value='ȷ��'>\n"
	."</form>\n";
}
echo "</body></html>";
exit;
?>