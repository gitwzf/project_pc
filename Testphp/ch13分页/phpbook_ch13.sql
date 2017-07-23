
CREATE DATABASE `phpbook_ch13`;

USE `phpbook_ch13`;
CREATE TABLE `news` (
  `newsid` bigint(20) NOT NULL auto_increment,
  `title` varchar(100) default NULL,
  `content` text,
  `addtime` datetime default '0000-00-00 00:00:00',
  `userid` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`newsid`),
  UNIQUE KEY `newsid` (`newsid`)
) TYPE=MyISAM;

