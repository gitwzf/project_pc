<?
//播放文件整个CD
require('../../inc/db.class.php');
require('../../config.php');
//传入$cd_id
$cd_id = $_GET['cid'];
if(!$cd_id) exit("非法操作!");
$db = new db_class;
$db->connect();
$urls = $db->query("SELECT rm_url,song_name FROM song WHERE cd_id = '$cd_id' ORDER BY click DESC");

$msg = '';
while($tmp = $db->fetch_array($urls))
{
	$tmp[0] = str_replace(' ','%20',$tmp[0]);
	$msg .= "mkList(\"";
	$msg .= $tmp[0];
	$msg .= "\",\"";
	$msg .= $tmp[1];
	$msg .= "\");\n";
}
$db->close();

$file = "exobudpl_wmp.js";

if($fd=fopen($file,"w"))
{
	flock($fd,LOCK_EX);
	fputs($fd,$msg,strlen($msg));
	flock($fd, LOCK_UN);
	fclose($fd);
}

header("Expires: Mon, 26 Jul 2000 05:00:00 GMT");
header("Cache-Control: no-cache, must-revalidate");
header("Content-type: text/html");
?>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Windows Media Player</title>
<link rel="stylesheet" type="text/css" href="exobud.css"> <!-- 加载 ExoBUD MP(II) 主程序 -->
<script language="JavaScript" src="exobud.js"></script><!-- 加载 ExoBUD MP(II) 基本设定档 -->
<script language="JavaScript" src="exobudset.js"></script><!-- 加载 ExoBUD MP(II) 播放清单设定文件 -->
<script language="JavaScript" src="exobudpl_wmp.js"></script>
<script language="JScript" for="Exobud" event="openStateChange(sf)">evtOSChg(sf);</script>
<script language="JScript" for="Exobud" event="playStateChange(ns)">evtPSChg(ns);</script>
<script language="JScript" for="Exobud" event="error()">evtWmpError();</script>
<script language="JScript" for="Exobud" event="Buffering(bf)">evtWmpBuff(bf);</script>
<script language="JavaScript" src="imgchg.js"></script>
<script language="JavaScript">
<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
// -->
</script> 
</head>
<body onLoad="initExobud();" oncontextmenu="return false" onDragStart="return false" onSelectStart="return false"
topmargin=0 leftmargin=0 marginwidth=0 marginheight=0 BGCOLOR="#ffffff" scroll=no>
<TABLE WIDTH="380" BORDER="0" CELLSPACING="0" CELLPADDING="0" HEIGHT="18"><TR><TD WIDTH="380" HEIGHT="338" BACKGROUND="img/380_340_tv_bg.gif" VALIGN="TOP"><TABLE WIDTH="380" BORDER="0" CELLSPACING="0" CELLPADDING="0" HEIGHT="295"><TR><TD WIDTH="380" COLSPAN="2"><IMG SRC="img/none.gif" WIDTH="160" HEIGHT="12"></TD></TR><TR><TD WIDTH="14" VALIGN="TOP"><IMG SRC="img/none.gif" WIDTH="14" HEIGHT="19"></TD><TD WIDTH="366" VALIGN="TOP"><OBJECT ID="Exobud" CLASSID="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6"
TYPE="application/x-oleobject" WIDTH="0" HEIGHT="0"
STYLE="position:relative;left:0px;top:0px;width:350px;height:245px;"> <PARAM NAME="autoStart" VALUE="true"> 
<PARAM NAME="balance" VALUE="0"> <PARAM NAME="currentPosition" VALUE="0"> <PARAM NAME="currentMarker" VALUE="0"> 
<PARAM NAME="enableContextMenu" VALUE="false"> <PARAM NAME="enableErrorDialogs" VALUE="false"> 
<PARAM NAME="enabled" VALUE="true"> <PARAM NAME="fullScreen" VALUE="false"> <PARAM NAME="invokeURLs" VALUE="false"> 
<PARAM NAME="mute" VALUE="false"> <PARAM NAME="playCount" VALUE="1"> <PARAM NAME="rate" VALUE="1"> 
<PARAM NAME="uiMode" VALUE="none"> <PARAM NAME="volume" VALUE="100"> </OBJECT></TD></TR><TR><TD WIDTH="14" VALIGN="TOP">&nbsp;</TD><TD WIDTH="366" VALIGN="TOP"><TABLE HEIGHT=20 CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH="350"> 
<TR> <TD WIDTH=245 HEIGHT="22"><MARQUEE BEHAVIOR="scroll" WIDTH=350 HEIGHT=12 SCROLLAMOUNT=2 SCROLLDELAY=70><SPAN ID="disp1" CLASS="title">音视频播放系统</SPAN></MARQUEE></TD>
</TR> </TABLE></TD></TR><TR><TD WIDTH="380" COLSPAN="2" HEIGHT="37"><TABLE HEIGHT=25 CELLPADDING=0 CELLSPACING=0 BORDER=0 ALIGN="CENTER" WIDTH="340"> 
<TR><TD WIDTH=199>
<IMG NAME="vmute" SRC="img/btn_mute_off.gif" WIDTH=16 HEIGHT=18 BORDER=0
ONCLICK="wmpMute()" STYLE="cursor:hand" TITLE="静音模式"><IMG NAME="vdn" SRC="img/btn_vdn.gif" WIDTH=16 HEIGHT=18 BORDER=0
ONCLICK="wmpVolDn()" STYLE="cursor:hand" TITLE="减少音量"><IMG NAME="vup" SRC="img/btn_vup.gif" WIDTH=16 HEIGHT=18 BORDER=0
ONCLICK="wmpVolUp()" STYLE="cursor:hand" TITLE="增加音量"><IMG NAME="pmode" SRC="img/btn_rndmode_off.gif" WIDTH=16 HEIGHT=18 BORDER=0
ONCLICK="chgPMode()" STYLE="cursor:hand" TITLE="播放方式(S=顺序 R=随机)"><IMG NAME="rept" SRC="img/btn_rept_off.gif" WIDTH=16 HEIGHT=18 BORDER=0
ONCLICK="chkRept()" STYLE="cursor:hand" TITLE="切换是否重复播放目前的节目"><IMG NAME="prevt" SRC="img/btn_prev.gif" WIDTH=16 HEIGHT=18 BORDER=0
ONCLICK="playPrev()" STYLE="cursor:hand" TITLE="上一个"><IMG NAME="pauzt" SRC="img/btn_pauz_off.gif" WIDTH=16 HEIGHT=18 BORDER=0
ONCLICK="wmpPP()" STYLE="cursor:hand" TITLE="暂停/继续"><IMG NAME="stopt" SRC="img/btn_stop.gif" WIDTH=16 HEIGHT=18 BORDER=0
ONCLICK="wmpStop()" STYLE="cursor:hand" TITLE="停止"><IMG NAME="playt" SRC="img/btn_play.gif" WIDTH=16 HEIGHT=18 BORDER=0
ONCLICK="startExobud()" STYLE="cursor:hand" TITLE="播放"><IMG NAME="nextt" SRC="img/btn_next.gif" WIDTH=16 HEIGHT=18 BORDER=0
ONCLICK="playNext()" STYLE="cursor:hand" TITLE="下一个"><IMG NAME="plist" SRC="img/btn_plist.gif" WIDTH=16 HEIGHT=18 BORDER=0
ONCLICK="openPlist()" STYLE="cursor:hand" TITLE="播放目录"></TD><TD WIDTH=144><TABLE HEIGHT=25 CELLPADDING=0 CELLSPACING=0 BORDER=0 ALIGN="CENTER"> 
<TR><TD WIDTH=27 ><IMG NAME="scope" SRC="img/roll_off.gif" WIDTH=20 HEIGHT=25 ALIGN="ABSMIDDLE"></TD><TD WIDTH=106 ALIGN=center BACKGROUND="img/bg_time.gif" nowrap ONCLICK="chgTimeFmt()"> 
<SPAN ID="disp2" CLASS="time" TITLE="时间长度显示方式 (正常/倒数)"
STYLE="width:106;cursor:hand">00:00 | 00:00</SPAN> </TD></TR></TABLE></TD></TR></TABLE></TD></TR></TABLE></TD></TR></TABLE><table width=380 height=15 cellpadding=0 cellspacing=0 border=0>
<tr><td height=0><div id="capText" style="width:100%;height:28;color:#000000;background-color:#ffffff;padding-top:0px;padding-left:0px;display:none"
><p>CopyRight&nbsp;&copy;&nbsp;2006 - 2008&nbsp;</div></td></tr></table>
</body>
</html>