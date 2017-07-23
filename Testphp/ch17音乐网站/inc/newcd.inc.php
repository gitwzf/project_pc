<!-- 最新的4张大碟，带图片的! -->
<table border=0 cellpadding=0 cellspacing=0 width=100%>        
<tr>
  <td height=18 colspan=4 background='images/bg3.gif' valign=bottom>
   <font color="<?php echo $message[14]; ?>">&nbsp;■</font>
   <font color='#ffffff'> 最 新 大 碟 </font>
   <font color="<?php echo $message[14]; ?>">■</font>
  </td>
</tr>
<tr align=center>
<?php
  $res = $db->query("select c.cd_id as cd_id, c.cd_name as cd_name, c.imgurl as imgurl, s.singer_name as singer_name, s.singer_id as singer_id from cd c, singer s where c.imgurl != '' and c.singer_id = s.singer_id order by c.add_date desc limit 0, 4");
  while($tmp = $db->fetch_array($res)){
	  if($tmp[imgurl] == 'http://') $tmp[imgurl] = $default_cd_cover;
	  if(strncasecmp($tmp[imgurl], "http://", 7))
	      $tmp[imgurl] = $data_url.$tmp[imgurl];
	  $href = "cd.php?cd_id=$tmp[cd_id]&cd_name=".urlencode($tmp[cd_name])
	  		."&singer_id=$tmp[singer_id]&singer_name=".urlencode($tmp[singer_name]);
	  echo "<td><a href='$href'><img src='$tmp[imgurl]' width=110 height=110 alt='$tmp[cd_name]'"
	  	." title='$tmp[cd_name]' border='0'></a> <br><a href='singer.php?singer_id=$tmp[singer_id]"
	  	."&singer_name=".urlencode($tmp[singer_name])."'>$tmp[singer_name]</a>:<a href='$href'>"
	  	."$tmp[cd_name]</a></td>";
  }
  unset($res);
  unset($tmp);
  $db->free_result();
?>
</tr>
</table>