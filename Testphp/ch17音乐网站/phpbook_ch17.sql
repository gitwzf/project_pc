# MySQL-Front Dump 2.2
#
# Host: localhost   Database: phpbook_ch17
#--------------------------------------------------------
# Server version 4.0.26-nt


#
# Table structure for table 'area'
#

CREATE TABLE `area` (
  `area_id` int(11) NOT NULL auto_increment,
  `area_name` varchar(40) NOT NULL default '',
  PRIMARY KEY  (`area_id`)
) TYPE=MyISAM;



#
# Dumping data for table 'area'
#
INSERT INTO area VALUES("1","港台");
INSERT INTO area VALUES("2","大陆");
INSERT INTO area VALUES("4","日韩");
INSERT INTO area VALUES("5","海外");
INSERT INTO area VALUES("6","古典音乐");


#
# Table structure for table 'cate'
#

CREATE TABLE `cate` (
  `cate_id` int(11) NOT NULL auto_increment,
  `cate_name` varchar(40) NOT NULL default '',
  PRIMARY KEY  (`cate_id`)
) TYPE=MyISAM;



#
# Dumping data for table 'cate'
#
INSERT INTO cate VALUES("1","男性歌手");
INSERT INTO cate VALUES("2","女性歌手");
INSERT INTO cate VALUES("3","乐队组合");
INSERT INTO cate VALUES("4","其它歌手");
INSERT INTO cate VALUES("5","影视金曲");


#
# Table structure for table 'cd'
#

CREATE TABLE `cd` (
  `cd_id` bigint(100) NOT NULL auto_increment,
  `cd_name` varchar(40) NOT NULL default '',
  `song_num` int(6) NOT NULL default '0',
  `imgurl` varchar(255) default NULL,
  `introduce` text,
  `lang` varchar(20) NOT NULL default '',
  `pub_date` date NOT NULL default '0000-00-00',
  `add_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `click` int(11) NOT NULL default '0',
  `singer_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`cd_id`),
  KEY `cd_id` (`cd_id`),
  KEY `singer_id` (`singer_id`)
) TYPE=MyISAM;



#
# Dumping data for table 'cd'
#
INSERT INTO cd VALUES("1","测试一","2","","测试测试我在沙发批发啊阿飞噶","中文","0000-00-00","2007-02-18 13:30:37","12","1");
INSERT INTO cd VALUES("2","爱你伍佰年","2","http://localhost/phpbook/ch17/music/wubai.jpg","爱你伍佰年","中文","2004-05-27","2007-02-18 14:20:22","3","2");


#
# Table structure for table 'count'
#

CREATE TABLE `count` (
  `total` int(10) NOT NULL default '0',
  `todaytotal` int(6) NOT NULL default '0',
  `monthtotal` int(8) NOT NULL default '0',
  `today` varchar(5) NOT NULL default '',
  `month` varchar(5) NOT NULL default ''
) TYPE=MyISAM;



#
# Dumping data for table 'count'
#


#
# Table structure for table 'geci'
#

CREATE TABLE `geci` (
  `song_id` int(11) NOT NULL default '0',
  `zuoqu` varchar(40) default NULL,
  `tianci` varchar(40) default NULL,
  `tigong` varchar(40) NOT NULL default '',
  `geci` text NOT NULL,
  PRIMARY KEY  (`song_id`)
) TYPE=MyISAM;



#
# Dumping data for table 'geci'
#
INSERT INTO geci VALUES("2","","","admin","[ti:大地]\r\n[ar:Beyond]\r\n[al:音乐天地]\r\n[by:毛毛祥]\r\n[00:00]\r\n[00:01]大地(粤)\r\n[00:03]Beyond\r\n[00:05]\r\n[00:25]在那些苍翠的路上\r\n[00:29]历遍了多少创伤\r\n[00:33]在那张苍老的面上\r\n[00:36]亦记载了风霜\r\n[00:40]秋风秋雨的度日\r\n[00:43]是青春少年时\r\n[00:46]迫不得已的话别 没说再见\r\n[00:52]回望昨日在异乡那门前\r\n[00:56]唏嘘的感慨一年年\r\n[01:00]但日落日出永没变迁\r\n[01:05]这刻在望着父亲笑容时\r\n[01:10]竟不知不觉的无言\r\n[01:13]让日落暮色渗满泪眼\r\n[01:32]在那些开放的路上\r\n[01:36]踏碎过多少理想\r\n[01:39]在那张高挂的面上\r\n[01:43]被引证了几多\r\n[01:46]千秋不变的日月\r\n[01:49]在相惜里共存\r\n[01:53]姑息分割的大地 划了界线\r\n[01:59]回望昨日在异乡那门前\r\n[02:03]唏嘘的感慨一年年\r\n[02:06]但日落日出永没变迁\r\n[02:12]这刻在望着父亲笑容时\r\n[02:16]竟不知不觉的无言\r\n[02:19]让日落暮色渗满泪眼\r\n[02:41]回望昨日在异乡那门前\r\n[02:45]唏嘘的感慨一年年\r\n[02:48]但日落日出永没变迁\r\n[02:54]这刻在望着父亲笑容时\r\n[02:58]竟不知不觉的无言\r\n[03:02]让日落暮色渗满泪眼\r\n[03:08]回望昨日在异乡那门前\r\n[03:12]唏嘘的感慨一年年\r\n[03:15]但日落日出永没变迁\r\n[03:21]这刻在望着父亲笑容时\r\n[03:25]竟不知不觉的无言\r\n[03:28]让日落暮色渗满泪眼\r\n");


#
# Table structure for table 'mail'
#

CREATE TABLE `mail` (
  `id` int(11) NOT NULL auto_increment,
  `receiver` varchar(15) NOT NULL default '',
  `sender` varchar(15) NOT NULL default '',
  `title` varchar(100) NOT NULL default '',
  `content` text,
  `fromip` varchar(50) NOT NULL default '',
  `date` datetime default NULL,
  `flag` char(1) NOT NULL default 'N',
  PRIMARY KEY  (`id`)
) TYPE=MyISAM;



#
# Dumping data for table 'mail'
#


#
# Table structure for table 'online'
#

CREATE TABLE `online` (
  `no` int(14) NOT NULL default '0',
  `id` varchar(40) NOT NULL default '',
  `userid` varchar(20) NOT NULL default '',
  UNIQUE KEY `no` (`no`)
) TYPE=MyISAM;



#
# Dumping data for table 'online'
#
INSERT INTO online VALUES("1172332065","5702c291a601e4d0a0dbc46a051b74d1","访客");


#
# Table structure for table 'ordersong'
#

CREATE TABLE `ordersong` (
  `order_id` int(11) NOT NULL auto_increment,
  `receiver` varchar(255) NOT NULL default '',
  `sender` varchar(40) NOT NULL default '',
  `song_name` varchar(100) NOT NULL default '',
  `song_id` bigint(100) NOT NULL default '0',
  `singer_name` varchar(100) NOT NULL default '',
  `singer_id` int(11) NOT NULL default '0',
  `date` datetime NOT NULL default '0000-00-00 00:00:00',
  `ssay` varchar(100) NOT NULL default '天天都有好心情',
  `says` text,
  `flag` tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (`order_id`),
  KEY `sender` (`sender`),
  KEY `date` (`date`)
) TYPE=MyISAM;



#
# Dumping data for table 'ordersong'
#


#
# Table structure for table 'post'
#

CREATE TABLE `post` (
  `post_id` bigint(100) NOT NULL auto_increment,
  `title` varchar(100) NOT NULL default '',
  `author` varchar(40) NOT NULL default '',
  `brd_id` int(11) NOT NULL default '0',
  `date` datetime NOT NULL default '0000-00-00 00:00:00',
  `thread_id` bigint(100) NOT NULL default '0',
  `text` text NOT NULL,
  `click` int(11) NOT NULL default '0',
  `fromip` varchar(16) NOT NULL default '127.0.0.1',
  `flag` tinyint(2) NOT NULL default '0',
  PRIMARY KEY  (`post_id`),
  KEY `brd_id` (`brd_id`),
  KEY `flag` (`flag`)
) TYPE=MyISAM;



#
# Dumping data for table 'post'
#
INSERT INTO post VALUES("1","说说公共的","wei","0","2007-02-18 13:55:34","0","第一次来，冒泡换支持，嘿嘿\n\n--\n大家好哇！\n--","1","127.0.0.1","0");


#
# Table structure for table 'singer'
#

CREATE TABLE `singer` (
  `singer_id` bigint(100) NOT NULL auto_increment,
  `alpha` char(1) NOT NULL default '',
  `singer_name` varchar(40) NOT NULL default '',
  `cd_num` int(6) NOT NULL default '0',
  `song_num` int(6) NOT NULL default '0',
  `imgurl` varchar(255) default NULL,
  `introduce` text,
  `click` int(11) NOT NULL default '0',
  `area_id` int(11) NOT NULL default '0',
  `cate_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`singer_id`),
  KEY `singer_id` (`singer_id`),
  KEY `area_id` (`area_id`),
  KEY `cate_id` (`cate_id`)
) TYPE=MyISAM;



#
# Dumping data for table 'singer'
#
INSERT INTO singer VALUES("1","b","Beyond","1","2","","Beyond","6","1","1");
INSERT INTO singer VALUES("2","w","伍佰","1","2","http://localhost/phpbook/ch17/music/wubai.jpg","爱你伍佰年","0","1","1");


#
# Table structure for table 'song'
#

CREATE TABLE `song` (
  `song_id` bigint(100) NOT NULL auto_increment,
  `song_name` varchar(100) NOT NULL default '',
  `rm_url` varchar(255) NOT NULL default '',
  `mp3_url` tinytext NOT NULL,
  `geci` tinyint(1) NOT NULL default '0',
  `lis_num` int(11) NOT NULL default '0',
  `click` int(11) NOT NULL default '0',
  `cd_id` int(11) NOT NULL default '0',
  `singer_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`song_id`),
  KEY `song_id` (`song_id`),
  KEY `rm_url` (`rm_url`),
  KEY `cd_id` (`cd_id`),
  KEY `singer_id` (`singer_id`)
) TYPE=MyISAM;



#
# Dumping data for table 'song'
#
INSERT INTO song VALUES("1","大地","http://localhost/phpbook/ch17/music/dadi.mp3","http://localhost/phpbook/ch17/music/dadi.mp3|http://localhost/phpbook/ch17/music/dadi.mp3|http://localhost/phpbook/ch17/music/dadi.mp3|http://localhost/phpbook/ch17/music/dadi.mp3|http://localhost/phpbook/ch17/music/dadi.mp3|","1","0","4","1","1");
INSERT INTO song VALUES("2","大地","http://localhost/phpbook/ch17/music/dadi.mp3","http://localhost/phpbook/ch17/music/dadi.mp3|","1","0","3","1","1");
INSERT INTO song VALUES("3","01.点烟.mp3","http://localhost/phpbook/ch17/music/01.dy.mp3","http://localhost/phpbook/ch17/music/01.dy.mp3|","0","0","6","2","2");
INSERT INTO song VALUES("4","02.少年耶,安啦!","http://localhost/phpbook/ch17/music/02.sny.mp3","http://localhost/phpbook/ch17/music/02.sny.mp3|","0","0","5","2","2");


#
# Table structure for table 'tmpgeci'
#

CREATE TABLE `tmpgeci` (
  `id` int(11) NOT NULL auto_increment,
  `zuoqu` varchar(40) default NULL,
  `tianci` varchar(40) default NULL,
  `tigong` varchar(40) NOT NULL default '',
  `geci` text NOT NULL,
  `song_name` varchar(100) NOT NULL default '',
  `song_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) TYPE=MyISAM;



#
# Dumping data for table 'tmpgeci'
#


#
# Table structure for table 'user'
#

CREATE TABLE `user` (
  `user_id` bigint(100) NOT NULL auto_increment,
  `user_name` varchar(40) NOT NULL default '',
  `passwd` varchar(40) NOT NULL default '',
  `email` varchar(100) NOT NULL default '',
  `favorite_cd` tinytext,
  `favorite_song` tinytext,
  `sex` varchar(20) NOT NULL default '',
  `face` int(4) NOT NULL default '-1',
  `oicq` varchar(50) default NULL,
  `province` varchar(20) NOT NULL default '',
  `addr` varchar(255) default NULL,
  `yearold` int(5) default NULL,
  `homepage` varchar(255) default NULL,
  `plan` tinytext,
  `sign` varchar(255) default NULL,
  `firstlogin` int(10) NOT NULL default '0',
  `lastlogin` datetime NOT NULL default '0000-00-00 00:00:00',
  `numlogin` int(11) NOT NULL default '0',
  `numpost` int(11) NOT NULL default '0',
  `lastfrom` varchar(255) NOT NULL default '',
  `numlisten` int(11) NOT NULL default '0',
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  KEY `sex` (`sex`),
  KEY `province` (`province`),
  KEY `user_id` (`user_id`)
) TYPE=MyISAM;



#
# Dumping data for table 'user'
#
INSERT INTO user VALUES("1","admin","12345","wei@hotmail.com","|1|","","美眉","-1","12345","北京","","20","http://","","","1171776525","2007-02-18 14:13:11","2","0","127.0.0.1","20");
INSERT INTO user VALUES("2","wei","12345","dudu@sina.com.cn","","","解结","-1","12345","上海","","20","http://","","大家好哇！","1171778085","2007-02-18 13:54:55","1","1","127.0.0.1","4");
