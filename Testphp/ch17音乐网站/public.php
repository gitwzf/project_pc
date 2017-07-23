<?php
//显示头部和标题
function html_head($title){
	global $site_name;
	global $site_addr;
	$title = $title." - ".$site_name;
	echo "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">"
		."<link rel=\"shortcut icon\" href=\"images/fav.ico\">"
		."<link rel=\"stylesheet\" type=\"text/css\" href=\"".$site_addr."style.css\">"
		."<title>".$title."</title>"
		."<script language=javascript src=\"music.js\"></script>"
		."</head><body topmargin=0>";
}
//当前位置
function now_pos($arg1,$arg2){
	global $type, $keyword;
	$$type = " selected";
	$target = "_self";
	if(!$keyword) {
		$keyword = "输入想查询的关键字";
		$target = "_blank";
	}
	if($arg2 == '') $arg2 = "★ 请下载并安装 <a href=util/rp8-cn-setup.exe>RealPlayer</a>听歌";
	print <<<__EOF__
	   <div align=center><center>
	   <table width=760 align=center border=0 bgcolor=#D6E3FF>
	    <tr>
	     <td>当前位置：$arg1</td>
	     <td align=right>$arg2</td>
	     </tr>	
	    </table>
	    <!-- 过渡 搜索 -->
	    <table border=0 cellpadding=0 cellspacing=0 width=760 height=21 background='images/bg1.gif' align=center>
		<tr><td width=160 bgcolor=#D6E3FF></td>
		<td width=22 bgcolor=#D6E3FF><img src='images/guodu.gif' width=22 height=22></td>
		<form method=post action="search.php" onSubmit="if(this.keyword.value == '') {alert('关键字不能为空'); return false;}" target='$target'>
		<td width=578>
		<select name=type>
			<option value='song'>歌曲</option>
			<option value='singer'>歌手</option>
			<option value='cd'>专辑</option>
		</select>
		<input type=text name=keyword size=20 maxlength=40 value="$keyword" onFocus="this.value=''" style="height: 20px; width: 140px; color: #000000; background-image: url('images/bg1.gif'); border-style: solid; border-width: 1"><input type='submit' value='搜索' name='search'>&nbsp;&nbsp;&nbsp;&nbsp;</td></form></tr></table></center></div>
__EOF__;
}
//表格
function table_patch($i, $max = 3){
	$w = intval(100/$max);
	settype($w, "string");
	$w .= "%";
	if($i == 0)
		echo "</table>\n";
	else if($i > 0) {
		$i = $max - $i;
		while($i--)
			echo "<td width='$w'></td>\n";
		echo "</tr></table>\n";
	}
}
//是否登录
function islogin(){
	global $m_online_tag;
	global $m_user;
	global $m_user_id;
	global $db;
	session_start();
	if($m_online_tag == session_id()) 
		return 1;
	if(isset($m_user_id)) {
		$m_online_tag = session_id(); //取得当前的SessionID
		$m_user = $db->query_first("SELECT * FROM user WHERE user_id = '$m_user_id'");
		if($m_user_id != $m_user[user_id]) return 0;
		//准备更新文件
		$lastfrom = getenv("REMOTE_ADDR");
		$lastlogin = date("Y-m-d H:i:s");
		$db->query("UPDATE user SET numlogin = numlogin+1,lastlogin = '$lastlogin', lastfrom = '$lastfrom' WHERE user_id = '$m_user[user_id]'");
		//更新cookie
		setcookie('m_user_id', $m_user_id, time()+3156000);
		session_register("m_user");
		session_register("m_online_tag");
		return 1;
	}else
		return 0;
}
//设置头像
function get_face($face, $sex) {
	if($face >= 0) 
		return $face;
	elseif($sex == '葛格') 
		return 2;
	elseif($sex == '底迪') 
		return 91;
	elseif($sex == '解结') 
		return 95;
	elseif($sex == '美眉') 
		return 47;
}
//计算经验值, 上站次数 + 听歌次数 + 发表次数*2 numlogin + numlisten + numpost * 2
function count_exp(){
	global $m_user;
	//ssession_start();
	$now = date(U);
	if($m_user[firstlogin] <= 0) $m_user[firstlogin] = $now;
	$exp = intval($m_user[numlogin]/* + $m_user[numlisten] */+ $m_user[numpost] * 2 + ($now - $m_user[firstlogin])/(3*86400));
	return $exp;
}
//根据经验积分给出等级名称
function count_class($exp){
	if($exp < 1) return "音乐游客";
	elseif($exp < 100) return "新手上路";
	elseif($exp < 450) return "一般发烧";
	elseif($exp < 850) return "中级发烧";
	elseif($exp < 1500) return "高级发烧";
	elseif($exp < 2500) return "长老级";
	elseif($exp < 3500) return "元老级";
	else return "开国大老";
}
//检查是否为管理员
function is_admin() { 
	@session_start();
	global $m_user;
	global $adminname;
	if(in_array($m_user[user_name], $adminname))
	return 1;
	else return 0;
}
//非法字符
function checkword($str){ 
	global $badword;
	$str = eregi_replace("[ '\"]","", $str);
	if(in_array($str, $badword)) $err = $str."含非法字符";
	return $err;
}
//错误退出
function error_quit($errmsg) { 
	global $message;
	global $site_name;
	global $site_addr;
	html_head("错误发生");
	include('./inc/head.inc.php');
	now_pos("<font color=red><b>错误发生!</b></font>","");
	echo "<br><br><center><b>错误信息</b>: $errmsg <br>\n".$message[1]."</center>\n";
	include_once('inc/foot.inc.php');
	exit;
}
//中途错误退出
function error_quit2($errmsg) { 
	global $message;
	global $site_name;
	global $site_addr;
	global $db;
	if($db) $db->close();
	echo "<script language=javascript>\n
			 function gotoIndex(){\n
			   document.location = 'index.php';\n
			   return true;\n
			 }\n
			 setTimeout('gotoIndex()', 3000);\n
		    </script>\n
			<br><br>\n
			<center>\n
			<font color=red><b>错误信息</b></font>: $errmsg <br>\n
			$message[1]\n
			</center>\n";
	include_once('inc/foot.inc.php');
	exit;
}
//全局出错退出
function error_quit3($errmsg, $mode = 1) { 
	global $message;
	global $site_name;
	global $site_addr;
	global $db;
	if($db) $db->close();
	html_head("错误发生");
	if($mode){
		echo "
	 <script language=javascript>
	  setTimeout('window.close()', 3000);
	  //opener.document.location = 'index.php';
    </script>";
	}
	echo "
	 <br><br>
	 <center>
	 <font color=red><b>错误信息</b></font>: $errmsg <br><br>
	 $message[1] $message[0]
	 </center>
	 </body></html>";
	exit;
}
//引文编码 method:1 编码, method 0:解码,2 修改文章?去掉签名
function quote_code($qtcode, $method = 0) {
	$qtcode = str_replace("\r", "", $qtcode);
	$lines = explode("\n", $qtcode);
	$qtcode = "";
	foreach($lines as $tmpline) {
		settype($tmpline, "string");
		if($method == 1) { //编码
			$chk = ltrim($tmpline);
			if(ereg('^--', $tmpline)) break; //签名档，来源
			else if(empty($chk)) continue; // 空行
			else if(ereg(':|【', $tmpline)) continue; //清除引文 标记 (:开头)
			else $qtcode .= ":".$tmpline."\r\n";
		} else if($method == 2) { //修改文章时用
			if(ereg('^--', $tmpline)) break;
			$qtcode .= $tmpline."\r\n";
		} else {
			if($tmpline[0] == ':')
			$qtcode .= "<font color=666666>".$tmpline."</font>\r\n";
			else
			$qtcode .= $tmpline."\r\n";
		}
	}
	return $qtcode;
}
//BB代码，支持回车、换行
function bbcode($bbcode) {
	$bbcode = str_replace("<","&lt;",$bbcode);
	$bbcode = str_replace(">","&gt;",$bbcode);
	$bbcode = str_replace("\"","&quot;",$bbcode);
	$bbcode = str_replace("\r","",$bbcode);
	$bbcode = str_replace("\n> ","<br>&lt;",$bbcode);
	$bbcode = str_replace("\n","<br>\r\n",$bbcode);
	$bbcode = str_replace("  ","&nbsp;&nbsp;",$bbcode);
	//设置[url]xxx[/url]
	$bbcode=eregi_replace("\\[url\\]www.([^\\[]*)\\[/url\\]","<a href=\"http://www.\\1\" target=_blank>\\1</a>",$bbcode);
	$bbcode=eregi_replace("\\[url\\]([^\\[]*)\\[/url\\]","<a href=\"\\1\" target=_blank>\\1</a>",$bbcode);
	$bbcode=eregi_replace("\\[url=\&quot;","[url=\"",$bbcode);
	$bbcode=eregi_replace("\\&quot;\\]","\"]",$bbcode);
	//设置[url="xxx"]yyy[/url]
	$bbcode=eregi_replace("\\[url=\"([^\"]*)\"\\]([^\\[]*)\\[\\/url\\]","<a href=\"\\1\" target=_blank>\\2</a>",$bbcode);
	$bbcode=eregi_replace("\\[url=([^\"]*)\\]([^\\[]*)\\[\\/url\\]","<a href=\"\\1\" target=_blank>\\2</a>",$bbcode);
	//设置[email]xxx[/email]
	$bbcode=eregi_replace("\\[email\\]([^\\[]*)\\[/email\\]","<a href=\"mailto:\\1\">\\1</a>",$bbcode);
	$bbcode=eregi_replace("\\[email=([^\\[]*)]([^\\[]*)\\[/email\\]","<a href=\"mailto:\\1\">\\2</a>",$bbcode);
	//设置[b]xxxx[/b]
	$bbcode=eregi_replace("\\[b\\]([^\\[]*)\\[/b\\]","<b>\\1</b>",$bbcode);
	$bbcode=eregi_replace("\\[i\\]([^\\[]*)\\[/i\\]","<i>\\1</i>",$bbcode);
	//设置[img]xxxx[/img]
	$bbcode=eregi_replace("\\[img\\]([^\\[]*)\\[/img\\]","<img src=\"\\1\">",$bbcode);
	$bbcode=eregi_replace("\\[img=([^\\[]*)]([^\\[]*)\\[/img\\]","<img src=\"\\1\" align=\"\\2\">",$bbcode);
	return $bbcode;
}
//email检测
function emailcheck($email){
	$ret=false;
	if(strstr($email, '@') && strstr($email, '.')){
		if(eregi("^([_a-z0-9]+([\\._a-z0-9-]+)*)@([a-z0-9]{2,}(\\.[a-z0-9-]{2,})*\\.[a-z]{2,3})$", $email)){
			$ret=true;
		}
	}
	return $ret;
}
//定义图片
function redefine_imagesize($image){
	$size = GetImageSize($image);
	if(($size[0] >= $size[1]) && ($size[0] > 150)) $size_msg="width=150";//宽大于高
	else if($size[1] > 150) $size_msg="height=150";
	else $size_msg="";
	return $size_msg;
}
?>