<?php
//��� geci_modify.php
require('config.php');
require('public.php');
require('inc/db.class.php');

if(!islogin()) err_quit3("���ȵ�¼��\n");
if((count_exp() < $message[7]) && !is_admin()) error_quit3("���Ȩ�޵ȼ�����, ����ֵ���볬��<b>".$message[7]."</b>��".$message[6]);
if($song_id == '') error_quit3("�Ƿ�����!");

if(isset($submit)) //���Ϊ��
{
 if(strlen($geci)<200) error_quit3("����޸ĺ����д��~");
 $now = date("Y-m-d H:i:s");
 $geci = trim($geci);
 $geci .= "\n\n--\n�� ".$m_user[user_name]."��".$now."���Ĺ������";

 $db->query("UPDATE geci SET geci = '$geci', zuoqu = '$zuoqu', tianci = '$tianci' WHERE song_id = '$song_id'");

 html_head("�޸ĸ�ʳɹ�");
 print "
	 <br><br><br>
	 <p align=center><span class=okmsg>��ӳɹ���</span> ��л���Ĳ��룡</p>
	 <p align=center> $message[0] </p>
	 <script>setTimeout('window.close()', 2000);</script>
 ";
}
else{
$query = "SELECT S.song_name, G.* FROM song S, geci G WHERE S.song_id = '$song_id' AND G.song_id = '$song_id'";
$res = $db->query_first($query);

//���ϴθĹ�������ȥ��
$res[geci] = addslashes($res[geci]);
//$res[geci] = strtok($res[geci],'\n\n--');
//$res[geci] = $nouse;//eregi_replace('$nouse','',$res[geci]);

html_head("�޸ġ�".$res[song_name]."���ĸ��");
print "
	<br>
	<table width=96% bgcolor='#000000' align=center cellPadding=2 cellSpacing=1 height=95%>
	<tr height=18 bgcolor=$message[2]><td>Ϊ".$res[song_name]."�޸ĸ��</td></tr>
	<tr width=100%><td background='images/bg1.gif'>
		 <table border=0 align=center>
	      <tr><form action='$PHP_SELF' mothod=post><td align=left>
		  ����: <input type=text size=15 name=tianci value='$res[tianci]' class=input> ����: <input type=text name=zuoqu size=15 value='$res[zuoqu]' class=input><br>
	      <textarea cols=65 rows=20 class=input nowrap name=geci>".htmlspecialchars($res[geci])."</textarea><br><br>
	      <input type=hidden name=song_id value='$song_id'>
	      &nbsp;&nbsp;&nbsp;&nbsp;<input type=submit name=submit onclick=this.blur() value=ȷ�� class=button>
		  &nbsp;&nbsp;<input type=reset value=���� class=button onclick=this.blur()>
		 </td></form></tr>
         </table>
	 </td></tr>
	";
if($res[tigong])
	print "
	  <tr bgcolor=$message[2]><td>��л����<b>$res[tigong]</b>�ṩ���.</td></tr>\n";
print "
	  <tr bgcolor=$message[3]><td>$message[1] $message[0]</td></tr>
	  </table>
	  ";
}

print "</body></html>";
$db->close();
exit;
?>