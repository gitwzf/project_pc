<?php
require('config.php');
require('public.php');
require('inc/db.class.php');

html_head("帮助文件");
include('inc/head.inc.php');
now_pos('<a href=index.php>首页</a> 》帮助', '');
?>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=760 background='images/bg1.gif'>
<TR><TD width=100% height=100%>
<h3><?echo $site_name; ?>使用帮助文件： </h3>
<ul>
  <li><b>请大家按以下格式上载歌曲，谢谢<br>
    <br>
    </b>1. 上载地址: <a href=ftp://10.11.11.11/music/>ftp://10.11.11.11/music/ </a> 可自建目录，不可删除。<br>
    <br>
    2.  上载规范: <br>
    <br>
    请按专辑上载，绝不要上载散歌！谢谢 <br>
    歌曲名最好用中文名 <br>
    如果有专辑封面图片，专辑简介，请一并上载或加以说明。<br>
    <br>
    <br>
  </li>
  <li><b>如何试听音乐？</b><br>
    <br>
    请先下载 请下载并安装 <a href=util/rp8-cn-setup.exe>RealPlayer</a>(5.15MB) 软件, 按提示安装成功后, 
    即可在本站试听音乐! <br>
    <br>
  </li>
  <li><b>登录时的"记住密码"是什么意思？</b><br>
    <br>
    如果您用的是私人机器, 那么请打勾, 这样即使您关闭机器, 再打开本站时, 您不用再重新登录, 不过上站次数还是照样增加, 请放心!
	<br>
	<br>
	如果是公用机器, 为了安全起见, 请不要选择该项!
    <br>
  </li>
  <li><b><a name=mem>如何成为注册会员？会员有哪些增值服务？</a><br>
    <br>
    </b>1. 点按首页的 
    <input class=button name=Button onClick="window.open('user_register.php','register','width=330,height=400,top=20,left=20')" type=button value=注册 target="_blank">
    , 填好资料, 确定无误提交成功后, 即成为本站注册会员. <br>
    <br>
    2. 注册为本站注册会员, 可以享受以下服务: <br>
    <br>
    可使用 我的播放列表 <br>
    可使用 我的专辑精选 <br>
    可对歌曲发表评论 <br>
    可在 交流中心 发表回复文章 <br>
    可使用点歌送歌功能, 并且系统可自动填上您的 名字 与 E-mail<br>
    可以进入歌友聊天室聊天. <br>
    <br>
  </li>
  <li><b>如何使用 &quot;我的播放列表&quot; 和 &quot;我的专辑精选&quot;?</b><br>
    <br>
    注册登录后，根据提示点击 &quot;收藏&quot; 即可。每个人最多有100个专辑精选和100首最爱歌曲。登录后<br>
    可以在我的工具栏(登录后的左栏)里点迁，方便自己收听喜欢的歌曲。<br>
    <br>
  </li>
  <li><b>如何使用 &quot;风情点歌机&quot; ?<br>
    <br>
    </b>注册登录后，在歌曲列表上点击 &quot;点歌&quot; 就行了。接下来根据提示做。<br>
    <br>
  </li>
  <li><b>为什么我不能 &quot;修改歌词&quot; , 说我的权限等级不够?<br>
    <br>
    </b>登录本站后，为安全起见要求您的经验值必须在 450 点以上或者是本站站长才能修改歌词，感谢您对本站的厚爱。<br>
    <br>
  </li>
  <li><b>发表文章时支持HTML么? 何为BBcode ?<br>
    <br>
    </b>发表文章或留言如无声明均<font color="#FF0000">不支持HTML语法</font>。但支持<font color="#FF0000">BBcode</font>,bbcode可以类似一些简单的HTML<br>
    语法。具体用法如下： <br>
    <br>
  </li>
  <ol type=1>
    <li>url支持：<br>
      用法1. <b>[url]</b>你的连接地址<b>[/url]</b><br>
      示例:<b>[url]</b>http://cheng.yeah.net<b>[/url]</b>， 显示<a href="http://cheng.yeah.net" target=_blank>http://cheng.yeah.net</a><br>
      <br>
      用法2. <b>[url=你的连接地址]</b>你的连接标题<b>[/url]</b><br>
      示例:<b>[url=http://cheng.yeah.net]</b>茶园工作室<b>[/url]</b>， 显示<a href="http://cheng.yeah.net" target=_blank>茶园工作室</a><br>
      <br>
      用法3. <b>[url]</b>连接地址<b>[/url]</b><br>
      示例:<b>[url]</b>cheng.yeah.net<b>[/url]</b>， 显示<a href="cheng.yeah.net" target=_blank>cheng.yeah.net</a><br>
      <br>
    <li>EMAIL支持:<br>
      用法1. <b>[email]</b>EMAIL地址<b>[/email]</b><br>
      示例:<b>[email]</b>cheng@263.net<b>[/email]</b>， 显示:<a href="mailto:cheng@263.net">cheng@263.net</a><br>
      <br>
      用法2. <b>[email=EMAIL地址]</b>显示内容<b>[/email]</b><br>
      示例:<b>[email=cheng@263.net]</b>cheng<b>[/email]</b>， 显示:<a href="mailto:cheng@263.net">cheng</a><br>
      <br>
    <li>字体支持：<br>
      用法1.<b>[b]</b>加粗字体<b>[/b]</b>　 显示:<b>加粗字体</b><br>
      <br>
      用法2.<b>[i]</b>倾斜字体<b>[/i]</b>　 显示:<i>倾斜字体</i><br>
      <br>
      <br>
  </ol>
  <li> <b>本站的经验值是如何计算的？ 跟聊天室的等级有何关系？ <br>
    </b><br>
    <font color=red>经验值 = 上站次数+发表文章数*2+注册天数/3</font><br>
	注：审核通过您提供的歌词，你的文章数也将加1，即加上2点经验值。
   <pre>
		 if($exp < 1) return "音乐游客";

		 else
			 if($exp < 100) return "新手上路";
		 else
			 if($exp < 450) return "一般发烧";
		 else
			 if($exp < 850) return "中级发烧";
		 else
			 if($exp < 1500) return "高级发烧";
		 else
			 if($exp < 2500) return "长老级";
		 else 
			 if($exp < 3500) return "元老级";
		 else
			 return "开国大老";
			 </pre>
	聊天等级为 经验值/150, 如经验值是750，则聊天等级为5 <br>
	聊天室 5级以上可以用HTML(img,script的除外)，8级以上可以看IP，看过滤表，看踢人表，10级最高(仅站长拥有)，可以踢人、过滤、授权。

  </li>
  <li> <b>更多帮助，怎么联系？<br>
    </b><br>
    如有任何建议请与<a href="mailto:cheng@263.net?subject=%C0%B4%D7%D4%D2%F4%C0%D6%D5%BE">cheng</a>联系.
  </li>
</ul>
</TD></TR></TABLE>
<?php
include('inc/foot.inc.php');
exit;
?>