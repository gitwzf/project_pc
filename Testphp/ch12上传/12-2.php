<?php
//�����ļ���c:\upload
if(copy($userfile, "c:\\upload\\")){
	  echo "<b>�ϴ��ļ��ɹ�</b>";
}else{
	  echo "<b>�ϴ�ʧ�ܣ�</b>";
}
//������ɺ�ɾ��
unlink($userfile);
?>