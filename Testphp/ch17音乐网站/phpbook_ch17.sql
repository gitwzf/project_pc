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
INSERT INTO area VALUES("1","��̨");
INSERT INTO area VALUES("2","��½");
INSERT INTO area VALUES("4","�պ�");
INSERT INTO area VALUES("5","����");
INSERT INTO area VALUES("6","�ŵ�����");


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
INSERT INTO cate VALUES("1","���Ը���");
INSERT INTO cate VALUES("2","Ů�Ը���");
INSERT INTO cate VALUES("3","�ֶ����");
INSERT INTO cate VALUES("4","��������");
INSERT INTO cate VALUES("5","Ӱ�ӽ���");


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
INSERT INTO cd VALUES("1","����һ","2","","���Բ�������ɳ�����������ɸ�","����","0000-00-00","2007-02-18 13:30:37","12","1");
INSERT INTO cd VALUES("2","���������","2","http://localhost/phpbook/ch17/music/wubai.jpg","���������","����","2004-05-27","2007-02-18 14:20:22","3","2");


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
INSERT INTO geci VALUES("2","","","admin","[ti:���]\r\n[ar:Beyond]\r\n[al:�������]\r\n[by:ëë��]\r\n[00:00]\r\n[00:01]���(��)\r\n[00:03]Beyond\r\n[00:05]\r\n[00:25]����Щ�Դ��·��\r\n[00:29]�����˶��ٴ���\r\n[00:33]�����Ų��ϵ�����\r\n[00:36]������˷�˪\r\n[00:40]�������Ķ���\r\n[00:43]���ഺ����ʱ\r\n[00:46]�Ȳ����ѵĻ��� û˵�ټ�\r\n[00:52]������������������ǰ\r\n[00:56]����ĸп�һ����\r\n[01:00]�������ճ���û��Ǩ\r\n[01:05]��������Ÿ���Ц��ʱ\r\n[01:10]����֪����������\r\n[01:13]������ĺɫ��������\r\n[01:32]����Щ���ŵ�·��\r\n[01:36]̤�����������\r\n[01:39]�����Ÿ߹ҵ�����\r\n[01:43]����֤�˼���\r\n[01:46]ǧ�ﲻ�������\r\n[01:49]����ϧ�ﹲ��\r\n[01:53]��Ϣ�ָ�Ĵ�� ���˽���\r\n[01:59]������������������ǰ\r\n[02:03]����ĸп�һ����\r\n[02:06]�������ճ���û��Ǩ\r\n[02:12]��������Ÿ���Ц��ʱ\r\n[02:16]����֪����������\r\n[02:19]������ĺɫ��������\r\n[02:41]������������������ǰ\r\n[02:45]����ĸп�һ����\r\n[02:48]�������ճ���û��Ǩ\r\n[02:54]��������Ÿ���Ц��ʱ\r\n[02:58]����֪����������\r\n[03:02]������ĺɫ��������\r\n[03:08]������������������ǰ\r\n[03:12]����ĸп�һ����\r\n[03:15]�������ճ���û��Ǩ\r\n[03:21]��������Ÿ���Ц��ʱ\r\n[03:25]����֪����������\r\n[03:28]������ĺɫ��������\r\n");


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
INSERT INTO online VALUES("1172332065","5702c291a601e4d0a0dbc46a051b74d1","�ÿ�");


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
  `ssay` varchar(100) NOT NULL default '���춼�к�����',
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
INSERT INTO post VALUES("1","˵˵������","wei","0","2007-02-18 13:55:34","0","��һ������ð�ݻ�֧�֣��ٺ�\n\n--\n��Һ��ۣ�\n--","1","127.0.0.1","0");


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
INSERT INTO singer VALUES("2","w","���","1","2","http://localhost/phpbook/ch17/music/wubai.jpg","���������","0","1","1");


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
INSERT INTO song VALUES("1","���","http://localhost/phpbook/ch17/music/dadi.mp3","http://localhost/phpbook/ch17/music/dadi.mp3|http://localhost/phpbook/ch17/music/dadi.mp3|http://localhost/phpbook/ch17/music/dadi.mp3|http://localhost/phpbook/ch17/music/dadi.mp3|http://localhost/phpbook/ch17/music/dadi.mp3|","1","0","4","1","1");
INSERT INTO song VALUES("2","���","http://localhost/phpbook/ch17/music/dadi.mp3","http://localhost/phpbook/ch17/music/dadi.mp3|","1","0","3","1","1");
INSERT INTO song VALUES("3","01.����.mp3","http://localhost/phpbook/ch17/music/01.dy.mp3","http://localhost/phpbook/ch17/music/01.dy.mp3|","0","0","6","2","2");
INSERT INTO song VALUES("4","02.����Ү,����!","http://localhost/phpbook/ch17/music/02.sny.mp3","http://localhost/phpbook/ch17/music/02.sny.mp3|","0","0","5","2","2");


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
INSERT INTO user VALUES("1","admin","12345","wei@hotmail.com","|1|","","��ü","-1","12345","����","","20","http://","","","1171776525","2007-02-18 14:13:11","2","0","127.0.0.1","20");
INSERT INTO user VALUES("2","wei","12345","dudu@sina.com.cn","","","���","-1","12345","�Ϻ�","","20","http://","","��Һ��ۣ�","1171778085","2007-02-18 13:54:55","1","1","127.0.0.1","4");
