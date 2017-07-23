<?php
//修改删除操作,可以修改歌手、专辑和歌曲，modify.php
require_once('../config.php');
require_once('../public.php');
require_once('../inc/db.class.php');
if(!is_admin())  error_quit3("错误，您不是管理员或还没有登录！");
//错误时通过JS返回
function error_return($msg) { 
	echo "<script language=javascript> alert('".$msg."'); window.history.go(-1);</script>";
	exit;
}
html_head("修改资料");
if(isset($step3)){	//最后一步
	if(!$singer_id && !$song_id && !$cd_id) error_return('调用错误!');
	if($type == 'singer'){
		if($singer_name == '' || $cate_id == '' || $area_id == '' || $do == '') error_return('填写不完整');
		if($imgurl == 'http://') $imgurl = '';
		$singer_name = urldecode($singer_name);
		if($do == 'edit'){
			$db->query("UPDATE singer SET alpha = '$alpha', singer_name = '$singer_name', cate_id = '$cate_id', area_id = '$area_id', imgurl = '$imgurl', introduce = '$introduce' WHERE singer_id = '$singer_id'");
		}else if($do == 'del'){
			$db->query("DELETE FROM singer WHERE singer_id = '$singer_id'"); //删资料
			$db->query("DELETE FROM cd WHERE singer_id = '$singer_id'"); //删专辑
			$db->query("DELETE FROM song WHERE singer_id = '$singer_id'"); //删歌
		}else{
			error_return('调用错误！');
		}
	}else if($type == 'song'){
		if($song_name == '') error_return('歌名要填写!');
		if($rm_url == 'http://' || $rm_url =='') error_return('试听地址必须填写!');
		//mp3 url处理
		for($i=1; $i<6; $i++){
			$str = "mp3".$i;
			if($$str != 'http://' && $$str != '') $mp3_url .= $$str.'|';
		}
		if($do == 'edit'){
			//song_name, rm_url, mp3_url, geci, cd_id, zuoqu, tianci, singer_id
			$geci1 = 1;
			if(empty($geci)) $geci1 = 0;
			$db->query("UPDATE song SET song_name = '$song_name', rm_url = '$rm_url', mp3_url = '$mp3_url', geci = '$geci1' WHERE song_id = '$song_id'");
			$db->query("UPDATE geci SET zuoqu = '$zuoqu', tianci = '$tianci', geci = '$geci' WHERE song_id = '$song_id'");
		}else if($do == 'del'){
			$db->query("DELETE FROM song WHERE song_id = '$song_id'"); //删除
			$db->query("DELETE FROM geci WHERE song_id = '$song_id'"); //歌词
			$db->query("UPDATE cd SET song_num=song_num-1 WHERE cd_id = '$cd_id'"); //刷新数据
			$db->query("UPDATE singer SET song_num=song_num-1 WHERE singer_id = '$singer_id'");
		}else {
			error_return('调用错误！');
		}
	}else if($type == 'cd'){
		if($cd_name == '') error_return('专辑名要填写!');
		if($imgurl == 'http://') $imgurl = '';
		if($do == 'edit'){
			//cd_name, imgurl, introduce, lang, pub_date, add_date, singer_id
			$db->query("UPDATE cd SET cd_name = '$cd_name', imgurl = '$imgurl', introduce = '$introduce', lang = '$lang', pub_date = '$pub_date' WHERE cd_id = '$cd_id'");
		}else if($do == 'del'){
			$db->query("DELETE FROM song WHERE cd_id = '$cd_id'"); //删除
			$db->query("DELETE FROM cd WHERE cd_id = '$cd_id'"); //删除
			$db->query("UPDATE singer SET song_num=song_num-'$song_num', cd_num=cd_num-1 WHERE singer_id = '$singer_id'");//同上
		}else {
			error_return('调用错误！');
		}
	}
	echo "<p><span class=okmsg>操作成功!</span> <a href=main.php>按此返回</a></p>";
}else if(isset($step2)){
	if(!$singer_id && !$song_id && !$cd_id) error_return('调用错误!');
	if($type == 'singer'){
		$res = $db->query_first("SELECT * FROM singer WHERE singer_id = '$singer_id'");
		$cate = $db->query("SELECT * FROM cate");
		$area = $db->query("SELECT * FROM area");
		echo "<h3>修改歌手资料: $res[singer_name] </h3>";
		echo "<form action=$PHP_SELF method=post>
         <input type=hidden name=step3 value=1>
		 <input type=hidden name=type value='singer'>
		 <input type=hidden name=singer_id value='$singer_id'>
		 拼音首字： <select name=alpha size=1>";
		$a = "a";
		for(;;){
			$a = strval($a);
			echo "<option value='$a' ";
			if($res[alpha] == $a) echo 'selected';
			echo ">$a</option>\n";
			if($a == "z") break;
			$a++;
		}echo "</select> (请选择，即为歌手的拼音首字母，方便排序) <br>
		 歌手姓名： <input type=text name=singer_name maxlength=40 size=15 value='$res[singer_name]'>	<br>
		 所属类别： <select name=cate_id size=1>";
		while($tmp = $db->fetch_array($cate)){
			echo "<option value='$tmp[cate_id]' ";
			if($tmp[cate_id] == $res[cate_id]) echo 'selected';
			echo "> $tmp[cate_name] </option>\n";
		}
		echo "</select>(请选择)<br>所在地区 : <select name=area_id size=1>";
		while($tmp = $db->fetch_array($area)){
			echo "<option value='$tmp[area_id]' ";
			if($tmp[area_id] == $res[area_id]) echo 'selected';
			echo "> $tmp[area_name] </option>\n";
		}
		echo "</select>(请选择)<br>
		 头像链接：<input type=text name=imgurl maxlength=200 size=40 value='$res[imgurl]'>(没有请留空, 以<font color=red>http://</font>开头) <br>歌手简介：<textarea name=introduce rows=12 cols=50>$res[introduce]</textarea>(不支持HTML)<br>
		 <p align=left>
		 结果处理： <input type=radio name=do value='edit' checked>修改
		 <input type=radio name=do value='del'><font color=red>删除</font>(小心，会删除该歌手的所有歌曲、专辑)
		 <input type=submit name=submit value='提交'>
		 <input type=reset value='重写'>
		 </p></form>";
	}else if($type == 'song') {//曲目
		$res = $db->query_first("SELECT S.song_name, S.rm_url, S.mp3_url, S.cd_id, S.singer_id, T.zuoqu, T.tianci, T.geci FROM song S, geci T WHERE S.song_id = '$song_id' AND S.song_id = T.song_id");
		$mp = split('\|', $res[mp3_url]);
		$tmp = $mp[0];
		$i = 1;
		while($tmp){
			$str = "mp3".$i;
			$$str = $tmp;
			$tmp = next($mp);
			$i++;
			if($i > 5) break;
		}
		$singer_name = urldecode($singer_name);
		echo "<h3>修改歌曲资料: $singer_name 》$res[song_name]</h3>";
		echo "<form action=$PHP_SELF method=post>
		 <input type=hidden name=step3 value='1'>
		 <input type=hidden name=type value='song'>
		 <input type=hidden name=song_id value='$song_id'>
		 <input	type=hidden name=singer_id value='$res[singer_id]'>
		 <input type=hidden name=cd_id value='$res[cd_id]'>
		 歌曲名称：<input type=text name=song_name maxlength=40 size=25 value='$res[song_name]'> 	<br>
		 试听地址：<input type=text name=rm_url size=35 value='$res[rm_url]'> (支持real/mp3格式,http://开头)<br>
		 MP3下载1：<input type=text name=mp31 size=35 value='$mp31'> (http:// 开头喔) <br>
		 MP3下载2：<input type=text name=mp32 size=35 value='$mp32'> (http:// 开头喔) <br>
		 MP3下载3：<input type=text name=mp33 size=35 value='$mp33'> (http:// 开头喔) <br>
		 MP3下载4：<input type=text name=mp34 size=35 value='$mp34'> (http:// 开头喔) <br>
		 MP3下载5：<input type=text name=mp35 size=35 value='$mp35'> (http:// 开头喔) <br>
		 谱 曲 者：<input type=text name=zuoqu size=20 value='$res[zuoqu]'> <br>
		 作 词 者：<input type=text name=tianci size=20 value='$res[tianci]'> <br>
		 歌词内容：<textarea name=geci rows=12 cols=50>$res[geci]</textarea>(不支持HTML)<br>
		 <p align=left>
		 结果处理：<input type=radio name=do value='edit' checked>修改
		 <input type=radio name=do value='del'>删除
		 <input type=submit name=submit value='提交'>
		 <input type=reset value='重写'>
		 </p></form>";
	}else if($type == 'cd') { //专辑
		$res = $db->query_first("SELECT * FROM cd WHERE cd_id = '$cd_id'");
		$singer_name = urldecode($singer_name);
		echo "<h3>修改专辑资料: $singer_name 》$res[cd_name]</h3>";
		echo "<form action=$PHP_SELF method=post>
		 <input type=hidden name=step3 value='1'>
		 <input type=hidden name=type value='cd'>
		 <input type=hidden name=cd_id value='$cd_id'>
		 <input	type=hidden name=singer_id value='$res[singer_id]'>
		 <input type=hidden name=song_num value='$res[song_num]'>
		 专辑名称：<input type=text name=cd_name maxlength=40 size=15 value='$res[cd_name]'> 	<br>
		 语言种类：<input type=text name=lang size=15 value='$res[lang]'> (英文/中文/台语/越语 etc)<br>
		 发行时间：<input type=text name=pub_date size=15 value='$res[pub_date]'> (如: 1999-12-23) <br>
		 专辑封面：<input type=text name=imgurl maxlength=200 size=40 value='$res[imgurl]'>(没有请留空, 以<font color=red>http://</font>开头) <br>
		 专辑简介：<textarea name=introduce rows=4 cols=50>$res[introduce]</textarea>(不支持HTML)<br>
		 <p align=left>
		 结果处理：<input type=radio name=do value='edit' checked>修改
		 <input type=radio name=do value='del'><font color=red>删除</font>(小心，会删除该专辑下的所有歌曲)
		 <input type=submit name=submit value='提交'><input type=reset value='重写'>
		 </p></form>";
	}
}else if(isset($step1)){
	$keyword = urldecode($keyword); //decode
	if($keyword == 'all') $keyword = '';
	if($type == 'song'){
		$res = $db->query("SELECT S.song_id, S.song_name, S.singer_id, S2.singer_name  FROM song S, singer S2 WHERE S.song_name LIKE '%$keyword%' AND S2.singer_id = S.singer_id ORDER BY S.singer_id");
		echo "<p>结果共找到".$db->num_rows($res)."条记录</p>";
		while($tmp = $db->fetch_array($res)){
			echo "<img src=../images/dot2.gif><a href='$PHP_SELF?step2=1&type=$type&keyword="
			.urlencode($keyword)."&song_id=$tmp[song_id]&singer_name=".urlencode($tmp[singer_name])
			."'>$tmp[song_name]</a>(<a href='$PHP_SELF?step2=1&type=singer&singer_id=$tmp[singer_id]'>$tmp[singer_name]</a>) <br>";
		}
	}else if($type == 'singer'){
		$res = $db->query("SELECT singer_id, singer_name  FROM singer WHERE singer_name LIKE '%$keyword%' ORDER BY alpha");
		echo "<h3>结果共找到".$db->num_rows($res)."条记录, 点击修改</h3>";
		$i = 1;
		while($tmp = $db->fetch_array($res)){
			echo "<img src=../images/dot2.gif><a href='$PHP_SELF?step2=1&type=$type&keyword="
				.urlencode($keyword)."&singer_id=$tmp[singer_id]'>$tmp[singer_name]</a>";
			if($i == 5) { $i=1; echo "<br>\n"; }
			else {$i++; echo "、";}
		}
	}else if($type == 'cd'){//CD
		$res = $db->query("SELECT C.cd_id, C.cd_name, S.singer_id, S.singer_name FROM cd C, singer S WHERE C.cd_name LIKE '%$keyword%' AND S.singer_id = C.singer_id ORDER BY C.singer_id");
		echo "<h3>结果共找到".$db->num_rows($res)."条记录, 点击修改</h3>";
		while($tmp = $db->fetch_array($res)){
			echo "<img src=../images/dot2.gif><a href='$PHP_SELF?step2=1&type=$type&keyword="
				.urlencode($keyword)."&cd_id=$tmp[cd_id]&singer_name=".urlencode($tmp[singer_name])
				."'>$tmp[cd_name]</a>(<a href='$PHP_SELF?step2=1&type=singer&keyword="
				.urlencode($keyword)."&singer_id=$tmp[singer_id]'>$tmp[singer_name])</a> <br>";
		}
	}
}
?>
<script language=javascript>
	function checkform4(){
	     keyword =window.form4.keyword.value;
	     if(keyword == '')
	        { 
			 alert('关键字不能为空');
	         return false; 
	        }  
	}
	</script>
<hr size=1 noshae>
<h3>音乐管理 -- 删除/修改</h3></TD></TR>
<form action='<?php echo $PHP_SELF;?>' name=form4 method=post onsubmit='return checkform4();'>
关键字: <input type=text name=keyword size=18 maxlength=30 value='<?php echo $keyword;?>'><br>		
<input type=radio name=type value=song <?php if(!$type) $type='song'; if($type == song) echo 'checked';?>>歌名
<input type=radio name=type value=singer <?php if($type == 'singer') echo 'checked';?>>歌手
<input type=radio name=type value=cd <?php if($type == 'cd') echo 'checked';?>>专辑
<input type=submit name=step1 value=查找>
</form>
<?php
echo "</body></html>";
$db->close();
exit;
?>