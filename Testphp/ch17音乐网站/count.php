<?php
 /*
CREATE TABLE online  # 在线表结构
(
  no int(14) not null,  	          # 代号,即秒数
  id varchar(40) not null,	          # 访问的SessionID
  #ip varchar(16) not null
  userid varchar(20) not null
);

CREATE TABLE count  # 其它统计数
(
  total int(10) NOT NULL DEFAULT 0,  # 总数
  todaytotal int(6) NOT NULL DEFAULT 0,   # 今天
  monthtotal int(8) NOT NULL DEFAULT 0,   # 本月
  today  varchar(5) NOT NULL,   # 日
  month  varchar(5) NOT NULL   # 月
);
insert into count (total,todaytotal,monthtotal,today,month) values ('100','11','86','11','2');
*/
function visit_count() {

  $overtime = 15*60; //刷新时间, 单位秒，也就是说默认用户将在您的网站上停留多长时间, 5*60为5分钟
  $newno = date('U');
  global $maxactive;

  global $m_online_tag;
  global $m_user;
  global $db;

  if(!$m_online_tag) {
	  @session_start();
	  $newsid=session_id();
  }
  else {
	  $newsid = $m_online_tag;
  }

  $userid = $m_user['user_name'];
  //$ip = getenv("REMOTE_ADDR");

  // return -3, 是因为访问客过多， 要求登录。
   if(empty($userid)) {
	   $userid = "访客";
	   $guest = $db->query_first("SELECT COUNT(*) FROM online WHERE userid = '$userid'");
	   $guest = $guest[0];
	   if($guest > ($maxactive / 20))
		   return -3;
   }

  $result = $db->query("SELECT * FROM online");
  $online = $db->num_rows($result); // 目前在线人数
  $flag = 1; //是否要加入数据?

  while ($tmp = $db->fetch_array($result)) {
	  $oldno = $tmp['no'];
	  $oldsid = $tmp['id'];
	  //$oldip = $tmp[ip];

	  //同IP不同人？ return -1;
	  //if(($ip == $oldip) && ($newsid != $oldsid)) return -1;
	  
	  if($newsid == $oldsid) { // old session
		$flag = 2;		//不用刷新
		if(($newno - $oldno) > 10 * 60) //cheng: 防止刷新太频烦
			$flag = 0;	//需要刷新
		else if(strcasecmp($tmp['userid'], $userid))
			$flag = 0;	//换掉用户名了 (登录或退出) 需要刷新
		else
			break;
	  } else if(!strcasecmp($tmp[userid], $userid) || ($newno - $oldno) > $overtime) {
		$db->query("DELETE FROM online WHERE no='$oldno'");
		$online --; 
	  }
   }

   if($flag == 0) {
	   $db->query("UPDATE online SET no = '$newno', userid = '$userid' WHERE id='$newsid'"); 
   } else if($flag == 1) {
	   if(($online > $maxactive) && !is_admin()) { //人满为患
		   return -2;
	   }
	   //$db->query("INSERT INTO online VALUES ('$newno','$newsid','$ip')");
	   $db->query("INSERT INTO online VALUES ('$newno','$newsid', '$userid')");
	   $online++;
   }

   $array = $db->query_first("SELECT * FROM count");
//原有数据
   $total = $array['total'];
   $todaytotal = $array['todaytotal'];
   $monthtotal = $array['monthtotal'];
   $today = $array['today'];
   $month = $array['month'];
   unset($array);
//符合条件的
  if(($flag == 1) || ($today != date('j'))) {
	  if($today != date('j')) { $todaytotal = 0; $today = date('j'); }
	  if($month != date('n')) { $todaytotal = $monthtotal = 0; $month = date('n');}

	  $total++;
	  $todaytotal++;
	  $monthtotal++;

	  $db->query("update count set total='$total',todaytotal='$todaytotal',monthtotal='$monthtotal',today='$today',month='$month' ");
}

//some others
   $songtotal = $db->query_first("SELECT COUNT(*) FROM song");
   $songtotal = $songtotal[0];
   $cdtotal = $db->query_first("SELECT COUNT(*) FROM cd");
   $cdtotal = $cdtotal[0];
   $singertotal = $db->query_first("SELECT COUNT(*) FROM singer");
   $singertotal = $singertotal[0];

//外观如下

 /*
  $str = "
	  <marquee scrollamount='1' scrolldelay='80' direction= 'up' width='120' height='30' id=board onmouseover=board.stop() onmouseout=board.start()>
		<font style='color: #333366; font-size:9pt;'>
		当前在线: <font color=red>$online</font><br>
		今日访问: <font color=red>$todaytotal</font><br>
		本月访问: <font color=red>$monthtotal</font><br>
		总计访问: <font color=red>$total</font><br>
	    收录专辑: <font color=red>$cdtotal</font><br>
		收录歌曲: <font color=red>$songtotal</font><br>
		涉及歌手: <font color=red>$singertotal</font><br>
		</font>
	 </marquee>
		 ";
  */
  $str = "<table border=0 cellspacing=0 cellpadding=0 width=100% style='font-size: 9pt'>\n"
  ."<tr><td width=50%><b>统计资料</b></td><td width=50%>当前在线: <font color=red>".$online."</font>/".$maxactive."</td></tr>\n"
  ."<tr><td>收录歌曲: <font color=red>".$songtotal."</font></td><td>今日访问: <font color=red>".$todaytotal."</font></td></tr>\n"
  ."<tr><td>收录专辑: <font color=red>".$cdtotal."</font></td><td>本月访问: <font color=red>".$monthtotal."</font></td></tr>\n"
  ."<tr><td>涉及歌手: <font color=red>".$singertotal."</font></td><td>总计访问: <font color=red>".$total."</font></td></tr>\n"
  ."</table>\n";

  return $str;
}

?>