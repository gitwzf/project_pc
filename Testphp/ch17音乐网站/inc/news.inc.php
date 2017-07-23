	   <TABLE border=0 cellPadding=0 cellSpacing=0 width=100%>
		<TR>
		  <TD height=18 colspan=4 background='images/bg3.gif' valign=bottom>
		   <font color="<?php echo $message[14]; ?>">&nbsp;■</font>
		   <font color=#FFFFFF> 好 帖 排 行 </font>
		   <font color="<?php echo $message[14]; ?>">■</font>
		  </TD>
	    </TR>
		<!-- 从数据库里读出来 -->
		<?php
			$res = $db->query("SELECT post_id, title, author, date, thread_id, flag FROM post WHERE flag = 1 AND brd_id = 0 ORDER BY date DESC LIMIT 0, 10");
			
			while($tmp = $db->fetch_array($res))
			{
				if(strlen($tmp[title]) > 45) $tmp[title] = substr($tmp[title],0,42)."..."; //长标题只显示25个字

				if(eregi(date("Y-m-d"),$tmp[date])) {
					$flag = "<font color=red>◆ </font>";
					$tmp[title] = str_replace('Re:','<font color=red>Re:</font>',$tmp[title]);
				}
				else $flag = "◇ "; //旗标 Re: 不用

				if(!ereg('Re:',$tmp[title])) $tmp[title] =$flag.$tmp[title];//re:的非主题

				print "
					<TR height=18 align=left>
				    <TD>
					<a href='board_read.php?brd_id=0&post_id=$tmp[post_id]&singer_name=".urlencode("公共的")."&thread_id=$tmp[thread_id]' onclick='return Post(this.href)'>$tmp[title]</a>
					<i>Post by <a href='user_query.php?user_name=".urlencode($tmp[author])."' onclick='return Query(this.href)'>$tmp[author]</a> on $tmp[date] </i>
					</TD></TR>\n";
			}
		?>
		<!-- END -->
        <TR align=right>
		<TD width=100%><a href=board.php?brd_id=0>更多的文章 &gt;&gt;..</a></TD>
		</TR>
	   </TABLE>