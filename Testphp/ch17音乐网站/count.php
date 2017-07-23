<?php
 /*
CREATE TABLE online  # ���߱�ṹ
(
  no int(14) not null,  	          # ����,������
  id varchar(40) not null,	          # ���ʵ�SessionID
  #ip varchar(16) not null
  userid varchar(20) not null
);

CREATE TABLE count  # ����ͳ����
(
  total int(10) NOT NULL DEFAULT 0,  # ����
  todaytotal int(6) NOT NULL DEFAULT 0,   # ����
  monthtotal int(8) NOT NULL DEFAULT 0,   # ����
  today  varchar(5) NOT NULL,   # ��
  month  varchar(5) NOT NULL   # ��
);
insert into count (total,todaytotal,monthtotal,today,month) values ('100','11','86','11','2');
*/
function visit_count() {

  $overtime = 15*60; //ˢ��ʱ��, ��λ�룬Ҳ����˵Ĭ���û�����������վ��ͣ���೤ʱ��, 5*60Ϊ5����
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

  // return -3, ����Ϊ���ʿ͹��࣬ Ҫ���¼��
   if(empty($userid)) {
	   $userid = "�ÿ�";
	   $guest = $db->query_first("SELECT COUNT(*) FROM online WHERE userid = '$userid'");
	   $guest = $guest[0];
	   if($guest > ($maxactive / 20))
		   return -3;
   }

  $result = $db->query("SELECT * FROM online");
  $online = $db->num_rows($result); // Ŀǰ��������
  $flag = 1; //�Ƿ�Ҫ��������?

  while ($tmp = $db->fetch_array($result)) {
	  $oldno = $tmp['no'];
	  $oldsid = $tmp['id'];
	  //$oldip = $tmp[ip];

	  //ͬIP��ͬ�ˣ� return -1;
	  //if(($ip == $oldip) && ($newsid != $oldsid)) return -1;
	  
	  if($newsid == $oldsid) { // old session
		$flag = 2;		//����ˢ��
		if(($newno - $oldno) > 10 * 60) //cheng: ��ֹˢ��̫Ƶ��
			$flag = 0;	//��Ҫˢ��
		else if(strcasecmp($tmp['userid'], $userid))
			$flag = 0;	//�����û����� (��¼���˳�) ��Ҫˢ��
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
	   if(($online > $maxactive) && !is_admin()) { //����Ϊ��
		   return -2;
	   }
	   //$db->query("INSERT INTO online VALUES ('$newno','$newsid','$ip')");
	   $db->query("INSERT INTO online VALUES ('$newno','$newsid', '$userid')");
	   $online++;
   }

   $array = $db->query_first("SELECT * FROM count");
//ԭ������
   $total = $array['total'];
   $todaytotal = $array['todaytotal'];
   $monthtotal = $array['monthtotal'];
   $today = $array['today'];
   $month = $array['month'];
   unset($array);
//����������
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

//�������

 /*
  $str = "
	  <marquee scrollamount='1' scrolldelay='80' direction= 'up' width='120' height='30' id=board onmouseover=board.stop() onmouseout=board.start()>
		<font style='color: #333366; font-size:9pt;'>
		��ǰ����: <font color=red>$online</font><br>
		���շ���: <font color=red>$todaytotal</font><br>
		���·���: <font color=red>$monthtotal</font><br>
		�ܼƷ���: <font color=red>$total</font><br>
	    ��¼ר��: <font color=red>$cdtotal</font><br>
		��¼����: <font color=red>$songtotal</font><br>
		�漰����: <font color=red>$singertotal</font><br>
		</font>
	 </marquee>
		 ";
  */
  $str = "<table border=0 cellspacing=0 cellpadding=0 width=100% style='font-size: 9pt'>\n"
  ."<tr><td width=50%><b>ͳ������</b></td><td width=50%>��ǰ����: <font color=red>".$online."</font>/".$maxactive."</td></tr>\n"
  ."<tr><td>��¼����: <font color=red>".$songtotal."</font></td><td>���շ���: <font color=red>".$todaytotal."</font></td></tr>\n"
  ."<tr><td>��¼ר��: <font color=red>".$cdtotal."</font></td><td>���·���: <font color=red>".$monthtotal."</font></td></tr>\n"
  ."<tr><td>�漰����: <font color=red>".$singertotal."</font></td><td>�ܼƷ���: <font color=red>".$total."</font></td></tr>\n"
  ."</table>\n";

  return $str;
}

?>