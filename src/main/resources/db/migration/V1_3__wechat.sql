-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.21 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.1.0.4894
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 bulleye_admin 的数据库结构
CREATE DATABASE IF NOT EXISTS `bulleye_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bulleye_admin`;


-- 导出  表 bulleye_admin.wx_account 结构
CREATE TABLE IF NOT EXISTS `wx_account` (
  `id` varchar(40) NOT NULL COMMENT '公众号id',
  `account_pic` varchar(500) DEFAULT NULL COMMENT '公众号二维码',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `number` varchar(50) DEFAULT NULL COMMENT '微信号',
  `appid` varchar(50) DEFAULT NULL COMMENT '微信appid',
  `appsecret` varchar(50) DEFAULT NULL COMMENT '微信appsecret',
  `token` varchar(50) DEFAULT NULL COMMENT '微信token',
  `domain` varchar(500) DEFAULT NULL COMMENT '域名',
  `access_token` varchar(50) DEFAULT NULL COMMENT 'access_token',
  `token_time` datetime DEFAULT NULL COMMENT '上次获取access_token的时间',
  `menu_state` varchar(50) DEFAULT NULL COMMENT 'menu_state',
  `adduserid` varchar(50) DEFAULT NULL COMMENT '添加人ID',
  `addusername` varchar(50) DEFAULT NULL COMMENT '添加人NAME',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `updateid` varchar(50) DEFAULT NULL COMMENT '修改人ID',
  `updatename` varchar(50) DEFAULT NULL COMMENT '修改人NAME',
  `updatetime` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `main_account` varchar(50) DEFAULT NULL COMMENT '主公众号',
  `wx_partner` varchar(255) DEFAULT NULL COMMENT '微信商户id',
  `wx_partnerkey` varchar(255) DEFAULT NULL COMMENT '微信商户支付秘钥',
  `wx_file` varchar(255) DEFAULT NULL COMMENT '证书文件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信公众号信息';

-- 正在导出表  bulleye_admin.wx_account 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `wx_account` DISABLE KEYS */;
INSERT INTO `wx_account` (`id`, `account_pic`, `name`, `number`, `appid`, `appsecret`, `token`, `domain`, `access_token`, `token_time`, `menu_state`, `adduserid`, `addusername`, `addtime`, `updateid`, `updatename`, `updatetime`, `remark`, `main_account`, `wx_partner`, `wx_partnerkey`, `wx_file`) VALUES
	('3', '', 'lhs', 'gh_1065c22dc61c', 'wx0a8f9a49c445fd2b', '5aaea0afe5b65d60892a475bf43d668b', 'myToken', 'orcalone.imwork.net', NULL, NULL, '512f55b5-c5be-4b46-b9c7-b68218a3a1cf', '8', 'admin', '2018-10-25 11:39:51', '8', 'admin', '2018-10-25 11:40:09', '', '1', '', '', '');
/*!40000 ALTER TABLE `wx_account` ENABLE KEYS */;


-- 导出  表 bulleye_admin.wx_member 结构
CREATE TABLE IF NOT EXISTS `wx_member` (
  `id` varchar(40) NOT NULL COMMENT '微信用户id',
  `account_id` varchar(50) DEFAULT NULL COMMENT '所属公众号id',
  `account_name` varchar(50) DEFAULT NULL COMMENT '所属公众号名称',
  `subscribe` varchar(50) DEFAULT NULL COMMENT '关注状态 1.关注中 2.未关注',
  `openid` varchar(50) NOT NULL COMMENT '用户openid',
  `nickname` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `sex` varchar(50) DEFAULT NULL COMMENT '性别',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `headimgurl` varchar(500) DEFAULT NULL COMMENT '头像',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `user_id` varchar(50) DEFAULT NULL COMMENT '关联用户账户id',
  `code` varchar(100) DEFAULT NULL COMMENT '永久二维码code',
  PRIMARY KEY (`id`),
  KEY `openid` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信用户';

-- 正在导出表  bulleye_admin.wx_member 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `wx_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `wx_member` ENABLE KEYS */;


-- 导出  表 bulleye_admin.wx_reply 结构
CREATE TABLE IF NOT EXISTS `wx_reply` (
  `id` varchar(40) NOT NULL COMMENT 'ID',
  `account_id` varchar(40) DEFAULT NULL COMMENT '所属公众号ID',
  `reply_keyword` varchar(100) DEFAULT NULL COMMENT '关键字',
  `reply_title` varchar(100) DEFAULT NULL COMMENT '标题',
  `reply_pic` varchar(150) DEFAULT NULL COMMENT '图片',
  `reply_url` varchar(100) DEFAULT NULL COMMENT '链接',
  `reply_content` varchar(250) DEFAULT NULL COMMENT '内容',
  `reply_type` varchar(50) DEFAULT NULL COMMENT '类型 1.图文 2.纯文字',
  `reply_num` bigint(20) DEFAULT NULL COMMENT '回复次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信自动回复';

-- 正在导出表  bulleye_admin.wx_reply 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `wx_reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `wx_reply` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
