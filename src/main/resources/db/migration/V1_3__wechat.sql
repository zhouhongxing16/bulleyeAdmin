-- --------------------------------------------------------
-- 主机:                           localhost
-- 服务器版本:                        5.7.9-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 bulleye_admin 的数据库结构
CREATE DATABASE IF NOT EXISTS `bulleye_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bulleye_admin`;

-- 导出  表 bulleye_admin.wx_account 结构
CREATE TABLE IF NOT EXISTS `wx_account` (
  `id` varchar(40) NOT NULL COMMENT '公众号id',
  `qr_code` varchar(500) DEFAULT NULL COMMENT '公众号二维码',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `source_id` varchar(50) DEFAULT NULL COMMENT '微信号',
  `app_id` varchar(50) DEFAULT NULL COMMENT '微信appid',
  `aes_key` varchar(50) DEFAULT NULL,
  `app_secret` varchar(50) DEFAULT NULL COMMENT '微信appsecret',
  `token` varchar(50) DEFAULT NULL COMMENT '微信token',
  `domain` varchar(500) DEFAULT NULL COMMENT '域名',
  `access_token` varchar(50) DEFAULT NULL COMMENT 'access_token',
  `token_time` bigint(20) DEFAULT NULL COMMENT '上次获取access_token的时间',
  `menu_state` varchar(50) DEFAULT NULL COMMENT 'menu_state',
  `user_id` varchar(50) DEFAULT NULL COMMENT '添加人ID',
  `created` bigint(20) DEFAULT NULL COMMENT '添加时间',
  `updated` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `partner` varchar(255) DEFAULT NULL COMMENT '微信商户id',
  `partner_key` varchar(255) DEFAULT NULL COMMENT '微信商户支付秘钥',
  `certificate_path` varchar(255) DEFAULT NULL COMMENT '证书文件',
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信公众号信息';

-- 正在导出表  bulleye_admin.wx_account 的数据：~0 rows (大约)
DELETE FROM `wx_account`;
/*!40000 ALTER TABLE `wx_account` DISABLE KEYS */;
INSERT INTO `wx_account` (`id`, `qr_code`, `name`, `source_id`, `app_id`, `aes_key`, `app_secret`, `token`, `domain`, `access_token`, `token_time`, `menu_state`, `user_id`, `created`, `updated`, `remark`, `partner`, `partner_key`, `certificate_path`, `status`) VALUES
	('dfdfgsdfgsdfgsdfgsdfgsdfgsdfgsdfg', '3', '2', 'gh_89ac68370508', 'wx428db2722153d12d', '2', 'e1e8aed568ff374cb368428529c78d13', 'chris520', '2', '2', '2019-02-15 23:34:49', '2', '2', 2, 2, '2', '2', '2', '2', 2);
INSERT INTO `wx_account` (`id`, `qr_code`, `name`, `source_id`, `app_id`, `aes_key`, `app_secret`, `token`, `domain`, `access_token`, `token_time`, `menu_state`, `user_id`, `created`, `updated`, `remark`, `partner`, `partner_key`, `certificate_path`, `status`) VALUES
  ('gh_1065c22dc61c', NULL, NULL, NULL, 'wx0a8f9a49c445fd2b', NULL, '5aaea0afe5b65d60892a475bf43d668b', 'myToken', '\r\norcalone.imwork.net', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

/*!40000 ALTER TABLE `wx_account` ENABLE KEYS */;

-- 导出  表 bulleye_admin.wx_member 结构
CREATE TABLE IF NOT EXISTS `wx_member` (
  `id` varchar(40) NOT NULL COMMENT '微信用户id',
  `account_id` varchar(50) DEFAULT NULL COMMENT '所属公众号id',
  `subscribe` bit(1) DEFAULT NULL COMMENT '关注状态 1.关注中 2.未关注',
  `open_id` varchar(50) NOT NULL COMMENT '用户openid',
  `nickname` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `sex` varchar(50) DEFAULT NULL COMMENT '性别',
  `sex_desc` varchar(50) DEFAULT NULL,
  `language` varchar(200) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `qr_code` varchar(200) DEFAULT NULL COMMENT '永久二维码code',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `head_img_url` varchar(500) DEFAULT NULL COMMENT '头像',
  `subscribe_time` bigint(20) DEFAULT NULL,
  `union_id` varchar(50) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `group_id` varchar(200) DEFAULT NULL COMMENT '分组',
  `privileges` varchar(200) DEFAULT NULL,
  `tag_ids` varchar(50) DEFAULT NULL,
  `subscribe_scene` varchar(200) DEFAULT NULL,
  `qr_scene` varchar(200) DEFAULT NULL,
  `qr_scene_str` varchar(200) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL COMMENT '关联用户账户id',
  `unsubscribe_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `openid` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信用户';

-- 正在导出表  bulleye_admin.wx_member 的数据：~0 rows (大约)
DELETE FROM `wx_member`;
/*!40000 ALTER TABLE `wx_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `wx_member` ENABLE KEYS */;

-- 导出  表 bulleye_admin.wx_reply 结构
CREATE TABLE IF NOT EXISTS `wx_reply` (
	`id` VARCHAR(50) NOT NULL COMMENT 'ID',
	`account_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '所属公众号ID',
	`key_word` VARCHAR(100) NULL DEFAULT NULL COMMENT '关键字',
	`key_value` LONGTEXT NULL COMMENT '值',
	`key_type` VARCHAR(50) NULL DEFAULT NULL COMMENT '类型 1.图文 2.纯文字',
	`graphic_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '图文标识',
	`num` INT(10) NULL DEFAULT NULL COMMENT '回复次数',
	`status` INT(11) NULL DEFAULT NULL,
	`created` BIGINT(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='微信自动回复'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;



-- 正在导出表  bulleye_admin.wx_reply 的数据：~0 rows (大约)
DELETE FROM `wx_reply`;
CREATE TABLE IF NOT EXISTS `wx_graphic` (
	`id` VARCHAR(50) NOT NULL,
	`title` VARCHAR(100) NULL DEFAULT NULL,
	`brief` VARCHAR(200) NULL DEFAULT NULL,
	`content` LONGTEXT NULL,
	`url` VARCHAR(200) NULL DEFAULT NULL,
	`imgurl` VARCHAR(200) NULL DEFAULT NULL,
	`read_num` INT(11) NULL DEFAULT '0',
	`created` BIGINT(20) NOT NULL,
	`status` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='图文消息'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

DELETE FROM `wx_graphic`;


CREATE TABLE IF NOT EXISTS `wx_menu` (
  `id` varchar(50) NOT NULL,
  `account_id` varchar(50) DEFAULT NULL COMMENT '公众号id',
  `type` varchar(20) DEFAULT NULL COMMENT '菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型',
  `name` varchar(100) DEFAULT NULL COMMENT '菜单标题',
  `key` varchar(100) DEFAULT NULL COMMENT '菜单KEY值，用于消息接口推送',
  `url` varchar(1000) DEFAULT NULL COMMENT '网页 链接',
  `media_id` varchar(100) DEFAULT NULL COMMENT '永久素材media_id',
  `app_id` varchar(50) DEFAULT NULL COMMENT '小程序的app_id',
  `pagepath` varchar(500) DEFAULT NULL COMMENT '小程序的页面路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信菜单表';
CREATE TABLE `wx_menu` (
	`id` VARCHAR(50) NOT NULL,
	`account_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '公众号id',
	`type` VARCHAR(20) NULL DEFAULT NULL COMMENT '菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型',
	`name` VARCHAR(100) NULL DEFAULT NULL COMMENT '菜单标题',
	`key` VARCHAR(100) NULL DEFAULT NULL COMMENT '菜单KEY值，用于消息接口推送',
	`url` VARCHAR(1000) NULL DEFAULT NULL COMMENT '网页 链接',
	`media_id` VARCHAR(100) NULL DEFAULT NULL COMMENT '永久素材media_id',
	`appid` VARCHAR(50) NULL DEFAULT NULL COMMENT '小程序的appid',
	`pagepath` VARCHAR(500) NULL DEFAULT NULL COMMENT '小程序的页面路径',
	`parent_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '父级id',
	`author` VARCHAR(50) NULL DEFAULT NULL COMMENT '是否获取用户信息 1.是 2.否',
	`sort` INT(10) NULL DEFAULT NULL COMMENT '排序',
	`remark` VARCHAR(100) NULL DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (`id`)
)
COMMENT='微信菜单表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
DELETE FROM `wx_menu`;

