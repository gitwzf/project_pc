
CREATE DATABASE `phpbook_ch16`;

USE `phpbook_ch16`;
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

