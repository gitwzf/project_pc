<?php
//��Ӹ��,�κ��˿������.geci_add.php
//����һ����ʱ�����ݿ�tmpgeci��ȹ�����ȷ�ϡ� 
require('config.php');
require('public.php');
require('inc/db.class.php');


if(!$song_id) error_quit3("�Ƿ�����!", 0);

if(isset($submit)) {

 if(strlen($geci)<100) error_quit3("��ĸ��Ҳ̫���˰�~");
 if(!$tigong) $tigong = 'guest';

 if(is_admin()) { //����Աֱ�Ӽӵ�����
   $db->query("INSERT INTO geci VALUES('$song_id', '$zuoqu', '$tianci', '$tigong', '$geci')");
   $db->query("UPDATE song SET geci = '1' WHERE song_id = '$song_id'");
 } else {
   $db->query("INSERT INTO tmpgeci (zuoqu, tianci, tigong, geci, song_name, song_id) VALUES ('$zuoqu', '$tianci', '$tigong', '$geci', '$song_name', '$song_id')");
 }
 html_head("��Ӹ�ʳɹ�");
 print "
	 <br><br><br>
	 <p align=center><span class=okmsg>��ӳɹ���</span> ��л���Ĳ��룡</p>
	 <p align=center> $message[0] </p>
	 <script>setTimeout('window.close()', 2000);</script>
 ";
} else {
	
	session_start();

	html_head("���á�".$song_name."���ĸ��");
	print "
	<br>
	<table width=96% bgcolor='#000000' align=center cellPadding=2 cellSpacing=1 height=95%>
	<tr height=20 bgcolor=$message[2]><td>Ϊ".$song_name."��Ӹ��</td></tr>
	<tr width=100%><td background='images/bg1.gif'>
		<table align=center>
		  <tr><form action=\"".$PHP_SELF."\" method=\"post\"><td align=left>
		  ����: <input type=\"text\" size=\"15\" name=\"tigong\" value=\"".$m_user[user_name]."\" class=\"input\"><br>
		  ����: <input type=\"text\" size=\"15\" name=\"tianci\" class=\"input\">
		  ����: <input type=\"text\" name=\"zuoqu\" size=\"15\" class=\"input\"><br>
	      <input type=\"hidden\" name=\"song_id\" value=\"".$song_id."\">
		  <input type=\"hidden\" name=\"song_name\" value=\"".$song_name."\">
	      <textarea cols=\"55\" rows=\"18\" class=\"input\" name=\"geci\"></textarea><br><br>
	      &nbsp;&nbsp;&nbsp;&nbsp;<input type=\"submit\" name=\"submit\" value=\"ȷ��\" class=\"button\" onclick=\"this.blur()\">
		  &nbsp;&nbsp;<input type=\"reset\" value=\"����\" class=\"button\" onclick=\"this.blur()\">
		  (��ʱ༭֧��Enter����BBcode)</td></form></tr>
	     </table>
	 </td></tr>
	 <tr bgcolor=$message[3] align=center><td>$message[1] $message[0]</td></tr>
	 </table>
	";
}

print "</body></html>";
$db->close();
exit;
?>