<?php
//�����ļ�,public.php
//��ʾͷ���ͱ���
function html_head($title)
{
	global $site_name;
	global $site_addr;
	$title = $title." - ".$site_name;
	print "
<html>
<head>
<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">
<link rel=\"shortcut icon\" href=\"images/fav.ico\">
<link rel=\"stylesheet\" type=\"text/css\" href=\"".$site_addr."style.css\">
<title>".$title."</title>
<script language=javascript src=\"music.js\"></script>
</head>
<body topmargin=0>
        ";
}
//��ǰλ��
function now_pos($arg1,$arg2) 
{
	global $type, $keyword;
	$$type = " selected";

	$target = "_self";
	if(!$keyword) {
		$keyword = "�������ѯ�Ĺؼ���";
		$target = "_blank";
	}

	if($arg2 == '') $arg2 = "�� �����ز���װ <a href=util/rp8-cn-setup.exe>RealPlayer</a>����";

	print <<<__EOF__
   <div align=center><center>
   <table width=760 align=center border=0 bgcolor=#D6E3FF>
    <tr>
     <td>��ǰλ�ã�$arg1</td>
     <td align=right>$arg2</td>
     </tr>	
    </table>
    <!-- ���� ���� -->
    <table border=0 cellpadding=0 cellspacing=0 width=760 height=21 background='images/bg1.gif' align=center>
	<tr><td width=160 bgcolor=#D6E3FF></td>
	<td width=22 bgcolor=#D6E3FF><img src='images/guodu.gif' width=22 height=22></td>
	<form method=post action="search.php" onSubmit="if(this.keyword.value == '') {alert('�ؼ��ֲ���Ϊ��'); return false;}" target='$target'>
	<td width=578>
	<select name=type>
		<option value='song'$song>����</option>
		<option value='singer'$singer>����</option>
		<option value='cd'$cd>ר��</option>
	</select>
	<input type=text name=keyword size=20 maxlength=40 value="$keyword" onFocus="this.value=''" style="height: 20px; width: 140px; color: #000000; background-image: url('images/bg1.gif'); border-style: solid; border-width: 1">
	<input type=image src='images/search.gif' align=absmiddle width=66 height=21 title='����' name=submit>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</td></form></tr>
	</table>
	</center></div>
__EOF__;
}

function table_patch($i, $max = 3) {
	$w = intval(100/$max);
	settype($w, "string");
	$w .= "%";

	if($i == 0)
	print "</table>\n";
	else if($i > 0) {
		$i = $max - $i;
		while($i--)
		print "<td width='$w'></td>\n";
		print "</tr></table>\n";
	}
}
//�Ƿ��¼
function islogin(){
	global $m_online_tag;
	global $m_user;
	global $m_user_id;
	global $db;

	session_start();
	if($m_online_tag == session_id()) return 1;

	if(isset($m_user_id)) { //�Ƿ��б����˵�sessionid ?
		$m_online_tag = session_id(); // ȡ�õ�ǰ��SessionID

		$m_user = $db->query_first("SELECT * FROM user WHERE user_id = '$m_user_id'");
		if($m_user_id != $m_user[user_id]) return 0;

		//update the datafile
		$lastfrom = getenv("REMOTE_ADDR");
		$lastlogin = date("Y-m-d H:i:s");
		$db->query("UPDATE user SET numlogin = numlogin+1,lastlogin = '$lastlogin', lastfrom = '$lastfrom' WHERE user_id = '$m_user[user_id]'");

		//update cookie
		setcookie('m_user_id', $m_user_id, time()+3156000); // ����SessionID��Cookie��
		session_register("m_user");
		session_register("m_online_tag");
		return 1;
	}
	else
	return 0;
}
//����Ƿ�Ϊ����Ա
function is_admin() { 
	@session_start();
	global $m_user;
	global $adminname;

	if(in_array($m_user[user_name], $adminname))
	return 1;
	else return 0;
}
//�Ƿ��ַ�
function checkword($str){ 
	global $badword;
	$str = eregi_replace("[ '\"]","", $str);
	if(in_array($str, $badword)) $err = $str."���Ƿ��ַ�";
	return $err;
}
//��������˳�
function error_quit($errmsg) { 
	global $message;
	global $site_name;
	global $site_addr;
	html_head("������");
	include('./inc/head.inc.php');
	now_pos("<font color=red><b>������!</b></font>","");
	print "<br><br><center><b>������Ϣ</b>: $errmsg <br>\n".$message[1]."</center>\n";
	include('inc/foot.inc.php');
	exit;
}
//��;�����˳�
function error_quit2($errmsg) { 
	global $message;
	global $site_name;
	global $site_addr;
	global $db;

	if($db) $db->close();

	print "
	 <script language=javascript>
	 function gotoIndex(){
	   document.location = 'index.php';
	   return true;
	 }
	 setTimeout('gotoIndex()', 3000);
    </script>
	<br><br>
	<center>
	<font color=red><b>������Ϣ</b></font>: $errmsg <br>
	$message[1]
	</center>
	";

	include('inc/foot.inc.php');
	exit;
}
//С���˳�
function error_quit3($errmsg, $mode = 1) { 
	global $message;
	global $site_name;
	global $site_addr;
	global $db;

	if($db) $db->close();
	html_head("������");

	if($mode)
	{
		print "
	 <script language=javascript>
	  setTimeout('window.close()', 3000);
	  //opener.document.location = 'index.php';
    </script>";
	}

	print "
	 <br><br>
	 <center>
	 <font color=red><b>������Ϣ</b></font>: $errmsg <br><br>
	 $message[1] $message[0]
	 </center>
	 </body></html>
	";
	exit;
}
//���ı��� method:1 ����, method 0:����,2 �޸�����?ȥ��ǩ��
function quote_code($qtcode, $method = 0) {
	$qtcode = str_replace("\r", "", $qtcode);
	$lines = explode("\n", $qtcode);
	$qtcode = "";

	foreach($lines as $tmpline) {
		settype($tmpline, "string");
		if($method == 1) { // ����
			$chk = ltrim($tmpline);
			if(ereg('^--', $tmpline)) break; // ǩ��������Դ
			else if(empty($chk)) continue; // ����
			else if(ereg(':|��', $tmpline)) continue; //������� ��� (:��ͷ)
			else $qtcode .= ":".$tmpline."\r\n";
		} else if($method == 2) { //�޸�����ʱ��
			if(ereg('^--', $tmpline)) break;
			$qtcode .= $tmpline."\r\n";
		} else {
			if($tmpline[0] == ':')
			$qtcode .= "<font color=666666>".$tmpline."</font>\r\n";
			else
			$qtcode .= $tmpline."\r\n";
		}
	}
	//ok, return the value
	return $qtcode;
}
//BB���룬֧�ֻس�������
function bbcode($bbcode) {
	$bbcode = str_replace("<","&lt;",$bbcode);
	$bbcode = str_replace(">","&gt;",$bbcode);
	$bbcode = str_replace("\"","&quot;",$bbcode);
	$bbcode = str_replace("\r","",$bbcode);
	$bbcode = str_replace("\n> ","<br>&lt;",$bbcode);
	$bbcode = str_replace("\n","<br>\r\n",$bbcode);
	$bbcode = str_replace("  ","&nbsp;&nbsp;",$bbcode);


	// do [url]xxx[/url]
	$bbcode=eregi_replace("\\[url\\]www.([^\\[]*)\\[/url\\]","<a href=\"http://www.\\1\" target=_blank>\\1</a>",$bbcode);
	$bbcode=eregi_replace("\\[url\\]([^\\[]*)\\[/url\\]","<a href=\"\\1\" target=_blank>\\1</a>",$bbcode);

	// change neccessary &quot; back to "
	$bbcode=eregi_replace("\\[url=\&quot;","[url=\"",$bbcode);
	$bbcode=eregi_replace("\\&quot;\\]","\"]",$bbcode);
	// do [url="xxx"]yyy[/url]
	$bbcode=eregi_replace("\\[url=\"([^\"]*)\"\\]([^\\[]*)\\[\\/url\\]","<a href=\"\\1\" target=_blank>\\2</a>",$bbcode);
	$bbcode=eregi_replace("\\[url=([^\"]*)\\]([^\\[]*)\\[\\/url\\]","<a href=\"\\1\" target=_blank>\\2</a>",$bbcode);

	// do [email]xxx[/email]
	$bbcode=eregi_replace("\\[email\\]([^\\[]*)\\[/email\\]","<a href=\"mailto:\\1\">\\1</a>",$bbcode);
	$bbcode=eregi_replace("\\[email=([^\\[]*)]([^\\[]*)\\[/email\\]","<a href=\"mailto:\\1\">\\2</a>",$bbcode);

	// do [b]xxxx[/b]
	$bbcode=eregi_replace("\\[b\\]([^\\[]*)\\[/b\\]","<b>\\1</b>",$bbcode);
	$bbcode=eregi_replace("\\[i\\]([^\\[]*)\\[/i\\]","<i>\\1</i>",$bbcode);

	// do [img]xxxx[/img]
	$bbcode=eregi_replace("\\[img\\]([^\\[]*)\\[/img\\]","<img src=\"\\1\">",$bbcode);
	$bbcode=eregi_replace("\\[img=([^\\[]*)]([^\\[]*)\\[/img\\]","<img src=\"\\1\" align=\"\\2\">",$bbcode);

	return $bbcode;
}
//email���
function emailcheck($email){
	$ret=false;
	if(strstr($email, '@') && strstr($email, '.')){
		if(eregi("^([_a-z0-9]+([\\._a-z0-9-]+)*)@([a-z0-9]{2,}(\\.[a-z0-9-]{2,})*\\.[a-z]{2,3})$", $email)){
			$ret=true;
		}
	}
	return $ret;
}
//����ͼƬ
function redefine_imagesize($image){
	$size = GetImageSize($image);
	if(($size[0] >= $size[1]) && ($size[0] > 150)) $size_msg="width=150";//����ڸ�!
	else if($size[1] > 150) $size_msg="height=150";
	else $size_msg="";
	return $size_msg;
}
?>