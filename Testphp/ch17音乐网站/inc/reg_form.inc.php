<?php
//ע����
html_head("��Աע��");
print "
<table border=0 background='images/bg1.gif'>
<form method=post action=$PHP_SELF>
<tr><td bgcolor=$message[3] colspan=2 class=p2>��������ϸ��д������Ŀ(��<font color=red>*</font>Ϊ����)</td></tr>
<tr><td bgcolor=$message[3]>ʹ�ô��� </td><td><input type=text class=input name=user_name value='$user_name' size=13><font color=red>*</font>(����������)</td></tr>
<tr><td bgcolor=$message[3]>ʹ������ </td><td><input type=password class=input name=passwd size=13><font color=red>*</font></td></tr>
<tr><td bgcolor=$message[3]>�ظ����� </td><td><input type=password class=input name=passwd1 size=13><font color=red>*</font></td></tr>
<tr><td bgcolor=$message[3]>�����ʼ� </td><td><input type=text class=input name=email size=30 value='$email'><font color=red>*</font></td></tr>
<tr><td bgcolor=$message[3]>�����Ա� </td><td><input type=radio name=sex value='���'>���
<input type=radio name=sex value='��ü' checked>��ü
<input type=radio name=sex value='�׵�'>�׵�
<input type=radio name=sex value='���'>���
<font color=red>*</font></td></tr>
<tr><td bgcolor=$message[3]>�������� </td><td><input type=text class=input name=yearold size=8 value='$yearold'>����</td></tr>
<tr><td bgcolor=$message[3]>OICQ���� </td><td><input type=text class=input name=oicq size=15 value='$oicq'></td></tr>
<tr><td bgcolor=$message[3]>����ʡ�� </td><td><select name=province size=1>
        <!--����ʡ��-->
		<option>��ѡ��</option>
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
<tr><td bgcolor=$message[3]>��ϸ��ַ </td><td><input type=text class=input name=addr value='$addr' size=30></td></tr>
<tr><td bgcolor=$message[3]>�����ҳ</td><td><input type=text class=input name=homepage size=30 value='http://'></td></tr>
<tr><td bgcolor=$message[3]>����ǩ�� </td><td><textarea class=input name=sign cols=35 rows=3>$sign</textarea></td></tr>
<tr><td bgcolor=$message[3] colspan=2 align=center>
<input type=submit name=submit value=�ύ class=button>
<input type=reset value=��д class=button></td></tr>
</form>
</table>
<br>
<center>$message[0]</center>
</body></html>
";