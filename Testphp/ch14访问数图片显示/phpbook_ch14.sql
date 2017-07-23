
CREATE DATABASE `phpbook_ch14`;

USE `phpbook_ch14`;
CREATE TABLE `counter` (
  `id` int(10) NOT NULL auto_increment,
  `counter` int(10) default '0',
  PRIMARY KEY  (`id`)
) TYPE=MyISAM;

CREATE TABLE `user_ip` (
  `id` int(10) NOT NULL auto_increment,
  `user_ip` varchar(64) default '0',
  `visit_time` timestamp(14) NOT NULL,
  `user` varchar(32) default NULL,
  PRIMARY KEY  (`id`)
) TYPE=MyISAM;

