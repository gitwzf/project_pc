<?php
//�޸�ɾ������,�����޸ĸ��֡�ר���͸�����modify.php
require_once('../config.php');
require_once('../public.php');
require_once('../inc/db.class.php');
if(!is_admin())  error_quit3("���������ǹ���Ա��û�е�¼��");
//����ʱͨ��JS����
function error_return($msg) { 
	echo "<script language=javascript> alert('".$msg."'); window.history.go(-1);</script>";
	exit;
}
html_head("�޸�����");
if(isset($step3)){	//���һ��
	if(!$singer_id && !$song_id && !$cd_id) error_return('���ô���!');
	if($type == 'singer'){
		if($singer_name == '' || $cate_id == '' || $area_id == '' || $do == '') error_return('��д������');
		if($imgurl == 'http://') $imgurl = '';
		$singer_name = urldecode($singer_name);
		if($do == 'edit'){
			$db->query("UPDATE singer SET alpha = '$alpha', singer_name = '$singer_name', cate_id = '$cate_id', area_id = '$area_id', imgurl = '$imgurl', introduce = '$introduce' WHERE singer_id = '$singer_id'");
		}else if($do == 'del'){
			$db->query("DELETE FROM singer WHERE singer_id = '$singer_id'"); //ɾ����
			$db->query("DELETE FROM cd WHERE singer_id = '$singer_id'"); //ɾר��
			$db->query("DELETE FROM song WHERE singer_id = '$singer_id'"); //ɾ��
		}else{
			error_return('���ô���');
		}
	}else if($type == 'song'){
		if($song_name == '') error_return('����Ҫ��д!');
		if($rm_url == 'http://' || $rm_url =='') error_return('������ַ������д!');
		//mp3 url����
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
			$db->query("DELETE FROM song WHERE song_id = '$song_id'"); //ɾ��
			$db->query("DELETE FROM geci WHERE song_id = '$song_id'"); //���
			$db->query("UPDATE cd SET song_num=song_num-1 WHERE cd_id = '$cd_id'"); //ˢ������
			$db->query("UPDATE singer SET song_num=song_num-1 WHERE singer_id = '$singer_id'");
		}else {
			error_return('���ô���');
		}
	}else if($type == 'cd'){
		if($cd_name == '') error_return('ר����Ҫ��д!');
		if($imgurl == 'http://') $imgurl = '';
		if($do == 'edit'){
			//cd_name, imgurl, introduce, lang, pub_date, add_date, singer_id
			$db->query("UPDATE cd SET cd_name = '$cd_name', imgurl = '$imgurl', introduce = '$introduce', lang = '$lang', pub_date = '$pub_date' WHERE cd_id = '$cd_id'");
		}else if($do == 'del'){
			$db->query("DELETE FROM song WHERE cd_id = '$cd_id'"); //ɾ��
			$db->query("DELETE FROM cd WHERE cd_id = '$cd_id'"); //ɾ��
			$db->query("UPDATE singer SET song_num=song_num-'$song_num', cd_num=cd_num-1 WHERE singer_id = '$singer_id'");//ͬ��
		}else {
			error_return('���ô���');
		}
	}
	echo "<p><span class=okmsg>�����ɹ�!</span> <a href=main.php>���˷���</a></p>";
}else if(isset($step2)){
	if(!$singer_id && !$song_id && !$cd_id) error_return('���ô���!');
	if($type == 'singer'){
		$res = $db->query_first("SELECT * FROM singer WHERE singer_id = '$singer_id'");
		$cate = $db->query("SELECT * FROM cate");
		$area = $db->query("SELECT * FROM area");
		echo "<h3>�޸ĸ�������: $res[singer_name] </h3>";
		echo "<form action=$PHP_SELF method=post>
         <input type=hidden name=step3 value=1>
		 <input type=hidden name=type value='singer'>
		 <input type=hidden name=singer_id value='$singer_id'>
		 ƴ�����֣� <select name=alpha size=1>";
		$a = "a";
		for(;;){
			$a = strval($a);
			echo "<option value='$a' ";
			if($res[alpha] == $a) echo 'selected';
			echo ">$a</option>\n";
			if($a == "z") break;
			$a++;
		}echo "</select> (��ѡ�񣬼�Ϊ���ֵ�ƴ������ĸ����������) <br>
		 ���������� <input type=text name=singer_name maxlength=40 size=15 value='$res[singer_name]'>	<br>
		 ������� <select name=cate_id size=1>";
		while($tmp = $db->fetch_array($cate)){
			echo "<option value='$tmp[cate_id]' ";
			if($tmp[cate_id] == $res[cate_id]) echo 'selected';
			echo "> $tmp[cate_name] </option>\n";
		}
		echo "</select>(��ѡ��)<br>���ڵ��� : <select name=area_id size=1>";
		while($tmp = $db->fetch_array($area)){
			echo "<option value='$tmp[area_id]' ";
			if($tmp[area_id] == $res[area_id]) echo 'selected';
			echo "> $tmp[area_name] </option>\n";
		}
		echo "</select>(��ѡ��)<br>
		 ͷ�����ӣ�<input type=text name=imgurl maxlength=200 size=40 value='$res[imgurl]'>(û��������, ��<font color=red>http://</font>��ͷ) <br>���ּ�飺<textarea name=introduce rows=12 cols=50>$res[introduce]</textarea>(��֧��HTML)<br>
		 <p align=left>
		 ������� <input type=radio name=do value='edit' checked>�޸�
		 <input type=radio name=do value='del'><font color=red>ɾ��</font>(С�ģ���ɾ���ø��ֵ����и�����ר��)
		 <input type=submit name=submit value='�ύ'>
		 <input type=reset value='��д'>
		 </p></form>";
	}else if($type == 'song') {//��Ŀ
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
		echo "<h3>�޸ĸ�������: $singer_name ��$res[song_name]</h3>";
		echo "<form action=$PHP_SELF method=post>
		 <input type=hidden name=step3 value='1'>
		 <input type=hidden name=type value='song'>
		 <input type=hidden name=song_id value='$song_id'>
		 <input	type=hidden name=singer_id value='$res[singer_id]'>
		 <input type=hidden name=cd_id value='$res[cd_id]'>
		 �������ƣ�<input type=text name=song_name maxlength=40 size=25 value='$res[song_name]'> 	<br>
		 ������ַ��<input type=text name=rm_url size=35 value='$res[rm_url]'> (֧��real/mp3��ʽ,http://��ͷ)<br>
		 MP3����1��<input type=text name=mp31 size=35 value='$mp31'> (http:// ��ͷ�) <br>
		 MP3����2��<input type=text name=mp32 size=35 value='$mp32'> (http:// ��ͷ�) <br>
		 MP3����3��<input type=text name=mp33 size=35 value='$mp33'> (http:// ��ͷ�) <br>
		 MP3����4��<input type=text name=mp34 size=35 value='$mp34'> (http:// ��ͷ�) <br>
		 MP3����5��<input type=text name=mp35 size=35 value='$mp35'> (http:// ��ͷ�) <br>
		 �� �� �ߣ�<input type=text name=zuoqu size=20 value='$res[zuoqu]'> <br>
		 �� �� �ߣ�<input type=text name=tianci size=20 value='$res[tianci]'> <br>
		 ������ݣ�<textarea name=geci rows=12 cols=50>$res[geci]</textarea>(��֧��HTML)<br>
		 <p align=left>
		 �������<input type=radio name=do value='edit' checked>�޸�
		 <input type=radio name=do value='del'>ɾ��
		 <input type=submit name=submit value='�ύ'>
		 <input type=reset value='��д'>
		 </p></form>";
	}else if($type == 'cd') { //ר��
		$res = $db->query_first("SELECT * FROM cd WHERE cd_id = '$cd_id'");
		$singer_name = urldecode($singer_name);
		echo "<h3>�޸�ר������: $singer_name ��$res[cd_name]</h3>";
		echo "<form action=$PHP_SELF method=post>
		 <input type=hidden name=step3 value='1'>
		 <input type=hidden name=type value='cd'>
		 <input type=hidden name=cd_id value='$cd_id'>
		 <input	type=hidden name=singer_id value='$res[singer_id]'>
		 <input type=hidden name=song_num value='$res[song_num]'>
		 ר�����ƣ�<input type=text name=cd_name maxlength=40 size=15 value='$res[cd_name]'> 	<br>
		 �������ࣺ<input type=text name=lang size=15 value='$res[lang]'> (Ӣ��/����/̨��/Խ�� etc)<br>
		 ����ʱ�䣺<input type=text name=pub_date size=15 value='$res[pub_date]'> (��: 1999-12-23) <br>
		 ר�����棺<input type=text name=imgurl maxlength=200 size=40 value='$res[imgurl]'>(û��������, ��<font color=red>http://</font>��ͷ) <br>
		 ר����飺<textarea name=introduce rows=4 cols=50>$res[introduce]</textarea>(��֧��HTML)<br>
		 <p align=left>
		 �������<input type=radio name=do value='edit' checked>�޸�
		 <input type=radio name=do value='del'><font color=red>ɾ��</font>(С�ģ���ɾ����ר���µ����и���)
		 <input type=submit name=submit value='�ύ'><input type=reset value='��д'>
		 </p></form>";
	}
}else if(isset($step1)){
	$keyword = urldecode($keyword); //decode
	if($keyword == 'all') $keyword = '';
	if($type == 'song'){
		$res = $db->query("SELECT S.song_id, S.song_name, S.singer_id, S2.singer_name  FROM song S, singer S2 WHERE S.song_name LIKE '%$keyword%' AND S2.singer_id = S.singer_id ORDER BY S.singer_id");
		echo "<p>������ҵ�".$db->num_rows($res)."����¼</p>";
		while($tmp = $db->fetch_array($res)){
			echo "<img src=../images/dot2.gif><a href='$PHP_SELF?step2=1&type=$type&keyword="
			.urlencode($keyword)."&song_id=$tmp[song_id]&singer_name=".urlencode($tmp[singer_name])
			."'>$tmp[song_name]</a>(<a href='$PHP_SELF?step2=1&type=singer&singer_id=$tmp[singer_id]'>$tmp[singer_name]</a>) <br>";
		}
	}else if($type == 'singer'){
		$res = $db->query("SELECT singer_id, singer_name  FROM singer WHERE singer_name LIKE '%$keyword%' ORDER BY alpha");
		echo "<h3>������ҵ�".$db->num_rows($res)."����¼, ����޸�</h3>";
		$i = 1;
		while($tmp = $db->fetch_array($res)){
			echo "<img src=../images/dot2.gif><a href='$PHP_SELF?step2=1&type=$type&keyword="
				.urlencode($keyword)."&singer_id=$tmp[singer_id]'>$tmp[singer_name]</a>";
			if($i == 5) { $i=1; echo "<br>\n"; }
			else {$i++; echo "��";}
		}
	}else if($type == 'cd'){//CD
		$res = $db->query("SELECT C.cd_id, C.cd_name, S.singer_id, S.singer_name FROM cd C, singer S WHERE C.cd_name LIKE '%$keyword%' AND S.singer_id = C.singer_id ORDER BY C.singer_id");
		echo "<h3>������ҵ�".$db->num_rows($res)."����¼, ����޸�</h3>";
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
			 alert('�ؼ��ֲ���Ϊ��');
	         return false; 
	        }  
	}
	</script>
<hr size=1 noshae>
<h3>���ֹ��� -- ɾ��/�޸�</h3></TD></TR>
<form action='<?php echo $PHP_SELF;?>' name=form4 method=post onsubmit='return checkform4();'>
�ؼ���: <input type=text name=keyword size=18 maxlength=30 value='<?php echo $keyword;?>'><br>		
<input type=radio name=type value=song <?php if(!$type) $type='song'; if($type == song) echo 'checked';?>>����
<input type=radio name=type value=singer <?php if($type == 'singer') echo 'checked';?>>����
<input type=radio name=type value=cd <?php if($type == 'cd') echo 'checked';?>>ר��
<input type=submit name=step1 value=����>
</form>
<?php
echo "</body></html>";
$db->close();
exit;
?>