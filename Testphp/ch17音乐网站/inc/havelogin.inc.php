	   <TABLE border=0 cellPadding=0 cellSpacing=0 width=100% bgcolor="<?php echo $message[2];?>">
        <TBODY>
          <TR bgcolor="<?php echo $message[14];?>" align=center colspan=2>
          <TD colSpan=2 height=18 align=center valign=bottom>
		   <font color=#E7E7E7>:::</font> <font color=#FFFFFF>����<?php echo $m_user[user_name]; ?></font> <font color=#E7E7E7>:::</font>
		  </TD></TR>
          <TR>
          <TD height=20 align=left>
           <img src="<?php echo "images/face/".get_face($m_user[face], $m_user[sex]).".jpg"?>" align=left>����[<font color=green><?php echo $m_user[lastfrom];?></font>], ����վ[<font color=green><?php echo $m_user[numlogin];?></font>]��, ����[<font color=green><?php echo $m_user[numlisten];?></font>]��, ��������[<font color=green><?php echo $m_user[numpost];?></font>]ƪ, �ܻ���[<font color=green><?php $exp=count_exp(); echo $exp; ?></font>]��, �ȼ�[<font color=red><?php echo count_class($exp);?></font>]��
           </TD></TR>
          <TR>
          <TD height=20 align=center>
           <a href=my_favorite_song.php>�ҵ������ղ�</a>
           </TD></TR>
          <TR>
          <TD height=20 align=center>
           <a href=my_favorite_cd.php>�ҵ�ר����ѡ</a>
           </TD></TR>
          <TR>
          <TD height=20 align=center>
           <a href=my_ordersong_log.php>�ҵĵ���¼
           </TD></TR> 
          <TR>
          <TD height=20 align=center>
           <a href=user_update.php onclick='return Modify(this.href)'>�޸ĸ�������</a>
           </TD></TR>

		  <?php 
		  //��ȡ���Ļ�����
		  $msgNum=$db->query_first("SELECT count(*) FROM mail WHERE receiver = '$m_user[user_name]' OR flag = '*'");
		  $msgNumNew=$db->query_first("SELECT count(*) FROM mail WHERE receiver = '$m_user[user_name]' AND flag = 'N'");
		  $msg = "<font color=red>".$msgNumNew[0]."</font>/".$msgNum[0];
		  ?>
          <TR>
          <TD height=20 align=center>
           <a href=user_mail.php target=_self>���Ļ�(<?php echo $msg;?>)</a>
           </TD></TR>
          <TR>
          <TD height=20 align=center>
           <a href=admin/index.php target=_blank>ϵͳ�������</a>
           </TD></TR>
          <TD height=20 align=center>
           <a href=user_logout.php>�˳����ε�¼</a>
           </TD></TR>
      </TBODY></TABLE>

      