<?php
//������У�����ҳת�����
if(@$index != 1) {
	$t1 = "�� �� Ŀ ¼";
	$t2 = "�� �� ר ��";

	$r1 = "
		  <TR><TD height=18 align=left colspan=2>
           <b>��������</b>
           </TD></TR>
		  <TR><TD height=18 align=center colspan=2>
           <a href='area.php?area_id=1&area_name=".urlencode('��̨')."'>��̨����</a>
           </TD></TR>
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='area.php?area_id=2&area_name=".urlencode('��½')."'>��½����</a>
           </TD></TR>		
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='area.php?area_id=4&area_name=".urlencode('�պ�')."'>�պ�����</a>
           </TD></TR>	  
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='area.php?area_id=5&area_name=".urlencode('����')."'>�������</a>
           </TD></TR>
		  <TR><TD height=18 align=left colspan=2>
           <b>���ʷ���</b>
           </TD></TR>
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='cate.php?cate_id=1&cate_name=".urlencode('���Ը���')."'>���Ը���</a>
           </TD></TR>	  
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='cate.php?cate_id=2&cate_name=".urlencode('Ů�Ը���')."'>Ů�Ը���</a>
           </TD></TR>	  
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='cate.php?cate_id=3&cate_name=".urlencode('�ֶ����')."'>�ֶ����</a>
           </TD></TR>	  
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='cate.php?cate_id=4&cate_name=".urlencode('�ϼ�����')."'>�ϼ�����</a>
           </TD></TR>
		  <TR><TD height=18 align=left colspan=2>
           <b>�ر�����</b>
           </TD></TR>
		  <TR>
          <TD height=18 align=center colspan=2>
           <a href='movie-tv.php?cate_id=".$message[11]."&cate_name=".urlencode('Ӱ�ӽ���')."'>Ӱ�ӽ���</a>
           </TD></TR>	  
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='singer.php?singer_id=96&singer_name=".urlencode('У԰��ҥ')."'>У԰��ҥ</a>
           </TD></TR>	  
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='singer.php?singer_id=95&singer_name=".urlencode('��������')."'>��������</a>
           </TD></TR>
		";
	if(isset($db)) {
		 $ncd = $db->query("SELECT C.cd_id as cd_id, C.cd_name as cd_name, S.singer_id as singer_id, S.singer_name as singer_name FROM cd C, singer S WHERE C.singer_id = S.singer_id ORDER BY add_date DESC LIMIT 0, 10");
		 $r2 = "";

		 while($tmp = $db->fetch_array($ncd)) {
			 $r2 .= "
			 <TR><TD width=30%><a href='singer.php?singer_id=".$tmp['singer_id']."&singer_name="
			 .urlencode($tmp['singer_name'])."'>".$tmp['singer_name']."</a></TD>
			 <TD width=70%><a href='cd.php?cd_id=".$tmp['cd_id']."&cd_name="				
			 .urlencode($tmp['cd_name'])."&singer_id=".$tmp['singer_id']."&singer_name="
			 .urlencode($tmp['singer_name'])."'>".$tmp['cd_name']."</a></TD></TR>
			 ";
		 }

		 unset($ncd);
		 $db->free_result();
	}

} else {
	$t1 = "�� �� �� ��";
	$t2 = "�� �� ר ��";
	
	$q1 = $db->query("SELECT song_id, song_name, click FROM song ORDER BY click DESC LIMIT 0, 10");
	$q2 = $db->query("SELECT C.cd_id as cd_id, C.cd_name as cd_name, C.click as click, C.singer_id as singer_id, S.singer_name as singer_name FROM cd C, singer S WHERE C.singer_id = S.singer_id ORDER BY C.click DESC LIMIT 0, 10");


	$r1 = $r2 = "";

	while($tmp1 = $db->fetch_array($q1)) {
		$r1 .= "<TR><TD width=80%><a href='listen/listen_id.php?song_id=".$tmp1['song_id']."'
			 onclick='return Listen(this.href);'>".$tmp1['song_name']."</a></TD>
			 <TD width=20%>".$tmp1['click']."</TD></TR>
			 ";

		if($tmp2 = $db->fetch_array($q2)) { //ר������ȸ������࣡
			$r2 .= "<TR><TD width=80%><a href='cd.php?cd_id=$tmp2[cd_id]&cd_name="				
			 .urlencode($tmp2[cd_name])."&singer_id=$tmp2[singer_id]&singer_name="
			 .urlencode($tmp2[singer_name])."'>$tmp2[cd_name]</a></TD>
			 <TD width=20%>".$tmp2[click]."</TD></TR>
			 ";
		}
	}

	unset($q1);
	unset($q2);
	$db->free_result();
}

?>
     <!-- ���¸���! -->
	 <TABLE border=0 cellPadding=2 cellSpacing=0 width=100% bgcolor="<?php echo $message[2];?>">
        <TBODY>
        <TR bgcolor="<?php echo $message[14];?>" align=center colspan=2>
          <TD colSpan=2 height=18 align=center valign=bottom>
		   <font color=#E7E7E7>:::</font> <font color=#FFFFFF><?php echo $t1; ?></font> <font color=#E7E7E7>:::</font>
		  </TD></TR>
		<?php echo $r1; ?>
        <TR bgcolor="<?php echo $message[14];?>" align=center colspan=2>
          <TD colSpan=2 height=18 align=center valign=bottom>
		   <font color=#E7E7E7>:::</font> <font color=#FFFFFF><?php echo $t2; ?></font> <font color=#E7E7E7>:::</font>
		  </TD></TR>
		<?php echo $r2; ?>
      </TBODY></TABLE>