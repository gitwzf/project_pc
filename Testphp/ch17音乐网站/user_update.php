<?php
//user_update.php  �û��޸�����
require('config.php');
require('public.php');
require('inc/db.class.php');

if(!islogin()) error_quit2("��δ��¼!");

html_head("�û������޸�");

if($submit) //�ύ���
{
 echo "<br><br><br>\n";

 if($user_id == '' || $province == '' || $email == '' || $sex == '') error_quit3("��д������");
 if($passwd != $passwd1) error_quit3("��������������벻һ��!");
 if(!emailcheck($email)) error_quit3("����ȷ��д����Email��ַ!");
 if($passwd == '') $passwd = $m_user['passwd'];

 //ok save it
 $db->query("UPDATE user SET passwd = '$passwd', email = '$email', sex = '$sex', face = '$face', oicq = '$oicq', province = '$province', addr = '$addr', yearold = '$yearold', homepage = '$homepage', sign= '$sign', plan = '$plan' WHERE user_id = '$user_id'");

 $m_user = $db->query_first("SELECT * FROM user WHERE user_id = '$user_id'"); //����session
 
 //��ʾ�޸ĳɹ���
 print "
		<p class=okmsg align=center>�޸ĳɹ���</p>
		<p align=center> $message[0] </p>
 		<script language=javascript>
			setTimeout('window.close()', 2000); opener.document.location.reload();
		</script>
		";
}
else //�����޸ı��
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
<tr><td bgcolor=$message[2] colspan=2 class=p2>��������ϸ��д������Ŀ(��<font color=red>*</font>Ϊ����)</td></tr>
<tr><td bgcolor=$message[2]>ʹ�ô��� </td><td>$m_user[user_name]<input type=hidden name=user_id value='$m_user[user_id]'></td></tr>
<tr><td bgcolor=$message[2]>�����ʼ� </td><td><input type=text class=input name=email size=30 value='$m_user[email]'><font color=red>*</font></td></tr>
<tr><td bgcolor=$message[2]>�����Ա� </td><td>
<select name=sex size=1>
<option value='$m_user[sex]'>$m_user[sex]</option>
<option value='���'>���</option>
<option value='��ü'>��ü</option>
<option value='�׵�'>�׵�</option>
<option value='���'>���</option>
</select><font color=red>*</font></td></tr>
<tr><td bgcolor=$message[2]>����ͷ�� </td><td>
<select name=face size=1 onChange='showimage()'>
<option value='$m_user[face]' selected>$m_user[face]</option> 
	";

for ($i=0;$i<180;$i++)
{
 echo "<option value='$i'>$i</option>\n";
}

print "
</select><img src='images/face/".get_face($m_user[face], $m_user[sex]).".jpg' name='faceIcon'> <font color=red>*</font></td></tr>
<tr><td bgcolor=$message[2]>�������� </td><td><input type=text class=input name=yearold size=8 value='$m_user[yearold]'>����</td></tr>
<tr><td bgcolor=$message[2]>OICQ���� </td><td><input type=text class=input name=oicq size=15 value='$m_user[oicq]'></td></tr>
<tr><td bgcolor=$message[2]>����ʡ�� </td><td><select name=province size=1>
        <!--����ʡ��-->
		<option value='$m_user[province]'>$m_user[province]</option>
        <option value='����'>����</option>
        <option value='�Ϻ�'>�Ϻ�</option>
        <option value='���'>���</option>
        <option value='����'>����</option>
        <option value='ɽ��'>ɽ��</option>
        <option value='���ɹ�'>���ɹ�</option>
        <option value='����'>����</option>
        <option value='����'>����</option>
        <option value='������'>������</option>
        <option value='����'>����</option>
        <option value='�㽭'>�㽭</option>
        <option value='����'>����</option>
        <option value='����'>����</option>
        <option value='����'>����</option>
        <option value='ɽ��'>ɽ��</option>
        <option value='�ӱ�'>�ӱ�</option>
        <option value='����'>����</option>
        <option value='����'>����</option>
        <option value='����'>����</option>
        <option value='�㶫'>�㶫</option>
        <option value='����'>����</option>
        <option value='����'>����</option>
        <option value='�Ĵ�'>�Ĵ�</option>
        <option value='����'>����</option>
        <option value='����'>����</option>
        <option value='����'>����</option>
        <option value='����'>����</option>
        <option value='����'>����</option>
        <option value='�ຣ'>�ຣ</option>
        <option value='�½�'>�½�</option>
        <option value='����'>����</option>
        <option value='���'>���</option>
        <option value='����'>����</option>
        <option value='̨��'>̨��</option>        
        <option value='����'>����</option>        
</select><font color=red>*</font></td></tr>
<tr><td bgcolor=$message[2]>��ϸ��ַ </td><td><input type=text class=input name=addr value='$m_user[addr]' size=30></td></tr>
<tr><td bgcolor=$message[2]>�����ҳ</td><td><input type=text class=input name=homepage size=30 value='$m_user[homepage]'></td></tr>
<tr><td bgcolor=$message[2]>����ǩ�� </td><td><textarea class=input name=sign cols=49 rows=3>$m_user[sign]</textarea></td></tr>
<tr><td bgcolor=$message[2]>������Ƭ </td><td><textarea class=input name=plan cols=49 rows=6>$m_user[plan]</textarea></td></tr>
<tr><td bgcolor=$message[2] colspan=2 class=p2>�����޸����룬��������������д��</td></tr>
<tr><td bgcolor=$message[2]>ʹ������ </td><td><input type=password class=input name=passwd size=13></td></tr>
<tr><td bgcolor=$message[2]>�ظ����� </td><td><input type=password class=input name=passwd1 size=13></td></tr>
<tr bgcolor=$message[2]><td></td><td align=left>
<input type=submit name=submit value=�ύ class=button>
<input type=reset value=��д class=button>
<input type=reset value=�ر��뿪 class=button onclick='window.close()'>
</td></tr>
</form>
</table></center></div>
";
}

print "</body></html>";

$db->close();
exit;
?>