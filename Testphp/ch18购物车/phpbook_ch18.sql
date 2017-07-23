
CREATE DATABASE `phpbook_ch18`;

USE `phpbook_ch18`;
CREATE TABLE `categories` (
  `categoryId` int(10) NOT NULL auto_increment,
  `categoryName` varchar(64) default NULL,
  `createTime` datetime default '0000-00-00 00:00:00',
  PRIMARY KEY  (`categoryId`)
) TYPE=MyISAM;

CREATE TABLE `product` (
  `productid` int(10) unsigned NOT NULL auto_increment,
  `productname` varchar(64) default '0',
  `category` varchar(32) default '0',
  `createtime` datetime default '0000-00-00 00:00:00',
  `price` double default '0',
  `color` varchar(20) default '0',
  `size` varchar(20) default '0',
  `count` int(10) unsigned default '0',
  `abstract` text,
  `detail` text,
  PRIMARY KEY  (`productid`)
) TYPE=MyISAM;

CREATE TABLE `user` (
  `userid` int(10) NOT NULL auto_increment,
  `username` varchar(20) default '0',
  `userpass` varchar(64) default '0',
  `userpassq` varchar(64) default NULL,
  `userpassa` varchar(64) default NULL,
  `email` varchar(40) NOT NULL default '0',
  `usertel` varchar(16) default '0',
  `useraddr` varchar(255) default '0',
  `createtime` datetime default '0000-00-00 00:00:00',
  `logintime` datetime default '0000-00-00 00:00:00',
  `currentlogintime` datetime default '0000-00-00 00:00:00',
  `logincount` int(10) NOT NULL default '0',
  `usercredit` int(10) NOT NULL default '0',
  `liking1` varchar(30) default NULL,
  `liking2` varchar(30) default NULL,
  `liking3` varchar(30) default NULL,
  PRIMARY KEY  (`userid`)
) TYPE=MyISAM;

CREATE TABLE `userorder` (
  `orderid` int(20) NOT NULL default '0',
  `productid` int(10) NOT NULL default '0',
  `productname` varchar(64) default '0',
  `price` double default '0',
  `quantity` int(10) NOT NULL default '0',
  `category` varchar(32) default '0',
  `userid` int(10) NOT NULL default '0',
  `sum` double default '0',
  `address` varchar(255) default '0',
  `tel` varchar(16) default '0',
  `ordertime` datetime default '0000-00-00 00:00:00',
  `issend` tinyint(3) NOT NULL default '0'
) TYPE=MyISAM;

