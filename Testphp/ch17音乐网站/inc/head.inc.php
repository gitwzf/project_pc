
<table border=0 align=center width=760 border=1 bgcolor='gray'>
<tr>
<td valign=middle width=220 height=60><a href='index.php'>
	վ��LOGO</a>
</td>
<td align=left width=300>
	ҳͷ��� 300*60
</td>
<td align=right valign=middle width=240>
<!-- ͳ�Ʋ��� -->
	 <?php
	   require('count.php');
  	   $ret = visit_count();
	   if($ret == -1) {
		   echo "\n</td></tr></table><br><br><br><br>\n"
		   ."<center><h3>ע�⣺ÿ��IPֻ����һ��վ������IP��".getenv("REMOTE_ADDR")."\");</h3></center>\n"
		   ."<script>alert(\"ÿ��IPֻ����һ��վ������IP��".getenv("REMOTE_ADDR")."\");</script>\n"
		   ."</body></html>\n";
		   $db->close();
		   exit();
	   } elseif($ret == -2) {
		   echo "\n</td></tr></table><br><br><br><br>\n"
		   ."<center><h3>ע�⣺Ŀǰ��վ���������Ѵ�����(".$maxactive.")�����Ժ�������лл��</h3></center>\n"
		   ."<script>alert(\"ע�⣺Ŀǰ��վ���������Ѵ�����(".$maxactive.")�����Ժ�������лл��\");</script>\n"
		   ."</body></html>\n";
		   $db->close();
		   exit();
	   } elseif($ret == -3) { //guest too many
		   echo "\n</td></tr></table><br><br><br><br>\n"
		   ."<center><h3>ע�⣺Ŀǰ��վ<b>�ÿ�</b>�����Ѵ�����("
		   .($maxactive / 20).")�����Ի�Ա��ݵ�¼��лл��</h3></center>\n"
		   ."<table border=0 width=180 align=center><tr><td>\n";
		   include("inc/unlogin.inc.php");
		   echo "</td></tr></table>\n</body></html>\n";
		   $db->close();
		   exit();
	   } else
		   echo $ret;
	 ?>
<!-- �Ƚϸ��� -->
</td>
</tr>
</table>
<table border=0 width=760 align=center cellspacing=0 cellpadding=0 background='images/bg2.gif'>
<tr> 
<td height="18" align="right">
<a href=index.php title='����������͸�'><font color=#E7E7E7>ͨ�׸���</font></a>&nbsp;
<a href='area.php?area_id=6&area_name=<?php echo urlencode('�ŵ�����');?>' title='�ŵ�����'><font color=#E7E7E7>�ŵ�����</font></a>&nbsp;
<a href='movie-tv.php?cate_id=<?php echo $message[11];?>&cate_name=<?php echo urlencode('Ӱ�ӽ���');?>' title='Ӱ�ӽ���'><font color=#E7E7E7>Ӱ�ӽ���</font></a>&nbsp;
<a href='singer.php?singer_id=96&singer_name=<?php echo urlencode('У԰��ҥ');?>' title='У԰��ҥ'><font color=#E7E7E7>У԰��ҥ</font></a>&nbsp;
<a href='singer.php?singer_id=95&singer_name=<?php echo urlencode('��������');?>' title='��������'><font color=#E7E7E7>��������</font></a>&nbsp;
<a href=member.php title='��վ��Ա'><font color=#E7E7E7>��Աר��</font></a>&nbsp;
<a href='board.php?brd_id=0' title='��������'><font color=#E7E7E7>����������</font></a>&nbsp;
<a href=help.php target=_blank style='cursor: help' title='���ֿ�����, ������ø�����'><font color=#E7E7E7>����</font></a>&nbsp;
</td>
</tr>
</table>