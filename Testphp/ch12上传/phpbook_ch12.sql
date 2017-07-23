
CREATE DATABASE `phpbook_ch12`;

USE `phpbook_ch12`;
CREATE TABLE `uploadfile` (
  `f_id` bigint(8) NOT NULL auto_increment,
  `f_name` varchar(50) NOT NULL default '',
  `f_save` varchar(200) NOT NULL default '',
  `f_url` varchar(200) NOT NULL default '',
  `f_date` date NOT NULL default '0000-00-00',
  `f_status` int(2) NOT NULL default '0',
  PRIMARY KEY  (`f_id`)
) TYPE=MyISAM;

