	   <TABLE border=0 cellPadding=0 cellSpacing=0 width=100% bgcolor="<?php echo $message[2];?>">
        <TBODY>
          <TR bgcolor="<?php echo $message[14];?>" align=center colspan=2>
          <TD colSpan=2 height=18 align=center valign=bottom>
		   <font color=#E7E7E7>:::</font> <font color=#FFFFFF>我是<?php echo $m_user[user_name]; ?></font> <font color=#E7E7E7>:::</font>
		  </TD></TR>
          <TR>
          <TD height=20 align=left>
           <img src="<?php echo "images/face/".get_face($m_user[face], $m_user[sex]).".jpg"?>" align=left>来自[<font color=green><?php echo $m_user[lastfrom];?></font>], 共上站[<font color=green><?php echo $m_user[numlogin];?></font>]次, 听歌[<font color=green><?php echo $m_user[numlisten];?></font>]次, 发表评论[<font color=green><?php echo $m_user[numpost];?></font>]篇, 总积分[<font color=green><?php $exp=count_exp(); echo $exp; ?></font>]点, 等级[<font color=red><?php echo count_class($exp);?></font>]。
           </TD></TR>
          <TR>
          <TD height=20 align=center>
           <a href=my_favorite_song.php>我的音乐收藏</a>
           </TD></TR>
          <TR>
          <TD height=20 align=center>
           <a href=my_favorite_cd.php>我的专辑精选</a>
           </TD></TR>
          <TR>
          <TD height=20 align=center>
           <a href=my_ordersong_log.php>我的点歌记录
           </TD></TR> 
          <TR>
          <TD height=20 align=center>
           <a href=user_update.php onclick='return Modify(this.href)'>修改个人资料</a>
           </TD></TR>

		  <?php 
		  //获取悄悄话条数
		  $msgNum=$db->query_first("SELECT count(*) FROM mail WHERE receiver = '$m_user[user_name]' OR flag = '*'");
		  $msgNumNew=$db->query_first("SELECT count(*) FROM mail WHERE receiver = '$m_user[user_name]' AND flag = 'N'");
		  $msg = "<font color=red>".$msgNumNew[0]."</font>/".$msgNum[0];
		  ?>
          <TR>
          <TD height=20 align=center>
           <a href=user_mail.php target=_self>悄悄话(<?php echo $msg;?>)</a>
           </TD></TR>
          <TR>
          <TD height=20 align=center>
           <a href=admin/index.php target=_blank>系统管理入口</a>
           </TD></TR>
          <TD height=20 align=center>
           <a href=user_logout.php>退出本次登录</a>
           </TD></TR>
      </TBODY></TABLE>

      