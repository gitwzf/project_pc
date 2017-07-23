
	<DIV align=center>
	   <TABLE border=0 cellPadding=5 cellSpacing=0 width=760 align=center background='images/bg1.gif'>
		<TR>
		 <Td align=left width=100%>
		<!-- 从数据库里读出来 -->
		<?php
			if(isset($db)) {
				$res = $db->query("SELECT userid FROM online ORDER BY userid");
				$num = $db->num_rows($res);
				echo "目前有<font color=red>".$num."</font>位网友在站上：\n";
				while($tmp = $db->fetch_array($res))
					echo "<a href='user_query.php?user_name=".urlencode($tmp['userid'])."' onclick=\"window.open(this.href,'query','width=350,height=250,scrollbars=yes'); return false;\">$tmp[userid]</a>\n";
			}
		?>
		 </Td>
		</TR>
		<!-- END -->
	   </TABLE>
	 </DIV>