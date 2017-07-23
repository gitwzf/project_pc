
CREATE DATABASE `phpbook_ch11`;

USE `phpbook_ch11`;
CREATE TABLE `users` (
  `uid` bigint(20) NOT NULL auto_increment,
  `username` varchar(100) default NULL,
  `userpassword` varchar(100) default NULL,
  `nickname` varchar(100) default NULL,
  `sex` tinyint(1) default '0',
  `email` varchar(100) default NULL,
  `regtime` timestamp(14) NOT NULL,
  PRIMARY KEY  (`uid`),
  UNIQUE KEY `uid` (`uid`)
) TYPE=MyISAM;

