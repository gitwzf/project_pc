<?php
//点击排行，非首页转向分类
if(@$index != 1) {
	$t1 = "歌 手 目 录";
	$t2 = "新 进 专 辑";

	$r1 = "
		  <TR><TD height=18 align=left colspan=2>
           <b>地区分类</b>
           </TD></TR>
		  <TR><TD height=18 align=center colspan=2>
           <a href='area.php?area_id=1&area_name=".urlencode('港台')."'>港台地区</a>
           </TD></TR>
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='area.php?area_id=2&area_name=".urlencode('大陆')."'>大陆地区</a>
           </TD></TR>		
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='area.php?area_id=4&area_name=".urlencode('日韩')."'>日韩地区</a>
           </TD></TR>	  
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='area.php?area_id=5&area_name=".urlencode('海外')."'>海外地区</a>
           </TD></TR>
		  <TR><TD height=18 align=left colspan=2>
           <b>性质分类</b>
           </TD></TR>
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='cate.php?cate_id=1&cate_name=".urlencode('男性歌手')."'>男性歌手</a>
           </TD></TR>	  
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='cate.php?cate_id=2&cate_name=".urlencode('女性歌手')."'>女性歌手</a>
           </TD></TR>	  
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='cate.php?cate_id=3&cate_name=".urlencode('乐队组合')."'>乐队组合</a>
           </TD></TR>	  
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='cate.php?cate_id=4&cate_name=".urlencode('合辑其它')."'>合辑其它</a>
           </TD></TR>
		  <TR><TD height=18 align=left colspan=2>
           <b>特别提醒</b>
           </TD></TR>
		  <TR>
          <TD height=18 align=center colspan=2>
           <a href='movie-tv.php?cate_id=".$message[11]."&cate_name=".urlencode('影视金曲')."'>影视金曲</a>
           </TD></TR>	  
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='singer.php?singer_id=96&singer_name=".urlencode('校园民谣')."'>校园民谣</a>
           </TD></TR>	  
          <TR>
          <TD height=18 align=center colspan=2>
           <a href='singer.php?singer_id=95&singer_name=".urlencode('革命金曲')."'>革命金曲</a>
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
	$t1 = "歌 曲 排 行";
	$t2 = "热 点 专 辑";
	
	$q1 = $db->query("SELECT song_id, song_name, click FROM song ORDER BY click DESC LIMIT 0, 10");
	$q2 = $db->query("SELECT C.cd_id as cd_id, C.cd_name as cd_name, C.click as click, C.singer_id as singer_id, S.singer_name as singer_name FROM cd C, singer S WHERE C.singer_id = S.singer_id ORDER BY C.click DESC LIMIT 0, 10");


	$r1 = $r2 = "";

	while($tmp1 = $db->fetch_array($q1)) {
		$r1 .= "<TR><TD width=80%><a href='listen/listen_id.php?song_id=".$tmp1['song_id']."'
			 onclick='return Listen(this.href);'>".$tmp1['song_name']."</a></TD>
			 <TD width=20%>".$tmp1['click']."</TD></TR>
			 ";

		if($tmp2 = $db->fetch_array($q2)) { //专辑不会比歌曲还多！
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
     <!-- 最新更动! -->
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