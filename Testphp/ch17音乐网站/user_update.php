<?php
//user_update.php  用户修改资料
require('config.php');
require('public.php');
require('inc/db.class.php');

if(!islogin()) error_quit2("尚未登录!");

html_head("用户资料修改");

if($submit) //提交结果
{
 echo "<br><br><br>\n";

 if($user_id == '' || $province == '' || $email == '' || $sex == '') error_quit3("填写不完整");
 if($passwd != $passwd1) error_quit3("您两次输入的密码不一致!");
 if(!emailcheck($email)) error_quit3("请正确填写您的Email地址!");
 if($passwd == '') $passwd = $m_user['passwd'];

 //ok save it
 $db->query("UPDATE user SET passwd = '$passwd', email = '$email', sex = '$sex', face = '$face', oicq = '$oicq', province = '$province', addr = '$addr', yearold = '$yearold', homepage = '$homepage', sign= '$sign', plan = '$plan' WHERE user_id = '$user_id'");

 $m_user = $db->query_first("SELECT * FROM user WHERE user_id = '$user_id'"); //更新session
 
 //提示修改成功。
 print "
		<p class=okmsg align=center>修改成功！</p>
		<p align=center> $message[0] </p>
 		<script language=javascript>
			setTimeout('window.close()', 2000); opener.document.location.reload();
		</script>
		";
}
else //给出修改表格
{
print "

<script language=javascript>
<!--
function showimage()
{
 document.images.faceIcon.src='images/face/'+document.form.face.options[document.form.face.selectedIndex].value+'.gif';
}
//-->
</script>
<br>
<div align=center><center>
<table border=0 width=97% background='images/bg1.gif'>
<form method=post action=$PHP_SELF name=form>
<tr><td bgcolor=$message[2] colspan=2 class=p2>请认真仔细填写以下项目(带<font color=red>*</font>为必填)</td></tr>
<tr><td bgcolor=$message[2]>使用代号 </td><td>$m_user[user_name]<input type=hidden name=user_id value='$m_user[user_id]'></td></tr>
<tr><td bgcolor=$message[2]>电子邮件 </td><td><input type=text class=input name=email size=30 value='$m_user[email]'><font color=red>*</font></td></tr>
<tr><td bgcolor=$message[2]>您的性别 </td><td>
<select name=sex size=1>
<option value='$m_user[sex]'>$m_user[sex]</option>
<option value='葛格'>葛格</option>
<option value='美眉'>美眉</option>
<option value='底迪'>底迪</option>
<option value='解结'>解结</option>
</select><font color=red>*</font></td></tr>
<tr><td bgcolor=$message[2]>您的头像 </td><td>
<select name=face size=1 onChange='showimage()'>
<option value='$m_user[face]' selected>$m_user[face]</option> 
	";

for ($i=0;$i<180;$i++)
{
 echo "<option value='$i'>$i</option>\n";
}

print "
</select><img src='images/face/".get_face($m_user[face], $m_user[sex]).".jpg' name='faceIcon'> <font color=red>*</font></td></tr>
<tr><td bgcolor=$message[2]>您的年龄 </td><td><input type=text class=input name=yearold size=8 value='$m_user[yearold]'>周岁</td></tr>
<tr><td bgcolor=$message[2]>OICQ号码 </td><td><input type=text class=input name=oicq size=15 value='$m_user[oicq]'></td></tr>
<tr><td bgcolor=$message[2]>所在省份 </td><td><select name=province size=1>
        <!--所在省份-->
		<option value='$m_user[province]'>$m_user[province]</option>
        <option value='北京'>北京</option>
        <option value='上海'>上海</option>
        <option value='天津'>天津</option>
        <option value='重庆'>重庆</option>
        <option value='山西'>山西</option>
        <option value='内蒙古'>内蒙古</option>
        <option value='辽宁'>辽宁</option>
        <option value='吉林'>吉林</option>
        <option value='黑龙江'>黑龙江</option>
        <option value='江苏'>江苏</option>
        <option value='浙江'>浙江</option>
        <option value='安徽'>安徽</option>
        <option value='福建'>福建</option>
        <option value='江西'>江西</option>
        <option value='山东'>山东</option>
        <option value='河北'>河北</option>
        <option value='河南'>河南</option>
        <option value='湖北'>湖北</option>
        <option value='湖南'>湖南</option>
        <option value='广东'>广东</option>
        <option value='广西'>广西</option>
        <option value='海南'>海南</option>
        <option value='四川'>四川</option>
        <option value='贵州'>贵州</option>
        <option value='云南'>云南</option>
        <option value='陕西'>陕西</option>
        <option value='甘肃'>甘肃</option>
        <option value='宁夏'>宁夏</option>
        <option value='青海'>青海</option>
        <option value='新疆'>新疆</option>
        <option value='西藏'>西藏</option>
        <option value='香港'>香港</option>
        <option value='澳门'>澳门</option>
        <option value='台湾'>台湾</option>        
        <option value='其他'>其他</option>        
</select><font color=red>*</font></td></tr>
<tr><td bgcolor=$message[2]>详细地址 </td><td><input type=text class=input name=addr value='$m_user[addr]' size=30></td></tr>
<tr><td bgcolor=$message[2]>你的主页</td><td><input type=text class=input name=homepage size=30 value='$m_user[homepage]'></td></tr>
<tr><td bgcolor=$message[2]>个人签名 </td><td><textarea class=input name=sign cols=49 rows=3>$m_user[sign]</textarea></td></tr>
<tr><td bgcolor=$message[2]>个人名片 </td><td><textarea class=input name=plan cols=49 rows=6>$m_user[plan]</textarea></td></tr>
<tr><td bgcolor=$message[2] colspan=2 class=p2>若不修改密码，以下两个不必填写。</td></tr>
<tr><td bgcolor=$message[2]>使用密码 </td><td><input type=password class=input name=passwd size=13></td></tr>
<tr><td bgcolor=$message[2]>重复密码 </td><td><input type=password class=input name=passwd1 size=13></td></tr>
<tr bgcolor=$message[2]><td></td><td align=left>
<input type=submit name=submit value=提交 class=button>
<input type=reset value=重写 class=button>
<input type=reset value=关闭离开 class=button onclick='window.close()'>
</td></tr>
</form>
</table></center></div>
";
}

print "</body></html>";

$db->close();
exit;
?>